package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Debt {

    /**
     * This class reprsents a single debt pair between a user owing money and user that is owed money
     */

    private User userOwed; //the user that is being owed money
    private User userOwing; //the user that is owing the money
    private String groupId; // the id of the group this debt belongs to
    private Double debtValue; //the value userOwed is owed by userOwing

    private List<User> users;

    /**
     * Constructor to create a new Debt object
     * Debt is initialized to 0
     * @param user1 user owed money
     * @param user2 user owing money
     * @param groupId the id of the group they're in
     */
    public Debt(User user1, User user2, String groupId){
        this.userOwed = user1;
        this.userOwing = user2;
        this.groupId = groupId;
        this.debtValue = 0.0;
    }

    /**
     * Empty constructor for JSON parsing
     */
    public Debt(){
        super();
    }

    /**
     * get the user owed money
     * @return the user owed money
     */
    public User getUserOwed(){
        //returns the user that is being owed money
        return this.userOwed;
    }

    /**
     * get the user owing money
     * @return the userowing money
     */

    public User getUserOwing() {
        //returns the user that owes the money
        return this.userOwing;
    }

    /**
     * get both the users
     * @return both the users
     */
    public List<User> getUsers(){
        // return a list of both the users
        return this.users;
    }

    /**
     * get the value of the debt between them
     * @return the value of the debt between them
     */
    public Double getDebtValue(){
        // returns the value of the debt between the two users
        return this.debtValue;
    }

    /**
     * set the debt between the two users
     * @param debtValue the new debt value to be set
     */
    public void setDebtValue(Double debtValue){
        // set the amount userOwing owes userOwed
        this.debtValue = debtValue;
    }

    /**
     * get the id of the group they belong to
     * @return the id of the group they belong to
     */
    public String getGroupId() {
        // get the id of the group this debt is involved in
        return this.groupId;
    }

//    public JSONObject toJSON(){
//        JSONObject obj = new JSONObject();
//        obj.put("userOwed", this.userOwed.toString());
//        obj.put("userOwing", this.userOwing.toString());
//        obj.put("groupId", this.groupId);
//        obj.put("debtValue", this.debtValue);
//        return obj;
//    }
    /**
     * method to return a JSONString representation of an instance of this class Debt
     * @return a JSONString representation of an instance of this class Debt
     */
    @Override
//    public String toString() {
//        return this.toJSON().toJSONString();
//    }
    public String toString() {
        // converts the current class into a JSON string for data storage purposes later
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * returns the instance of class Debt present in the JSONString
     * if unable to parse, throw exception
     * @param debtString the JSONString containing all the information
     * @return returns the instance stored in the JSONString
     * @throws JsonProcessingException if unable to process the String into a Group instance
     */
    public static Debt fromString(String debtString) throws JsonProcessingException {
        // converts the JSONString into the required object class
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(debtString, Debt.class);
    }
}
