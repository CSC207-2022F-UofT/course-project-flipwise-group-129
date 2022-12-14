package UseCases;

import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.PurchaseInfo;
import DataStructures.UpdatedLists;
import Entities.*;
import InputBoundary.AddPurchaseBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddPurchase implements AddPurchaseBoundaryIn {
    private PurchaseInfo purchaseInfo;
    private Item purchasedItem;
    private List<User> participatingUsers;
    private float price;
    private Group purchaseGroup;
    private User buyer;
    private AddPurchaseBoundaryOut presenter;
    private GroupDataInterface groupData;
    private ItemDataInterface itemData;
    private UserDataInterface userData;
    private UpdatedLists newLists;

    /**
     * Removes the item being purchased from the planning list and assigns the buyer, price, and users involved
     * in the purchase
     * to the item entity object then adds it to the purchased list of the group, saves the new group information
     * to the database,
     * then returns the new lists to be displayed to the view
     * @param purchaseInfo the data structure containing all the information required to make a purchase and
     *                     update the necessary data
     * @return the information that is prepared by the presenter to the controller
     */
    @Override
    public UpdatedLists executeUseCase(PurchaseInfo purchaseInfo) {
        this.purchaseInfo = purchaseInfo;
        // Calling the helper method which instantiates values for the attribute variables which is extracted
        // from the purchaseInfo data structure
        extractInformation(purchaseInfo);

        System.out.println(this.purchasedItem);

        // Obtain the planning list from the group and then remove the item that's being purchased from it to
        // move it to the purchased list
        PlanningList planningList = this.purchaseGroup.getPlanningList();
        boolean removeCheck = planningList.removeFromList(this.purchasedItem);
        System.out.println(planningList);
        System.out.println(removeCheck);
        if (!removeCheck) {
            raiseError("Item could not be removed from planning list, either the information " +
                    "was incorrect or the item did not exist in the list");
        }

        // Obtain the purchase list from the group and then update it using the addToPurchase helper function,
        // which adds the new item to the groups purchase list
        PurchaseList purchaseList = this.purchaseGroup.getPurchaseList();
        addToPurchase(purchaseList);

        // Instantiate new 2 dimensional lists to store the information on the new purchase and planning list
        // which will be returned to update the view through the output boundary and presenter
        // These functions call the convertList helper method as they share a lot of functionality
        List<List<String>> planningListItemIds = convertList(planningList);
        List<List<String>> purchasedListItemIds = convertList(purchaseList);
        newLists = new UpdatedLists(planningListItemIds, purchasedListItemIds);

        // Call a helper function to write the updated data in to the database using the interfaces
        writeData();

        raiseError("Success");

        // Call the presenter through the output boundary with the updated lists data structure
        return this.presentInformation();
    }

    /**
     * Call the presenter to return information to the view
     * @return the correct view based on if the use case failed or succeeded
     */
    private UpdatedLists presentInformation() {
        if (Objects.equals(this.newLists.getResultMessage(), "Success")) {
            return this.presenter.prepareSuccessViewInformation(this.newLists);
        }
        else {
            return this.presenter.prepareFailViewInformation(this.newLists);
        }
    }

    /**
     * Write the new group and item data to the database
     */
    private void writeData() {
        // Need to use a try catch in case of a IO exception
        try {
            // since the group and item data is updated, the boundaries are used to update the json files storing
            // this information
            this.groupData.addorUpdateGroup(this.purchaseInfo.getPurchaseGroup(), this.purchaseGroup.toString());
            this.itemData.addorUpdateItem(this.purchaseInfo.getItemId(), this.purchasedItem.toString());
        } catch (IOException e) {
            // in the case of the io exception, we return a runtime exception
            raiseError("IO Exception");
        } catch (ParseException e) {
            raiseError("Parse Exception");
        }
    }

    /**
     * Update the information of the item with the purchase and add it to the purchase list of the group
     * @param purchaseList the object of the purchase list of the group to add the item to
     */
    private void addToPurchase(PurchaseList purchaseList) {
        // Set the new attributes for the item which only have values when the purchase is made,
        // price, buyer, involved users
        this.purchasedItem.setPrice(this.price);
        this.purchasedItem.setBuyer(this.buyer.getUsername());
        List<String> participatingUsernames = extractUsernames(this.participatingUsers);
        this.purchasedItem.setUsersInvolved(participatingUsernames);
        // update the purchased list of the group with the new item with updated information
        purchaseList.addItems(this.purchasedItem);
    }

    /**
     * Converts a list of users into a list of strings of their usernames
     * @param users the list of users
     * @return the list of usernames of the users in the list participatingUsers
     */
    private List<String> extractUsernames(List<User> users) {
        List<String> tempList = new ArrayList<>();
        for (User user : users) {
            tempList.add(user.getUsername());
        }
        return tempList;
    }

    /**
     * Extract the information from the purchase info data structure into the appropriate variables
     * @param purchaseInfo the data structure package containing the required information
     */
    private void extractInformation(PurchaseInfo purchaseInfo) {
        //grab and instantiate the data access boundaries because they are needed for the rest of the extraction process
        this.groupData = purchaseInfo.getGroupData();
        this.itemData = purchaseInfo.getItemData();
        this.userData = purchaseInfo.getUserData();

        this.participatingUsers = new ArrayList<>();

        // remember need try catches for all the IO or json exceptions
        //use data boundaries to parse the information and grab the class attribute variables as objects of the entities

        try {
            this.purchasedItem = Item.fromString(this.itemData.itemAsString(this.purchaseInfo.getItemId()));
        } catch (IOException | ParseException e) {
            raiseError("JSON Processing Exception");
        }

        // need another helper function to extract the users lol, helper for a helper
        extractUsers(purchaseInfo);
        this.price = purchaseInfo.getPrice();
        try {
            this.purchaseGroup = Group.fromString(this.groupData.groupAsString(purchaseInfo.getPurchaseGroup()));
        } catch (IOException | ParseException e) {
            raiseError("JSON Processing Exception");
        }
        try {
            this.buyer = User.fromString(this.userData.userAsString(purchaseInfo.getBuyer()));
        } catch (IOException | ParseException e) {
            raiseError("JSON Processing Exception");
        }

        // grab the presenter from the input data structure
        this.presenter = purchaseInfo.getPresenter();

    }

    /**
     * Returns an error message to the presenter to inform the user through the view that the use case failed
     * @param errorMessage the message to pass back to the user through the view
     */
    private void raiseError(String errorMessage) {
        if (!Objects.equals(errorMessage, "Success")) {
            this.newLists = new UpdatedLists(errorMessage);
            this.presenter.prepareFailViewInformation(newLists);
        }
    }

    /**
     * Helper function for extractInformation to grab the users from the database using the usernames
     * @param purchaseInfo the data structure package containing the required information
     */
    private void extractUsers(PurchaseInfo purchaseInfo) {
        // this function needs to iterate through the usernames passed in from the controller and boundary
        // then use the data access boundary and User static functions to get a list of user entity objects from that
        List<String> usernames = purchaseInfo.getUsers();
        for (String username : usernames) {
            // need a try catch for the json processing exception
            try {
                this.participatingUsers.add(User.fromString(this.userData.userAsString(username)));
            } catch (IOException | ParseException e) {
                raiseError(e.toString());
            }
            // yay O(n) time
        }
    }

    /**
     * Abstract method used to convert the planning and purchased lists from the group into the format
     * of the lists that are returned to the view through the updatedLists data structure
     * @param inputList the list to be converted into the format for returning
     * @return the inputList in the format that needs to be returned, which is a 2 dimensional list
     */
    public List<List<String>> convertList(ItemList inputList) {
        // this class is abstracted to convert the new planning and purchase lists from the group
        // into the format of 2 dimensional lists with the required data to show the modification on the view
        List<List<String>> tempListItemStrings = new ArrayList<>();
        for (Item item : inputList) {
            ArrayList<String> tempList = new ArrayList<>();
            tempList.add(item.getItemId());
            tempList.add(item.getItemName());
            // since only purchased items have a price and buyer, need to check the
            // instance of which list is being updated
            if (inputList instanceof PurchaseList) {
                tempList.add(String.valueOf(item.getPrice()));
                tempList.add(item.getBuyer());
            }
            // add the list to the outer list, yay O(n) time
            tempListItemStrings.add(tempList);
        }
        return tempListItemStrings;
    }
}
