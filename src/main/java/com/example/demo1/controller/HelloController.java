package com.example.demo1.controller;

import com.example.demo1.application.EmployeeService;
import com.example.demo1.domain.model.Employee;
import com.example.demo1.infrastructure.EmployeeApiAdapter;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HelloController {
    private final EmployeeService employeeService;
    @FXML
    private TextField nameTextField;
    @FXML
    private TableView<Employee> employeeTableView;

    // On récupere les colonnes mentionnées dans le FXML


    // Constructeur obligatoire
    public HelloController() {
        // L'instance de EmployeeService demande obligatoirement un constructeur
        // creation d'une instance de EmployeeService en utilisant EmployeeApiAdaptater comme sour ce dedonnées
        // EmployeeApiAdapteater sait comment se connecter à l'API et récuprer les données
        // EmployeeService utilise cette instance pour accéder aux données sans se soucier de la connexion
        this.employeeService = new EmployeeService(new EmployeeApiAdapter());
    }

    @FXML
    protected void initialize() {
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
        TableColumn<Employee, String> serviceColumn = new TableColumn<>("Site");
        serviceColumn.setCellValueFactory(toto -> new SimpleStringProperty(toto.getValue().getServices().getName()));

        TableColumn<Employee, String> siteColumn = new TableColumn<>("Site");
        siteColumn.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getSite().getCity()));

        employeeTableView.getColumns().addAll(nameColumn, firstNameColumn, phoneColumn, mailColumn, serviceColumn, siteColumn);
    }

    @FXML
    protected void onSearchByNameButtonClick() {
        String name = nameTextField.getText();
        System.out.println();

        if (name != null) {
            List<Employee> employees = employeeService.searchByName(name);

            for (Employee e : employees) {
                System.out.println("ID " + e.getId_employee());
                System.out.println("name " + e.getName());
                System.out.println("mail " + e.getMail());
                System.out.println("Prenom " + e.getFirstname());
                System.out.println("Service " + e.getServices().getName());
                System.out.println("Site " + e.getSite().getCity());
            }


            employeeTableView.getItems().setAll(employees);
        } else {
            System.out.println("vide");
        }


    }


    @FXML
    protected void onSearchBySiteButtonClick() {

    }
}