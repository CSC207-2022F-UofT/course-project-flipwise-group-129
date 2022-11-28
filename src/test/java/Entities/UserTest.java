package Entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void addGroup() {
        User user1 = new User("mishaalk", "2222", new ArrayList<>());
        User user2 = new User("randomC", "1111", new ArrayList<>());

        Set<User> users = new TreeSet<>();
        users.add(user1);
        users.add(user2);
        Group group = new Group("group1", users);

        user1.addGroup(group);

        assert user1.getGroups().contains(group);

    }

    @Test
    void removeFromGroup() {
        User user1 = new User("mishaalk", "2222", new ArrayList<>());
        User user2 = new User("randomC", "1111", new ArrayList<>());

        Set<User> users = new TreeSet<>();
        users.add(user1);
        users.add(user2);
        Group group = new Group("group1", users);

        user1.addGroup(group);

        user1.removeFromGroup(group);

        assert !user1.getGroups().contains(group);
    }
}