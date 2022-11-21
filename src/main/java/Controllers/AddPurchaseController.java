package Controllers;

import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.PurchaseInfo;
import DataStructures.UpdatedLists;
import InputBoundary.AddPurchaseBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import Presenters.AddPurchasePresenter;

import java.util.List;

public class AddPurchaseController {

    /**
     * This function will be called from the main/view in order to control the use case AddPurchase which occurs whenever a user
     * from a group purchases an item from the planning list. This controller function packages all the required information that
     * the view sends forward into the PurchaseInfo data structure, instantiates the presenter and passes all of this information
     * to call the use case through the input boundary.
     * @param addPurchaseUseCase object of the use case
     * @param purchasedItemId item id of the item being purchased
     * @param participatingUsernames usernames of the users involved in the purchase
     * @param buyerUsername username of the user buying the item
     * @param price price of the item
     * @param purchaseGroupId id of the group the purchase is made in
     * @param groupDataAccess interface for group data access
     * @param itemDataAccess interface for item data access
     * @param userDataAccess interface for user data access
     * @return the information sent back by the presenter through the use case to go to the view layer as an updated
     * lists object
     */
    public UpdatedLists controlAddPurchaseUseCase(AddPurchaseBoundaryIn addPurchaseUseCase, String purchasedItemId, List<String> participatingUsernames, String buyerUsername, float price, String purchaseGroupId, GroupDataInterface groupDataAccess, ItemDataInterface itemDataAccess, UserDataInterface userDataAccess) {
        //Implement the instantiation of the presenter to pass along the pipeline and assign the boundary attribute variable to the use case object
        // Defining the class attribute variable which will store the use case object as one of the boundary interface
        AddPurchaseBoundaryOut presenter = new AddPurchasePresenter();

        //Package the information into the data structure to pass to the use case through the boundary
        PurchaseInfo purchaseInfo = new PurchaseInfo(purchasedItemId, participatingUsernames, buyerUsername, price, purchaseGroupId, presenter, groupDataAccess, itemDataAccess, userDataAccess);

        // Call the use case
        return addPurchaseUseCase.executeUseCase(purchaseInfo);
    }
}
