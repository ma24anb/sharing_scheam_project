package com.mycompany.share_item;
 
import java.io.*;
import java.util.ArrayList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Joshua Apeloko
 */
public class FileHandler {
 

    // LOAD

 
    /**
     * Loads members and items from the given file path.
     * Call this once on startup from Share_item.java.
     *
     * @param filePath         path to the input .dat file
     * @param memberCollection the MemberCollection to populate
     * @param collection       the Collection to populate with Items
     * @throws IOException if the file cannot be read
     */
    public static void load(String filePath,
                            MemberCollection memberCollection,
                            Collection collection) throws IOException {
 
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
 
        String line;
        Member currentMember = null;  // tracks whose items we are reading
        ArrayList<Item> allItems = new ArrayList<>();
 
        // PASS 1 — build all Member, Book and DVD objects
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
 
            String[] fields = line.split("\\|", -1); // -1 keeps trailing empty strings
            String type = fields[0];
 
            switch (type) {
                case "Member":
                    currentMember = parseMember(fields);
                    memberCollection.addMember(currentMember);
                    break;
 
                case "Book":
                    // currentMember is null if this Book appears before any Member line
                    // (donated by a member who has left) — donatedBy will be null
                    Book book = parseBook(fields, currentMember);
                    collection.addItem(book);
                    allItems.add(book);
                    break;
 
                case "DVD":
                    DVD dvd = parseDVD(fields, currentMember);
                    collection.addItem(dvd);
                    allItems.add(dvd);
                    break;
 
                default:
                    System.err.println("WARNING: Unknown record type '" + type
                            + "' — line skipped: " + line);
            }
        }
        reader.close();
 
        // PASS 2 — reconstruct loan relationships
        //
        // We must do this AFTER all members are loaded because a borrower email
        // might appear on line 1 while the Member line for that person is line 3.
        for (Item item : allItems) {
            String borrowerEmail = item.getBorrowerEmail();
            if (borrowerEmail != null && !borrowerEmail.isEmpty()) {
                Member borrower = memberCollection.getMemberByEmail(borrowerEmail);
                if (borrower != null) {
                    // Set loan directly on the item — bypasses borrowing-limit
                    // checks in Member.lend(), which is correct here because we
                    // are restoring a previously saved valid state, not making a new loan
                    item.loanTo(borrower);
                    // Add directly to borrowing list for the same reason
                    borrower.getLoanItems().add(item);
                } else {
                    System.err.println("WARNING: Borrower email '" + borrowerEmail
                            + "' not found — loan for '"
                            + item.getTitle() + "' not reconstructed.");
                }
            }
        }
 
        // PASS 3 — sync each member's donatedItems list
        //
        // We use collection.addItem() instead of collection.addBook/addDVD()
        // because those methods call donator.addDonation() which would crash
        // with a NullPointerException for orphaned items (donatedBy == null).
        // So we sync the donatedItems lists manually here instead.
        for (Item item : allItems) {
            Member donator = item.getDonator();
            if (donator != null && !donator.getDonatedItems().contains(item)) {
                donator.getDonatedItems().add(item);
            }
        }
    }
 
    // SAVE
 
    /**
     * Saves the current system state to the given file path.
     * Call this when the user selects save and provides a filename.
     *
     * Orphaned items (donatedBy == null) are written first, before any Member
     * blocks, matching the required input file format.
     *
     * @param filePath         destination file path entered by the user
     * @param memberCollection the MemberCollection containing all members
     * @param collection       the Collection containing all items
     * @throws IOException if the file cannot be written
     */
    public static void save(String filePath,
                            MemberCollection memberCollection,
                            Collection collection) throws IOException {
 
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
 
        ArrayList<Item> allItems = collection.getItems();
 
        // 1. Write orphaned items first (donated by members who have left)
        for (Item item : allItems) {
            if (item.getDonator() == null) {
                writer.write(itemToLine(item));
                writer.newLine();
            }
        }
 
        // 2. Write each member followed by the items they donated
        for (Member member : memberCollection.getAllMembers()) {
            writer.write(memberToLine(member));
            writer.newLine();
 
            for (Item item : allItems) {
                if (item.getDonator() == member) {
                    writer.write(itemToLine(item));
                    writer.newLine();
                }
            }
        }
 
        writer.close();
        System.out.println("Data saved to: " + filePath);
    }
 
    // PRIVATE HELPERS — parsing
 
    /** Parses a Member line: Member|Name|Address|Email|DonatedQty */
    private static Member parseMember(String[] f) {
        String name     = f[1];
        String address  = f[2];
        String email    = f[3];
        int donatedQty  = Integer.parseInt(f[4].trim());
        return new Member(name, address, email, donatedQty);
    }
 
    /** Parses a Book line: Book|Title|Author|ISBN|Language|BorrowerEmail */
    private static Book parseBook(String[] f, Member currentMember) {
        String title         = f[1];
        String author        = f[2];
        String isbn          = f[3];
        String language      = f[4];
        String borrowerEmail = (f.length > 5) ? f[5].trim() : "";
 
        Book book = new Book(title, author, currentMember, language, isbn);
        book.setBorrowerEmail(borrowerEmail); // stored temporarily for pass 2
        return book;
    }
 
    /**
     * Parses a DVD line: DVD|Title|Language|Director|AudioLanguages|BorrowerEmail
     * AudioLanguages is a comma-separated list inside one field.
     */
    private static DVD parseDVD(String[] f, Member currentMember) {
        String title         = f[1];
        String language      = f[2];
        String director      = f[3];
        String audioRaw      = (f.length > 4) ? f[4] : "";
        String borrowerEmail = (f.length > 5) ? f[5].trim() : "";
 
        String[] audioLanguages = audioRaw.isEmpty()
                ? new String[0]
                : audioRaw.split(",");
 
        for (int i = 0; i < audioLanguages.length; i++) {
            audioLanguages[i] = audioLanguages[i].trim();
        }
 
        DVD dvd = new DVD(title, director, currentMember, language, audioLanguages);
        dvd.setBorrowerEmail(borrowerEmail);
        return dvd;
    }
 
    // PRIVATE HELPERS — serialising

 
    /** Converts a Member object back to its file-format line. */
    private static String memberToLine(Member m) {
        return "Member"
                + "|" + m.getName()
                + "|" + m.getAddress()
                + "|" + m.getEmail()
                + "|" + m.getDonatedQty();
    }
 
    /** Converts a Book or DVD object back to its file-format line. */
    private static String itemToLine(Item item) {
        // getBorrower() is the actual method name in Item.java
        String borrowerEmail = (item.getBorrower() != null)
                ? item.getBorrower().getEmail()
                : "";
 
        if (item instanceof Book) {
            Book b = (Book) item;
            return "Book"
                    + "|" + b.getTitle()
                    + "|" + b.getAuthor()
                    + "|" + b.getIsbn()
                    + "|" + b.getLanguage()
                    + "|" + borrowerEmail;
 
        } else { // DVD
            DVD d = (DVD) item;
            String audioLangs = String.join(",", d.getAudioLanguages());
            return "DVD"
                    + "|" + d.getTitle()
                    + "|" + d.getLanguage()
                    + "|" + d.getDirector()
                    + "|" + audioLangs
                    + "|" + borrowerEmail;
        }
    }
}
