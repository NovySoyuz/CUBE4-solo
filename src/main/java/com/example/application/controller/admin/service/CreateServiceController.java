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

    // Methode associé au bouton de création
    public void handleCreateButton() {
        Services service = new Services();
        service.setName(nameField.getText());

        // Le champ ne doit pas etre vide
        if (nameField.getText().isEmpty()) {
            MessagesManager.errorMessage("Erreur", "Tous les champs sont obligatoires");
            return;
        }

        // Creation du service
        try {
            servicesService.createService(service);
            MessagesManager.successMessage("Succés", "Le service à été créé avec succés");
        } catch (Exception e) {
            MessagesManager.errorMessage("Erreur", "Une erreur est survenue lors de la création de la commande : " + e.getMessage());
        }
    }
}
