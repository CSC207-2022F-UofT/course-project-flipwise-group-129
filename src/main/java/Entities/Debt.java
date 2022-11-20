package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Debt {

    private User userOwed;
    private User userOwing;
    private String groupId;
    private Double debtValue;

    public Debt(User user1, User user2, String groupId){
        this.userOwed = user1;
        this.userOwing = user2;
        this.groupId = groupId;
        this.debtValue = 0.0;
    }

    public User getUserOwed(){
        return this.userOwed;
    }

    public User getUserOwing() {
        return this.userOwing;
    }

    public User[] getUsers(){
        User[] users = new User[2];
        users[0] = this.userOwed;
        users[1] = this.userOwing;
        return users;
    }

    public Double getDebtValue(){
        return this.debtValue;
    }

    public void setDebtValue(Double debtValue){
        this.debtValue = debtValue;
    }

    public String getGroupId() {
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
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Debt fromString(String debtString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(debtString, Debt.class);
    }
}
