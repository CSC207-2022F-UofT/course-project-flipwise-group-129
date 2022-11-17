package Controllers;

import DataStructures.PurchaseInfo;
import Entities.Group;
import Entities.Item;
import Entities.User;
import InputBoundary.AddPurchaseBoundaryIn;

import java.util.List;

public class AddPurchaseController {

    private AddPurchaseBoundaryIn addPurchaseUseCaseBoundaryIn;

    public void controlAddPurchaseUseCase(Item purchasedItem, List<User> participatingUsers, User buyer, float price, Group purchaseGroup) {
        PurchaseInfo purchaseInfo = new PurchaseInfo(purchasedItem, participatingUsers, buyer, price, purchaseGroup);

        addPurchaseUseCaseBoundaryIn.executeUseCase(purchaseInfo);
    }
}
