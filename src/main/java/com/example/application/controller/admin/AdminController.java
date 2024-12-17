package com.example.application.controller.admin;

import com.example.application.controller.PageController;
import javafx.event.ActionEvent;

public class AdminController {
    PageController pageController = new PageController();


    public void employee() {
        pageController.openNewWindow("admin/employee/employee.fxml", "Fiche employé");
    }

    public void sites(ActionEvent actionEvent) {
        System.out.println("sites");
    }

    public void services(ActionEvent actionEvent) {
        System.out.println("services");
    }

}
