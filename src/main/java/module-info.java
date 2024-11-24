module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.controller;
    exports com.example.demo1.domain.model;
    opens com.example.demo1.controller to javafx.fxml;
}