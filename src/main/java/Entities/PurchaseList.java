package Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class PurchaseList extends ItemList{
    /*
    Represents a list of all items that have been purchased so far
     */
    public PurchaseList(){
        super.setItems(new ArrayList<>());
    }

    /**
     * method to return a JSONString representation of an instance of this class PurchaseList
     * @return a JSONString representation of an instance of this class PurchaseList
     */
    @Override
    public String toString() {
        // convert an instance of this object into a JSONString
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
