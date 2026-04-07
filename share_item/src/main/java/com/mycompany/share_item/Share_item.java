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

    final static String[] mainMenuOptions = {"Search Items", "Add Item", "Manage members", "Save data", "Exit program"};
    final static String[] memberMenuOptions = {"Add member", "Update member", "Remove member"};
    final static String[] itemMenuOptions = {"Update item", "Remove item", "Lend item to member", "Return item to collection"};

    private static Collection itemCollection = new Collection();

    // this class manages the input validation for non choice inputs such as strings
    public static void addMember(Member member) {

    }

    public static void updateMember(Member member) {
    }

    public static void removeMember(Member member) {
    }

    public static void manageItem(Item item) {
        Menu itemMenu = new Menu(itemMenuOptions);
        int selectedItemMenuOption = itemMenu.run();
        if (selectedItemMenuOption == 1) {

        }
        else if(selectedItemMenuOption == 2){}
        else if(selectedItemMenuOption == 3){}
        else if(selectedItemMenuOption == 4){}

    }

    public static void searchItems() {

        String searchKeyword = InputHandler.getInput("Please enter item title");
        ArrayList<Item> fetchedItems = itemCollection.searchItems(searchKeyword);
        if(fetchedItems.size() == 0) return;
        Menu fetchedItemsMenu = new Menu(fetchedItems, true);
        int selectedItem = fetchedItemsMenu.run();
        manageItem(fetchedItems.get(selectedItem - 1));

    }

    public static void addItem() {

    }

    public static void updateItem() {
    }

    public static void removeItem(Item item) {
        itemCollection.removeItem(item);
    }

    public static void lendItem() {
    }

    public static void returnItem() {
    }

    public static void manageItems() {
        Menu itemMenu = new Menu(itemMenuOptions, true);
        int selectedItemsMenuOption = itemMenu.run();
        if (selectedItemsMenuOption == itemMenuOptions.length) {
            return;
        } else if (selectedItemsMenuOption == 1) {
            searchItems();
        }
    }

    public static void manageMembers() {
        Menu memberMenu = new Menu(memberMenuOptions, true);
        int selectedMemberMenuOption = memberMenu.run();
        if (selectedMemberMenuOption == memberMenuOptions.length) {
            return;
        }

    }

    public static void saveData() {

    }

    public static void main(String[] args) {

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
