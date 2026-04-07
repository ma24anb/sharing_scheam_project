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

    private ArrayList<Item> borrowing = new ArrayList<>();

    public Collection() {
    }

    public void addBook(String title, String author, Member donator,
            String language, String isbn) {
        borrowing.add(new Book(title, author, donator, language, isbn));
    }

    public void addDVD(String title, String author, Member donator,
            String language, String isbn) {
        borrowing.add(new DVD(title, isbn, donator, language, 0));
    }

    public ArrayList<Item> searchItems(String searchItem) {
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : borrowing) {
            if (item.getTitle().toLowerCase().contains(searchItem.toLowerCase())) {
                items.add(item);
            }
        }
        return items;
    }

    public Item getItem(String title) {
        return null;
    }

    public void removeItem(Item item) {
        borrowing.remove(item);
    }
}
