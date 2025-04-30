package com.example.shoppinglist.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Controller for handling UI logic in the shopping list application.
 */
public class MainController {

    /**
     * Input field for the item name.
     */
    @FXML
    private TextField itemNameField;

    /**
     * List view to display the shopping list items.
     */
    @FXML
    private ListView<String> itemListView;

    /**
     * Called when the user clicks the "Add Item" button.
     */
    @FXML
    private void handleAddItem() {
        final String itemName = itemNameField.getText().trim();

        if (itemName.isEmpty()) {
            showAlert("Invalid Item", "Item name cannot be empty.");
            return;
        }

        final String listItem = itemName + " (Qty: 0)";
        itemListView.getItems().add(listItem);
        itemNameField.clear();
    }

    /**
     * Displays an alert dialog with the given title and message.
     *
     * @param title   the title of the alert
     * @param message the content message
     */
    private void showAlert(final String title, final String message) {
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
