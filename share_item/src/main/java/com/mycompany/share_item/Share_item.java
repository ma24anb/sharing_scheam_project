/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.share_item;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ayadm
 */
public class Share_item {

    final static String[] mainMenuOptions = { "Search Items", "Add Item", "Manage members", "Save data",
            "Exit program" };

    final static String[] mainMemberMenuOptions = { "Search Members", "Add Member" };
    final static String[] subMemberMenuOptions = { "Update member", "Remove member" };

    private static Collection itemCollection = new Collection();
    private static MemberCollection memberCollection = new MemberCollection();

    public static int getMemberChoice() {
        String[] allExistingMemberNames = new String[memberCollection.getAllMembers().size()];
        for (int i = 0; i < memberCollection.getAllMembers().size(); i++) {
            allExistingMemberNames[i] = memberCollection.getAllMembers().get(i).getName();
        }
        if (allExistingMemberNames.length == 0) {
            InputHandler.displayError("Sorry There isn't any members!!");
        }
        Menu existingMembersMenu = new Menu(allExistingMemberNames, true);
        existingMembersMenu.setMenuName("Choose member");
        int selectedMemberOption = existingMembersMenu.run();
        return selectedMemberOption;
    }

    // this class manages the input validation for non choice inputs such as strings
    public static void manageItem(Item item) {

        InputHandler.promptMessage("====== Item details ======");
        InputHandler.displayMessage(String.format("Title: %s", item.getTitle()));
        InputHandler.displayMessage(String.format("Language: %s", item.getLanguage()));
        if (item instanceof Book) {
            Book book = (Book) item;
            InputHandler.displayMessage(String.format("Author: %s", book.getAuthor()));
            InputHandler.displayMessage(String.format("ISBN: %s", book.getIsbn()));
        } else if (item instanceof DVD) {
            DVD dvd = (DVD) item;
            InputHandler.displayMessage(String.format("Director: %s", dvd.getDirector()));
            InputHandler.displayMessage("Audio Languages", dvd.getAudioLanguages());
        }
        if (item.isAvailable()) {
            InputHandler.displayMessage("Status: Available");
        } else {
            InputHandler.displayMessage("Status: onLoan");
            InputHandler.displayMessage(String.format("Borrowed by: %s",
                    item.getBorrower() == null ? "N/A" : item.getBorrower().getName()));

        }
        InputHandler.displayMessage(
                String.format("Donated by: %s", item.getDonator() == null ? "N/A" : item.getDonator().getName()));

        ArrayList<String> itemMenuOptions = new ArrayList<>();
        itemMenuOptions.add("Update Item");
        itemMenuOptions.add("Remove Item");
        itemMenuOptions.add("Lend Item");
        if (!item.isAvailable()) {
            itemMenuOptions.add("Return item");
        }
        Menu itemMenu = new Menu(itemMenuOptions.toArray(new String[0]), true);
        itemMenu.setMenuName("item menu");
        int selectedItemMenuOption = itemMenu.run();
        if (selectedItemMenuOption == 1) {
            updateItem(item);
        } else if (selectedItemMenuOption == 2) {
            removeItem(item);
        } else if (selectedItemMenuOption == 3) {
            lendItem(item);
        } else if (!item.isAvailable() && selectedItemMenuOption == 4) {
            returnItem(item);
        } else if (selectedItemMenuOption == itemMenuOptions.size() + 1) {
            return;
        }

    }

    public static void searchItems() {

        String searchKeyword = InputHandler.getInput("Please enter item title");
        // System.out.println(searchKeyword);
        ArrayList<Item> fetchedItems = itemCollection.searchItems(searchKeyword);
        // System.out.println(fetchedItems);
        if (fetchedItems.size() == 0) {
            InputHandler.promptMessage("Sorry there isn't any results.");
            return;
        }
        Menu fetchedItemsMenu = new Menu(fetchedItems, true);
        fetchedItemsMenu.setMenuName("items results");
        int selectedItem = fetchedItemsMenu.run();
        // check if the user selects return to previous menu option
        if (selectedItem == fetchedItems.size() + 1) {
            return;
        }
        manageItem(fetchedItems.get(selectedItem - 1));

    }

