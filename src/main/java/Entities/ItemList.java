package Entities;
import java.util.ArrayList;
import java.util.List;

abstract public class ItemList {
    private List<Item> items;

    public List<Item> getItems(){
        return this.items;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }
}
