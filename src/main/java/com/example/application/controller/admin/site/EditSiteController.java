package com.example.application.controller.admin.site;

import com.example.application.controller.BaseController;
import com.example.application.domain.model.Employee;
import com.example.application.infrastructure.EmployeeApiAdapter;
import com.example.application.services.EmployeeService;
import com.example.application.services.SiteService;
import com.example.application.domain.model.Site;
import com.example.application.infrastructure.SiteApiAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;

public class EditSiteController {
    private final SiteService siteService;
    private final EmployeeService employeeService;
    private final BaseController baseController;
    @FXML
    private TableView<Site> siteTableView;
    private TableColumn<Site, Integer> idColumn;
    private  TableColumn<Site, String> siteColumn;

    public EditSiteController() {
        this.siteService = new SiteService(new SiteApiAdapter());
        this.employeeService = new EmployeeService(new EmployeeApiAdapter());
        this.baseController = new BaseController();
    }
    public void initialize() {
        siteTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        siteTableView.setEditable(true);

        idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        siteColumn = new TableColumn<>("Site");
        siteColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        siteTableView.getColumns().addAll(idColumn, siteColumn);

        List<Site> sites = siteService.getAllSite();
        siteTableView.getItems().setAll(sites);

        siteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        siteColumn.setOnEditCommit(event -> {
            Site site = event.getRowValue();
            site.setCity(event.getNewValue());
            baseController.updateInDatabase(siteService, "updateSite", site);
        });
    }

    public void onDeleteButtonClick(ActionEvent actionEvent) {
        // Récuperation de l'id du site
        int selectedId = siteTableView.getSelectionModel().getSelectedItem().getId();
        // Creation de l'une liste si un employé est detecté avec l'id du site
        List<Employee> employees = employeeService.searchBySite(selectedId);

        if (!employees.isEmpty()) {
            BaseController.errorMessage("Erreur ", "Le site est encore affecté à des utilisateurs");
        } else {
            BaseController.deleteSelectedItem(siteTableView, siteService, "getId", "deleteSite", "Succés", "Erreur");
        }
    }
}
