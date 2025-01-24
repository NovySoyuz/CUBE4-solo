package com.example.application.controller.admin.employee;

import com.example.application.services.EmployeeService;
import com.example.application.services.ServicesService;
import com.example.application.services.SiteService;
import com.example.application.controller.HomeController;
import com.example.application.domain.model.Employee;
import com.example.application.domain.model.Services;
import com.example.application.domain.model.Site;
import com.example.application.infrastructure.EmployeeApiAdapter;
import com.example.application.infrastructure.ServicesApiAdapter;
import com.example.application.infrastructure.SiteApiAdapter;
import com.example.application.utils.HomeKeyController;
import com.example.application.utils.MessagesManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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
        // Récupération des sites et services
        List<Site> sites = siteService.getAllSite();
        siteComboBox.setItems(FXCollections.observableList(sites));
        List<Services> services = servicesService.getAllServices();
        servicesComboBox.setItems(FXCollections.observableList(services));
    }

    public void handleCreateButton() {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || firstNameField.getText().isEmpty() || phoneField.getText().isEmpty() || servicesComboBox.getItems() == null || siteComboBox.getItems() == null) {
            MessagesManager.errorMessage("Erreur", "Tous les champs sont obligatoires");
            return;
        }
        if (!emailField.getText().contains("@")) {
            MessagesManager.errorMessage("Erreur", "Erreur format adresse mail");
            return;
        }
            Employee employee = new Employee();
            employee.setName(nameField.getText());
            employee.setMail(emailField.getText());
            employee.setFirstname(firstNameField.getText());
            employee.setPhone(phoneField.getText());
            employee.setServices(servicesComboBox.getValue());
            employee.setSite(siteComboBox.getValue());

        try {
            employeeService.createEmployee(employee);
            // Création d'un message
            MessagesManager.successMessage("Succès", "L'employé a été créé avec succès !");
        } catch (Exception e) {
            MessagesManager.errorMessage("Erreur", "Une erreur est survenue lors de la création de la commande : " + e.getMessage());
        }
    }
}