    public static void addItem() {

        InputHandler.promptMessage("====== Please choose the member =======");
        int selectedMemberOption = getMemberChoice();
        if (selectedMemberOption == memberCollection.getAllMembers().size() + 1) {
            return;
        }
        Member selectedMember = memberCollection.getAllMembers().get(selectedMemberOption - 1);

        InputHandler.promptMessage("Please select item type");

        String[] itemTypes = new String[] { "Book", "DVD" };
        Menu itemTypesMenu = new Menu(itemTypes, true);
        itemTypesMenu.setMenuName("Item type menu");
        int selectedType = itemTypesMenu.run();

        String[] bookInfoFields = new String[] { "Title", "Language", "Author", "ISBN" };
        String[] dvdInfoFields = new String[] { "Title", "Case Language", "Audio Languages(seprated by commas)",
                "Director" };
        ArrayList<String> userInputCollection = new ArrayList<>();

        // if the item is book
        InputHandler.promptMessage("please enter < to go back to Main menu");
        if (selectedType == 1) {
            for (String info : bookInfoFields) {
                String userInput = InputHandler.getInput(String.format("Please enter: %s", info));
                if (userInput == null)
                    return;
                userInputCollection.add(userInput);

            }
            String title = userInputCollection.get(0);
            String language = userInputCollection.get(1);
            String author = userInputCollection.get(2);
            String ISBN = userInputCollection.get(3);

            itemCollection.addBook(title, author, selectedMember, language, ISBN);

        } else if (selectedType == 2) {
            for (String info : dvdInfoFields) {
                String userInput = InputHandler.getInput(String.format("Please enter: %s", info));
                if (userInput == null)
                    return;
                userInputCollection.add(userInput);

            }
            String title = userInputCollection.get(0);
            String caseLanguage = userInputCollection.get(1);
            String audioLanguages = userInputCollection.get(2);
            String director = userInputCollection.get(3);

            itemCollection.addDVD(title, director, selectedMember, caseLanguage, audioLanguages.split(","));

        } else if (selectedType == itemTypes.length + 1) {
            addItem();
            return;
        }
        InputHandler.promptMessage("Item added successfully.");

    }

    public static void updateItem(Item item) {
        ArrayList<String> updateFieldOptions = new ArrayList<>();
        updateFieldOptions.add("Title");
        updateFieldOptions.add("Language");
        if (item instanceof Book) {
            updateFieldOptions.add("Author");
            updateFieldOptions.add("ISBN");
        } else if (item instanceof DVD) {
            updateFieldOptions.add("Audio Languages");
            updateFieldOptions.add("Director");
        }
        Menu updateFieldOptionsMenu = new Menu(updateFieldOptions.toArray(new String[0]), true);
        int selectedField = updateFieldOptionsMenu.run();

        InputHandler.promptMessage("please enter < to go back to main menu.");
        // if the user selects the return to previous menu
        if (selectedField == updateFieldOptions.size() + 1) {
            manageItem(item);
        }
        if (selectedField == 1) {
            String newTitle = InputHandler.getInput("Please enter new title");
            if (newTitle == null)
                return;
            item.setTitle(newTitle);
        } else if (selectedField == 2) {
            String newLanguage = InputHandler.getInput("Please enter new language");
            if (newLanguage == null)
                return;
            item.setLanguage(newLanguage);
        }

        if (item instanceof Book) {
            Book book = (Book) item;
            if (selectedField == 3) {
                String newAuthor = InputHandler.getInput("Please enter new Author's name");
                if (newAuthor == null)
                    return;
                book.setAuthor(newAuthor);
            } else if (selectedField == 4) {
                String newISBN = InputHandler.getInput("Please enter new ISBN");
                if (newISBN == null)
                    return;
                book.setIsbn(newISBN);
            }

        } else if (item instanceof DVD) {
            DVD dvd = (DVD) item;
            if (selectedField == 3) {
                String newAudioLanguages = InputHandler
                        .getInput("Please enter new audio languages (seperated by commas)");
                if (newAudioLanguages == null)
                    return;
                dvd.setAudioLanguages(newAudioLanguages.split(","));
            } else if (selectedField == 4) {
                String newDirector = InputHandler.getInput("Please enter new Director");
                if (newDirector == null)
                    return;
                dvd.setDirector(newDirector);
            }

        }

        InputHandler.promptMessage("Item updated successfully.");

    }

