package com.example.application.controller.admin;

import com.example.application.utils.NavigationManager;

public class AdminController {


    public void employee() {
        NavigationManager.openNewWindow("admin/employee/employeeView.fxml", "Fiche employé");
    }

    public void sites() {
        NavigationManager.openNewWindow("admin/site/siteView.fxml", "Fiche site");
    }

    public void services() {
        NavigationManager.openNewWindow("admin/service/serviceView.fxml", "Fiche service");
    }

}
