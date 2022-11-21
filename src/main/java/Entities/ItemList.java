package Entities;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

abstract public class ItemList {
    /*
    Abstract class representing a list of items
    Used for the lists of item that were planned to be purchased
    and the list of items that have been purchased.
     */
    private List<Item> items; //list of items

    /**
     * get all the items in the list
     * @return the list of all items in the list
     */
    public List<Item> getItems(){
        // return the list of al items in this list
        return this.items;
    }

    /**
     * set the items in the list
     * @param items in the list
     */
    public void setItems(List<Item> items){
        // set the list of all items in this list
        this.items = items;
    }

    /**
     * add an item into the list
     * @param item the item to be added into the list
     */
    public void addItems(Item item){
        // add an item into the list
        this.items.add(item);
    }

//    public JSONObject toJSON(){
//        JSONObject obj = new JSONObject();
//        List<String> allItems = new ArrayList<>();
//        this.items.forEach(item -> allItems.add(item.toString()));
//        return obj;
//    }
}
