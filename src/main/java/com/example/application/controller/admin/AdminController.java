package com.example.application.controller.admin;

import com.example.application.controller.BaseController;
import javafx.event.ActionEvent;

public class AdminController {


    public void employee() {
        BaseController.openNewWindow("admin/employee/employeeView.fxml", "Fiche employé");
    }

    public void sites() {
        BaseController.openNewWindow("admin/site/siteView.fxml", "Fiche site");
    }

    public void services(ActionEvent actionEvent) {
        System.out.println("services");
    }

}
