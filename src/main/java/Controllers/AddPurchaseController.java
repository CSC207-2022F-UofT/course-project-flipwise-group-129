package Controllers;

import DataStructures.PurchaseInfo;
import Entities.Group;
import Entities.Item;
import Entities.User;
import InputBoundary.AddPurchaseBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import Presenters.AddPurchasePresenter;

import java.util.List;

public class AddPurchaseController {
    private AddPurchaseBoundaryIn addPurchaseUseCaseBoundaryIn;

    public void controlAddPurchaseUseCase(Item purchasedItem, List<User> participatingUsers, User buyer, float price, Group purchaseGroup) {
        AddPurchaseBoundaryOut presenter = new AddPurchasePresenter();
        PurchaseInfo purchaseInfo = new PurchaseInfo(purchasedItem, participatingUsers, buyer, price, purchaseGroup, presenter);

        addPurchaseUseCaseBoundaryIn.executeUseCase(purchaseInfo);
    }
}
