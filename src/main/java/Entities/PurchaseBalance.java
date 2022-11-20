package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    /**
     * get the list of all debts where the user is owed money
     * @param username the username of the user that is owed money
     * @return list of all debts where the user is owed money
     */
    public List<Debt> getUserOwed(String username){
        List<Debt> debts = new ArrayList<>();
        for (Debt debt : this.allDebts) {
            if (Objects.equals(debt.getUserOwed().getUsername(), username)){
                debts.add(debt);
            }
        }
        return debts;
    }

    /**
     * get the list of all debts where the user is owing money
     * @param username the username of the user that is owing money
     * @return list of all debts where the user is owing money
     */
    public List<Debt> getUserOwing(String username){
        List<Debt> debts = new ArrayList<>();
        for (Debt debt : this.allDebts) {
            if (Objects.equals(debt.getUserOwing().getUsername(), username)){
                debts.add(debt);
            }
        }
        return debts;
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
