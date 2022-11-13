package DataStructures;
import Entities.Item;
import Entities.User;

import java.util.List;

public class PurchaseInfo {
    private final Item purchasedItem;
    private final List<User> participatingUsers;
    private final float price;

    public PurchaseInfo(Item item, List<User> users, float itemPrice) {
        this.purchasedItem = item;
        this.participatingUsers = users;
        this.price = itemPrice;
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
}
