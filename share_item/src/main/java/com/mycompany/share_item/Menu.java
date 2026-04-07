/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.share_item;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nohimhasitha
 */
public class Menu {

    private String[] menuOptionsSet; // where the options are stored
    private int[] validNumRange; // create the number range for each options set
    private boolean zeroBasedIndexing = false;
    Scanner input = new Scanner(System.in);

    public Menu(String[] menuOptionsSet) {
        this.menuOptionsSet = menuOptionsSet;
        this.validNumRange = new int[menuOptionsSet.length];

        for (int range = 0; range < validNumRange.length; range++) { // this for loop automatically defines the range when an object is initialised.
            validNumRange[range] = range + 1;
        }
    }

    public Menu(String[] menuOptionsSet, boolean returnOption) {
        this.menuOptionsSet = new String[menuOptionsSet.length + 1];
        for (int i = 0; i < menuOptionsSet.length; i++) {
            this.menuOptionsSet[i] = menuOptionsSet[i];
        }
        this.menuOptionsSet[this.menuOptionsSet.length - 1] = "Return to previous menu";
        this.validNumRange = new int[this.menuOptionsSet.length];
        for (int range = 0; range < validNumRange.length; range++) { // this for loop automatically defines the range when an object is initialised.
            validNumRange[range] = range + 1;
        }
    }

    public Menu(ArrayList<Item> items, boolean returnOption) {

        // first extract the items title from the array list
        String[] menuOptionsSet = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            menuOptionsSet[i] = items.get(i).getTitle().toUpperCase();

        }
        this.menuOptionsSet = new String[menuOptionsSet.length + 1];
        for (int i = 0; i < menuOptionsSet.length; i++) {
            this.menuOptionsSet[i] = menuOptionsSet[i];
        }
        this.menuOptionsSet[this.menuOptionsSet.length - 1] = "Return to previous menu";
        this.validNumRange = new int[this.menuOptionsSet.length];

        // the menu will be 1 based and the correct item has to be calculated by substracting 1
        for (int range = 0; range < validNumRange.length; range++) { // this for loop automatically defines the range when an object is initialised.
            validNumRange[range] = range + 1;
        }
    }

    public boolean isEmpty() {
        return menuOptionsSet.length == 0;
    }

    // a method to check if a number is in the allowed range
    // this is used throughout the program to check if users input is valid
    public boolean isInValidNumRange(int option) {
        boolean isInRange = false;
        for (int num : this.validNumRange) {

            if (option == num) {
                isInRange = true;
            }
        }
        return isInRange;
    }

    public void displayMenu() {

        String largestStr = "Please select an Option: ";
        String outputStr = new String();

        for (int option = 0; option < menuOptionsSet.length; option++) {

            outputStr += String.format("%s) %s %n", option + 1, menuOptionsSet[option]);
            if (menuOptionsSet[option].length() > largestStr.length()) {
                largestStr = menuOptionsSet[option];
            }

        }

        System.out.println("");
        System.out.printf("%s %s %s%n", "=".repeat(largestStr.length() / 2), "MENU", "=".repeat(largestStr.length() / 2));
        System.out.print(outputStr);
        System.out.printf("%s%s%s%n", "=".repeat(largestStr.length() / 2), "======", "=".repeat(largestStr.length() / 2));

        if (zeroBasedIndexing) {
            System.out.println("0: Select to go to Main menu.");
        }
        System.out.print("Please Select an option: ");
    }

    public boolean validateInputRange(int option) {
        boolean isInRange = false;

        if (option < 0) {
            return false;
        }

        for (int number : validNumRange) {

            if (number == option) {

                isInRange = true;
                break;
            }

        }

        return isInRange;
    }

    public void displayErrorMessage() {
        String errorStr = "The Selected option is Incorrect! Please try again.";
        String formatStr = "#".repeat(errorStr.length());
        System.out.printf("%n%s%n%s%n%s%n%n", formatStr, errorStr, formatStr);
    }

    public void displayErrorMessage(String err) {
        String errorStr = err;
        String formatStr = "#".repeat(errorStr.length());
        System.out.printf("%n%s%n%s%n%s%n%n", formatStr, errorStr, formatStr);
    }

    public int run() {

        int num = -1; // a variable to hold the selected option
        boolean isValid = false;
        do {
            displayMenu();
            // this is critical as it ensures only numbers are entered
            if (input.hasNextInt()) {
                num = input.nextInt();
                input.nextLine();
                isValid = validateInputRange(num);

                if (!isValid) {
                    displayErrorMessage();
                }
            } else {
                displayErrorMessage("Please enter a number!");
                input.next();       // Discard invalid token
            }
        } while (!isValid);

        return num; // return the validated num

    }

    public boolean isMenuEmpty() {
        boolean isEmpty = false;
        if (menuOptionsSet.length == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }

}
