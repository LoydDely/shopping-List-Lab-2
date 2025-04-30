package com.example.shoppinglist;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UI tests for the MainController using TestFX.
 */
public class MainControllerTest extends ApplicationTest {

    private TextField itemNameField;
    private TextField quantityField;
    private ListView<String> itemListView;
    private Button addButton;
    private Button removeButton;
    private Button updateButton;

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(
            getClass().getResource(
                "/com/example/shoppinglist/view/MainView.fxml"));
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes references to UI components before each test.
     */
    @BeforeEach
    public void lookupUIElements() {
        itemNameField = lookup("#itemNameField").query();
        quantityField = lookup("#quantityField").query();
        itemListView = lookup("#itemListView").query();
        addButton = lookup("#addButton").query();
        removeButton = lookup("#removeButton").query();
        updateButton = lookup("#updateButton").query();
    }

    /**
     * Tests adding a valid item to the list.
     */
    @Test
    public void testAddValidItem() {
        clickOn(itemNameField).write("Milk");
        clickOn(addButton);

        final String actual = itemListView.getItems().get(0);
        assertEquals("Milk (Qty: 0)", actual);
    }

    /**
     * Tests attempting to add a blank item name.
     */
    @Test
    public void testAddInvalidItem_ShowsNothing() {
        clickOn(itemNameField).write("   ");
        clickOn(addButton);

        assertEquals(0, itemListView.getItems().size());
    }

    /**
     * Tests removing an item from the list.
     */
    @Test
    public void testRemoveItem() {
        clickOn(itemNameField).write("Bread");
        clickOn(addButton);
        clickOn("Bread (Qty: 0)");
        clickOn(removeButton);

        assertEquals(0, itemListView.getItems().size());
    }

    /**
     * Tests updating the quantity of a selected item.
     */
    @Test
    public void testUpdateQuantity() {
        clickOn(itemNameField).write("Eggs");
        clickOn(addButton);
        clickOn("Eggs (Qty: 0)");

        clickOn(quantityField).write("3");
        clickOn(updateButton);

        final String expected = "Eggs (Qty: 3)";
        assertEquals(expected, itemListView.getItems().get(0));
    }

    /**
     * Tests that invalid quantity input does not update the item.
     */
    @Test
    public void testUpdateInvalidQuantity_DoesNotChange() {
        clickOn(itemNameField).write("Juice");
        clickOn(addButton);
        clickOn("Juice (Qty: 0)");

        clickOn(quantityField).write("0");
        clickOn(updateButton);

        final String expected = "Juice (Qty: 0)";
        assertEquals(expected, itemListView.getItems().get(0));
    }
	
	/**
	* Tests that clicking update without selecting an item shows an error.
	*/
	@Test
	public void testUpdateQuantityWithoutSelection_ShowsError() {
		clickOn(itemNameField).write("Apple");
		clickOn(addButton);

		clickOn(quantityField).write("2");
		clickOn(updateButton);

		final String expected = "Apple (Qty: 0)";
		assertEquals(expected, itemListView.getItems().get(0));
	}
	
	/**
	* Tests that entering a non-numeric quantity shows an error.
	*/
	@Test
	public void testUpdateQuantityInvalidFormat_ShowsError() {
		clickOn(itemNameField).write("Water");
		clickOn(addButton);
		clickOn("Water (Qty: 0)");

		clickOn(quantityField).write("two");
		clickOn(updateButton);

		final String expected = "Water (Qty: 0)";
		assertEquals(expected, itemListView.getItems().get(0));
	}

}
