package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class PaymentDetails {
    private final String groupID;

    private final String username;

    private final float itemPrice;

    private final String itemID;

    private final List<String> usersInvolvedInPurchase;

    /**
     * Assigns values from the parameters to the class attribute variables.
     * @param group_id the String which represents the ID of the group which was involved in the purchase.
     * @param user the User who made the purchase.
     * @param price the price of the item purchased represented as a float.
     * @param item_id the String which represents the ID of the item which was purchased.
     * @param users the list of all users involved in the purchase of the item;
     */
    public PaymentDetails(String group_id, String user, float price, String item_id, List<String> users) {
        this.groupID = group_id;
        this.username = user;
        this.itemPrice = price;
        this.itemID = item_id;
        this.usersInvolvedInPurchase = users;
    }

    /*
    Since this data structure is only being read from, we are defining the getters for each of the attributes, so it can
    be used in the use case.
     */

    /**
     * @return the groupID of the group involved in the payment.
     */
    public String getGroupID() {
        return this.groupID;
    }

    /**
     * @return the username of the user who purchased the item.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the price of the item purchased.
     */
    public float getItemPrice() {
        return this.itemPrice;
    }

    /**
     * @return the itemID of the purchased item.
     */
    public String getItemID() {
        return this.itemID;
    }

    public List<String> getUsersInvolvedInPurchase() {
        return this.usersInvolvedInPurchase;
    }
}
