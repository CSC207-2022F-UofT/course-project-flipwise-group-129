package Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlanningList extends ItemList{

    /*
    Represents the list of all items that have been planned to be purchased
     */
    public PlanningList(){
        super.setItems(new ArrayList<>());
    }

    public boolean removeFromList(Item item){
        // remove an item from the list if it exists
        if (super.getItems().contains(item)){
            super.getItems().remove(item);
            return true;
        }else{
            return false;
        }
    }
    @Override
    public String toString() {
        // convert an instance of this class into a JSONString
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
