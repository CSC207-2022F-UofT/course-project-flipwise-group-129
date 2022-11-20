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
     * Creates a new AddtoPlanningList use case instance to implement the use case
     * @param outputBoundary the output boundary to communicate updated display information to the view
     * @param groupAccess the data access interface to retrieve and update group relevant data
     */
    public SettlementPayment(
            SettlementBoundaryOut outputBoundary, GroupDataInterface groupAccess){
        this.outputBoundary = outputBoundary;
        this.groupAccess = groupAccess;
    }

    /**
     * This function will be called from the controller to execute the use case AddtoPlanningList
     * This function retrieves the current group object and creates a new item instance and adds
     * to the group's planning list. It then saves the item and group back into the database
     * @param settlement a package of all relevant information to create the item and access database
     * @return This returns an instance of UpdatedLists data structure that contains lists of planning list and
     * purchased list information, or if there is an error in the use case, a string that describes the error
     */
    @Override
    public UpdatedDebts executeDebtSettlement(PaymentDetails settlement) {
        String groupId = settlement.getGroupId();
        // Get the Group and Item entities to manipulate
        Group currGroup = null;
        try {
            currGroup = retreiveGroup(groupId);
        } catch (RuntimeException e) {
            return outputBoundary.failErrorMessage(new UpdatedDebts("Group could not be found"));
        }
        // Add the new item to the current group's planning list and save it back into the database.
        Debt currentDebt = currGroup.getPurchaseBalance().getDebtPair(settlement.getOwed(), settlement.getOwing());
        currentDebt.setDebtValue(0.0);
        try {
            saveGroup(settlement.getGroupId(), currGroup.toString());
        } catch (RuntimeException e) {
            return outputBoundary.failErrorMessage(new UpdatedDebts("Debt could not be saved"));
        }
        List<List<String>> stringDebtList = getUpdatedDebts(currGroup.getPurchaseBalance());
        UpdatedDebts updatedDebts = new UpdatedDebts(stringDebtList);
        return outputBoundary.displayDebts(updatedDebts);
    }

    /**
     * This function converts the current group's planning list object into a list that is readable in the view
     * @param purchaseBalance the current group's planningList object
     * @return This returns a list of itemName and itemId from the items in the current group's planning list
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
     * @return This returns a success or failure of this attempt
     */
    private void saveGroup(String groupId, String groupData) {
        try {
            groupAccess.addorUpdateGroup(groupId, groupData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
