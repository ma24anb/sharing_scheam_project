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

/**
 *
 * @author nohimhasitha
 */
public class ItemTest {

    @Test
    public void testSetTitle() {
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        b.setTitle("New title");
        assertEquals("New title", b.getTitle());

    }

    @Test
    public void testGetTitle() {
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        assertEquals("testTitle", b.getTitle());

    }

    @Test
    public void testGetLaguage() {
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        assertEquals("english", b.getLanguage());
    }

    @Test
    public void testSetLanguage() {
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        b.setLanguage("french");
        assertEquals("french", b.getLanguage());
    }

    @Test
    public void testLoanTo() {
        Member m = new Member("testMember", "124 street", "example@gmail.com", 0);
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        b.loanTo(m);
        assertEquals(m, b.getBorrower());
    }

    @Test
    public void testIsAvailable() {
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        assertTrue(b.isAvailable());
    }

    @Test
    public void testReturnLoan() {
        Member m = new Member("testMember", "124 street", "example@gmail.com", 0);
        Book b = new Book("testTitle", "john",
                null, "english", "123455");
        b.loanTo(m);
        b.returnLoan();
        assertTrue(b.isAvailable());
    }

    @Test
    public void testGetDonator() {
        Member m = new Member("testMember", "124 street", "example@gmail.com", 0);
        Book b = new Book("testTitle", "john",
                m, "english", "123455");
        assertEquals(m, b.getDonator());

    }

    @Test
    public void testClearBorrower() {
        Member m = new Member("testMember", "124 street", "example@gmail.com", 0);
        Book b = new Book("testTitle", "john",
                m, "english", "123455");
        b.loanTo(m);
        b.clearBorrower();
        assertTrue(b.isAvailable());
    }

    @Test
    public void testGetBorrower() {
        Member m = new Member("testMember", "124 street", "example@gmail.com", 0);
        Book b = new Book("testTitle", "john",
                m, "english", "123455");
        b.loanTo(m);
        assertEquals(m, b.getBorrower());
    }

}
