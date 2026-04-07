/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.share_item;

/**
 *
 * @author ayadm
 */
public class Share_item {

    final static String[] mainMenuOptions = {"Manage Items", "Manage members", "Save data", "Exit program"};
    final static String[] memberMenuOptions = {"Add member", "Update member", "Remove member"};
    final static String[] itemMenuOptions = {"Search items", "Add item", "Update item", "Remove item", "Lend item to member", "Return item to collection"};

    public static void addMember() {
    }

    public static void updateMember() {
    }

    public static void removeMember() {
    }

    public static void searchItems() {
    }

    public static void addItem() {
    }

    public static void updateItem() {
    }

    public static void removeItem() {
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
        while (selectedOption != 4) {

            switch (selectedOption) {
                case 1:
                    manageItems();
                    break;
                case 2:
                    manageMembers();
                    break;
                case 3:
                    saveData();
                case 4:
                    System.out.println("exit");
            }

            selectedOption = mainMenu.run();

        }

    }
}
