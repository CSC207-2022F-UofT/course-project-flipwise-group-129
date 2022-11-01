package Entities;
import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private ArrayList<Group> groups;

    public User(String username, String password, ArrayList<Group> groups){
        this.username = username;
        this.password = password;
        this.groups = groups;
    }
}
