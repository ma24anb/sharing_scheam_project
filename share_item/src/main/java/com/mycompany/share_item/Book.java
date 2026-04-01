/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.share_item;

/**
 *
 * @author ayadm
 */
public class Book extends Item {

    // Attributes (from UML)
    private String author;
    private String isbn;

    // Constructor (matches UML exactly)
    public Book(String title, String author, Member donatedBy, String language, String isbn) {
        super(title, language, donatedBy);
        this.author = author;
        this.isbn = isbn;
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    // Setter for author
    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter for ISBN
    public String getIsbn() {
        return isbn;
    }

    // Setter for ISBN
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // toString method (as required in UML)
    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + '\'' +
                ", author='" + author + '\'' +
                ", language='" + getLanguage() + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}