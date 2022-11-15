package Entities;
import java.util.List;
import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private ArrayList<Group> groups;

    private static List<User> allUsers = new ArrayList<>();

    public User(String username, String password, ArrayList<Group> groups){
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

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public List<User> getUsers(){
        return allUsers;
    }

    public void addUser(User newUser){
        allUsers.add(newUser);
    }
}
