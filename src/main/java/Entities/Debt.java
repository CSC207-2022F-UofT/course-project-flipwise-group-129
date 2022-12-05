package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Debt {

    /**
     * This class represents a single debt pair between a user owing money and user that is owed money
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
     * @return the userOwing money
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
}
