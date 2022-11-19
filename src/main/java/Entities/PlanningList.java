package Entities;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlanningList extends ItemList{

    public PlanningList(){
        super.setItems(new ArrayList<>());
    }

    public boolean removeFromList(Item item){
        if (super.getItems().contains(item)){
            super.getItems().remove(item);
            return true;
        }else{
            return false;
        }
    }
    @Override
    public String toString() {
        return this.toJSON().toJSONString();
    }
}
