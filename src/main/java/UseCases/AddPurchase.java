package UseCases;

import DataStructures.PurchaseInfo;
import Entities.Item;
import Entities.User;

import java.util.List;

public class AddPurchase {
    private final PurchaseInfo purchaseInfo;
    private Item purchasedItem;
    private List<User> participatingUsers;
    private float price;

    public AddPurchase(PurchaseInfo purchaseInfo) {
        this.purchaseInfo = purchaseInfo;

        this.purchasedItem = purchaseInfo.getItem();
        this.participatingUsers = purchaseInfo.getUsers();
        this.price = purchaseInfo.getPrice();

        // Dont i need the group and shit to do this lol?
    }
}
