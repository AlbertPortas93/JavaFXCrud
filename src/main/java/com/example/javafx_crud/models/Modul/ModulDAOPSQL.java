package com.example.javafx_crud.models.Modul;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModulDAOPSQL implements ModulDAO{
    public Connection con;

    public ModulDAOPSQL(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam2", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam2", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addModul(Modul mAux){
        try{
            PreparedStatement pst = con.prepareStatement("insert into moduls_professionals(nom, id_professor) values (?,?)");
            pst.setString(1, mAux.getNom());
            pst.setString(2,mAux.getId_professor());
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Connection");

            alert.setHeaderText("Moduls");
            alert.setContentText("Added!");

            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteModul(Modul mAux){
        try{
           PreparedStatement pst = con.prepareStatement("delete from moduls_professionals where id = ?");
            pst.setString(1, mAux.getId());
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Moduls Registration");
            alert.setHeaderText("Moduls Registration");
            alert.setContentText("Deleted!");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateModul(Modul mAux){
        try{
            PreparedStatement pst = con.prepareStatement("update moduls_professionals set nom = ?, id_professor = ? where id = ?");
            pst.setString(1, mAux.getNom());
            pst.setString(2, mAux.getId_professor());
            pst.setString(3, mAux.getId());
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modul Registration");
            alert.setHeaderText("Modul Registration");
            alert.setContentText("Updated!");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
