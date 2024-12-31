package com.example.application.controller.admin.service;

import com.example.application.controller.BaseController;
import javafx.event.ActionEvent;

public class ServiceController {
    public void createEmployee(ActionEvent actionEvent) {
        BaseController.openNewWindow("admin/service/createServiceView.fxml", "Creation d'un service");
    }

    public void updateEmployee(ActionEvent actionEvent) {
        BaseController.openNewWindow("admin/service/EditServiceView.fxml", "Mise à jour d'un service");
    }
}
