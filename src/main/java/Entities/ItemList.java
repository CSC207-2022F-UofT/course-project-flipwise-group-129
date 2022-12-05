package Entities;
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

    /**
     * iterator function for iteration
     * @return the iterator that has been initialized
     */
    public Iterator<Item> iterator() {
        return new ItemListIterator<>(this.items);
    }
}

class ItemListIterator<Item> implements Iterator<Item> {

    /**
     * Class implementing the iterator object for the class itemList.
     * This class is used to iterate over the list of items in any implementation of ItemList
     */
    Integer current;
    List<Item> items;

    /**
     * Constructor for the iterator object
     * @param obj the list of items in the ItemList implementation
     */
    ItemListIterator(List<Item> obj){
        // initialize cursor
        if (obj.isEmpty()){
            current = null;
        }else{
            current = 0;
        }
        this.items = obj;
    }

    /**
     * checking if the next element exists
     * @return whether a next element exists
     */
    public boolean hasNext() {
        return current != null;
    }

    /**
     * moves the cursor/iterator to next element
     * @return the element at the current cursor position
     */
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
