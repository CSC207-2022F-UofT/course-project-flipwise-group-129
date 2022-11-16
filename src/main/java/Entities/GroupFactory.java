package Entities;

import java.util.Set;

public class GroupFactory {
    Group create(String name, Set<User> users){
        return new Group(name, users);
    }

}
