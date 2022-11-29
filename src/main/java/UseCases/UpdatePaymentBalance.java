package UseCases;

import DataStructures.PaymentInformation;
import DataStructures.UpdatedDebts;
import Entities.*;
import InputBoundary.UpdatePaymentBalanceBoundaryIn;
import OutputBoundary.UpdatePaymentBalanceBoundaryOut;
import DataAccessInterface.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.*;

public class UpdatePaymentBalance implements UpdatePaymentBalanceBoundaryIn {

    final GroupDataInterface groupDataInterface;
    final ItemDataInterface itemDataInterface;
    final UpdatePaymentBalanceBoundaryOut updatePaymentBalancePresenter;
    final PaymentInformation paymentDetails;

    public UpdatePaymentBalance(GroupDataInterface gdi, ItemDataInterface idi, UpdatePaymentBalanceBoundaryOut upbp,
                                PaymentInformation pd) {
        this.groupDataInterface = gdi;
        this.itemDataInterface = idi;
        this.updatePaymentBalancePresenter = upbp;
        this.paymentDetails = pd;
    }

    /**
     * Updates the list of debts for a group after the purchase of an item has been made.
     * @param paymentDetails the data structure containing all the information required to update the debts in the
     *                       group.
     * @return the information that is prepared by the presenter to the controller.
     */
    @Override
    public UpdatedDebts updatePaymentBalance(PaymentInformation paymentDetails) {
        String groupID = paymentDetails.getGroupID();
        String userPurchasedItem = paymentDetails.getUsername();
        float itemPrice = paymentDetails.getItemPrice();
        String itemID = paymentDetails.getItemID();
        List<String> usersInvolvedInPurcase = paymentDetails.getUsersInvolvedInPurchase();

        /*
        There are going to be three steps, I can only implement step 2 right now, and then I can
        implement steps 1 and 3 after the database has been made.

        - (1) Use the itemID to get all the information that is required to construct our purchasedItem
        item object.
        - (2) Use this item object to obtain all the users that were involved in the purchase.
        - (1) Use the groupID to get all the information which is used to construct the group of people who are involved
        in the purchase.
        - (2) Use the group object to get the purchaseBalance.
        - (2) Use the itemPrice and the userPurchasedItem to update the debts between the
        userPurchasedItem and all users involved in the purchase.
        - (3) Deconstruct the data in the group object we previously created, and override existing
        data with this new data in the database.
        - (2) Obtain necessary information from the corresponding entities to construct an UpdatedDebts
        object.
        - (2) Return this.updatePaymentBalancePresenter.prepareUpdatedDebtList(with the UpdatedDebts object
        we created in the last step).

         */

        /*

         We first use the itemID to get this item from the Database. We can also assume that class Item contains a
         list of Users involved in the purchase of said item.
         */
        Item purchasedItem;
        try {
            purchasedItem = getItemFromDb(itemID);
        }
        catch (RuntimeException e) {
            return raiseError(e);
        }

        /*
         We now use the groupID to get the group of people in which the purchase has been made, and then we call
         the getUsers() method to get the Set of Users in the group (not all of them are necessarily involved in
         the purchase).
         */
        Group groupInvolvedInPurchase;
        try {
            groupInvolvedInPurchase = getGroupFromDb(groupID);
        }
        catch (RuntimeException e) {
            return raiseError(e);
        }
        Set<String> usersInGroup = groupInvolvedInPurchase.getUsers();

        int amountOfUsersInvolvedInPurchase = usersInvolvedInPurcase.size();
        List<String> groupUsernames = new ArrayList<>();
        for(String user : usersInGroup) {
            groupUsernames.add(user);
        }

        /*
        We can now call getPurchaseBalance() and getAllDebts() on groupInvolvedInPurchase to get the list of all
        debts in the group.
         */
        List<Debt> currentDebtList = groupInvolvedInPurchase.getPurchaseBalance().getAllDebts();

        /*
        Each of the debts in currentDebtList contains the user who is owed the money, and the user who needs to
        pay that user, so we work our way through the list and all the usernames in usersInvolvedInPurchase, and
        if the user owed is equal to the user who made the purchase, and the user owing is equal to username, then we
        can update the debt between the two users using setDebtValue().
         */
        for(Debt d : currentDebtList) {
            for(String username : usersInvolvedInPurcase) {
                if(d.getUserOwing().getUsername().equals(username) &&
                        d.getUserOwed().getUsername().equals(userPurchasedItem)) {
                    double currentDebt = d.getDebtValue();
                    double updatedDebt = currentDebt + itemPrice/amountOfUsersInvolvedInPurchase;
                    d.setDebtValue(updatedDebt);
                }
            }
        }

        /*
        Now we will need to save the data into the database. We need to save the user and group into the database.
         */
        saveData(groupInvolvedInPurchase);

        /*
        We now need to construct a Map<String, List<Object>>, where the String is the name of a user in the group who is
        owed money, and at index[0], the List contains the name of the user who owes money as a String, and at index[1],
        the List contains the amount the user owes as a double.
         */
        Map<String, List<Object>> updatedDebtsList = new HashMap<>();
        for(Debt d : currentDebtList) {
            List<Object> userOwingAndDebtValue = new ArrayList<>();
            userOwingAndDebtValue.add(d.getUserOwing().getUsername());
            userOwingAndDebtValue.add(d.getDebtValue());
            updatedDebtsList.put(d.getUserOwed().getUsername(), userOwingAndDebtValue);
        }

        /*
        Now we can take the Map use it in the constructor for UpdatedDebts to create our final returned value.
         */
        return new UpdatedDebts(updatedDebtsList);
    }

