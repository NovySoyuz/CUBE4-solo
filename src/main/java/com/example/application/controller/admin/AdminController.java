package com.example.application.controller.admin;

import com.example.application.controller.BaseController;
import javafx.event.ActionEvent;

public class AdminController {


    public void employee() {
        BaseController.openNewWindow("admin/employee/employee.fxml", "Fiche employé");
    }

    public void sites(ActionEvent actionEvent) {
        System.out.println("sites");
    }

    public void services(ActionEvent actionEvent) {
        System.out.println("services");
    }

}
