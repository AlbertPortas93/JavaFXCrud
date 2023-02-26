package com.example.javafx_crud.models.Alumne;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Alumne {

    private final StringProperty id;
    private final StringProperty nom;
    private final StringProperty cognom;

    private final StringProperty data_naix;

    private final StringProperty curs;

    private final StringProperty progenitors;



    public Alumne(){
        id = new SimpleStringProperty(this, "id");
        nom = new SimpleStringProperty(this, "nom");
        cognom = new SimpleStringProperty(this, "cognom");
        data_naix = new SimpleStringProperty(this, "data_naix");
        curs = new SimpleStringProperty(this, "curs");
        progenitors = new SimpleStringProperty(this, "progenitors") ;
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

    public StringProperty data_naixProperty(){ return data_naix; }
    public String getData_naix(){ return data_naix.get(); }
    public void setData_naix(String newData_naix){ data_naix.set(newData_naix); }

    public StringProperty cursProperty(){ return curs; }
    public String getCurs(){ return curs.get(); }
    public void setCurs(String newCurs){ curs.set(newCurs); }

    public StringProperty progenitorsProperty(){ return progenitors; }
    public String getProgenitors(){ return progenitors.get(); }
    public void setProgenitors(String newProgenitors){ progenitors.set(newProgenitors); }





}
