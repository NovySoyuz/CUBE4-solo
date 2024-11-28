package com.example.demo1.controller.admin;

import com.example.demo1.controller.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    HomeController homeController = new HomeController();


    public void employee() {
        homeController.openNewWindow("admin/employee/employee.fxml", "Fiche employé");
    }

    public void sites(ActionEvent actionEvent) {
        System.out.println("sites");
    }

    public void services(ActionEvent actionEvent) {
        System.out.println("services");
    }

}
