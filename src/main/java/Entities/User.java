/**
 * Represents a user in the application
 */
package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class User implements Comparable<User> {

    private String username;
    private String password;
    private List<String> groups;

    /**
     * Constructor to create a new user object
     * @param username of the user
     * @param password of the user
     * @param groups that the user is a part of
     */

    public User(String username, String password, List<String> groups){
        this.username = username;
        this.password = password;
        this.groups = groups;
    }

    /**
     * empty constructor for JSON parsing
     */
    public User(){
        super();
    }

    /**
     * get the users password
     * @return the users password
     */
    public String getPassword() {
        // return this users password
        return password;
    }

    /**
     * get the name of the user
     * @return the name of the user
     */
    public String getUsername(){
        //return the username of this user
        return username;
    }

    /**
     * the groups user is in
     * @return list of all groups the user is in
     */
    public List<String> getGroups() {
        //return all the groups that the users is in
        return groups;
    }

    /**
     * add a group to the list of groups the user is in
     * @param group the group to add to list of groups the user is in
     */
    public void addGroup(String group){
        // add a group for this user to the  list of groups they're in
        this.groups.add(group);
    }

    /**
     * remove user from a group
     * @param group the group to remove the user from
     */
    public void removeFromGroup(Group group){
        // remove group from the list of groups the user is part of
        this.groups.remove(group);
    }

    /**
     * method to return a JSONString representation of an instance of this class User
     * @return a JSONString representation of an instance of this class User
     */
    @Override
    public String toString() {
        //convert an instance of this object into a JSONString
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * returns the instance of class User present in the JSONString
     * if unable to parse, throw exception
     * @param userString the JSONString containing all the information
     * @return returns the instance stored in the JSONString
     * @throws JsonProcessingException if unable to process the String into a Group instance
     */
    public static User fromString(String userString) throws JsonProcessingException {
        //convert JSONString into an instance of this object.
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(userString, User.class);
    }

    /**
     * compare two User objects
     * if their usernames match, they're the same user
     * @param o the object to be compared.
     * @return 0 if they're equal else 1
     */
    @Override
    public int compareTo(User o) {
        if (this.username.equals(o.getUsername())){
            return 0;
        }
        return 1;
    }
}
