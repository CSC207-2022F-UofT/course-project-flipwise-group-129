package Entities;
import java.util.ArrayList;
import java.util.List;

abstract public class ItemList {
    private List<String> items;

    public List<String> getItems(){
        return this.items;
    }

    public void setItems(List<String> items){
        this.items = items;
    }
}