    public static void removeItem(Item item) {
        // if the item is on loan currently
        if (!item.isAvailable()) {
            // retrieve the member who is borrowing the item
            Member borrowingMember = item.getBorrower();
            borrowingMember.removeItemReferences(item);
        }
        itemCollection.removeItem(item);
        InputHandler.promptMessage("item removed successfully.");

    }

    public static void lendItem(Item item) {

        // option to lend the item to a member among all members
        // first check if the item is actually available and not on loan
        if (item.isAvailable()) {
            int selectedMemberOption = getMemberChoice();
            if (selectedMemberOption == memberCollection.getAllMembers().size() + 1) {
                return;
            }
            Member selectedMember = memberCollection.getAllMembers().get(selectedMemberOption - 1);
            int maxBorrow = Math.min(5, selectedMember.getDonatedQty());
            if (selectedMember.borrowingQty() >= maxBorrow) {
                InputHandler.promptMessage("Sorry the maximum borrowing limit reached already. ");
                // return to manage item menu
                manageItem(item);
            } else {
                selectedMember.lend(item);
                InputHandler.promptMessage("lend item successfull.");
            }

        } else {
            InputHandler.promptMessage("Sorry the item is Already on Loan");
        }

    }

    public static void returnItem(Item item) {
        item.returnLoan();
        InputHandler.promptMessage("return item successfull.");

    }

    public static void manageMembers() {
        while (true) {
            Menu memberMenu = new Menu(mainMemberMenuOptions, true);
            memberMenu.setMenuName("main Member menu");
            int selectedMemberMenuOption = memberMenu.run();
            if (selectedMemberMenuOption == mainMemberMenuOptions.length + 1) {
                return;
            }
            if (selectedMemberMenuOption == 1) {
                searchMembers();
            } else if (selectedMemberMenuOption == 2) {
                addNewMember();
            }
        }
    }

    public static void manageMember(Member member) {

        InputHandler.promptMessage("====== Member details ======");
        InputHandler.displayMessage(String.format("Name: %s", member.getName()));
        InputHandler.displayMessage(String.format("Postal Address: %s", member.getAddress()));
        InputHandler.displayMessage(String.format("Email Address: %s", member.getEmail()));
        InputHandler.displayMessage(String.format("No of Items Donated: %s", member.getDonatedQty()));
        InputHandler.displayMessage(String.format("Borrowing quantity: %s", member.borrowingQty()));
        if (member.borrowingQty() != 0) {
            String[] borrowingTitles = new String[member.borrowingQty()];
            for (int i = 0; i < member.borrowingQty(); i++) {
                borrowingTitles[i] = member.getLoanItems().get(i).getTitle();
            }

            InputHandler.displayMessage("Borrowing Titles: ", borrowingTitles);
        }

        Menu memberMenu = new Menu(subMemberMenuOptions, true);
        memberMenu.setMenuName("member modification menu");
        int selectedSubMemberMenuOption = memberMenu.run();

        if (selectedSubMemberMenuOption == 1) {
            updateMember(member);
        } else if (selectedSubMemberMenuOption == 2) {
            removeMember(member);
        } else if (selectedSubMemberMenuOption == subMemberMenuOptions.length + 1) {
            return;
        }
    }

