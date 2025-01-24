package com.example.application;

import com.example.application.utils.NavigationManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("homeController.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        primaryStage.setTitle("Annuaire d'entreprise");
        primaryStage.setScene(scene);
        primaryStage.show();
        NavigationManager.getInstance().setPrimaryStage(primaryStage);
    }
}