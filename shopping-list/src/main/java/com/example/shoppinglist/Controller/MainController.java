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
     * Input field for the item quantity.
     */
    @FXML
    private TextField quantityField;

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
     * Called when the user clicks the "Remove Selected Item" button.
     */
    @FXML
    private void handleRemoveItem() {
        final int selectedIndex =
            itemListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            itemListView.getItems().remove(selectedIndex);
        } else {
            showAlert("No Selection",
                "Please select an item to remove.");
        }
    }

    /**
     * Called when the user clicks the "Update Quantity" button.
     */
    @FXML
    private void handleUpdateQuantity() {
        final int selectedIndex =
            itemListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0) {
            showAlert("No Selection",
                "Please select an item to update.");
            return;
        }

        final String quantityText = quantityField.getText().trim();
        final int quantity;

        try {
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            showAlert("Invalid Quantity",
                "Quantity must be a positive number.");
            return;
        }

        if (quantity <= 0) {
            showAlert("Invalid Quantity",
                "Quantity must be greater than 0.");
            return;
        }

        final String oldItem =
            itemListView.getItems().get(selectedIndex);
        final String itemName = oldItem.split(" \\(Qty:")[0];

        final String updatedItem = itemName + " (Qty: " + quantity + ")";
        itemListView.getItems().set(selectedIndex, updatedItem);
        quantityField.clear();
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
