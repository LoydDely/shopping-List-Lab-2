package com.example.shoppinglist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * JavaFX entry point for the Shopping List app.
 */
public final class MainApp extends Application {

    /**
     * Launches the JavaFX application.
     *
     * @param args the command-line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final String fxmlPath =
            "/com/example/shoppinglist/view/MainView.fxml";
        final FXMLLoader loader =
            new FXMLLoader(getClass().getResource(fxmlPath));
        final Parent root = loader.load();
        final Scene scene = new Scene(root);

        primaryStage.setTitle("Shopping List - Blazin Nova");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
