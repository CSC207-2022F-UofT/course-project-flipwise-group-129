package Controllers;

import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.PurchaseInfo;
import InputBoundary.AddPurchaseBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import Presenters.AddPurchasePresenter;

import java.util.List;

public class AddPurchaseController {
    // Defining the class attribute variable which will store the use case object as one of the boundary interface
    private AddPurchaseBoundaryIn addPurchaseUseCaseBoundaryIn;

    /**
     * This function will be called from the main/view in order to control the use case AddPurchase which occurs whenever a user
     * from a group purchases an item from the planning list. This controller function packages all the required information that
     * the view sends forward into the PurchaseInfo data structure, instantiates the presenter and passes all of this information
     * to call the use case through the input boundary.
     * @param addPurchaseUseCase
     * @param purchasedItemId
     * @param participatingUsernames
     * @param buyerUsername
     * @param price
     * @param purchaseGroupId
     * @param groupDataAccess
     * @param itemDataAccess
     * @param userDataAccess
     */
    public void controlAddPurchaseUseCase(AddPurchaseBoundaryIn addPurchaseUseCase, String purchasedItemId, List<String> participatingUsernames, String buyerUsername, float price, String purchaseGroupId, GroupDataInterface groupDataAccess, ItemDataInterface itemDataAccess, UserDataInterface userDataAccess) {
        //Implement the instantiation of the presenter to pass along the pipeline and assign the boundary attribute variable to the use case object
        this.addPurchaseUseCaseBoundaryIn = addPurchaseUseCase;
        AddPurchaseBoundaryOut presenter = new AddPurchasePresenter();

        //Package the information into the data structure to pass to the use case through the boundary
        PurchaseInfo purchaseInfo = new PurchaseInfo(purchasedItemId, participatingUsernames, buyerUsername, price, purchaseGroupId, presenter, groupDataAccess, itemDataAccess, userDataAccess);

        // Call the use case
        this.addPurchaseUseCaseBoundaryIn.executeUseCase(purchaseInfo);
    }
}
