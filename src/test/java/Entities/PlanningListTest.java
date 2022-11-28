package Entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanningListTest {

    @Test
    void setItems() {

        Item item1 = new Item("apple");
        Item item2 = new Item("orange");
        Item item3 = new Item("pomegranate");

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        PlanningList itemList = new PlanningList();

        itemList.setItems(items);

        assert itemList.getItems().equals(items);
    }

    @Test
    void addItems() {

        Item item1 = new Item("apple");

        PlanningList itemList = new PlanningList();

        itemList.addItems(item1);

        assert itemList.getItems().contains(item1);
    }

    @Test
    void removeItem(){
        Item item1 = new Item("apple");

        PlanningList itemList = new PlanningList();

        itemList.addItems(item1);
        itemList.removeFromList(item1);

        assert !itemList.getItems().contains(item1);
    }
}