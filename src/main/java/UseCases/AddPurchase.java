package UseCases;

import DataStructures.PurchaseInfo;
import DataStructures.UpdatedLists;
import Entities.*;
import Presenters.AddPurchasePresenter;

import java.util.ArrayList;
import java.util.List;

public class AddPurchase {
    private final PurchaseInfo purchaseInfo;
    private Item purchasedItem;
    private List<User> participatingUsers;
    private float price;
    private Group purchaseGroup;
    private UpdatedLists newLists;

    public AddPurchase(PurchaseInfo purchaseInfo) {
        this.purchaseInfo = purchaseInfo;

        this.purchasedItem = purchaseInfo.getItem();
        this.participatingUsers = purchaseInfo.getUsers();
        this.price = purchaseInfo.getPrice();
        this.purchaseGroup = purchaseInfo.getPurchaseGroup();

        PlanningList planningList = this.purchaseGroup.getPlanningList();
        planningList.removeFromList(this.purchasedItem);
            
        PurchaseList purchaseList = this.purchaseGroup.getPurchaseList();
        this.purchasedItem.setPrice(this.price);
        purchaseList.addItems(this.purchasedItem);

        newLists = new UpdatedLists(this.purchaseGroup.getPlanningList(), this.purchaseGroup.getPurchaseList());
    }
}
