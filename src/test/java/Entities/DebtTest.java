package Entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DebtTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setDebtValue() {

        User user1 = new User("mishaalk", "2222", new ArrayList<>());
        User user2 = new User("randomC", "1111", new ArrayList<>());
        User user3 = new User("sopleee", "3333", new ArrayList<>());

        Set <User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        Group group = new Group("group1", users);

        Debt debt = new Debt()
    }
}