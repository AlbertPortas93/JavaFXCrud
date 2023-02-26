package com.example.javafx_crud.models.Alumne;

import com.example.javafx_crud.controllers.ProfessorsController;
import com.example.javafx_crud.controllers.ModulController;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumneDAOPSQL implements AlumneDAO{

    public Connection conn;

    public AlumneDAOPSQL()
    {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "SonicyMario93";
        {
            try {
                conn = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void connection(){
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "SonicyMario93";
        {
            try {
                conn = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void addAlumne(Alumne Aaux){
        try{
            PreparedStatement pst = conn.prepareStatement("INSERT INTO alumnes(NOM, cognoms, data_naix, curs, progenitors) values (?,?,?,?::\"curs_type\",?)");

            Array progenitors_final = conn.createArrayOf("varchar", new String[]{Aaux.getProgenitors()});

            pst.setString(1, Aaux.getNom());
            pst.setString(2, Aaux.getCognom());
            pst.setString(3, Aaux.getData_naix());
            pst.setString(4, Aaux.getCurs());
            pst.setArray(5, progenitors_final);

            pst.executeUpdate();




        }
        catch (SQLException e){
            Logger.getLogger(ProfessorsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public void deleteAlumne(Alumne aAux){

        try{
            PreparedStatement pst = conn.prepareStatement("delete from alumnes where id = ?");
            pst.setInt(1, Integer.parseInt(aAux.getId()));
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Moduls Registration");
            alert.setHeaderText("Moduls Registration");
            alert.setContentText("Deleted!");
            alert.showAndWait();

        }
        catch (SQLException e){
            Logger.getLogger(ModulController.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    @Override
    public void updateAlumne(Alumne aAux) {
        try{
            PreparedStatement pst = conn.prepareStatement("update alumnes set nom = ?, cognoms = ? ,data_naix = ?, curs = ?::\"curs_type\", progenitors = ? where id = ?");

            Array progenitors_final = conn.createArrayOf("varchar", new String[]{aAux.getProgenitors()});

            pst.setString(1, aAux.getNom());
            pst.setString(2, aAux.getCognom());
            pst.setString(3, aAux.getData_naix());
            pst.setString(4, aAux.getCurs());
            pst.setArray(5, progenitors_final);
            pst.setInt(6, Integer.parseInt(aAux.getId()));



            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modul Registration");
            alert.setHeaderText("Modul Registration");
            alert.setContentText("Updated!");
            alert.showAndWait();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ModulController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