    /**
     * Helper method for updatePaymentBalance which returns a failed instance of UpdatedDebts.
     * @param e the runtime error given by the try catch in updatePaymentBalance when checking if itemID or groupID
     *          exist.
     * @return the failed view of the updatePaymentBalance presenter.
     */
    private UpdatedDebts raiseError(RuntimeException e) {
        return this.updatePaymentBalancePresenter.prepareFailView(new UpdatedDebts(e.toString()));
    }

    /**
     * Helper method for updatePaymentBalance to check if the itemID exists in the given database.
     * @param itemID the String which is the ID of the item which was purchased.
     * @return an instance of the actual Item which has been purchased if it exists in the database, otherwise,
     * returns a RuntimeException.
     */
    private Item getItemFromDb(String itemID){
        // get the user from the database and create a User interface
        //check if the user exists
        if (!this.itemDataInterface.itemIdExists(itemID)){
            throw new RuntimeException("Item Id does not exist");
        }
        String userString = this.itemDataInterface.itemAsString(itemID);

        try {
            return Item.fromString(itemID);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process item from database");
        }
    }

    /**
     * Helper method for updatePaymentBalance to check if the groupID exists in the given database.
     * @param groupID the String which is the ID of the group in which the item was purchased.
     * @return an instance of the actual group in which the item has been purchased if it exists in the database,
     * otherwise, returns a RuntimeException.
     */
    private Group getGroupFromDb(String groupID){
        // get the user from the database and create a User interface
        //check if the user exists
        if (!this.groupDataInterface.groupIdExists(groupID)){
            throw new RuntimeException("Group Id does not exist");
        }
        String userString = this.groupDataInterface.groupAsString(groupID);

        try {
            return Group.fromString(groupID);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process group from database");
        }
    }

    /**
     * A helper method for updatePaymentBalance which just saves the updated data to the database.
     * @param group the Group in which the item has been purchased and in which the debts have been updated.
     */
    private void saveData(Group group){
        //pass new info to db
        try {
            this.groupDataInterface.addorUpdateGroup(group.getGroupId(), group.toString());
        } catch (IOException e) {
            throw new RuntimeException("Unable to modify group info to database");
        }
    }
}