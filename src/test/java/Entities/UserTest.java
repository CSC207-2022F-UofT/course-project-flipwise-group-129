package Entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

class UserTest {

    @Test
    void addGroup() {
        User user1 = new User("mishaalk", "2222", new ArrayList<>());
        User user2 = new User("randomC", "1111", new ArrayList<>());

        Set<String> users = new TreeSet<>();
        users.add(user1.getUsername());
        users.add(user2.getUsername());
        Group group = new Group("group1", users);

        user1.addGroup(group.getGroupId());

        assert user1.getGroups().contains(group.getGroupId());

    }

    @Test
    void removeFromGroup() {
        User user1 = new User("mishaalk", "2222", new ArrayList<>());
        User user2 = new User("randomC", "1111", new ArrayList<>());

        Set<String> users = new TreeSet<>();
        users.add(user1.getUsername());
        users.add(user2.getUsername());
        Group group = new Group("group1", users);

        user1.addGroup(group.getGroupId());

        user1.removeFromGroup(group);

        assert !user1.getGroups().contains(group.getGroupId());
    }
}