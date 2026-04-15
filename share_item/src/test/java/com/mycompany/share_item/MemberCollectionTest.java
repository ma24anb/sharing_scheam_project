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
public class MemberCollectionTest {

    private MemberCollection memberCollection;

    public MemberCollectionTest() {
    }

    @Before
    public void setUp() {
        memberCollection = new MemberCollection();

    }

    @Test
    public void testAddMember() {
        Member m = new Member("john", "sample address", "test@gmail.com", 0);
        memberCollection.addMember(m);
        assertTrue(memberCollection.getAllMembers().contains(m));

    }

    @Test
    public void testRemoveMember() {
        Member m = new Member("john", "sample address", "test@gmail.com", 0);
        memberCollection.addMember(m);
        memberCollection.removeMember(m);
        assertFalse(memberCollection.getAllMembers().contains(m));
    }

    @Test
    public void testSearchMembers() {
        Member m1 = new Member("john", "sample address", "test@gmail.com", 0);
        Member m2 = new Member("smith", "sample address", "test@gmail.com", 0);
        memberCollection.addMember(m1);
        memberCollection.addMember(m2);
        assertTrue(memberCollection.searchMembers("john").contains(m1));
    }

    @Test
    public void testGetMemberByEmail() {
        Member m1 = new Member("john", "sample address", "test@gmail.com", 0);
        memberCollection.addMember(m1);
        assertEquals(m1, memberCollection.getMemberByEmail("test@gmail.com"));
    }

    @Test
    public void testIsEmailReserved() {
        Member m1 = new Member("john", "sample address", "test@gmail.com", 0);
        memberCollection.addMember(m1);
        assertTrue(memberCollection.isEmailReserved("test@gmail.com"));

    }

    @Test
    public void testGetAllMembers() {
        Member m1 = new Member("john", "sample address", "test@gmail.com", 0);
        Member m2 = new Member("smith", "sample address", "test@gmail.com", 0);
        memberCollection.addMember(m1);
        memberCollection.addMember(m2);
        assertArrayEquals(new Member[] { m1, m2 }, memberCollection.getAllMembers().toArray(new Member[0]));
    }

}
