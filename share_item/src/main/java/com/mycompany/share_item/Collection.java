/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.share_item;

import java.util.*;

/**
 *
 * @author ayadm
 */
public class Collection {

    private ArrayList<Item> items = new ArrayList<>();

    public Collection() {
    }

    public void addBook(String title, String author, Member donator,
            String language, String isbn) {
        Book b = new Book(title, author, donator, language, isbn);
        items.add(b);
        donator.addDonation(b);
    }

    public void addDVD(String title, String director, Member donator,
            String language, String[] audioLanguages) {
        DVD d = new DVD(title, director, donator, language, audioLanguages);
        items.add(d);

        // update member donation record
        donator.addDonation(d);

    }

    public ArrayList<Item> searchItems(String searchTerm) {
        ArrayList<Item> results = new ArrayList<>();

        for (Item item : items) {
            if (item.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(item);
            }
        }

        return results;
    }

    public Item getItem(String title) {
        for (Item item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null;
    }

    public void removeItem(Item item) {
        if (item == null) {
            return;
        }

        items.remove(item);
    }

}
