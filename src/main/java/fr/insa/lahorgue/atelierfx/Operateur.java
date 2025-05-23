/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.lahorgue.atelierfx;

/**
 *
 * @author phupka01
 */
public class Operateur {
    private String code;
    private String nom;
    private String prenom;
    private boolean libre;
    //Constructeur

    public Operateur(String code, String nom, String prenom, boolean libre) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.libre = libre;
    }
    //Getter

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public boolean isLibre() {
        return libre;
    }
    //Setter

    public void setCode(String code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }
    
}
