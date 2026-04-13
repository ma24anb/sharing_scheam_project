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

import java.util.ArrayList;

/**
 *
 * @author nohimhasitha
 */
public class MemberTest {

    @Test
    public void testGetName() {
        Member m = new Member("testMember", "124 street", "test@example.com", 0);
        assertEquals("testMember", m.getName());
    }

    @Test
    public void testGetAddress() {
        Member m = new Member("testMember", "124 street", "test@example.com", 0);
        assertEquals("124 street", m.getAddress());
    }

    @Test
    public void testGetEmail() {
        Member m = new Member("testMember", "124 street", "test@example.com", 0);
        assertEquals("test@example.com", m.getEmail());
    }

    @Test
    public void testSetName() {
        Member m = new Member("testMember", "124 street", "test@example.com", 0);
        m.setName("newName");
        assertEquals("newName", m.getName());
    }

    @Test
    public void testSetAddress() {
        Member m = new Member("testMember", "124 street", "test@example.com", 0);
        m.setAddress("newAddress");
        assertEquals("newAddress", m.getAddress());
    }

    @Test
    public void testSetEmail() {
        Member m = new Member("testMember", "124 street", "test@example.com", 0);
        m.setEmail("newEmail@example.com");
        assertEquals("newEmail@example.com", m.getEmail());
    }

    @Test
    public void testGetDonatedQty() {
        Member m = new Member("testMember", "124 street", "test@example.com", 0);
        assertEquals(0, m.getDonatedQty());
    }

    @Test
    public void testGetDonatedItems() {
        Member m = new Member("testMember", "124 street", "test@example.com", 0);
        assertTrue(m.getDonatedItems().isEmpty());
    }

    @Test
    public void testBorrowingQty() {
        Member m = new Member("testMember", "124 street", "test@example.com", 0);
        assertEquals(0, m.borrowingQty());
    }

    @Test
    public void testGetLoanItems() {
        Member m = new Member("testMember", "124 street", "test@example.com", 0);
        assertTrue(m.getLoanItems().isEmpty());
    }

    @Test
    public void testLend() {
        Member m = new Member("testMember", "124 street", "test@example.com", 1);
        Item item = new Book("testTitle", "john",
                m, "english", "123455");
        m.lend(item);
        assertEquals(m.getLoanItems().get(0), item);
    }

    @Test
    public void testAddDonation() {
        Member m = new Member("testMember", "124 street", "test@example.com", 1);
        Item item = new Book("testTitle", "john",
                m, "english", "123455");
        m.addDonation(item);
        assertEquals(m.getDonatedItems().get(0), item);
    }

    @Test
    public void testReturnItem() {
        Member m = new Member("testMember", "124 street", "test@example.com", 1);
        Item item = new Book("testTitle", "john",
                m, "english", "123455");
        m.lend(item);
        m.returnItem(item);
        assertFalse(m.getLoanItems().contains(item));
    }

    @Test
    public void testRemoveItemReferences() {
        Member m = new Member("testMember", "124 street", "test@example.com", 1);
        Item item = new Book("testTitle", "john",
                m, "english", "123455");
        m.addDonation(item);
        m.removeItemReferences(item);
        assertFalse(m.getDonatedItems().contains(item));
    }
}
