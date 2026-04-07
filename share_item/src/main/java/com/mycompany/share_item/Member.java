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
public class Member {

    private String name;
    private String address;
    private String email;
    private int donatedQty;
    private final ArrayList<Item> borrowing = new ArrayList<>();
    private final ArrayList<Item> donatedItems = new ArrayList<>();

    public Member(String name, String address, String email, int donatedQty) {

        this.name = name;
        this.address = address;
        this.donatedQty = donatedQty;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDonatedQty() {
        return donatedQty;
    }

    public ArrayList<Item> getDonatedItems() {
        return donatedItems;
    }

    public int borrowingQty() {
        return borrowing.size();
    }

    public ArrayList<Item> getLoanItems() {
        return borrowing;
    }

    public void lend(Item item) {
        // lend max of 5 items 

        int maxBorrow = Math.min(5, this.donatedQty);
        if (borrowingQty() >= maxBorrow) {
            return;
        }
        if (!item.isAvailable()) {
            return;
        }

        borrowing.add(item);
        item.loanTo(this);

    }

    public void addDonation(Item item) {
        if (item == null) {
            return;
        }
        // add item to this members donated list
        donatedItems.add(item);

        // increase quantity
        donatedQty++;

    }

    public void returnItem(Item item) {
        if (item == null) {
            return;
        }

        // Only return if this member actually has the item
        if (borrowing.contains(item)) {
            borrowing.remove(item);
            item.returnLoan();   // Clear onLoanTo inside Item
        }
    }

    @Override
    public String toString() {
        return "Member{"
                + "name='" + name + '\''
                + ", address='" + address + '\''
                + ", email='" + email + '\''
                + ", donatedQty=" + donatedQty
                + ", borrowingQty=" + borrowing.size()
                + '}';

    }

}
