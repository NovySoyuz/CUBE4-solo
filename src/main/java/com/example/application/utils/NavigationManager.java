package com.example.application.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class NavigationManager {
    private static NavigationManager instance;
    private static final Stack<Parent> history = new Stack<>();
    private static Stage primaryStage;

    NavigationManager() {
    }

    public static NavigationManager getInstance() {
        if (instance == null) {
            instance = new NavigationManager();
        }
        return instance;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }


    public void navigateTo(ActionEvent actionEvent, String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/application/" + fxml));
            Parent newView = loader.load();
            // Ajouter la vue actuelle à l'historique avant de naviguer
            if (primaryStage.getScene() != null) {
                history.push(primaryStage.getScene().getRoot());
            }
            // Remplace le contenu de la scène actuelle
            Scene currentScene = ((Node) actionEvent.getSource()).getScene();
            currentScene.setRoot(newView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void openNewWindow(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationManager.class.getResource("/com/example/application/" + fxml));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
