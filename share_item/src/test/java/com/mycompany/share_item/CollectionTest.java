/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.share_item;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

/**
 *
 * @author nohimhasitha
 */
public class CollectionTest {

    private Collection collection;

    @Before
    public void setUp() {
        // create a new collection before each test so it doesnt accumulate all the
        // items
        collection = new Collection();
    }

    @Test
    public void testAddBook() {
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        collection.addItem(b);
        assertTrue(collection.getItems().contains(b));
    }

    @Test
    public void testAddDVD() {
        DVD d = new DVD("testTitle", "john",
                null, "english", new String[] { "english", "french" });
        collection.addItem(d);
        assertTrue(collection.getItems().contains(d));

    }

    @Test
    public void testAddItem() {
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        collection.addItem(b);
        assertTrue(collection.getItems().contains(b));
    }

    @Test
    public void testSearchItems() {
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        collection.addItem(b);
        assertTrue(collection.searchItems("testTitle").contains(b));
    }

    @Test
    public void testGetItems() {
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        Book b1 = new Book("testTitle2", "john2",
                null, "english", "123455");
        collection.addItem(b);
        collection.addItem(b1);

        ArrayList<Item> tempArrayList = new ArrayList<>();
        tempArrayList.add(b);
        tempArrayList.add(b1);
        assertEquals(tempArrayList, collection.getItems());
    }

    @Test
    public void testGetItem() {

        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        collection.addItem(b);
        assertEquals(collection.getItem("testTitle"), (b));

    }

    @Test
    public void testRemoveItem() {
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        collection.addItem(b);
        assertTrue(collection.getItems().contains(b));
        collection.removeItem(b);
        assertFalse(collection.getItems().contains(b));

    }
}
