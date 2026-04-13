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
public class DVDTest {

    public DVDTest() {
    }

    @Test
    public void testConstructor() {
    }

    @Test
    public void testGetDirector() {
        Member donor = new Member("mark", "123456 Street", "john@gmail.com", 0);

        String[] languages = { "English", "French", "Spanish" };

        DVD dvd = new DVD(
                "Inception",
                "Christopher Nolan",
                donor,
                "English",
                languages);
        assertEquals("Christopher Nolan", dvd.getDirector());

    }

    @Test
    public void testSetDirector() {
        Member donor = new Member("mark", "123456 Street", "john@gmail.com", 0);

        String[] languages = { "English", "French", "Spanish" };

        DVD dvd = new DVD(
                "Inception",
                "Christopher Nolan",
                donor,
                "English",
                languages);
        dvd.setDirector("testDirector");
        assertEquals("testDirector", dvd.getDirector());

    }

    @Test
    public void testGetAudioLanguages() {
        Member donor = new Member("mark", "123456 Street", "john@gmail.com", 0);

        String[] languages = { "English", "French", "Spanish" };

        DVD dvd = new DVD(
                "Inception",
                "Christopher Nolan",
                donor,
                "English",
                languages);
        dvd.setDirector("testDirector");
        assertArrayEquals(languages, dvd.getAudioLanguages());
    }

    @Test
    public void testSetAudioLanguages() {
        Member donor = new Member("mark", "123456 Street", "john@gmail.com", 0);

        String[] languages = { "English", "French", "Spanish" };
        String[] newLanguages = { "English", "French", "Spanish", "Japanese" };

        DVD dvd = new DVD(
                "Inception",
                "Christopher Nolan",
                donor,
                "English",
                languages);

        dvd.setAudioLanguages(newLanguages);
        assertArrayEquals(newLanguages, dvd.getAudioLanguages());
    }

    @Test
    public void testToString() {
        // test string representation contains correct values
    }

    // ⭐ Optional (for higher marks)

    @Test
    public void testEmptyAudioLanguages() {
        // test behaviour when array is empty
    }

    @Test
    public void testNullAudioLanguages() {
        // test behaviour when array is null
    }
}
