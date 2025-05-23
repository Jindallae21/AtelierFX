/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.lahorgue.atelierfx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author phupka01
 */
public class Gamme {
    //attributs
    private String RefGamme;
    private ArrayList<Operation> ListeOperations;
    private ArrayList<Equipement> ListeEquipements;
   
   private static BufferedReader reader = null;
   private static String cheminacces;
  
   
    
//Constructeur

    public Gamme(String RefGamme, ArrayList<Operation> ListeOperations, ArrayList<Equipement> ListeEquipements) {
        this.RefGamme = RefGamme;
        this.ListeOperations = ListeOperations;
        this.ListeEquipements = ListeEquipements;
    }
    //Getters

    public static String getCheminacces() {
        return cheminacces;
    }

    public static BufferedReader getReader() {
        return reader;
    }

    public String getRefGamme() {
        return RefGamme;
    }

    public ArrayList<Operation> getListeOperations() {
        return ListeOperations;
    }

    public ArrayList<Equipement> getListeEquipements() {
        return ListeEquipements;
    }
    //Setters

    public static void setCheminacces(String cheminacces) {
        Gamme.cheminacces = cheminacces;
    }

    public static void setReader(BufferedReader reader) {
        Gamme.reader = reader;
    }

    public void setRefGamme(String RefGamme) {
        this.RefGamme = RefGamme;
    }

    public void setListeOperations(ArrayList<Operation> ListeOperations) {
        this.ListeOperations = ListeOperations;
    }

    public void setListeEquipements(ArrayList<Equipement> ListeEquipements) {
        this.ListeEquipements = ListeEquipements;
    }
    //Méthodes
     //Méthodes de gestion
      public static void CreerGamme(String RefGamme, ArrayList<Operation> ListeOperations, ArrayList<Equipement> ListeEquipements){
        cheminacces = "Gammes.txt";
        reader = new BufferedReader(new FileReader(cheminacces));

      }
    public void modifierGamme(){
        //ouvrir une interface permettant de modifier le contenu des gammes 
    }    
    public void supprimerGamme() {
          
    }
    public void AfficheGamme(Gamme GammeAff) {
        System.out.println(GammeAff.getRefGamme());
    }
    public static float coutGamme(Gamme Gamme){
       ArrayList<Equipement> equipements = Gamme.getListeEquipements();
       float duree = Gamme.dureeGamme(Gamme);
       float cout = 0;
       int i;
       for (i=0; i<equipements.size(); i++){
           cout = cout + equipements.get(i).getCout();
       }
        return duree*cout; 
    }
    public float dureeGamme(Gamme Gamme) {
        ArrayList<Operation> operations = Gamme.getListeOperations();
        float duree = 0;
        int i;
        for (i=0; i<operations.size(); i++){ 
           duree = duree + operations.get(i).getDureeOperation();   
        }
        return duree;
    }
}
