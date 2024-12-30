package com.example.application.controller.admin.employee;

import com.example.application.controller.BaseController;
import com.example.application.domain.model.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

public class EditEmployeeController extends BaseController {
    private final BaseController baseController;

    public EditEmployeeController() {
        this.baseController = new BaseController();
    }

    @Override
    protected void initialize() {
        super.initialize();
        // Mettre à jour les données dans les colonnes
        employeeTableView.setEditable(true);
        // Permet de double clicker sur la cellule et de l'éditer
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        // Gestionnaire d'evenement lors d'une modification est validée
        nameColumn.setOnEditCommit(event -> {
            // Récuperation de l'objet employé associé à la ligne en cours
            Employee employee = event.getRowValue();
            // Mettre à jour la propriété de l'employé avec la nouvelle valeure dde la cellule
            employee.setName(event.getNewValue());
            // Mise à jour du service
            baseController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setFirstname(event.getNewValue());
            // Mise à jour du service
            baseController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setPhone(event.getNewValue());
            // Mise à jour du service
            baseController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

        mailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mailColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setMail(event.getNewValue());
            // Mise à jour du service
            baseController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

        serviceColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList(servicesService.getAllServices())
        ));
        serviceColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setServices(event.getNewValue());
            baseController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

        siteColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList(siteService.getAllSite())
        ));
        siteColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setSite(event.getNewValue());
            baseController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

    }

    public void onDeleteButtonClick(ActionEvent actionEvent) {
        BaseController.deleteSelectedItem(employeeTableView, employeeService, "getId_employee", "deleteEmployee", "succés", "Erreur");

    }
}
