package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class User {

    /*
    Represents a User in this applications
     */

    private final String username;
    private final String password;
    private List<Group> groups;

    /**
     * Constructor to create a new user object
     * @param username
     * @param password
     * @param groups
     */

    public User(String username, String password, List<Group> groups){
        this.username = username;
        this.password = password;
        this.groups = groups;
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
    public List<Group> getGroups() {
        //return all the groups that the users is in
        return groups;
    }

//    public List<User> getUsers(){
//        return allUsers;
//    }
//
//    public void addUser(User newUser){
//        allUsers.add(newUser);
//    }

    /**
     * add a group to the list of groups the user is in
     * @param group the group to add to list of groups the user is in
     */
    public void addGroup(Group group){
        // add a group for this user to the  list of groups they're in
        this.groups.add(group);
    }

    /**
     * remove user from a group
     * @param group the group to remove the user from
     * @return whether removal was successful
     */
    public boolean removeFromGroup(Group group){
        // remove group from the list of groups the user is part of
        if (this.groups.contains(group)){
            this.groups.remove(group);
            return true;
        }
        return false;
    }

//    public JSONObject toJSON(){
//        JSONObject obj = new JSONObject();
//        obj.put("username", this.username);
//        obj.put("password", this.password);
//        List<String> groupStrings = new ArrayList<>();
//        this.groups.forEach(group -> groupStrings.add(group.toString()));
//        obj.put("groups", groupStrings);
//
//        return obj;
//    }
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

}
