package Entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseBalanceTest {

    @Test
    void addDebtPair() {

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

        List<Debt> debts = new ArrayList<>();
        debts.add(debt1);
        debts.add(debt2);

        PurchaseBalance purchaseBalance = new PurchaseBalance(debts);
        purchaseBalance.addDebtPair(debt3);

        assert purchaseBalance.getAllDebts().contains(debt3);

    }

    @Test
    void removeDebtPair() {

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

        List<Debt> debts = new ArrayList<>();
        debts.add(debt1);
        debts.add(debt2);
        debts.add(debt3);

        PurchaseBalance purchaseBalance = new PurchaseBalance(debts);
        purchaseBalance.removeDebtPair(debt3);

        assert !purchaseBalance.getAllDebts().contains(debt3);

    }
}