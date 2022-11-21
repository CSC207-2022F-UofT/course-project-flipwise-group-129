package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PurchaseBalance {

    /*
    Represents a list of all the debts
     */

    private List<Debt> allDebts;

    public PurchaseBalance(){
        this.allDebts = new ArrayList<>();
    }
    public PurchaseBalance(List<Debt> debts){
        this.allDebts = debts;
    }

    public List<Debt> getAllDebts() {
        // return a list of all the debts
        return allDebts;
    }

    public void addDebtPair(Debt debt){
        // add a pair of debts between two users
        this.allDebts.add(debt);
    }

    public boolean removeDebtPair(Debt debt){
        //remove a debt between two users
        if (this.allDebts.contains(debt)){
            this.allDebts.remove(debt);
            return true;
        }
        return false;
    }

//    public JSONObject toJSON(){
//        JSONObject obj = new JSONObject();
//        List<String> allDebts = new ArrayList<>();
//        this.allDebts.forEach(debt -> allDebts.add(debt.toString()));
//        obj.put("allDebts", allDebts);
//
//        return obj;
//    }
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
