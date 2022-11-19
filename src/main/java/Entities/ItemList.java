package Entities;
import org.json.simple.JSONObject;

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

    public void addItems(Item item){
        this.items.add(item);
    }

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        List<String> allItems = new ArrayList<>();
        this.items.forEach(item -> allItems.add(item.toString()));
        return obj;
    }
}
