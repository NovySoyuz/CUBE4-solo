package com.example.application.controller.admin.site;

import com.example.application.controller.HomeController;
import com.example.application.utils.NavigationManager;
import javafx.event.ActionEvent;

public class SiteController {
    public void createSite() {
        NavigationManager.openNewWindow("admin/site/createSiteView.fxml", "Creation d'un site");
    }

    public void updateSite() {
        NavigationManager.openNewWindow("admin/site/EditSiteView.fxml", "Mise à jour d'un site");
    }
}
