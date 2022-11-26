package Entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;
class DebtTest {

    @Test
    void setDebtValue() {

        User user1 = new User("mishaalk", "2222", new ArrayList<>());
        User user2 = new User("randomC", "1111", new ArrayList<>());
        User user3 = new User("sopleee", "3333", new ArrayList<>());

        Set<User> users = new TreeSet<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        Group group = new Group("group1", users);

        Debt debt1 = new Debt(user1, user2, group.getGroupId());
        Debt debt2 = new Debt(user2, user1, group.getGroupId());
        Debt debt3 = new Debt(user3, user1, group.getGroupId());

        debt1.setDebtValue(20.0);

        group.getPurchaseBalance().addDebtPair(debt1);
        group.getPurchaseBalance().addDebtPair(debt2);
        group.getPurchaseBalance().addDebtPair(debt3);

        assert debt1.getDebtValue().equals(20.0);
        assert debt2.getDebtValue().equals(0.0);
        assert debt3.getDebtValue().equals(0.0);
    }
}