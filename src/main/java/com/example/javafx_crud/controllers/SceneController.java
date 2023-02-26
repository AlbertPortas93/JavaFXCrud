package com.example.javafx_crud.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SwitchToScene_Professors(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/javafx_crud/hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void SwitchToScene_Alumnes(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/javafx_crud/alumnes-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToScene_Moduls(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/javafx_crud/modul-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
