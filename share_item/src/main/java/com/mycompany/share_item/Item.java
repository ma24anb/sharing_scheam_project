/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.share_item;

/**
 *
 * @author ayadm
 */
public abstract class Item {

    // Attributes
    private String title;
    private String language;
    private Member donatedBy;
    private Member onLoanTo;
    private String borrowerEmail = "";

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

    public String getBorrowerEmail() {
        return borrowerEmail;
    }

    public void setBorrowerEmail(String borrowerEmail) {
        this.borrowerEmail = borrowerEmail;
    }

    // Loan item to a member
    public void loanTo(Member borrower) {
        if (isAvailable()) {
            this.onLoanTo = borrower;
            // borrower.borrowItem(this); // update member record
            // this is not needed as the loanTo method is called from the member.lend()
            // method which already updates the member record
            // please do not uncomment the above line as it will cause an infinite loop
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

    public void clearBorrower() {
        this.onLoanTo = null;
    }

    public Member getBorrower() {
        return onLoanTo;
    }
}
