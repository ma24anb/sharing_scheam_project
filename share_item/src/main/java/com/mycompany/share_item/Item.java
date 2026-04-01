/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.share_item;

/**
 *
 * @author ayadm
 */
public class Item {
    


    // Attributes
    private String title;
    private String language;
    private Member donatedBy;
    private Member onLoanTo;

    // Constructor
    public Item(String title, String language, Member donatedBy) {
        this.title = title;
        this.language = language;
        this.donatedBy = donatedBy;
        this.onLoanTo = null; // initially not on loan
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for language
    public String getLanguage() {
        return language;
    }

    // Setter for language
    public void setLanguage(String language) {
        this.language = language;
    }

    // Loan item to a member
    public void loanTo(Member borrower) {
        if (isAvailable()) {
            this.onLoanTo = borrower;
            borrower.borrowItem(this); // update member record
        } else {
            System.out.println("Item is already on loan.");
        }
    }

    // Check availability
    public boolean isAvailable() {
        return onLoanTo == null;
    }

    // Return the item
    public void returnLoan() {
        if (onLoanTo != null) {
            onLoanTo.returnItem(this); // update member record
            this.onLoanTo = null;
        }
    }

    // Get donor
    public Member getDonator() {
        return donatedBy;
    }

    // Clear donor info
    public void clearDonator() {
        this.donatedBy = null;
    }
}
