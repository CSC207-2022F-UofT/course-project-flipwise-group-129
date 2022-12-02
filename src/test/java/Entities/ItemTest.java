package Entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void setPrice() {
        Item item1 = new Item("apple");
        item1.setPrice(20);

        assert item1.getPrice() == 20;
    }

    @Test
    void setItemName() {

        Item item1 = new Item("apple");
        item1.setItemName("oranges");

        assert item1.getItemName().equals("oranges");

    }

    @Test
    void setBuyer() {
        User user1 = new User("mishaalk", "2222", new ArrayList<>());

        Item item1 = new Item("apple");
        item1.setBuyer(user1.getUsername());

        assert item1.getBuyer().equals(user1.getUsername());

    }

    @Test
    void setUsersInvolved() {

        User user1 = new User("mishaalk", "2222", new ArrayList<>());
        User user2 = new User("randomC", "1111", new ArrayList<>());

        List<String> users = new ArrayList<>();
        users.add(user1.getUsername());
        users.add(user2.getUsername());

        Item item1 = new Item("apple");
        item1.setUsersInvolved(users);

        assert item1.getUsersInvolved().equals(users);

    }

    @Test
    void addUsersInvolved() {

        User user1 = new User("mishaalk", "2222", new ArrayList<>());

        Item item1 = new Item("apple");
        item1.addUsersInvolved(user1.getUsername());

        assert item1.getUsersInvolved().contains(user1.getUsername());

    }
}