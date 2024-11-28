package com.example.demo1.controller.admin.employee;

import com.example.demo1.controller.HomeController;
import javafx.event.ActionEvent;

public class EmployeeController {
    private HomeController homeController = new HomeController();
    public void createEmployee(ActionEvent actionEvent) {
        homeController.openNewWindow("admin/employee/CreateEmployeeView.fxml", "Creation d'un employé");
    }

    public void updateEmployee(ActionEvent actionEvent) {
        System.out.println("update");
    }

    public void deleteEmployee(ActionEvent actionEvent) {
        System.out.println("delete");
    }
}
