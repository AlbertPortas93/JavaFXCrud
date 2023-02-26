package com.example.javafx_crud.models.Modul;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Modul {
    private final StringProperty id_modul;
    private final StringProperty nom;
    private final StringProperty id_professor;

    private final StringProperty nom_professor;


    public Modul(){
        id_modul = new SimpleStringProperty(this, "id");
        nom = new SimpleStringProperty(this, "nom");
        id_professor = new SimpleStringProperty(this, "id_professor");
        nom_professor = new SimpleStringProperty(this, "nom_professor");

    }

    public StringProperty idProperty(){ return id_modul; }
    public String getId() { return id_modul.get(); }

    public void setId(String newId) { id_modul.set(newId); }

    public StringProperty nomProperty(){ return nom; }
    public String getNom() { return nom.get(); }
    public void setNom(String newNom){ nom.set(newNom); }

    public StringProperty id_professorProperty(){ return id_professor; }
    public String getId_professor(){ return id_professor.get(); }
    public void setId_professor(String newCognom){ id_professor.set(newCognom); }

    public StringProperty nom_professor(){ return nom_professor; };
    public String getNom_professor(){ return nom_professor.get(); };

    public void setNom_professor(String newNom_professor){ nom_professor.set(newNom_professor);}




}
