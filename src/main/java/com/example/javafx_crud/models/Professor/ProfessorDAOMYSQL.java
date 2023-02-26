package com.example.javafx_crud.models.Professor;

import com.example.javafx_crud.controllers.ProfessorsController;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorDAOMYSQL implements ProfessorDAO{
    public Connection con;

    int myIndex;
    int id;

    public ProfessorDAOMYSQL(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam2", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam2", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addProfessor(Professor pAux){
        try{


            PreparedStatement pst = con.prepareStatement("insert into professors(nom, cognom) values (?,?)");
            pst.setString(1, pAux.getNom());
            pst.setString(2, pAux.getCognom());
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Connection");

            alert.setHeaderText("Professors");
            alert.setContentText("Added!");

            alert.showAndWait();

        }
        catch (SQLException e){
            Logger.getLogger(ProfessorsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void deleteProfessor(Professor pAux){

        try{
            PreparedStatement pst = con.prepareStatement("delete from professors where id = ?");
            pst.setInt(1, Integer.parseInt(pAux.getId()));
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Professor Registration");
            alert.setHeaderText("Professor Registration");
            alert.setContentText("Deleted!");
            alert.showAndWait();

        }
        catch (SQLException e){
            Logger.getLogger(ProfessorsController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void updateProfessor(Professor pAux){
        try{
            PreparedStatement pst = con.prepareStatement("update professors set nom = ?, cognom = ? where id = ?");
            pst.setString(1, pAux.getNom());
            pst.setString(2, pAux.getCognom());
            pst.setInt(3, Integer.parseInt(pAux.getId()));
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Professor Registration");
            alert.setHeaderText("Professor Registration");
            alert.setContentText("Updated!");
            alert.showAndWait();


        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProfessorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
