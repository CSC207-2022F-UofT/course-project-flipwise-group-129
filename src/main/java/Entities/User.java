package Entities;
import java.util.List;
import java.util.ArrayList;

public class User {

    private String username;
    private String password;

    private String userId;
    private List<Group> groups;

//    private static List<User> allUsers = new ArrayList<>();

    public User(String username, String password, List<Group> groups){
        // this.userId = will be implemented once datastore has been
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
}
