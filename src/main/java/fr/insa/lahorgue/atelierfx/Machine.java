package fr.insa.lahorgue.atelierfx;

import java.io.IOException;
import java.util.List;

/**
 * Classe Machine qui hérite d'Equipement.
 * Représente une machine positionnée dans l'atelier.
 */
public class Machine extends Equipement {

    private String type;
    private float x;
    private float y;

    public Machine(String refMachine, String dMachine, String type, float x, float y) {
        super(refMachine, dMachine);
        this.type = type;
        this.x = x;
        this.y = y;
    }

    // Getters et Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public String getRefMachine() { return this.refEquipement; }
    public String getDMachine() { return this.dEquipement; }

    public static float CalculCout(Machine machine) {
        // Placeholder : tu peux remplacer cette logique par une vraie formule métier
        return machine.getCout();
    }
    
     public static List<List<String>> chargerMachinesDepuisFichier() throws IOException {
        return Utile.importerMemoire("fr/insa/lahorgue/atelierfx/machine.txt");
    }
    
}