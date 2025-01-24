package com.example.application.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

public class MethodsController {
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
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            return;
        }
    }
}
