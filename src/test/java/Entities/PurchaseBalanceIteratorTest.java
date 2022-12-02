package Entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseBalanceIteratorTest {

    @Test
    void hasNextEmpty() {

        List<Debt> debts = new ArrayList<>();


        PurchaseBalanceIterator<Debt> iterator = new PurchaseBalanceIterator<>(debts);

        assert !iterator.hasNext();
    }

    @Test
    void hasNextNonEmpty() {
        User user1 = new User("mishaalk", "2222", new ArrayList<>());
        User user2 = new User("randomC", "1111", new ArrayList<>());
        User user3 = new User("sopleee", "3333", new ArrayList<>());

        Set<String> users = new TreeSet<>();
        users.add(user1.getUsername());
        users.add(user2.getUsername());
        users.add(user3.getUsername());

        Group group = new Group("group1", users);

        Debt debt1 = new Debt(user1, user2, group.getGroupId());
        Debt debt2 = new Debt(user2, user1, group.getGroupId());

        List<Debt> debts = new ArrayList<>();
        debts.add(debt1);
        debts.add(debt2);

        PurchaseBalanceIterator<Debt> iterator = new PurchaseBalanceIterator<>(debts);

        assert iterator.hasNext();
    }

    @Test
    void nextEmpty() {

        List<Debt> debts = new ArrayList<>();


        PurchaseBalanceIterator<Debt> iterator = new PurchaseBalanceIterator<>(debts);

        assert iterator.current == null;
    }

    @Test
    void nextNonEmpty() {

        User user1 = new User("mishaalk", "2222", new ArrayList<>());
        User user2 = new User("randomC", "1111", new ArrayList<>());
        User user3 = new User("sopleee", "3333", new ArrayList<>());

        Set<String> users = new TreeSet<>();
        users.add(user1.getUsername());
        users.add(user2.getUsername());
        users.add(user3.getUsername());

        Group group = new Group("group1", users);

        Debt debt1 = new Debt(user1, user2, group.getGroupId());
        Debt debt2 = new Debt(user2, user1, group.getGroupId());

        List<Debt> debts = new ArrayList<>();
        debts.add(debt1);
        debts.add(debt2);

        PurchaseBalanceIterator<Debt> iterator = new PurchaseBalanceIterator<>(debts);

        assert iterator.current != null;
    }
}