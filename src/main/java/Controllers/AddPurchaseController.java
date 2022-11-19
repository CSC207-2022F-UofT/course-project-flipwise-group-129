package Controllers;

import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataStructures.PurchaseInfo;
import InputBoundary.AddPurchaseBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import Presenters.AddPurchasePresenter;

import java.util.List;

public class AddPurchaseController {
    private AddPurchaseBoundaryIn addPurchaseUseCaseBoundaryIn;

    public void controlAddPurchaseUseCase(String purchasedItemId, List<String> participatingUsernames, String buyerUsername, float price, String purchaseGroupId, GroupDataInterface groupDataAccess, ItemDataInterface itemDataAccess) {
        AddPurchaseBoundaryOut presenter = new AddPurchasePresenter();
        PurchaseInfo purchaseInfo = new PurchaseInfo(purchasedItemId, participatingUsernames, buyerUsername, price, purchaseGroupId, presenter, groupDataAccess, itemDataAccess);

        addPurchaseUseCaseBoundaryIn.executeUseCase(purchaseInfo);
    }
}
