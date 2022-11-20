package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Debt {

    private User userOwed; //the user that is being owed money
    private User userOwing; //the user that is owing the money
    private String groupId; // the id of the group this debt belongs to
    private Double debtValue; //the value userOwed is owed by userOwing

    public Debt(User user1, User user2, String groupId){
        this.userOwed = user1;
        this.userOwing = user2;
        this.groupId = groupId;
        this.debtValue = 0.0;
    }

    public User getUserOwed(){
        //returns the user that is being owed money
        return this.userOwed;
    }

    public User getUserOwing() {
        //returns the user that owes the money
        return this.userOwing;
    }

    public User[] getUsers(){
        // return a list of both the users
        User[] users = new User[2];
        users[0] = this.userOwed;
        users[1] = this.userOwing;
        return users;
    }

    public Double getDebtValue(){
        // returns the value of the debt between the two users
        return this.debtValue;
    }

    public void setDebtValue(Double debtValue){
        // set the amount userOwing owes userOwed
        this.debtValue = debtValue;
    }

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

    public static Debt fromString(String debtString) throws JsonProcessingException {
        // convers the JSONString into the required object class
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(debtString, Debt.class);
    }
}
