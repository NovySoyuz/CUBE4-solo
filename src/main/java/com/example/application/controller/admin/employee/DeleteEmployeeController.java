package com.example.application.controller.admin.employee;

import com.example.application.application.EmployeeService;
import com.example.application.application.ServicesService;
import com.example.application.application.SiteService;
import com.example.application.domain.model.Employee;
import com.example.application.domain.model.Services;
import com.example.application.domain.model.Site;
import com.example.application.infrastructure.EmployeeApiAdapter;
import com.example.application.infrastructure.ServicesApiAdapter;
import com.example.application.infrastructure.SiteApiAdapter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DeleteEmployeeController {
    private final SiteService siteService;
    private final ServicesService servicesService;
    private final EmployeeService employeeService;
    @FXML
    private TextField nameTextField;
    @FXML
    private TableView<Employee> employeeTableView;
    // ComboBox est une liste déroulante
    @FXML
    private ComboBox<Site> siteComboBox;
    @FXML
    private ComboBox<Services> serviceComboBox;

    // On récupere les colonnes mentionnées dans le FXML

    // Constructeur obligatoire
    public DeleteEmployeeController() {
        // L'instance de EmployeeService demande obligatoirement un constructeur
        // creation d'une instance de EmployeeService en utilisant EmployeeApiAdaptater comme source de données
        // EmployeeApiAdapteater sait comment se connecter à l'API et récuprer les données
        // EmployeeService utilise cette instance pour accéder aux données sans se soucier de la connexion
        this.employeeService = new EmployeeService(new EmployeeApiAdapter());
        this.siteService = new SiteService(new SiteApiAdapter());
        this.servicesService = new ServicesService(new ServicesApiAdapter());
    }

    @FXML
    protected void initialize() {
        List<Site> sites = siteService.getAllSite();
        // On vient afficher dans la liste déroulante le résultat de getAll
        siteComboBox.setItems(FXCollections.observableList(sites));
        List<Services> services = servicesService.getAllServices();
        serviceComboBox.setItems(FXCollections.observableList(services));

        // redimension du tableau pour prendre l'ensemble de la largeur
        employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        // Affectation d'une nouvelle colonne avec le nom "nom"
        // On declare notre type d'objet donc ici Employee
        // On set les cellules. Le "name" va directement chercher le getter dans la table Employee
        // On utilise PropertyValueFactory pour lier une colonne à une propriété simple de l'objet
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee, String> firstNameColumn = new TableColumn<>("Prenom");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));

        TableColumn<Employee, String> phoneColumn = new TableColumn<>("Telephone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Employee, String> mailColumn = new TableColumn<>("Mail");
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));

        // expression lambda utile quand il faut traverser plusieurs objets pour accéder à une donnée
        // Ici service est un objet présent dans Employee ou l'ont souhaite acceder au nom
        // Avec une expression Lambda il faut utiliser SimpleStringProperty
        TableColumn<Employee, String> serviceColumn = new TableColumn<>("Service");
        serviceColumn.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getServices().getName()));

        TableColumn<Employee, String> siteColumn = new TableColumn<>("Site");
        siteColumn.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getSite().getCity()));

        TableColumn<Employee, String> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_employee"));

        employeeTableView.getColumns().addAll(nameColumn, firstNameColumn, phoneColumn, mailColumn, serviceColumn, siteColumn, idColumn);

        List<Employee> employees = employeeService.getAllEmployees();
        employeeTableView.getItems().setAll(employees);
    }

    @FXML
    protected void onSearchByNameButtonClick() {
        // On récupere la valeur dans le champs de texte
        String name = nameTextField.getText();

        if (name != null) {
            List<Employee> employees = employeeService.searchByName(name);
            employeeTableView.getItems().setAll(employees);
        } else {
            System.out.println("vide");
        }
    }

    public void onSearchBySiteButtonClick() {
        // On récupere la valeur dans
        Site selectedSite = siteComboBox.getValue();
        if (selectedSite != null) {
            List<Employee> employees = employeeService.searchBySite(selectedSite.getId());
            employeeTableView.getItems().setAll(employees);
            System.out.println(selectedSite.getCity());
        } else {
            System.out.println("Aucun site sélectionné.");
        }
    }

    @FXML
    protected void onSearchByServiceButtonClick() {
        Services selectedServices = serviceComboBox.getValue();
        if (selectedServices != null) {
            List<Employee> employees = employeeService.searchByService(selectedServices.getId());
            employeeTableView.getItems().setAll(employees);
        } else {
            System.out.println("Aucun service");
        }
    }

}
