package fr.insa.lahorgue.atelierfx;

/**
 * Classe Machine qui hérite d'Equipement.
 * Représente une machine positionnée dans l'atelier.
 */
public class Machine extends Equipement {

    private String type;
    private float x;
    private float y;

    /**
     * Constructeur Machine
     * 
     * @param refMachine Référence de la machine
     * @param dMachine Désignation de la machine
     * @param type Type de machine
     * @param x Coordonnée X
     * @param y Coordonnée Y
     */
    public Machine(String refMachine, String dMachine, String type, float x, float y) {
        super(refMachine, dMachine); // Initialise les champs de la superclasse Equipement
        this.type = type;
        this.x = x;
        this.y = y;
    }

    // Getters et Setters pour les champs propres à Machine

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

    // Accès raccourci à la référence et à la désignation
    public String getRefMachine() {
        return this.refEquipement;
    }

    public String getDMachine() {
        return this.dEquipement;
    }

    /**
     * Méthode de calcul du coût (peut être redéfinie dans d'autres types d'équipement)
     * @param machine l'objet machine
     * @return le coût associé
     */
    public static float CalculCout(Machine machine) {
        // Placeholder : tu peux remplacer cette logique par une vraie formule métier
        return machine.getCout();
    }
}
