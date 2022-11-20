package Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class PurchaseList extends ItemList{
    /*
    Represents a list of all items that have been purchsed so far
     */
    public PurchaseList(){
        super.setItems(new ArrayList<>());
    }

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
