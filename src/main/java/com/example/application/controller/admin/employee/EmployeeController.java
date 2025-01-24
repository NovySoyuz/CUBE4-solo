package com.example.application.controller.admin.employee;

import com.example.application.controller.HomeController;
import com.example.application.utils.NavigationManager;
import javafx.event.ActionEvent;

public class EmployeeController {
    public void createEmployee(ActionEvent actionEvent) {
        NavigationManager.openNewWindow("admin/employee/createEmployeeView.fxml", "Creation d'un employé");
    }

    public void updateEmployee(ActionEvent actionEvent) {
        NavigationManager.openNewWindow("admin/employee/editEmployeeView.fxml", "Ensemble des employés");
    }
}
