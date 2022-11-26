package Entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    void addUser() {

        User user1 = new User("mishaalk", "2222", new ArrayList<>());
        User user2 = new User("randomC", "1111", new ArrayList<>());
        User user3 = new User("sopleee", "3333", new ArrayList<>());

        Set<User> users = new TreeSet<>();
        users.add(user1);
        users.add(user2);
        Group group = new Group("group1", users);

        group.addUser(user3);

        assert group.getUsers().contains(user3);
    }
}