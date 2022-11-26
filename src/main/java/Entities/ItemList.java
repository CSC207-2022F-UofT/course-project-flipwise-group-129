package Entities;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract public class ItemList implements Iterable<Item> {
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

    public Iterator<Item> iterator() {
        return new ItemListIterator<>(this.items);
    }

//    public static void main(String[] args) {
//        Item item1 = new Item("apple");
//        Item item2 = new Item("orange");
//        Item item3 = new Item("pomegranate");
//        PlanningList planning = new PlanningList();
//
//        planning.addItems(item1);
//        planning.addItems(item2);
//        planning.addItems(item3);
//
//        for (Item item : planning) {
//            System.out.println(item);
//        }
//    }
}

class ItemListIterator<Item> implements Iterator<Item> {

    Integer current;
    List<Item> items;

    // constructor
    ItemListIterator(List<Item> obj){
        // initialize cursor
        if (obj.isEmpty()){
            current = null;
        }else{
            current = 0;
        }
        this.items = obj;
    }

    // Checks if the next element exists
    public boolean hasNext() {
        return current != null;
    }

    // moves the cursor/iterator to next element
    public Item next() {
        Item toReturn = items.get(this.current);
        if (items.size() == current + 1){
            current = null;
        }else{
            current = current + 1;
        }
        return toReturn;
    }

}
