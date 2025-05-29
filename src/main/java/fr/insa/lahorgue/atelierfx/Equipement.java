package fr.insa.lahorgue.atelierfx;

public class Equipement {
    protected String refEquipement;
    protected String designation;
    protected float cout;

    // Constructeur
    public Equipement(String refEquipement, String designation) {
        this.refEquipement = refEquipement;
        this.designation = designation;
    }

    // Getters
    public String getRefEquipement() {
        return refEquipement;
    }

    public String getDesignation() {
        return designation;
    }

    public float getCout() {
        return cout;
    }

    // Setters
    public void setRefEquipement(String refEquipement) {
        this.refEquipement = refEquipement;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setCout(float cout) {
        this.cout = cout;
    }

    // Méthode calcul coût (à redéfinir dans les sous-classes si besoin)
    public float calculCout() {
        return cout;
    }
}
