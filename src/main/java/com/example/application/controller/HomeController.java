package com.example.application.controller;

import com.example.application.application.EmployeeService;
import com.example.application.application.ServicesService;
import com.example.application.application.SiteService;
import com.example.application.domain.model.Employee;
import com.example.application.domain.model.Services;
import com.example.application.domain.model.Site;
import com.example.application.infrastructure.EmployeeApiAdapter;
import com.example.application.infrastructure.ServicesApiAdapter;
import com.example.application.infrastructure.SiteApiAdapter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

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