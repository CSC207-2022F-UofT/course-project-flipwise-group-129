package UseCases;

import DataStructures.PurchaseInfo;
import DataStructures.UpdatedLists;
import Entities.Group;
import Entities.Item;
import Entities.PurchaseList;
import Entities.User;
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

        // Dont i need the group and shit to do this lol?
        this.purchaseGroup.removeFromPlanningList(this.purchasedItem); // Sophie doing this
        this.purchaseGroup.addToPurchaseList(this.purchasedItem, price, participatingUsers);

        PurchaseList purchaseList = this.purchaseGroup.getPurchaseList();
        purchasedItem.setPrice(price);
        List<Item> tempList = purchaseList.getItems();
        tempList.add(purchasedItem);
        purchaseList.setItems(tempList);

        newLists = new UpdatedLists(this.purchaseGroup.getPlanningList(), this.purchaseGroup.getPurchaseList());
    }
}
