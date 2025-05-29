package fr.insa.lahorgue.atelierfx;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    // Getters et setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    // Renommage getters pour cohérence (hérité de Equipement)

    /**
     *
     * @return
     */
    @Override
    public String getRefEquipement() { return this.refEquipement; }
    public String getDesignation() { return this.dEquipement; }

    /**
     * Calcul du coût : méthode métier placeholder.
     * @param machine
     * @return 
     */
    public static float calculCout(Machine machine) {
        return machine.getCout();
    }

    /**
     * Charge les machines depuis le fichier (ressource).
     * @return 
     * @throws java.io.IOException
     */
    public static List<List<String>> chargerMachinesDepuisFichier() throws IOException {
        return Utile.importerMemoire("/fr/insa/lahorgue/atelierfx/machine.txt");
    }

    public List<String> toList() {
    return Arrays.asList(
        getRefEquipement(),
        getDesignation(),
        getType(),
        String.valueOf(getX()),
        String.valueOf(getY()),
        String.valueOf(getCout())
    );
}

    /**
     * Enregistre la machine dans un fichier donné, au format texte (virgules).
     * @param cheminFichier
     * @throws java.io.IOException
     */
    public void enregistrerDansFichier(String cheminFichier) throws IOException {
        List<String> champs = Arrays.asList(
            getRefEquipement(),
            getDesignation(),
            getType(),
            String.valueOf(getX()),
            String.valueOf(getY()),
            String.valueOf(getCout())
        );
        Utile.ajouterLigne(cheminFichier, champs);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("Machine[ref=%s, desi=%s, type=%s, x=%.2f, y=%.2f, cout=%.2f]",
                getRefEquipement(), getDesignation(), getType(), getX(), getY(), getCout());
    }
  

}
