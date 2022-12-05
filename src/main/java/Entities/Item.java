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
    private String buyer; // the id of the person who bought the item
    private double price; // the price of the item

    private String itemId; //id for unique identification purposes
    private List<String> usersInvolved; // the list of all the user ids involved in the purchase

    /**
     * Constructor to make a new item
     * @param itemName the name of the item
     */
    public Item(String itemName){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        this.itemId = "Item" + itemName + ts.toInstant().toString();
        this.itemName = itemName;
        this.buyer = null;
        this.price = 0.0;
        this.usersInvolved = new ArrayList<>();
    }

    /**
     * constructor empty for JSON parsing
     */
    public Item(){
        super();
    }

    /**
     * get the name of the item
     * @return the name of the item
     */
    public String getItemName(){
        // return the name of the item
        return itemName;
    }

    /**
     * get the buyer
     * @return the buyer
     */
    public String getBuyer(){
        // return the buyer of this item
        return buyer;
    }

    /**
     * get the price of this item
     * @return the price
     */
    public double getPrice(){
        // reutn the price of the item
        return price;
    }

    /**
     * get the id of this item
     * @return the id
     */
    public String getItemId(){
        //return the item id
        return this.itemId;
    }

    /**
     * get all the users involved in the purchase
     * @return the users involved in the purchase as a List
     */
    public List<String> getUsersInvolved() {
        //return the list of all the users involved in the purchase.
        return this.usersInvolved;
    }

    /**
     * set the price of the item when bought
     * @param price the price
     */
    public void setPrice(double price){
        //set the price of the item, useful for editing the value after construction
        this.price = price;
    }

    /**
     * set the name of the item
     * @param name the name of the item
     */
    public void setItemName(String name){
        //set the name of the item
        this.itemName = name;
    }

    /**
     * set the person who bought the item
     * @param buyer the person who bought the item
     */
    public void setBuyer(String buyer){
        //set the buyer who bought the product
        this.buyer = buyer;
    }

    /**
     * set all the users invovled in the purchase of item
     * @param users the users involved in the purchase of item
     */
    public void setUsersInvolved(List<String> users){
        //enter all the users involved
        this.usersInvolved = users;
    }

    /**
     *  add a person to the purchase of item
     * @param user the person to add to purchase of item
     */
    public void addUsersInvolved(String user){
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
    /**
     * method to return a JSONString representation of an instance of this class Item
     * @return a JSONString representation of an instance of this class Item
     */
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * returns the instance of class Item present in the JSONString
     * if unable to parse, throw exception
     * @param itemString the JSONString containing all the information
     * @return returns the instance stored in the JSONString
     * @throws JsonProcessingException if unable to process the String into a Group instance
     */
    public static Item fromString(String itemString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(itemString, Item.class);
    }
}
