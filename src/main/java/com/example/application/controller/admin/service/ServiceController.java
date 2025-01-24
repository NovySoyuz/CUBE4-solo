package com.example.application.controller.admin.service;

import com.example.application.controller.HomeController;
import com.example.application.utils.NavigationManager;
import javafx.event.ActionEvent;

public class ServiceController {
    public void createService() {
        NavigationManager.openNewWindow("admin/service/createServiceView.fxml", "Creation d'un service");
    }

    public void updateService() {
        NavigationManager.openNewWindow("admin/service/EditServiceView.fxml", "Mise à jour d'un service");
    }
}
