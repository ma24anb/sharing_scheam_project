/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.share_item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nohimhasitha
 */
public class BookTest {

    @Test
    public void testSetAuthor() {
        Member donor = new Member("John", "Address", "john@email.com", 0);
        Book b = new Book("Java", "Old Author", donor, "English", "111");

        b.setAuthor("mark");
        assertEquals("mark", b.getAuthor());

    }

    @Test
    public void testGetAuthor() {
        Member donor = new Member("John", "Address", "john@email.com", 0);
        Book b = new Book("Java", "Old Author", donor, "English", "111");

        assertEquals("Old Author", b.getAuthor());
    }

    @Test
    public void testGetIsbn() {
        Member donor = new Member("John", "Address", "john@email.com", 0);
        Book b = new Book("Java", "Old Author", donor, "English", "111");

        assertEquals("111", b.getIsbn());

    }

    @Test
    public void testSetIsbn() {
        Member donor = new Member("John", "Address", "john@email.com", 0);
        Book b = new Book("Java", "Old Author", donor, "English", "111");
        b.setIsbn("222");
        assertEquals("222", b.getIsbn());

    }

}
