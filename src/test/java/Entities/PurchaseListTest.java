package Entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseListTest {

    @Test
    void setItems() {

        Item item1 = new Item("apple");
        Item item2 = new Item("orange");
        Item item3 = new Item("pomegranate");

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        PurchaseList itemList = new PurchaseList();

        itemList.setItems(items);

        assert itemList.getItems().equals(items);
    }

    @Test
    void addItems() {

        Item item1 = new Item("apple");

        PurchaseList itemList = new PurchaseList();

        itemList.addItems(item1);

        assert itemList.getItems().contains(item1);
    }

}