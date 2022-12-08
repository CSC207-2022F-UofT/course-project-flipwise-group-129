package UseCaseLayer.DataStructures;
import UseCaseLayer.DataAccessInterface.GroupDataInterface;
import UseCaseLayer.DataAccessInterface.ItemDataInterface;
import UseCaseLayer.DataAccessInterface.UserDataInterface;
import UseCaseLayer.OutputBoundary.AddPurchaseBoundaryOut;

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

    /**
     * Assigns values from the parameters to the class attribute variables
     * @param purchasedItemId the id of the item that's being purchased
     * @param participatingUsernames the usernames of the users involved in the purchase
     * @param buyerUsername the username of the user who purchased the item
     * @param itemPrice the price of the item that was purchased
     * @param purchaseGroupId the id of the group that this purchase was made in
     * @param presenter the presenter output boundary object for the use case to reflect ui changes
     * @param groupData the data access boundary for groups
     * @param itemData the data access boundary for items
     * @param userData the data access boundary for users
     */
    public PurchaseInfo(String purchasedItemId, List<String> participatingUsernames, String buyerUsername,
                        float itemPrice, String purchaseGroupId, AddPurchaseBoundaryOut presenter,
                        GroupDataInterface groupData, ItemDataInterface itemData, UserDataInterface userData) {
        // Instantiate the private attributes of all the different pieces of data packaged into this data structure
        // for the use cases use
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

    // Since this data structure will only be read from, define and implement getters for each of the attributes

    /**
     * @return the item id of the item being purchased
     */
    public String getItemId(){
        return this.purchasedItemId;
    }

    /**
     * @return participating usernames private attribute
     */
    public List<String> getUsers(){ return this.participatingUsernames; }

    /**
     * @return price private attribute
     */
    public float getPrice(){
        return this.price;
    }

    /**
     * @return purchase group id private attribute
     */
    public String getPurchaseGroup() { return this.purchaseGroupId; }

    /**
     * @return buyer username private attribute
     */
    public String getBuyer() { return this.buyerUsername; }

    /**
     * @return output boundary presenter private attribute
     */
    public AddPurchaseBoundaryOut getPresenter() { return this.presenter; }

    /**
     * @return group data access interface private attribute
     */
    public GroupDataInterface getGroupData() { return this.groupData; }

    /**
     * @return item data access interface private attribute
     */
    public ItemDataInterface getItemData() { return this.itemData; }

    /**
     * @return user data access interface private attribute
     */
    public UserDataInterface getUserData() { return this.userData; }
}
