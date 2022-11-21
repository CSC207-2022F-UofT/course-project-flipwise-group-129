package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class Group {
    /*
    This class represents a Group of users that have decided to conduct purchases together
     */
    private Set<User> users; // all the users that are involved the group
    private PurchaseList purchaseList; // the list of things to purchase
    private PlanningList planningList; // the list of all the things that are planned to purchase
    private PurchaseBalance purchaseBalance; // has all the debts in the group

    private String groupName; // name of the group

    private String groupId; // id of the group, generated from the timestamp

    public Group(String name, Set<User> users){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        this.groupId = "Group" + name + ts.toInstant().toString();
        this.groupName = name;
        this.users = users;
        this.purchaseList = new PurchaseList();
        this.planningList = new PlanningList();
    }

//    public Group(String jsonString){
//        JSONParser parser = new JSONParser();
//        JSONObject obj;
//        try {
//            obj = (JSONObject) parser.parse(jsonString);
//
//            //setting atts
//            this.userOwed = new User(obj.get("userOwed").toString());
//            this.userOwing = new User(obj.get("userOwing").toString());
//            this.groupId = obj.get("groupId").toString();
//            this.debtValue = new Double(obj.get("debtValue").toString());
//
//
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    public Set<User> getUsers(){
        // return all the users in this group
        return this.users;
    }

    public PurchaseList getPurchaseList(){
        // return the list of all the things that have been purchased
        return this.purchaseList;
    }

    public PlanningList getPlanningList() {
        // return the list of all the thigns that have been planned to be purchased
        return planningList;
    }

    public PurchaseBalance getPurchaseBalance() {
        // return all the debts in this group
        return purchaseBalance;
    }

    public String getGroupId() {
    // return the groupId
        return this.groupId;
    }

    public String getGroupName() {
        // return the name of the group
        return this.groupName;
    }

    public void addUser(User user){
        // add a user into the group
        this.users.add(user);
    }

//    public JSONObject toJSON(){
//        JSONObject obj = new JSONObject();
//        List<String> allUsers = new ArrayList<>();
//        this.users.forEach(user -> allUsers.add(user.toString()));
//        obj.put("users", allUsers);
//        obj.put("groupId", this.groupId);
//        obj.put("plannedItems", this.planningList.toString());
//        obj.put("purchasedItems", this.purchaseList.toString());
//        obj.put("purchaseBalance", this.purchaseBalance.toString());
//        obj.put("groupName", this.groupName);
//
//        return obj;
//    }
    @Override
    public String toString() {
        //converts the current instance into a JSONString for datastorage purposes
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Group fromString(String groupString) throws JsonProcessingException {
        // converts the JSONString into an instance of Group
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(groupString, Group.class);
    }
}
