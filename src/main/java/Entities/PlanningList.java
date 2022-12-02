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

    /**
     * removes an item no longer planned to buy
     * @param item the item to be removed
     * @return whether the item was successfully removed
     */
    public boolean removeFromList(Item item){
        // remove an item from the list if it exists
        if (this.getItems().contains(item)){
            List<Item> tempList = this.getItems();
            tempList.remove(item);
            this.setItems(tempList);
            return true;
        }else{
            return false;
        }
    }


    /**
     * method to return a JSONString representation of an instance of this class PlanningList
     * @return a JSONString representation of an instance of this class PlanningList
     */
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
