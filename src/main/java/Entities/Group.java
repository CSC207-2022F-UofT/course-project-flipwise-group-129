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
    private Set<User> users;
    private PurchaseList purchaseList;
    private PlanningList planningList;
    private PurchaseBalance purchaseBalance;

    private String groupName;

    private String groupId;
    private static Set<Group> groups;

    public Group(String name, Set<User> users){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        this.groupId = "Group" + name + ts.toInstant().toString();
        this.groupName = name;
        this.users = users;
        this.purchaseList = new PurchaseList();
        this.planningList = new PlanningList();
        groups.add(this);
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
        return this.users;
    }

    public PurchaseList getPurchaseList(){
        return this.purchaseList;
    }

    public PlanningList getPlanningList() {
        return planningList;
    }

    public PurchaseBalance getPurchaseBalance() {
        return purchaseBalance;
    }

    public String getGroupId() { return this.groupId;}

    public String getGroupName() { return this.groupName; }

    public static Set<Group> getGroups(){
        return groups;
    }

    public void addUser(User user){
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
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Group fromString(String groupString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(groupString, Group.class);
    }
}
