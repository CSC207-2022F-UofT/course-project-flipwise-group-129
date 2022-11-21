package Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Item {

    /*
    This class represents an item that has been bought in a purchase
     */
    private String itemName; //the name of the item
    private User buyer; // the person who bought the item
    private Float price; // the price of the item

    private String itemId; //id for unique identification purposes
    private List<User> usersInvolved; // the list of all the users invovled in the purchase

    public Item(String itemName, User buyer, Float price, List<User> usersInvolved){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        this.itemId = "Item" + itemName + ts.toInstant().toString();
        this.itemName = itemName;
        this.buyer = buyer;
        this.price = price;
        this.usersInvolved = usersInvolved;
    }

    public String getItemName(){
        // return the name of the item
        return itemName;
    }

    public User getBuyer(){
        // return the buyer of this item
        return buyer;
    }

    public Float getPrice(){
        // reutn the price of the item
        return price;
    }

    public String getItemId(){
        //return the item id
        return this.itemId;
    }

    public List<User> getUsersInvolved() {
        //return the list of all the users involved in the purchase.
        return this.usersInvolved;
    }

    public void setPrice(Float price){
        //set the price of the item, useful for editing the value after construction
        this.price = price;
    }

    public void setItemName(String name){
        //set the name of the item
        this.itemName = name;
    }

    public void setBuyer(User buyer){
        //set the buyer who bought the product
        this.buyer = buyer;
    }

    public void setUsersInvolved(List<User> users){
        //enter all the users involved
        this.usersInvolved = users;
    }

    public void addUsersInvolved(User user){
        // add all the users invovled in this item purchase
        this.usersInvolved.add(user);
    }

//    public JSONObject toJSON(){
//        JSONObject obj = new JSONObject();
//        obj.put("itemId", this.itemId);
//        obj.put("itemName", this.itemName);
//        obj.put("userBuyer", this.buyer);
//        List<String> usersInvolved = new ArrayList<>();
//        this.usersInvolved.forEach(user -> usersInvolved.add(user.toString()));
//        obj.put("usersInvolved", usersInvolved);
//        obj.put("price", this.price);
//
//        return obj;
//    }
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Item fromString(String itemString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(itemString, Item.class);
    }
}
