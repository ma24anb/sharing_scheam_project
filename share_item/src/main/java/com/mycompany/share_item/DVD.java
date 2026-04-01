/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.share_item;

/**
 *
 * @author ayadm
 */
public class DVD extends Item {

    // Attributes (from UML)
    private String director;
    private int duration; // in minutes

    // Constructor
    public DVD(String title, String director, Member donatedBy, String language, int duration) {
        super(title, language, donatedBy);
        this.director = director;
        this.duration = duration;
    }

    // Getter for director
    public String getDirector() {
        return director;
    }

    // Setter for director
    public void setDirector(String director) {
        this.director = director;
    }

    // Getter for duration
    public int getDuration() {
        return duration;
    }

    // Setter for duration
    public void setDuration(int duration) {
        this.duration = duration;
    }

    // toString method
    @Override
    public String toString() {
        return "DVD{" +
                "title='" + getTitle() + '\'' +
                ", director='" + director + '\'' +
                ", language='" + getLanguage() + '\'' +
                ", duration=" + duration + " mins" +
                '}';
    }
}
