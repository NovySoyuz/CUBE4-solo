package com.example.application.controller.admin.employee;

import com.example.application.controller.HomeController;
import com.example.application.domain.model.Employee;
import com.example.application.utils.MessagesManager;
import com.example.application.utils.MethodsController;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

public class EditEmployeeController extends HomeController {
    private final MethodsController methodsController;

    public EditEmployeeController() {
        this.methodsController = new MethodsController();
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
            methodsController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setFirstname(event.getNewValue());
            // Mise à jour du service
            methodsController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setPhone(event.getNewValue());
            // Mise à jour du service
            methodsController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

        mailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mailColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setMail(event.getNewValue());
            if (!event.getNewValue().contains("@")) {
                MessagesManager.errorMessage("Erreur", "Erreur format adresse mail");
                return;
            }
            methodsController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

        serviceColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList(servicesService.getAllServices())
        ));
        serviceColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setServices(event.getNewValue());
            methodsController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

        siteColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList(siteService.getAllSite())
        ));
        siteColumn.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setSite(event.getNewValue());
            methodsController.updateInDatabase(employeeService, "updateEmployee", employee);
        });

    }

    public void onDeleteButtonClick() {
        try {
            methodsController.deleteSelectedItem(employeeTableView, employeeService, "getId_employee", "deleteEmployee", "succés", "Erreur");
            MessagesManager.errorMessage("Erreur", "Erreur lors de la suppression");
        } catch (Exception e) {
            return;
        }

    }
}
