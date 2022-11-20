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

    /**
     * Initialize to an empty list of debts
     */
    public PurchaseBalance(){
        this.allDebts = new ArrayList<>();
    }

    /**
     * initialize a list of debts
     * @param debts the list of debts to be set
     */
    public PurchaseBalance(List<Debt> debts){
        this.allDebts = debts;
    }

    /**
     * get all the debts
     * @return all the debts
     */
    public List<Debt> getAllDebts() {
        // return a list of all the debts
        return allDebts;
    }

    /**
     * add a user-user debt pair\
     * appends ths debt pair into list of debts
     * @param debt the debt pair to be added
     */
    public void addDebtPair(Debt debt){
        // add a pair of debts between two users
        this.allDebts.add(debt);
    }

    /**
     * remove a user-user debt from the list
     * @param debt the debt pair to be removed
     * @return whether debt removal was successful
     */
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

    /**
     * method to return a JSONString representation of an instance of this class PurchaseBalance
     * @return a JSONString representation of an instance of this class PurchaseBalance
     */
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
