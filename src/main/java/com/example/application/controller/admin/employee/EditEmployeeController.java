package com.example.application.controller.admin.employee;

import com.example.application.application.EmployeeService;
import com.example.application.controller.BaseController;
import com.example.application.domain.model.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class EditEmployeeController extends BaseController {
    private final BaseController baseController;

    public EditEmployeeController() {
        this.baseController = new BaseController();
    }

    @Override
    protected void initialize() {
        super.initialize();
        employeeTableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setName(event.getNewValue());
            // Mise à jour du service
            baseController.updateEmployeeInDatabase(employee);
        });

        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setName(event.getNewValue());
            // Mise à jour du service
            baseController.updateEmployeeInDatabase(employee);
        });

        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setName(event.getNewValue());
            // Mise à jour du service
            baseController.updateEmployeeInDatabase(employee);
        });

        mailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mailColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setName(event.getNewValue());
            // Mise à jour du service
            baseController.updateEmployeeInDatabase(employee);
        });

        serviceColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList(servicesService.getAllServices())
        ));
        serviceColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setServices(event.getNewValue());
            updateEmployeeInDatabase(employee);
        });

        siteColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList(siteService.getAllSite())
        ));
        siteColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setSite(event.getNewValue());
            updateEmployeeInDatabase(employee);
        });

    }

    public void onDeleteButtonClick(ActionEvent actionEvent) {
        // Récupérer l'élément sélectionné
        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            // Confirmer la suppression
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cet employé ?", ButtonType.YES, ButtonType.NO);
            confirmationAlert.showAndWait();
            if (confirmationAlert.getResult() == ButtonType.YES) {
                // Appeler le service pour supprimer l'utilisateur
                boolean isDeleted = employeeService.deleteEmployee(selectedEmployee.getId_employee());
                if (isDeleted) {
                    // Mettre à jour la TableView
                    employeeTableView.getItems().remove(selectedEmployee);
                    BaseController.successMessage("Succès", "Employé supprimé avec succès !");

                } else {
                    BaseController.errorMessage("Erreur", "Erreur lors de la suppression !");
                }
            }
        } else {
            BaseController.errorMessage("Erreur", "Veuillez sélectionner un employé à supprimer !");
        }
    }
}
