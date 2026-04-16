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
public class MenuTest {
    private Menu menu;

    public MenuTest() {
    }

    // please note that only important methods are tested here
    @Before
    public void setup() {
        menu = new Menu(new String[] { "test1", "test2", "test3" });

    }

    @Test
    public void testIsInValidNumRange() {
        assertTrue(menu.isInValidNumRange(2));
        assertFalse(menu.isInValidNumRange(10));
    }

    @Test
    public void testIsMenuEmpty() {
        assertFalse(menu.isMenuEmpty());
    }

}
