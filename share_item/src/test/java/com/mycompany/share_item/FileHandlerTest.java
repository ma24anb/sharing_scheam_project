package com.mycompany.share_item;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for FileHandler.
 * Tests load(), save(), and the round-trip (save then reload).
 */
public class FileHandlerTest {

    private static final String TEMP_INPUT  = "test_input.dat";
    private static final String TEMP_OUTPUT = "test_output.dat";

    // Mirrors the real input file format:
    //  - Lines 1-2: orphaned items (donated by members who left)
    //  - Members with items, one item on loan to each member
    private static final String SAMPLE_FILE =
            "Book|The Shining|Stephen King|9780345806789|English|wong.j@aol.com\n"
          + "Book|드라큘라|Bram Stoker|9788061879049|Korean|\n"
          + "Member|John Wong|1 Church Lane, Hatfield, AL10 0AG|wong.j@aol.com|3\n"
          + "DVD|精武門|Chinese|羅維|Cantonese,Mandarin,English|a.smith@yahoo.com\n"
          + "Member|Amy Smith|74 Main Street, Hertford, SG13 7AB|a.smith@yahoo.com|3\n"
          + "Book|Le Symbole Perdu|Dan Brown|9782709636346|French|\n";

    @BeforeEach
    void writeTestFile() throws IOException {
        try (BufferedWriter w = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(TEMP_INPUT), "UTF-8"))) {
            w.write(SAMPLE_FILE);
        }
    }

    @AfterEach
    void deleteTestFiles() {
        new File(TEMP_INPUT).delete();
        new File(TEMP_OUTPUT).delete();
    }

    // ------------------------------------------------------------------
    // LOAD tests
    // ------------------------------------------------------------------

    @Test
    void testCorrectNumberOfMembersLoaded() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);
        assertEquals(2, mc.getAllMembers().size());
    }

    @Test
    void testCorrectNumberOfItemsLoaded() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);
        assertEquals(4, col.getItems().size());
    }

    @Test
    void testMemberDetailsCorrect() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);

        Member john = mc.getMemberByEmail("wong.j@aol.com");
        assertNotNull(john);
        assertEquals("John Wong", john.getName());
        assertEquals("1 Church Lane, Hatfield, AL10 0AG", john.getAddress());
        assertEquals(3, john.getDonatedQty());
    }

    @Test
    void testOrphanedItemsHaveNullDonator() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);

        // These two appear before any Member line in the file
        Item shining = col.getItem("The Shining");
        Item dracula = col.getItem("드라큘라");

        assertNotNull(shining);
        assertNotNull(dracula);
        assertNull(shining.getDonator(), "Orphaned item should have null donator");
        assertNull(dracula.getDonator(), "Orphaned item should have null donator");
    }

    @Test
    void testLoanRelationshipReconstructed() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);

        // "The Shining" is on loan to wong.j@aol.com (line 1, borrower is line 3)
        Item shining = col.getItem("The Shining");
        assertNotNull(shining.getBorrower(), "'The Shining' should be on loan");
        assertEquals("wong.j@aol.com", shining.getBorrower().getEmail());
    }

    @Test
    void testBorrowerHasItemInBorrowingList() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);

        Member john = mc.getMemberByEmail("wong.j@aol.com");
        boolean found = false;
        for (Item item : john.getLoanItems()) {
            if (item.getTitle().equals("The Shining")) {
                found = true;
                break;
            }
        }
        assertTrue(found, "John Wong's borrowing list should contain 'The Shining'");
    }

    @Test
    void testItemNotOnLoanHasNullBorrower() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);

        Item le = col.getItem("Le Symbole Perdu");
        assertNotNull(le);
        assertNull(le.getBorrower(), "Item with empty borrower field should not be on loan");
    }

    @Test
    void testDVDAudioLanguagesParsed() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);

        Item item = col.getItem("精武門");
        assertInstanceOf(DVD.class, item);
        DVD dvd = (DVD) item;
        String[] audio = dvd.getAudioLanguages();
        assertEquals(3, audio.length);
        assertEquals("Cantonese", audio[0]);
        assertEquals("Mandarin",  audio[1]);
        assertEquals("English",   audio[2]);
    }

    @Test
    void testBookFieldsParsed() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);

        Item item = col.getItem("The Shining");
        assertInstanceOf(Book.class, item);
        Book book = (Book) item;
        assertEquals("Stephen King",  book.getAuthor());
        assertEquals("9780345806789", book.getIsbn());
        assertEquals("English",       book.getLanguage());
    }

    @Test
    void testDonatedItemsListPopulated() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);

        Member john = mc.getMemberByEmail("wong.j@aol.com");
        // John Wong's items are: DVD 精武門 (lines after his Member line)
        assertFalse(john.getDonatedItems().isEmpty(),
                "John Wong should have at least one donated item");
    }

    // ------------------------------------------------------------------
    // SAVE + reload (round-trip) tests
    // ------------------------------------------------------------------

    @Test
    void testSaveAndReloadPreservesMembers() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);
        FileHandler.save(TEMP_OUTPUT, mc, col);

        MemberCollection mc2 = new MemberCollection();
        Collection col2 = new Collection();
        FileHandler.load(TEMP_OUTPUT, mc2, col2);

        assertEquals(mc.getAllMembers().size(), mc2.getAllMembers().size());
    }

    @Test
    void testSaveAndReloadPreservesItems() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);
        FileHandler.save(TEMP_OUTPUT, mc, col);

        MemberCollection mc2 = new MemberCollection();
        Collection col2 = new Collection();
        FileHandler.load(TEMP_OUTPUT, mc2, col2);

        assertEquals(col.getItems().size(), col2.getItems().size());
    }

    @Test
    void testSaveAndReloadPreservesLoanStatus() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);
        FileHandler.save(TEMP_OUTPUT, mc, col);

        MemberCollection mc2 = new MemberCollection();
        Collection col2 = new Collection();
        FileHandler.load(TEMP_OUTPUT, mc2, col2);

        Item shining = col2.getItem("The Shining");
        assertNotNull(shining.getBorrower());
        assertEquals("wong.j@aol.com", shining.getBorrower().getEmail());
    }

    @Test
    void testSaveWritesOrphanedItemsBeforeMembers() throws IOException {
        MemberCollection mc = new MemberCollection();
        Collection col = new Collection();
        FileHandler.load(TEMP_INPUT, mc, col);
        FileHandler.save(TEMP_OUTPUT, mc, col);

        BufferedReader r = new BufferedReader(
                new InputStreamReader(new FileInputStream(TEMP_OUTPUT), "UTF-8"));
        String firstLine = r.readLine();
        r.close();

        assertFalse(firstLine.startsWith("Member"),
                "Orphaned items must appear before Member lines in the saved file");
    }
}
