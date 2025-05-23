/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.lahorgue.atelierfx;

/**
 *
 * @author phupka01
 */
public class Equipement {
    //attributs
    private String refEquipement;
    private String dEquipement;
    private float cout;
    //Note : la méthode de calcul du coût sera définie par une méthode, qu'il sera possible de redéfinir dans les sous-classes Machine et Equipement !
    //Constructeur
    public Equipement(String refEquipement, String dEquipement) {
        this.refEquipement = refEquipement;
        this.dEquipement = dEquipement;
    }
   //Getters
    public String getRefEquipement() {
        return refEquipement;
    }

    public String getdEquipement() {
        return dEquipement;
    }

    public float getCout() {
        return cout;
    }
    
    
    //Setters

    public void setRefEquipement(String refEquipement) {
        this.refEquipement = refEquipement;
    }

    public void setdEquipement(String dEquipement) {
        this.dEquipement = dEquipement;
    }

    public void setCout(float cout) {
        this.cout = cout;
    }
    //Une méthode de calcul du cout spéciale devra être ajoutée dans les classes Machine et Poste
}
