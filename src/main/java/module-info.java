module com.example.javafx_crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafx_crud to javafx.fxml;
    exports com.example.javafx_crud;

    exports com.example.javafx_crud.models.Professor;
    opens com.example.javafx_crud.models.Professor to javafx.fxml;

    exports com.example.javafx_crud.models.Alumne;
    opens com.example.javafx_crud.models.Alumne to javafx.fxml;

    exports com.example.javafx_crud.controllers;
    opens com.example.javafx_crud.controllers to javafx.fxml;
}