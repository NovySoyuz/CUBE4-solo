package com.example.application.utils;

import com.example.application.controller.HomeController;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class HomeKeyController extends HomeController {
    private boolean ctrlPressed = false;
    private boolean shiftPressed = false;
    private final NavigationManager navigationManager;

    public HomeKeyController() {
        this.navigationManager = new NavigationManager();
    }

    // Ouverture d'une page avec un combo de touche

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
            openAdminPage(new ActionEvent());
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
    private void openAdminPage(ActionEvent actionEvent) {
        NavigationManager.openNewWindow("admin/adminConnectionView.fxml", "Page administrateur");
    }
}