package com.example.javafx_crud.controllers;

import com.example.javafx_crud.models.Professor.Professor;
import com.example.javafx_crud.models.Professor.ProfessorDAOMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProfessorsController implements Initializable {


    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;


    private Stage stage;
    private Scene scene;
    private Parent root;
    ProfessorDAOMYSQL dbProfessor;
    public void SwitchToScene_Menu(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/com/example/javafx_crud/Scene_menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        dbProfessor = new ProfessorDAOMYSQL();
        table();
    }


    @FXML
    private TableColumn<Professor, String> CognomColumn;

    @FXML
    private TableColumn<Professor, String> IdColumn;

    @FXML
    private TableColumn<Professor, String> NomColumn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Professor> table;

    @FXML
    private TextField txtCognom;

    @FXML
    private TextField txtName;



    @FXML
    void Add(ActionEvent event) {
        String nom,cognom;
        nom = txtName.getText();
        cognom = txtCognom.getText();

        Professor pAux = new Professor();
        pAux.setNom(nom);
        pAux.setCognom(cognom);

        dbProfessor.addProfessor(pAux);

        table();

        txtName.setText("");
        txtCognom.setText("");
        txtName.requestFocus();

    }


    public void table(){

        dbProfessor.connection();
        ObservableList<Professor> professors = FXCollections.observableArrayList();
        try{
            pst = dbProfessor.con.prepareStatement("select id, nom, cognom from professors");
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Professor pf = new Professor();
                pf.setId(rs.getString("id"));
                pf.setNom(rs.getString("nom"));
                pf.setCognom(rs.getString("cognom"));
                professors.add(pf);

            }

            table.setItems(professors);
            IdColumn.setCellValueFactory(f -> f.getValue().idProperty());
            NomColumn.setCellValueFactory(f -> f.getValue().nomProperty());
            CognomColumn.setCellValueFactory(f -> f.getValue().cognomProperty());
        }
        catch (SQLException e){
            Logger.getLogger(ProfessorsController.class.getName()).log(Level.SEVERE, null, e);
        }

        table.setRowFactory(tv -> {
            TableRow<Professor> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if(event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtName.setText(table.getItems().get(myIndex).getNom());
                    txtCognom.setText(table.getItems().get(myIndex).getCognom());
                }
            });
            return myRow;
        });
    }


    @FXML
    void Delete(ActionEvent event) {

        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        Professor pAux = new Professor();
        pAux.setId(String.valueOf(id));

        dbProfessor.deleteProfessor(pAux);

        table();

    }

    @FXML
    void update(ActionEvent event) {

        String nom, cognom;

        dbProfessor.connection();
        myIndex = table.getSelectionModel().getSelectedIndex();

        id= Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        nom = txtName.getText();
        cognom = txtCognom.getText();

        Professor pAux = new Professor();
        pAux.setId(String.valueOf(id));
        pAux.setNom(nom);
        pAux.setCognom(cognom);

        dbProfessor.updateProfessor(pAux);

        table();

    }

}