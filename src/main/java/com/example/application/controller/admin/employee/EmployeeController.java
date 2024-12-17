package com.example.application.controller.admin.employee;

import com.example.application.controller.PageController;
import javafx.event.ActionEvent;

public class EmployeeController {
    private PageController pageController = new PageController();
    public void createEmployee(ActionEvent actionEvent) {
        pageController.openNewWindow("admin/employee/createEmployeeView.fxml", "Creation d'un employé");
    }

    public void updateEmployee(ActionEvent actionEvent) {
        pageController.openNewWindow("admin/employee/deleteEmployeeView.fxml", "Ensemble des employés");
    }

    public void deleteEmployee(ActionEvent actionEvent) {
        System.out.println("delete");
    }
}
