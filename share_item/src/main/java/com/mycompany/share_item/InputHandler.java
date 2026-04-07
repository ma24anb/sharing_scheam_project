package com.mycompany.share_item;

import java.util.Scanner;
import java.util.InputMismatchException;

public class InputHandler {

    static Scanner input = new Scanner(System.in);


    public static void promptMessage(String message) {
        String format = "-".repeat(message.length());
        System.out.printf("%n%s%n%s%n%s%n", format, message.toUpperCase(), format);

    }

    public static String getInput(String message) {
        promptMessage(message);
        String inputStr = input.nextLine();
        // never use .equals for checking whether an input is empty or not.
        while (inputStr.trim().isEmpty()) {
            promptMessage("cannot be empty! please try again.");
            promptMessage(message);

            inputStr = input.nextLine();
        }
        // Removed input.close() to prevent closing System.in
        return inputStr;
    }

    public static int getIntegerInput(String message) {
        int inputInt = 0;
        try {
            Scanner input = new Scanner(System.in);
            promptMessage(message);
            inputInt = input.nextInt();
            } catch (InputMismatchException e) {
            System.out.println("Please enter a number!!");
        }
        
        return inputInt;

    }

    public static void displayError(String err) {
        promptMessage(err);
    }

     
}
