package com.example.demo1.controller.admin.employee;

import com.example.demo1.controller.HomeController;
import javafx.event.ActionEvent;

public class EmployeeController {
    private HomeController homeController = new HomeController();
    public void createEmployee(ActionEvent actionEvent) {
        homeController.openNewWindow("admin/employee/createEmployeeView.fxml", "Creation d'un employé");
    }

    public void updateEmployee(ActionEvent actionEvent) {
        homeController.openNewWindow("admin/employee/deleteEmployeeView.fxml", "Ensemble des employés");
    }

    public void deleteEmployee(ActionEvent actionEvent) {
        System.out.println("delete");
    }
}
