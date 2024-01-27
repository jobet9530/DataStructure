import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testMenuOptions() {
        String input = "1\nTestItem\n100\n2\nTestItem\n3\nTestItem\n4\n5\nTestItem\n150\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String[] outputLines = outputStreamCaptor.toString().split(System.lineSeparator());

        assertEquals("TestItem added successfully", outputLines[outputLines.length - 9]);
        assertEquals("Item found", outputLines[outputLines.length - 7]);
        assertEquals("Item deleted successfully", outputLines[outputLines.length - 5]);
        assertEquals("Item name: TestItem, Item price: 150", outputLines[outputLines.length - 3]);
        assertEquals("Exiting program. Goodbye!", outputLines[outputLines.length - 1]);
    }

    @Test
    public void testInvalidChoice() {
        String input = "6\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String[] outputLines = outputStreamCaptor.toString().split(System.lineSeparator());

        assertEquals("Invalid choice. Please choose a valid option.", outputLines[outputLines.length - 2]);
        assertEquals("Exiting program. Goodbye!", outputLines[outputLines.length - 1]);
    }

    @Test
    public void testEmptyItemList() {
        String input = "4\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String[] outputLines = outputStreamCaptor.toString().split(System.lineSeparator());

        assertEquals("No items in the list", outputLines[outputLines.length - 2]);
        assertEquals("Exiting program. Goodbye!", outputLines[outputLines.length - 1]);
    }

    @Test
    public void testUpdateNonexistentItem() {
        String input = "5\nTestItem\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String[] outputLines = outputStreamCaptor.toString().split(System.lineSeparator());

        assertEquals("Please enter the name of the item.", outputLines[outputLines.length - 3]);
        assertEquals("Exiting program. Goodbye!", outputLines[outputLines.length - 1]);
    }

    @Test
    public void testSearchEmptyName() {
        String input = "2\n\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String[] outputLines = outputStreamCaptor.toString().split(System.lineSeparator());

        assertEquals("Please enter the name of the item.", outputLines[outputLines.length - 3]);
        assertEquals("Exiting program. Goodbye!", outputLines[outputLines.length - 1]);
    }

    @Test
    public void testSearchNonexistentItem() {
        String input = "2\nNonexistentItem\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String[] outputLines = outputStreamCaptor.toString().split(System.lineSeparator());

        assertEquals("Item not found", outputLines[outputLines.length - 2]);
        assertEquals("Exiting program. Goodbye!", outputLines[outputLines.length - 1]);
    }

    @Test
    public void testDeleteNonexistentItem() {
        String input = "3\nNonexistentItem\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String[] outputLines = outputStreamCaptor.toString().split(System.lineSeparator());

        assertEquals("Item not found", outputLines[outputLines.length - 2]);
        assertEquals("Exiting program. Goodbye!", outputLines[outputLines.length - 1]);
    }

    @Test
    public void testUpdateItem() {
        String input = "1\nTestItem\n100\n5\nTestItem\n150\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String[] outputLines = outputStreamCaptor.toString().split(System.lineSeparator());

        assertEquals("TestItem added successfully", outputLines[outputLines.length - 8]);
        assertEquals("Item updated successfully", outputLines[outputLines.length - 4]);
        assertEquals("Item name: TestItem, New item price: 150", outputLines[outputLines.length - 3]);
        assertEquals("Exiting program. Goodbye!", outputLines[outputLines.length - 1]);
    }
}
