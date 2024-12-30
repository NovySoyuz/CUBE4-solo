package com.example.application.controller.admin.employee;

import com.example.application.controller.BaseController;
import javafx.event.ActionEvent;

public class EmployeeController {
    public void createEmployee(ActionEvent actionEvent) {
        BaseController.openNewWindow("admin/employee/createEmployeeView.fxml", "Creation d'un employé");
    }

    public void updateEmployee(ActionEvent actionEvent) {
        BaseController.openNewWindow("admin/employee/editEmployeeView.fxml", "Ensemble des employés");
    }
}
