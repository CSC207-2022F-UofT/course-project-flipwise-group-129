package UseCases;
import DataAccessInterface.*;
import DataStructures.PaymentDetails;
import Entities.*;
import InputBoundary.SettlementBoundaryIn;
import DataStructures.UpdatedDebts;
import OutputBoundary.SettlementBoundaryOut;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SettlementPayment implements SettlementBoundaryIn{
    SettlementBoundaryOut outputBoundary;
    GroupDataInterface groupAccess;

    /**
     * Creates a new SettlementPaymenbt use case instance to implement the use case
     * Settles debt between two users
     * @param outputBoundary the output boundary to communicate updated display information to the view
     * @param groupAccess the data access interface to retrieve and update group relevant data
     */
    public SettlementPayment(
            SettlementBoundaryOut outputBoundary, GroupDataInterface groupAccess){
        this.outputBoundary = outputBoundary;
        this.groupAccess = groupAccess;
    }

    /**
     * This function will be called from the controller to execute the use case SettlementPaymentList
     * This function retrieves the details of the payment to be executed
     * it obtains the two users to be settled, and sets their debt pair to 0.0
     * @param settlement a package of all relevant information to create the item and access database
     * @return This returns an instance of UpdatedDebts data structure that contains all the new balances in the group
     * or a string wrapped in UpdatedDebts in case of an error.
     */
    @Override
    public UpdatedDebts executeDebtSettlement(PaymentDetails settlement) {
        String groupId = settlement.getGroupId();
        // Get the Group entity to manipulate
        Group currGroup = null;
        try {
            currGroup = retreiveGroup(groupId);
        } catch (RuntimeException e) {
            return raiseError(e.toString());
        }
        // get the corresponding debt pair and set the value to 0.0.
        Debt currentDebt = currGroup.getPurchaseBalance().getDebtPair(settlement.getOwed(), settlement.getOwing());
        currentDebt.setDebtValue(0.0);
        try {
            saveGroup(settlement.getGroupId(), currGroup.toString());
        } catch (RuntimeException e) {
            return raiseError(e.toString());
        }

        List<List<String>> stringDebtList = getUpdatedDebts(currGroup.getPurchaseBalance());
        UpdatedDebts updatedDebts = new UpdatedDebts(stringDebtList);
        return outputBoundary.displayDebts(updatedDebts);
    }

    private UpdatedDebts raiseError(String e) {
        return outputBoundary.failErrorMessage(new UpdatedDebts(e));
    }

    /**
     * This function gets a list of all the new debts in the group
     * @param purchaseBalance the list of Debts in the group
     * @return This returns a list of debts formatted as a list of list of string [userOwedUsername, userOwingUsername, debtValue]
     */
    private List<List<String>> getUpdatedDebts(PurchaseBalance purchaseBalance){
        List<List<String>> stringPurchaseBalance = new ArrayList<>();
        for(Debt curDebt: purchaseBalance.getAllDebts()){
            List<String> currentDebt = new ArrayList<>();
            currentDebt.add(curDebt.getUserOwed().getUsername());
            currentDebt.add(curDebt.getUserOwing().getUsername());
            currentDebt.add(curDebt.getDebtValue().toString());
            stringPurchaseBalance.add(currentDebt);
        }
        return stringPurchaseBalance;
    }

    /**
     * This function returns a group object from the matching groupId in the database
     * @param groupId the current group's groupId
     * @return This returns a group object if found in the database, otherwise, null
     */
    private Group retreiveGroup(String groupId) {
        String groupInfo = groupAccess.groupAsString(groupId);
        try {
            return Group.fromString(groupInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
        /**
     * This function saves the updated group information back into the database
     * @param groupId the current groupId
     * @param groupData the current group's information
     */
    private void saveGroup(String groupId, String groupData) {
        try {
            groupAccess.addorUpdateGroup(groupId, groupData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
