package com.example.application.utils;

import com.example.application.controller.HomeController;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class HomeKeyController extends HomeController {
    private boolean ctrlPressed = false;
    private boolean shiftPressed = false;

    // Ouverture d'une page avec un combo de touche

    public void handleKeyPressed(KeyEvent event) {
        // event.getCode représente la touche spécifique qui a été pressée
        if (event.getCode() == KeyCode.CONTROL) {
            ctrlPressed = true;
        }
        if (event.getCode() == KeyCode.SHIFT) {
            shiftPressed = true;
        }

        // Détection du combo Ctrl + Shift + A
        if (ctrlPressed && shiftPressed && event.getCode() == KeyCode.A) {
            openAdminPage(new ActionEvent());
        }
    }
    private void openAdminPage(ActionEvent actionEvent) {
        NavigationManager.openNewWindow("admin/adminConnectionView.fxml", "Page administrateur");
    }
}