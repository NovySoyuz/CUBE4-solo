package com.example.application.controller.admin.service;

import com.example.application.domain.model.Services;
import com.example.application.infrastructure.ServicesApiAdapter;
import com.example.application.services.ServicesService;
import com.example.application.utils.MessagesManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateServiceController {
    @FXML
    public TextField nameField;
    private final ServicesService servicesService;

    public CreateServiceController() {
        this.servicesService = new ServicesService(new ServicesApiAdapter());
    }


    public void handleCreateButton() {
        Services service = new Services();
        service.setName(nameField.getText());

        if (nameField.getText().isEmpty()) {
            MessagesManager.errorMessage("Erreur", "Tous les champs sont obligatoires");
            return;
        }
        try {
            servicesService.createService(service);
            MessagesManager.successMessage("Succés", "Le site à été créé avec succés");
        } catch (Exception e) {
            MessagesManager.errorMessage("Erreur", "Une erreur est survenue lors de la création de la commande : " + e.getMessage());
        }


    }
}
