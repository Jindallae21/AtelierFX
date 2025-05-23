/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.lahorgue.atelierfx;

/**
 *
 * @author phupka01
 */
public class Operation {
    private String refOperation;
    private String dOperation;
    private String refEquipement;
    private float dureeOperation;
    
        //Constructeur

    public Operation(String refOperation, String dOperation, String refEquipement, float dureeOperation) {
        this.refOperation = refOperation;
        this.dOperation = dOperation;
        this.refEquipement = refEquipement;
        this.dureeOperation = dureeOperation;
    }
    
    //Getters

    public String getRefOperation() {
        return refOperation;
    }

    public String getdOperation() {
        return dOperation;
    }

    public String getRefEquipement() {
        return refEquipement;
    }

    public float getDureeOperation() {
        return dureeOperation;
    }
    
    
    //Setters

    public void setRefOperation(String refOperation) {
        this.refOperation = refOperation;
    }

    public void setdOperation(String dOperation) {
        this.dOperation = dOperation;
    }

    public void setRefEquipement(String refEquipement) {
        this.refEquipement = refEquipement;
    }

    public void setDureeOperation(float dureeOperation) {
        this.dureeOperation = dureeOperation;
    }
}

