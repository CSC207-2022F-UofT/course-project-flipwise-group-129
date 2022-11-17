package DataStructures;

public class PaymentDetails {
    private final String groupID;

    private final String username;

    private final float itemPrice;

    private final String itemID;

    public PaymentDetails(String group_id, String user, float price, String item_id) {
        this.groupID = group_id;
        this.username = user;
        this.itemPrice = price;
        this.itemID = item_id;
    }

    public String getGroupID() {
        return this.groupID;
    }

    public String getUsername() {
        return this.username;
    }

    public float getItemPrice() {
        return this.itemPrice;
    }

    public String getItemID() {
        return this.itemID;
    }

}