    public static void searchMembers() {

        String searchKeyword = InputHandler.getInput("Please enter member's name");
        // System.out.println(searchKeyword);
        ArrayList<Member> fetchedMembers = memberCollection.searchMembers(searchKeyword);
        // System.out.println(fetchedMembers);
        if (fetchedMembers.size() == 0) {
            InputHandler.promptMessage("Ooops! we couldn't find any members!");
            return;
        }
        String[] fetchedMemberNames = new String[fetchedMembers.size()];
        for (int i = 0; i < fetchedMembers.size(); i++) {
            fetchedMemberNames[i] = fetchedMembers.get(i).getName();
        }
        Menu fetchedMemberMenu = new Menu(fetchedMemberNames, true);
        int selectedItem = fetchedMemberMenu.run();
        // check if the user selects return to previous menu option
        if (selectedItem == fetchedMembers.size() + 1) {
            // if previous menu is selected then go back to the manage members menu
            // manage members ==> search members ==> select member
            // select member <== search member <== manage member
            manageMembers();
            return;
        }
        manageMember(fetchedMembers.get(selectedItem - 1));

    }

    public static void addNewMember() {
        InputHandler.promptMessage("please enter < to return to previous menu.");
        String name = InputHandler.getInput("Enter Name");
        if (name == null)
            return;
        String address = InputHandler.getInput("Enter Address");
        if (address == null)
            return;

        String email = InputHandler.getInput("Enter Email");
        if (email == null)
            return;

        // System.out.println(memberCollection.isEmailReserved(email));
        while (memberCollection.isEmailReserved(email)) {
            InputHandler.promptMessage("Sorry the email is Already in use.");
            email = InputHandler.getInput("Enter Email");
        }
        memberCollection.addMember(new Member(name, address, email, 0));
        InputHandler.promptMessage("member added successfully.");

    }

    public static void updateMember(Member member) {
        String[] memberUpdateOptions = new String[] { "Name", "Email", "Address" };
        Menu memberUpdateOptionsMenu = new Menu(memberUpdateOptions);
        int selectedUpdateField = memberUpdateOptionsMenu.run();
        if (selectedUpdateField == memberUpdateOptions.length + 1) {
            return;
        }
        if (selectedUpdateField == 1) {
            String newName = InputHandler.getInput("Please enter Name");
            if (newName == null)
                return;
            member.setName(newName);
        } else if (selectedUpdateField == 2) {
            String newEmail = InputHandler.getInput("Please enter Email");
            if (newEmail == null)
                return;
            while (memberCollection.isEmailReserved(newEmail)) {
                InputHandler.promptMessage("Sorry the email is Already in use.");
                newEmail = InputHandler.getInput("Please enter Email");
                if (newEmail == null)
                    return;
            }
            member.setEmail(newEmail);

        } else if (selectedUpdateField == 3) {
            String newAddress = InputHandler.getInput("Please enter Address");
            if (newAddress == null)
                return;
            member.setAddress(newAddress);
        }
        InputHandler.promptMessage("member updated successfully.");
    }

    public static void removeMember(Member member) {
        // check if each item has any references to this member
        ArrayList<Item> currentMemberDonatedItems = member.getDonatedItems();
        ArrayList<Item> currentMemberBorrowedItems = member.getLoanItems();

        // for each item remove the member's reference
        for (Item item : currentMemberDonatedItems) {
            item.clearDonator();
        }

        for (Item item : currentMemberBorrowedItems) {
            item.clearBorrower();
        }

        memberCollection.removeMember(member);
        InputHandler.promptMessage("Member removed successfully.");
    }

    public static void saveData() {
        try {
            String filePath = InputHandler.getInput("Please enter the file path (can be the same or different)");
            FileHandler.save(filePath, memberCollection, itemCollection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            FileHandler.load("input-1.dat", memberCollection, itemCollection);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Menu mainMenu = new Menu(mainMenuOptions);
        mainMenu.setMenuName("main menu");
        int selectedOption = mainMenu.run();
        // this is to ensure whenever user select the exit ==> the last option is
        // calculated by the total length of the option + 1
        while (true) {
            switch (selectedOption) {
                case 1:
                    searchItems();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    manageMembers();
                    break;
                case 4:
                    saveData();
                    break;
                case 5:
                    InputHandler.promptMessage("Program terminated");
                    return;

            }

            selectedOption = mainMenu.run();

        }

    }
}
