package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class PurchaseBalance implements Iterable<Debt>{

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

    /**
     * return a debt pair between a user owing money and a user owed money if exist
     * @param userOwed the username of the user owed money
     * @param userOwing the username of the user owing money
     * @return the debt pair if it exists, else null
     */
    public Debt getDebtPair(String userOwed, String userOwing){
        for (Debt debt : this.allDebts) {
            if (debt.getUserOwing().getUsername().equals(userOwing) && debt.getUserOwed().getUsername().equals(userOwed)){
                return debt;
            }
        }
        return null;
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

    /**
     * iterator function for iteration
     * @return the iterator that has been initialized
     */
    public Iterator<Debt> iterator() {
        return new PurchaseBalanceIterator<>(this.allDebts);
    }

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

class PurchaseBalanceIterator<Debt> implements Iterator<Debt> {

    /**
     * Class implementing the iterator object for the class PurchaseBalance.
     * This class is used to iterate over the list of debts in any implementation of PurchaseBalance
     */

    Integer current;
    List<Debt> debts;

    /**
     * Constructor for the iterator object
     * @param obj the list of debts in the PurchaseBalance implementation
     */
    PurchaseBalanceIterator(List<Debt> obj){
        // initialize cursor
        if (obj.isEmpty()){
            current = null;
        }else{
            current = 0;
        }
        this.debts = obj;
    }

    /**
     * checking if the next element exists
     * @return whether a next element exists
     */
    public boolean hasNext() {
        return current != null;
    }

    /**
     * moves the cursor/iterator to next element
     * @return the element at the current cursor position
     */
    public Debt next() {
        Debt toReturn = debts.get(this.current);
        if (debts.size() == current + 1){
            current = null;
        }else{
            current = current + 1;
        }
        return toReturn;
    }

}
