// ============================================================
// 1. ADD TO Item.java  (the person who wrote Item.java)
//    A temporary field used only during file loading.
//    It holds the raw borrower email string from the file
//    until all members have been loaded and we can match it.
// ============================================================

private String borrowerEmail = "";

public String getBorrowerEmail() {
    return borrowerEmail;
}

public void setBorrowerEmail(String borrowerEmail) {
    this.borrowerEmail = borrowerEmail;
}


// ============================================================
// 2. ADD TO Collection.java  (the person who wrote Collection.java)
//    FileHandler needs these two methods.
// ============================================================

// Used by FileHandler.load() to add items directly without
// triggering the addBook/addDVD logic (which crashes on null donator)
public void addItem(Item item) {
    items.add(item);
}

// Used by FileHandler.save() to get all items for writing to file
public ArrayList<Item> getItems() {
    return items;
}


// ============================================================
// 3. NO CHANGES NEEDED to MemberCollection.java, Member.java,
//    Book.java, or DVD.java — they are used as-is.
// ============================================================
