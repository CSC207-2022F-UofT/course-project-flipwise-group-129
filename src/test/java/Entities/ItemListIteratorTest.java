package Entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemListIteratorTest {

    @Test
    void hasNextNonEmpty() {

        Item item1 = new Item("apple");
        Item item2 = new Item("orange");
        Item item3 = new Item("pomegranate");

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        ItemListIterator<Item> itemIterator = new ItemListIterator<>(items);

        assert itemIterator.hasNext();
    }

    @Test
    void hasNextEmpty() {

        List<Item> items = new ArrayList<>();

        ItemListIterator<Item> itemIterator = new ItemListIterator<>(items);

        assert !itemIterator.hasNext();
    }

    @Test
    void nextNonEmpty() {
        Item item1 = new Item("apple");
        Item item2 = new Item("orange");
        Item item3 = new Item("pomegranate");

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        ItemListIterator<Item> itemIterator = new ItemListIterator<>(items);

        boolean nextAvailable = itemIterator.next() != null;
        assert nextAvailable;
    }

    @Test
    void nextEmpty(){
        List<Item> items = new ArrayList<>();

        ItemListIterator<Item> itemIterator = new ItemListIterator<>(items);

        boolean nextAvailable = itemIterator.current == null;
        assert nextAvailable;
    }
}