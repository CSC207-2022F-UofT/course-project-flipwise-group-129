package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.util.Set;
public class Group {
    /**
    This class represents a Group of users that have decided to conduct purchases together
     */
    private Set<String> users; // all the ids of users that are involved the group
    private PurchaseList purchaseList; // the list of things to purchase
    private PlanningList planningList; // the list of all the things that are planned to purchase
    private PurchaseBalance purchaseBalance; // has all the debts in the group

    private String groupName; // name of the group

    private String groupId; // id of the group, generated from the timestamp

    /**
     * Constructor to create a new group object
     * @param name the name of the group to be created
     * @param users all the users in the group
     */
    public Group(String name, Set<String> users){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        this.groupId = "Group" + name + ts.toInstant().toString();
        this.groupName = name;
        this.users = users;
        this.purchaseList = new PurchaseList();
        this.planningList = new PlanningList();
        this.purchaseBalance = new PurchaseBalance();
    }

    /**
     * Empty Constructor for JSON parsing
     */
    public Group(){
        super();
    }

    /**
     * Get all the users in the group
     * @return the set of all users in the group
     */
    public Set<String> getUsers(){
        // return all the users in this group
        return this.users;
    }

    /**
     * get the list of all things purchased in the group
     * @return the list of all things purchased in the group
     */
    public PurchaseList getPurchaseList(){
        // return the list of all the things that have been purchased
        return this.purchaseList;
    }

    /**
     * get the list of all things planned to be purchased in the group
     * @return get the list of all things planned to be purchased in the group
     */
    public PlanningList getPlanningList() {
        // return the list of all the things that have been planned to be purchased
        return planningList;
    }

    /**
     * get all the debts in the group
     * @return all the debts in the group
     */
    public PurchaseBalance getPurchaseBalance() {
        // return all the debts in this group
        return purchaseBalance;
    }

    /**
     * get the id of the group
     * @return the id of the group
     */
    public String getGroupId() {
    // return the groupId
        return this.groupId;
    }

    /**
     * get the name of the group
     * @return the name of the group
     */
    public String getGroupName() {
        // return the name of the group
        return this.groupName;
    }

    /**
     * add a user to the group
     * adds the user to the set of users in the group
     * @param user the user to add to the set of users in the group
     */
    public void addUser(String user){
        // add a user into the group
        this.users.add(user);
    }

    /**
     * method to return a JSONString representation of an instance of this class Group
     * @return a JSONString representation of an instance of this class Group
     */
    @Override
    public String toString() {
        //converts the current instance into a JSONString for data storage purposes
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * returns the instance of class Group present in the JSONString
     * if unable to parse, throw exception
     * @param groupString the JSONString containing all the information
     * @return returns the instance stored in the JSONString
     * @throws JsonProcessingException if unable to process the String into a Group instance
     */
    public static Group fromString(String groupString) throws JsonProcessingException {
        // converts the JSONString into an instance of Group
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(groupString, Group.class);
    }
}
