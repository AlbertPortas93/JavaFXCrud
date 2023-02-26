package com.example.javafx_crud.controllers;

import com.example.javafx_crud.models.Alumne.Alumne;
import com.example.javafx_crud.models.Alumne.AlumneDAOPSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class AlumnesController implements Initializable {



    private Stage stage;
    private Scene scene;
    private Parent root;
    public void SwitchToScene_Menu(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/com/example/javafx_crud/Scene_menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public static Connection conn;
    PreparedStatement pst;
    int myIndex;
    int id;



    @FXML
    private TableColumn<Alumne, String> CognomColumn;

    @FXML
    private TableColumn<Alumne, String> NaixColumn;

    @FXML
    private TableColumn<Alumne, String> NomColumn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Alumne, String> cursColumn;

    @FXML
    private TableColumn<Alumne, String> idColumn;

    @FXML
    private TableColumn<Alumne, String> progenitorsColumn;

    @FXML
    private TableView<Alumne> table;

    @FXML
    private TextField txtCognom;

    @FXML
    private TextField txtCurs;

    @FXML
    private TextField txtNaix;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtProgenitors;

    private static AlumneDAOPSQL bdAlumne;

    @FXML
    void Add(ActionEvent event) {
        String nom,cognom,data_naix,curs, progenitors;

        nom = txtNom.getText();
        cognom = txtCognom.getText();
        data_naix = txtNaix.getText();
        curs = txtCurs.getText();
        progenitors = txtProgenitors.getText();
                //.split(",");

        Alumne aAux = new Alumne();
        aAux.setNom(nom);
        aAux.setCognom(cognom);
        aAux.setData_naix(data_naix);
        aAux.setCurs(curs);
        aAux.setProgenitors(progenitors);

        bdAlumne.addAlumne(aAux);


        table();

        txtNom.setText("");
        txtCognom.setText("");
        txtNaix.setText("");
        txtCurs.setText("");
        txtProgenitors.setText("");
        txtNom.requestFocus();


    }

    private void table() {
        bdAlumne.connection();
        ObservableList<Alumne> alumnes = FXCollections.observableArrayList();
        try{
            pst = bdAlumne.conn.prepareStatement("select id, nom, cognoms, data_naix, curs, progenitors from alumnes");
            System.out.println("hola");
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Alumne a0 = new Alumne();
                a0.setId(rs.getString("id"));
                a0.setNom(rs.getString("nom"));
                a0.setCognom(rs.getString("cognoms"));
                a0.setData_naix(rs.getString("data_naix"));
                a0.setCurs(rs.getString("curs"));
                Array a = rs.getArray("progenitors");
                String[] test = (String[])a.getArray();
                String str = "";
                for (int i = 0; i < test.length; i++)
                {
                    if(i+1 == test.length) str = str + test[i];
                    else str = str + test[i] + ", ";


                }
                a0.setProgenitors(str);

                alumnes.add(a0);

            }

            table.setItems(alumnes);
            idColumn.setCellValueFactory(f -> f.getValue().idProperty());
            NomColumn.setCellValueFactory(f -> f.getValue().nomProperty());
            CognomColumn.setCellValueFactory(f -> f.getValue().cognomProperty());
            NaixColumn.setCellValueFactory(f -> f.getValue().data_naixProperty());
            cursColumn.setCellValueFactory(f -> f.getValue().cursProperty());
            progenitorsColumn.setCellValueFactory(f -> f.getValue().progenitorsProperty());

        }
        catch (SQLException e){
            Logger.getLogger(ProfessorsController.class.getName()).log(Level.SEVERE, null, e);
        }

        table.setRowFactory(tv -> {
            TableRow<Alumne> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if(event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtNom.setText(table.getItems().get(myIndex).getNom());
                    txtCognom.setText(table.getItems().get(myIndex).getCognom());
                    txtNaix.setText(table.getItems().get(myIndex).getData_naix());
                    txtCurs.setText(table.getItems().get(myIndex).getCurs());
                    txtProgenitors.setText(String.valueOf(table.getItems().get(myIndex).getProgenitors()));
                }
            });
            return myRow;
        });
    }

    @FXML
    void Delete(ActionEvent event) {


        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        Alumne aAux = new Alumne();
        aAux.setId(String.valueOf(id));

        bdAlumne.deleteAlumne(aAux);

        table();

    }

    @FXML
    void Update(ActionEvent event) {

        String nom, cognoms, data_naix, curs, progenitors;
        bdAlumne.connection();
        myIndex = table.getSelectionModel().getSelectedIndex();

        id= Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        nom = txtNom.getText();
        cognoms = txtCognom.getText();
        data_naix = txtNaix.getText();
        curs = txtCurs.getText();
        progenitors = txtProgenitors.getText();

        Alumne aAux = new Alumne();
        aAux.setId(String.valueOf(id));
        aAux.setNom(nom);
        aAux.setCognom(cognoms);
        aAux.setData_naix(data_naix);
        aAux.setCurs(curs);
        aAux.setProgenitors(progenitors);

        bdAlumne.updateAlumne(aAux);

        table();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bdAlumne = new AlumneDAOPSQL();
        table();

    }
}
