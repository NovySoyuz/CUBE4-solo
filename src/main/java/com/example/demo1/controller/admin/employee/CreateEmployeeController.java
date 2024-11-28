package com.example.demo1.controller.admin.employee;

import com.example.demo1.application.EmployeeService;
import com.example.demo1.application.ServicesService;
import com.example.demo1.application.SiteService;
import com.example.demo1.domain.model.Employee;
import com.example.demo1.domain.model.Services;
import com.example.demo1.domain.model.Site;
import com.example.demo1.infrastructure.EmployeeApiAdapter;
import com.example.demo1.infrastructure.ServicesApiAdapter;
import com.example.demo1.infrastructure.SiteApiAdapter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

public class CreateEmployeeController {
    private final SiteService siteService;
    private final ServicesService servicesService;
    private final EmployeeService employeeService;
    @FXML
    public TextField nameField;
    @FXML
    public TextField firstNameField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField phoneField;
    @FXML
    private ComboBox<Services> servicesComboBox;
    @FXML
    private ComboBox<Site> siteComboBox;

    public CreateEmployeeController () {
        this.employeeService = new EmployeeService(new EmployeeApiAdapter());
        this.servicesService = new ServicesService(new ServicesApiAdapter());
        this.siteService = new SiteService(new SiteApiAdapter());
    }

    @FXML
    protected void initialize() {
        List<Site> sites = siteService.getAllSite();
        siteComboBox.setItems(FXCollections.observableList(sites));
        List<Services> services = servicesService.getAllServices();
        servicesComboBox.setItems(FXCollections.observableList(services));
    }

    public void handleCreateButton() {
        Employee employee = new Employee();
        employee.setName(nameField.getText());
        employee.setMail(emailField.getText());
        employee.setFirstname(firstNameField.getText());
        employee.setPhone(phoneField.getText());
        employee.setServices(servicesComboBox.getValue());
        employee.setSite(siteComboBox.getValue());

        employeeService.createEmployee(employee);
        // Création d'un message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("L'employé a été créé avec succès !");
        alert.showAndWait();
    }

    public void handleCancelButton(ActionEvent actionEvent) {
    }
}
