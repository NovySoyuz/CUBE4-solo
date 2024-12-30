package com.example.application.controller.admin.site;

import com.example.application.services.SiteService;
import com.example.application.controller.BaseController;
import com.example.application.domain.model.Site;
import com.example.application.infrastructure.SiteApiAdapter;
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


    public void handleCreateButton(ActionEvent actionEvent) {
        if (nameField.getText().isEmpty()) {
            BaseController.errorMessage("Erreur", "Tous les champs sont obligatoires");
        } else {
            Site site = new Site();
            site.setCity(nameField.getText());

            siteService.createSite(site);
            BaseController.successMessage("Succés", "Le site à été créé avec succés");
        }
    }
}
