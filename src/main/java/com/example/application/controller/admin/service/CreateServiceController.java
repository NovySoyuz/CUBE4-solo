package com.example.application.controller.admin.service;

import com.example.application.controller.BaseController;
import com.example.application.domain.model.Services;
import com.example.application.infrastructure.ServicesApiAdapter;
import com.example.application.services.ServicesService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateServiceController {
    @FXML
    public TextField nameField;
    private final ServicesService servicesService;

    public CreateServiceController() {
        this.servicesService = new ServicesService(new ServicesApiAdapter());
    }


    public void handleCreateButton(ActionEvent actionEvent) {
        Services service = new Services();
        service.setName(nameField.getText());

        if (nameField.getText().isEmpty()) {
            BaseController.errorMessage("Erreur", "Tous les champs sont obligatoires");
        } else {
            servicesService.createService(service);
            BaseController.successMessage("Succés", "Le site à été créé avec succés");
        }
    }
}
