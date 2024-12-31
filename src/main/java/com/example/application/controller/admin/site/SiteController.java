package com.example.application.controller.admin.site;

import com.example.application.controller.BaseController;
import javafx.event.ActionEvent;

public class SiteController {
    public void createEmployee(ActionEvent actionEvent) {
        BaseController.openNewWindow("admin/site/createSiteView.fxml", "Creation d'un site");
    }

    public void updateEmployee(ActionEvent actionEvent) {
        BaseController.openNewWindow("admin/site/EditSiteView.fxml", "Mise à jour d'un site");
    }
}
