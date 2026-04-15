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

    public void displayItemSpecifics() {
        InputHandler.displayMessage(String.format("Director: %s", this.getDirector()));
        InputHandler.displayMessage("Audio Languages", this.getAudioLanguages());
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

    public boolean updateSubClassAttributes(int selectedField) {
        if (selectedField == 3) {
            String newAudioLanguages = InputHandler
                    .getInput("Please enter new audio languages (seperated by commas)");
            if (newAudioLanguages == null)
                return false;
            this.setAudioLanguages(newAudioLanguages.split(","));
        } else if (selectedField == 4) {
            String newDirector = InputHandler.getInput("Please enter new Director");
            if (newDirector == null)
                return false;
            this.setDirector(newDirector);
        }
        return false;
    }
}
