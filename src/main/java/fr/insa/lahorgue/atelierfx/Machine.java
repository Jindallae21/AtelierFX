package fr.insa.lahorgue.atelierfx;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public static void sauvegarderMachinesDansFichier(List<Machine> machines, String cheminFichier) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(cheminFichier))) {
            for (Machine m : machines) {
                // on écrit la machine au format CSV (séparateur ;)
                writer.write(String.join(";", m.toList()));
                writer.newLine();
            }
        }
    }
    
    
        /**
     * Modifie une colonne spécifique dans une ligne ciblée par un texte dans le fichier.
     *
     * @param cheminFichier chemin du fichier
     * @param texteCible texte pour identifier la ligne à modifier (ex: référence machine)
     * @param indexColonne index de la colonne à modifier (0-based)
     * @param nouveau nouvelle valeur à mettre dans la colonne
     * @throws IOException en cas d'erreur IO
     */
    public static void modifierElement(String cheminFichier, String texteCible, int indexColonne, String nouveau) throws IOException {
        Path path = Paths.get(cheminFichier);
        List<String> lignes = Files.readAllLines(path);

        List<String> lignesModifiees = new ArrayList<>();

        for (String ligne : lignes) {
            if (ligne.contains(texteCible)) {
                String[] colonnes = ligne.split(";");
                if (indexColonne >= 0 && indexColonne < colonnes.length) {
                    colonnes[indexColonne] = nouveau.trim();
                    ligne = String.join(";", colonnes);
                }
            }
            lignesModifiees.add(ligne);
        }
        Files.write(path, lignesModifiees);
    }

}