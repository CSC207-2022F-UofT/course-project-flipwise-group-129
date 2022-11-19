package Entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private List<Group> groups;

    public User(String username, String password, List<Group> groups){
        this.username = username;
        this.password = password;
        this.groups = groups;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername(){
        return username;
    }

    public List<Group> getGroups() {
        return groups;
    }

//    public List<User> getUsers(){
//        return allUsers;
//    }
//
//    public void addUser(User newUser){
//        allUsers.add(newUser);
//    }

    public void addGroup(Group group){
        this.groups.add(group);
    }

    public boolean removeFromGroup(Group group){
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
