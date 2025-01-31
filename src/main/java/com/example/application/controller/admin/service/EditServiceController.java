package com.example.application.controller.admin.service;

import com.example.application.controller.HomeController;
import com.example.application.domain.model.Employee;
import com.example.application.domain.model.Services;
import com.example.application.infrastructure.EmployeeApiAdapter;
import com.example.application.infrastructure.ServicesApiAdapter;
import com.example.application.services.EmployeeService;
import com.example.application.services.ServicesService;
import com.example.application.utils.MessagesManager;
import com.example.application.utils.MethodsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;

public class EditServiceController {
    private final ServicesService servicesService;
    private final MethodsController methodsController;
    private final EmployeeService employeeService;
    @FXML
    private TableView<Services> serviceTableView;
    private TableColumn<Services, Integer> idColumn;
    private TableColumn<Services, String> nameColumn;

    public EditServiceController() {
        this.servicesService = new ServicesService(new ServicesApiAdapter());
        this.methodsController = new MethodsController();
        this.employeeService = new EmployeeService(new EmployeeApiAdapter());
    }

    public void initialize() {
        serviceTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        serviceTableView.setEditable(true);

        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        serviceTableView.getColumns().addAll(idColumn, nameColumn);

        List<Services> services = servicesService.getAllServices();
        serviceTableView.getItems().setAll(services);

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            Services service = event.getRowValue();
            service.setName(event.getNewValue());
            methodsController.updateInDatabase(servicesService, "updateService", service);
        });
    }

    public void onDeleteButtonClick() {
        int selectedId = serviceTableView.getSelectionModel().getSelectedItem().getId();
        List<Employee>  employees = employeeService.searchByService(selectedId);

        if (!employees.isEmpty()) {
            MessagesManager.errorMessage("Erreur ", "Le site est encore affecté à des utilisateurs");
        } else {
            methodsController.deleteSelectedItem(serviceTableView, servicesService, "getId", "deleteService", "succés", "erreur");
        }
    }
}
