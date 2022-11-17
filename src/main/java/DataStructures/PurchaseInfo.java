package DataStructures;
import Entities.Group;
import Entities.Item;
import Entities.User;
import OutputBoundary.AddPurchaseBoundaryOut;

import java.util.List;

public class PurchaseInfo {
    private final String purchasedItemId;
    private final List<String> participatingUsernames;
    private final String buyerUsername;
    private final float price;
    private final String purchaseGroupId;
    private final AddPurchaseBoundaryOut presenter;

    public PurchaseInfo(String purchasedItemId, List<String> participatingUsernames, String buyerUsername, float itemPrice, String purchaseGroupId, AddPurchaseBoundaryOut presenter) {
        this.purchasedItemId = purchasedItemId;
        this.participatingUsernames = participatingUsernames;
        this.buyerUsername = buyerUsername;
        this.price = itemPrice;
        this.purchaseGroupId = purchaseGroupId;
        this.presenter = presenter;
    }

    public String getItem(){
        return this.purchasedItemId;
    }

    public List<String> getUsers(){ return this.participatingUsernames; }

    public float getPrice(){
        return this.price;
    }

    public String getPurchaseGroup() { return this.purchaseGroupId; }

    public String getBuyer() { return this.buyerUsername; }

    public AddPurchaseBoundaryOut getPresenter() { return this.presenter; }
}
