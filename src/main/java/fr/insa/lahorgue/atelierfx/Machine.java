package fr.insa.lahorgue.atelierfx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Machine extends Equipement {
    private String type;
    private float x;
    private float y;

    public Machine(String refMachine, String designation, String type, float x, float y) {
        super(refMachine, designation);
        this.type = type;
        this.x = x;
        this.y = y;
    }

    // Getters et setters spécifiques
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    @Override
    public float calculCout() {
        // Ici on peut définir une méthode métier réelle, pour l'instant renvoie cout
        return this.getCout();
    }

    // Convertit la machine en liste de champs pour stockage fichier
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

    // Chargement depuis fichier ressources
   public static List<Machine> chargerMachinesDepuisFichier(String cheminFichier) throws IOException {
    List<Machine> machines = new ArrayList<>();
    List<String> lignes = Files.readAllLines(Paths.get(cheminFichier));

    for (String ligne : lignes) {
        String[] parts = ligne.split(";");  // ou autre séparateur
        if (parts.length >= 6) {
            String ref = parts[0];
            String desi = parts[1];
            String type = parts[2];
            float x = Float.parseFloat(parts[3]);
            float y = Float.parseFloat(parts[4]);
            float cout = Float.parseFloat(parts[5]);

            Machine m = new Machine(ref, desi, type, x, y);
            m.setCout(cout);
            machines.add(m);
        }
    }
    return machines;
}


    // Enregistrement dans fichier
    public void enregistrerDansFichier(String cheminFichier) throws IOException {
        Utile.ajouterLigne(cheminFichier, this.toList());
    }

    @Override
    public String toString() {
        return String.format("Machine[ref=%s, desi=%s, type=%s, x=%.2f, y=%.2f, cout=%.2f]",
                getRefEquipement(), getDesignation(), getType(), getX(), getY(), getCout());
    }
}
