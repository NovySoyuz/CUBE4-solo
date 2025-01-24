package com.example.application.controller.admin.site;

import com.example.application.services.SiteService;
import com.example.application.controller.HomeController;
import com.example.application.domain.model.Site;
import com.example.application.infrastructure.SiteApiAdapter;
import com.example.application.utils.MessagesManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateSiteController {
    @FXML
    public TextField nameField;
    private final SiteService siteService;

    public CreateSiteController() {
        this.siteService = new SiteService(new SiteApiAdapter());
    }


    public void handleCreateButton() {

        Site site = new Site();
        site.setCity(nameField.getText());

        if (nameField.getText().isEmpty()) {
            MessagesManager.errorMessage("Erreur", "Tous les champs sont obligatoires");
            return;
        }

        try {
            siteService.createSite(site);
            MessagesManager.successMessage("Succés", "Le site à été créé avec succés");
        } catch (Exception e) {
            MessagesManager.errorMessage("Erreur", "Une erreur est survenue lors de la création de la commande : " + e.getMessage());
        }
    }
}
