package com.example.javafx_crud.models.Professor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.*;

public class Professor {

    private final StringProperty id;
    private final StringProperty nom;
    private final StringProperty cognom;


    public Professor(){
        id = new SimpleStringProperty(this, "id");
        nom = new SimpleStringProperty(this, "nom");
        cognom = new SimpleStringProperty(this, "cognom");
    }

    public StringProperty idProperty(){ return id; }
    public String getId() { return id.get(); }

    public void setId(String newId) { id.set(newId); }

    public StringProperty nomProperty(){ return nom; }
    public String getNom() { return nom.get(); }
    public void setNom(String newNom){ nom.set(newNom); }

    public StringProperty cognomProperty(){ return cognom; }
    public String getCognom(){ return cognom.get(); }
    public void setCognom(String newCognom){ cognom.set(newCognom); }




}
