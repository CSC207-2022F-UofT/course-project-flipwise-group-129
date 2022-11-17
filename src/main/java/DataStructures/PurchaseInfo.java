package DataStructures;
import Entities.Group;
import Entities.Item;
import Entities.User;
import OutputBoundary.AddPurchaseBoundaryOut;

import java.util.List;

public class PurchaseInfo {
    private final Item purchasedItem;
    private final List<User> participatingUsers;
    private final User buyer;
    private final float price;
    private final Group purchaseGroup;
    private final AddPurchaseBoundaryOut presenter;

    public PurchaseInfo(Item item, List<User> users, User buyer, float itemPrice, Group purchaseGroup, AddPurchaseBoundaryOut presenter) {
        this.purchasedItem = item;
        this.participatingUsers = users;
        this.buyer = buyer;
        this.price = itemPrice;
        this.purchaseGroup = purchaseGroup;
        this.presenter = presenter;
    }

    public Item getItem(){
        return this.purchasedItem;
    }

    public List<User> getUsers(){
        return this.participatingUsers;
    }

    public float getPrice(){
        return this.price;
    }

    public Group getPurchaseGroup() { return this.purchaseGroup; }

    public User getBuyer() { return this.buyer; }

    public AddPurchaseBoundaryOut getPresenter() { return this.presenter; }
}
