package com.example.application.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class HomeController extends BaseController{
    private boolean ctrlPressed = false;
    private boolean shiftPressed = false;

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
        BaseController.openNewWindow("admin/admin-view.fxml", "Page administrateur");
    }
}