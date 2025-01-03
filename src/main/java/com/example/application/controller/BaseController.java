package com.example.application.controller;

import com.example.application.services.EmployeeService;
import com.example.application.services.ServicesService;
import com.example.application.services.SiteService;
import com.example.application.domain.model.Employee;
import com.example.application.domain.model.Services;
import com.example.application.domain.model.Site;
import com.example.application.infrastructure.EmployeeApiAdapter;
import com.example.application.infrastructure.ServicesApiAdapter;
import com.example.application.infrastructure.SiteApiAdapter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BaseController {
    protected final SiteService siteService;
    protected final ServicesService servicesService;
    protected final EmployeeService employeeService;
    @FXML
    private TextField nameTextField;
    @FXML
    protected TableView<Employee> employeeTableView;
    protected TableColumn<Employee, String> nameColumn;
    protected TableColumn<Employee, String> firstNameColumn;
    protected TableColumn<Employee, String> phoneColumn;
    protected TableColumn<Employee, String> mailColumn;
    protected TableColumn<Employee, Services> serviceColumn;
    protected TableColumn<Employee, Site> siteColumn;
    // ComboBox est une liste déroulante
    @FXML
    private ComboBox<Site> siteComboBox;
    @FXML
    private ComboBox<Services> serviceComboBox;

    // On récupere les colonnes mentionnées dans le FXML

    // Constructeur obligatoire
    public BaseController() {
        // L'instance de EmployeeService demande obligatoirement un constructeur
        // creation d'une instance de EmployeeService en utilisant EmployeeApiAdaptater comme source de données
        // EmployeeApiAdapteater sait comment se connecter à l'API et récuprer les données
        // EmployeeService utilise cette instance pour accéder aux données sans se soucier de la connexion
        this.employeeService = new EmployeeService(new EmployeeApiAdapter());
        this.siteService = new SiteService(new SiteApiAdapter());
        this.servicesService = new ServicesService(new ServicesApiAdapter());
    }
    @FXML
    protected void initialize() {
        List<Site> sites = siteService.getAllSite();
        // On vient afficher dans la liste déroulante le résultat de getAll
        siteComboBox.setItems(FXCollections.observableList(sites));
        List<Services> services = servicesService.getAllServices();
        serviceComboBox.setItems(FXCollections.observableList(services));

        // redimension du tableau pour prendre l'ensemble de la largeur
        employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        // Affectation d'une nouvelle colonne avec le nom "nom"
        // On declare notre type d'objet donc ici Employee
        // On set les cellules. Le "name" va directement chercher le getter dans la table Employee
        // On utilise PropertyValueFactory pour lier une colonne à une propriété simple de l'objet
        nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        firstNameColumn = new TableColumn<>("Prenom");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));

        phoneColumn = new TableColumn<>("Telephone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        mailColumn = new TableColumn<>("Mail");
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));

        // expression lambda utile quand il faut traverser plusieurs objets pour accéder à une donnée
        // Ici service est un objet présent dans Employee ou l'ont souhaite acceder au nom
        // Avec une expression Lambda il faut utiliser SimpleStringProperty
        serviceColumn = new TableColumn<>("Service");
        serviceColumn.setCellValueFactory(cellValue -> new SimpleObjectProperty(cellValue.getValue().getServices().getName()));

        siteColumn = new TableColumn<>("Site");
        siteColumn.setCellValueFactory(cellValue -> new SimpleObjectProperty(cellValue.getValue().getSite().getCity()));

        employeeTableView.getColumns().addAll(nameColumn, firstNameColumn, phoneColumn, mailColumn, serviceColumn, siteColumn);

        List<Employee> employees = employeeService.getAllEmployees();
        employeeTableView.getItems().setAll(employees);
    }

    @FXML
    protected void onSearchByNameButtonClick() {
        // On récupere la valeur dans le champs de texte
        String name = nameTextField.getText();

        if (name != null) {
            List<Employee> employees = employeeService.searchByName(name);
            employeeTableView.getItems().setAll(employees);
        } else {
            System.out.println("vide");
        }
    }

    public void onSearchBySiteButtonClick() {
        // On récupere la valeur dans
        Site selectedSite = siteComboBox.getValue();
        if (selectedSite != null) {
            List<Employee> employees = employeeService.searchBySite(selectedSite.getId());
            employeeTableView.getItems().setAll(employees);
        } else {
            System.out.println("Aucun site sélectionné.");
        }
    }

    @FXML
    protected void onSearchByServiceButtonClick() {
        Services selectedServices = serviceComboBox.getValue();
        if (selectedServices != null) {
            List<Employee> employees = employeeService.searchByService(selectedServices.getId());
            employeeTableView.getItems().setAll(employees);
        } else {
            System.out.println("Aucun service");
        }
    }

    public static void openNewWindow(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(BaseController.class.getResource("/com/example/application/" + fxml));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void errorMessage (String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void successMessage (String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
/*
    public void updateEmployeeInDatabase(Employee employee) {
        Employee isUpdated = employeeService.updateEmployee(employee);
        if (isUpdated != null) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Modification enregistrée avec succès !");
            successAlert.show();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la mise à jour !");
            errorAlert.show();
        }
    }

 */

    public void updateInDatabase(Object service, String updateMethod, Object entity) {
        try {
            // Appel de la methode
            // entity permet de passer un objet en entrée
            var method = service.getClass().getMethod(updateMethod, entity.getClass());
            Object result = method.invoke(service, entity);

            if (result != null) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Modification enregistrée avec succès !");
                successAlert.show();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la mise à jour !");
                errorAlert.show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Méthode générique pour supprimer un élément sélectionné dans une TableView.
     *
     * @param <T>         Le type de l'objet dans la TableView.
     * @param tableView   La TableView contenant les éléments.
     * @param successMessage Le message à afficher en cas de succès.
     * @param errorMessage   Le message à afficher en cas d'erreur.
     */
    public static <T> void deleteSelectedItem(TableView<T> tableView, Object service, String getIdMethod, String deleteMethod, String successMessage, String errorMessage) {
        // T est un parametre generique representant un type quelconque
        // TableView<T> signifie que la méthode travaille avec une TableView contenant des objets du type T
        // Obtenir l'element selectionné dans le tableau
        T selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Confirmer la suppression
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Voulez-vous vraiment supprimer cet élément ?", ButtonType.YES, ButtonType.NO);
            confirmationAlert.showAndWait();
            if (confirmationAlert.getResult() == ButtonType.YES) {
                try {
                    // Récupérer la méthode pour obtenir l'ID
                    // (int) = valeur retourné par invokk elle est convertie en int
                    // selectedItem.getClass().getMethod = renvoie la class de l'objet slectedItem, cherche la methode dans le classe de l'objet
                    // pour récuperer l'id donc le getter
                    // invoke permet d'appeler dynamiquement la methode getMethod sur l'objet selectedItem
                    // Le resultat retouré est la valeur de getMethod, donc l'ID
                    int itemId = (int) selectedItem.getClass().getMethod(getIdMethod).invoke(selectedItem);

                    // Appeler la méthode de suppression sur le service
                    // Ici on fait la meme chose que precedemment sauf que nous allons cherche dans le service pour trouver la methode de suppression
                    // int.class correpond au type d'argument attendu par la methode ici un int
                    boolean isDeleted = (boolean) service.getClass()
                            .getMethod(deleteMethod, int.class)
                            .invoke(service, itemId);

                    if (isDeleted) {
                        // Mettre à jour la TableView
                        tableView.getItems().remove(selectedItem);
                        BaseController.successMessage("Succès", successMessage);
                    } else {
                        BaseController.errorMessage("Erreur", errorMessage);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    BaseController.errorMessage("Erreur", "Une erreur est survenue lors de la suppression.");
                }
            }
        } else {
            BaseController.errorMessage("Erreur", "Veuillez sélectionner un élément à supprimer !");
        }
    }
}
