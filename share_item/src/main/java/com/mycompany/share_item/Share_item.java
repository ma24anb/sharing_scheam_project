/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.share_item;

import java.util.ArrayList;

/**
 *
 * @author ayadm
 */
public class Share_item {

    final static String[] mainMenuOptions = {"Search Items", "Add Item", "Manage members", "Save data",
        "Exit program"};
    final static String[] memberMenuOptions = {"Add member", "Update member", "Remove member"};
    final static String[] itemMenuOptions = {"Update item", "Remove item", "Lend item to member",
        "Return item to collection"};

    private static Collection itemCollection = new Collection();
    private static MemberCollection memberCollection = new MemberCollection();

    // this class manages the input validation for non choice inputs such as strings
    public static void manageItem(Item item) {
        Menu itemMenu = new Menu(itemMenuOptions, true);
        int selectedItemMenuOption = itemMenu.run();
        if (selectedItemMenuOption == 1) {
            updateItem(item);
        } else if (selectedItemMenuOption == 2) {
            removeItem(item);
        } else if (selectedItemMenuOption == 3) {
            lendItem(item);
        } else if (selectedItemMenuOption == 4) {
            returnItem();
        } else if (selectedItemMenuOption == itemMenuOptions.length + 1) {
            return;
        }

    }

    public static void searchItems() {

        String searchKeyword = InputHandler.getInput("Please enter item title");
        System.out.println(searchKeyword);
        ArrayList<Item> fetchedItems = itemCollection.searchItems(searchKeyword);
        System.out.println(fetchedItems);
        if (fetchedItems.size() == 0) {
            return;
        }
        Menu fetchedItemsMenu = new Menu(fetchedItems, true);
        int selectedItem = fetchedItemsMenu.run();
        // check if the user selects return to previous menu option
        if (selectedItem == fetchedItems.size() + 1) {
            return;
        }
        manageItem(fetchedItems.get(selectedItem - 1));

    }

    public static void addItem() {
        System.out.println("add item");
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
        // if the user selects the return to previous menu op
        if (selectedField == updateFieldOptions.size() + 1) {
            manageItem(item);
        }
        if (selectedField == 1) {
            String newTitle = InputHandler.getInput("Please enter new title");
            item.setTitle(newTitle);
        } else if (selectedField == 2) {
            String newLanguage = InputHandler.getInput(null);
            item.setLanguage(newLanguage);
        }

        if (item instanceof Book) {
            Book book = (Book) item;
            if (selectedField == 3) {
                String newAuthor = InputHandler.getInput("Please enter new Author's name");
                book.setAuthor(newAuthor);
            } else if (selectedField == 4) {
                String newISBN = InputHandler.getInput("Please enter new ISBN");
                book.setIsbn(newISBN);
            }

        } else if (item instanceof DVD) {
            DVD dvd = (DVD) item;
            if (selectedField == 3) {

            } else if (selectedField == 4) {
                String newDirector = InputHandler.getInput("Please enter new Director");
                dvd.setDirector(newDirector);
            }

        }

    }

    public static void removeItem(Item item) {
        itemCollection.removeItem(item);
    }

    public static void lendItem(Item item) {
        System.out.println("lend item");
    }

    public static void returnItem() {
        System.out.println("return item");
    }

    public static void manageMembers() {
        Menu memberMenu = new Menu(memberMenuOptions, true);
        int selectedMemberMenuOption = memberMenu.run();
        if (selectedMemberMenuOption == memberMenuOptions.length) {
            return;
        }

    }

    public static void addMember(Member member) {
        memberCollection.addMember(member);
    }

    public static void updateMember(Member member) {
    }

    public static void removeMember(Member member) {
        memberCollection.removeMember(member);
    }

    public static void saveData() {

    }

    public static void main(String[] args) {

        // Dummy members (adjust constructor as needed)
        // Member alice = new Member("Alice", "Smith", "alice@email.com", 1);
        // Member bob = new Member("Bob", "Brown", "bob@email.com", 2);
        // Member charlie = new Member("Charlie", "Johnson", "charlie@email.com", 3);
        // memberCollection.addMember(alice);
        // memberCollection.addMember(bob);
        // memberCollection.addMember(charlie);

        // // Dummy items (adjust parameters as needed for your Book/DVD constructors)
        // itemCollection.addBook("The Hobbit", "J.R.R. Tolkien", alice, "English", "1234567890");
        // itemCollection.addBook("1984", "George Orwell", bob, "English", "0987654321");
        // itemCollection.addDVD("Inception", "Christopher Nolan", charlie, "English", "11112222");
        // itemCollection.addDVD("The Matrix", "Wachowski Sisters", alice, "English", "33334444");

        Menu mainMenu = new Menu(mainMenuOptions);
        int selectedOption = mainMenu.run();
        while (selectedOption != mainMenuOptions.length - 1) {
            switch (selectedOption) {
                case 1:
                    searchItems();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    manageMembers();
                case 4:
                    saveData();

            }

            selectedOption = mainMenu.run();

        }

    }
}
