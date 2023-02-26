package com.example.javafx_crud.controllers;

import com.example.javafx_crud.models.Modul.Modul;
import com.example.javafx_crud.models.Modul.ModulDAOPSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;

public class ModulController implements Initializable {

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;


    private Stage stage;
    private Scene scene;
    private Parent root;

    ModulDAOPSQL dbModul;

    public void SwitchToScene_Menu(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/com/example/javafx_crud/Scene_menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Connect();
        dbModul = new ModulDAOPSQL();
        table();

    }

    @FXML
    private TableColumn<Modul, String> Id_ModulColumn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Modul, String> id_professorColumn;

    @FXML
    private TableColumn<Modul, String> nomColumn;

    @FXML
    private TableColumn<Modul, String> nom_professorColumn;

    @FXML
    private TableView<Modul> table;

    @FXML
    private TextField txtIdProfessor;

    @FXML
    private TextField txtModul;

    @FXML
    private TextField txtNomProfessor;

    @FXML
    void Add(ActionEvent event) {

        String nom,id_professor;
        //Connect();
        dbModul.connect();
        nom = txtModul.getText();
        id_professor = txtIdProfessor.getText();

            Modul mAux = new Modul();
            mAux.setNom(nom);
            mAux.setId_professor(id_professor);
            dbModul.addModul(mAux);

            table();

            txtModul.setText("");
            txtIdProfessor.setText("");
            txtModul.requestFocus();

    }

    public void table(){
        //Connect();
        dbModul.connect();
        ObservableList<Modul> moduls = FXCollections.observableArrayList();
        try{
            pst = dbModul.con.prepareStatement("select id, nom, id_professor from moduls_professionals");
            ResultSet rs = pst.executeQuery();


            while(rs.next()){
                Modul m = new Modul();
                m.setId(rs.getString("id"));
                m.setNom(rs.getString("nom"));
                String id_prof  = rs.getString("id_professor");
                m.setId_professor(id_prof);
                PreparedStatement pst0 = dbModul.con.prepareStatement("select nom, cognom from professors where id = ?");
                pst0.setString(1, id_prof);
                ResultSet rs0 = pst0.executeQuery();
                if (rs0.next()) {
                    String nom_pro = rs0.getString("nom") ;
                    System.out.println(nom_pro);
                    String cog_prof = rs0.getString("cognom");
                    String nom_complet = nom_pro + " " + cog_prof;
                    m.setNom_professor(nom_complet);
                }


                moduls.add(m);

            }

            table.setItems(moduls);
            Id_ModulColumn.setCellValueFactory(f -> f.getValue().idProperty());
            nomColumn.setCellValueFactory(f -> f.getValue().nomProperty());
            id_professorColumn.setCellValueFactory(f -> f.getValue().id_professorProperty());
            nom_professorColumn.setCellValueFactory(f -> f.getValue().nom_professor());
        }
        catch (SQLException e){
            Logger.getLogger(ProfessorsController.class.getName()).log(Level.SEVERE, null, e);
        }

        table.setRowFactory(tv -> {
            TableRow<Modul> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if(event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtModul.setText(table.getItems().get(myIndex).getNom());
                    txtIdProfessor.setText(table.getItems().get(myIndex).getId_professor());
                    txtNomProfessor.setText(table.getItems().get(myIndex).getNom_professor());
                }
            });
            return myRow;
        });
    }

    @FXML
    void Delete(ActionEvent event) {

        //Connect();
        dbModul.connect();
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        Modul mAux = new Modul();
        mAux.setId(String.valueOf(id));
        dbModul.deleteModul(mAux);

        table();



    }


    @FXML
    void Update(ActionEvent event) {

        String nom, id_professor;
        //Connect();
        dbModul.connect();
        myIndex = table.getSelectionModel().getSelectedIndex();

        id= Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        nom = txtModul.getText();
        id_professor = txtIdProfessor.getText();

        Modul mAux = new Modul();
        mAux.setNom(nom);
        mAux.setId_professor(id_professor);
        mAux.setId(String.valueOf(id));
        dbModul.updateModul(mAux);

        table();



    }
}
