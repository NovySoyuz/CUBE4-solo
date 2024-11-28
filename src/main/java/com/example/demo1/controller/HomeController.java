package com.example.demo1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import java.io.IOException;

public class HomeController {
    private boolean ctrlPressed = false;
    private boolean shiftPressed = false;

    public void onSearchByName() {
        openNewWindow("hello-view.fxml", "Recherche par nom");
    }

    public void onSearchBySite(ActionEvent actionEvent) {
        openNewWindow("site-view.fxml", "Recherche par site");
    }

    public void onSearchByService(ActionEvent actionEvent) {
        openNewWindow("service-view.fxml", "Recherche par service");
    }

    public void openNewWindow(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/" + fxml));
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

    public void handleKeyPressed(KeyEvent event) {
        // event.getCode représente la touche spécifique qui a été pressée ou relachée
        if (event.getCode() == KeyCode.CONTROL) {
            ctrlPressed = true;
        }
        if (event.getCode() == KeyCode.SHIFT) {
            shiftPressed = true;
        }

        // Détection du combo Ctrl + Shift + A
        if (ctrlPressed && shiftPressed && event.getCode() == KeyCode.A) {
            openAdminPage();
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.CONTROL) {
            ctrlPressed = false;
        }
        if (event.getCode() == KeyCode.SHIFT) {
            shiftPressed = false;
        }
    }

    private void openAdminPage() {
        openNewWindow("admin/admin-view.fxml", "Page administrateur");
    }


}
