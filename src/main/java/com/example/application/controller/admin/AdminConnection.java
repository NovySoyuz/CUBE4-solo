package com.example.application.controller.admin;

import com.example.application.domain.model.Employee;
import com.example.application.infrastructure.EmployeeApiAdapter;
import com.example.application.services.EmployeeService;
import com.example.application.utils.MessagesManager;
import com.example.application.utils.NavigationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AdminConnection {
    private final EmployeeService employeeService;
    @FXML
    public TextField name;
    @FXML
    public TextField firstname;

    public AdminConnection() {
        this.employeeService = new EmployeeService(new EmployeeApiAdapter());
    }

    public void connection(ActionEvent actionEvent) {
        if (name.getText().isEmpty() || firstname.getText().isEmpty()) {
            MessagesManager.errorMessage("Erreur", "Tous les champs sont à completer");
            return;
        }
        Employee employee = new Employee();
        employee.setFirstname(firstname.getText());
        employee.setName(name.getText());

        try {
            employeeService.adminConnection(employee);
        } catch (Exception e) {
            MessagesManager.errorMessage("Erreur de connexion", "Mot de passe ou username incorrect ou vous n'avez pas les droits");
            return;
        }
        NavigationManager.getInstance().navigateTo(actionEvent, "admin/admin-view.fxml", "");
    }
}
