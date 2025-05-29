package fr.insa.lahorgue.atelierfx;

public class Operation {
    private String refOperation;
    private String designation;
    private String refEquipement;
    private float dureeOperation;

    public Operation(String refOperation, String designation, String refEquipement, float dureeOperation) {
        this.refOperation = refOperation;
        this.designation = designation;
        this.refEquipement = refEquipement;
        this.dureeOperation = dureeOperation;
    }

    // Getters
    public String getRefOperation() {
        return refOperation;
    }

    public String getDesignation() {
        return designation;
    }

    public String getRefEquipement() {
        return refEquipement;
    }

    public float getDureeOperation() {
        return dureeOperation;
    }

    // Setters
    public void setRefOperation(String refOperation) {
        this.refOperation = refOperation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setRefEquipement(String refEquipement) {
        this.refEquipement = refEquipement;
    }

    public void setDureeOperation(float dureeOperation) {
        this.dureeOperation = dureeOperation;
    }
}
