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
    private String[] audioLanguages;

    // Constructor
    public DVD(String title, String director, Member donatedBy, String language,
             String[] audioLanguages) {
        super(title, language, donatedBy);
        this.director = director;
        this.audioLanguages = audioLanguages;
    }

    // Getter for director
    public String getDirector() {
        return director;
    }

    // Setter for director
    public void setDirector(String director) {
        this.director = director;
    }

    // Getter for audioLanguages
    public String[] getAudioLanguages() {
        return audioLanguages;
    }

    // Setter for audioLanguages
    public void setAudioLanguages(String[] audioLanguages) {
        this.audioLanguages = audioLanguages;
    }

    // toString method
    @Override
    public String toString() {
        return "DVD{"
                + "title='" + getTitle() + '\''
                + ", director='" + director + '\''
                + ", language='" + getLanguage() + '\''
                + ", Audio Languages=" + audioLanguages + " mins"
                + '}';
    }
}
