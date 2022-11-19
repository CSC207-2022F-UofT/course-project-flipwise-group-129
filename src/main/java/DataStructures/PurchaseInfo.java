package DataStructures;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import OutputBoundary.AddPurchaseBoundaryOut;

import java.util.List;

public class PurchaseInfo {
    private final String purchasedItemId;
    private final List<String> participatingUsernames;
    private final String buyerUsername;
    private final float price;
    private final String purchaseGroupId;
    private final AddPurchaseBoundaryOut presenter;
    private final GroupDataInterface groupData;
    private final ItemDataInterface itemData;
    private final UserDataInterface userData;

    public PurchaseInfo(String purchasedItemId, List<String> participatingUsernames, String buyerUsername, float itemPrice, String purchaseGroupId, AddPurchaseBoundaryOut presenter, GroupDataInterface groupData, ItemDataInterface itemData, UserDataInterface userData) {
        this.purchasedItemId = purchasedItemId;
        this.participatingUsernames = participatingUsernames;
        this.buyerUsername = buyerUsername;
        this.price = itemPrice;
        this.purchaseGroupId = purchaseGroupId;
        this.presenter = presenter;
        this.groupData = groupData;
        this.itemData = itemData;
        this.userData = userData;
    }

    public String getItemId(){
        return this.purchasedItemId;
    }

    public List<String> getUsers(){ return this.participatingUsernames; }

    public float getPrice(){
        return this.price;
    }

    public String getPurchaseGroup() { return this.purchaseGroupId; }

    public String getBuyer() { return this.buyerUsername; }

    public AddPurchaseBoundaryOut getPresenter() { return this.presenter; }

    public GroupDataInterface getGroupData() { return this.groupData; }

    public ItemDataInterface getItemData() { return this.itemData; }

    public UserDataInterface getUserData() { return this.userData; }
}
