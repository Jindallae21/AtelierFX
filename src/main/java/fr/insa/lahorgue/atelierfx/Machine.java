/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.lahorgue.atelierfx;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author phupka01
 */
public class Machine extends Equipement {   
    private String type;
    private float x;
    private float y;


    
    //Getters et Setter
    public String getRefMachine() {
        return refMachine;
    }

    public void setRefMachine(String refMachine) {
        this.refMachine = refMachine;
    }

    public String getdMachine() {
        return dMachine;
    }

    public void setdMachine(String dMachine) {
        this.dMachine = dMachine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     *
     * @return
     */
    @Override
    public float getCout() {
        return cout;
    }

    /**
     *
     * @param cout
     */
    @Override
    public void setCout(float cout) {
        this.cout = cout;
    }
    
    //Constructeur

    public Machine(String refMachine, String dMachine, String type, float x, float y) {
        super(refEquipement, dEquipement);
        this.refMachine = refMachine;
        this.dMachine = dMachine;
        this.type = type;
        this.x = x;
        this.y = y;
    }
//Méthodes
    public static float Calculcout(Machine Machine){
        return Machine.getCout();
        //écrire ici la méthode de calcul du cout d'une machine
    }
    
}
    
    

