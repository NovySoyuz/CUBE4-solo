module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.example.application to javafx.fxml;
    exports com.example.application;
    exports com.example.application.controller;
    exports com.example.application.domain.model;
    opens com.example.application.controller to javafx.fxml;
    exports com.example.application.controller.admin;
    opens com.example.application.controller.admin to javafx.fxml;
    exports com.example.application.controller.admin.employee;
    opens com.example.application.controller.admin.employee to javafx.fxml;
}