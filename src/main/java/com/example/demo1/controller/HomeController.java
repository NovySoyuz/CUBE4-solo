package com.example.demo1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    public void onSearchByName() {
        System.out.println("test bouton");
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
}
