package fr.insa.lahorgue.atelierfx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gamme {
    private String RefGamme;
    private ArrayList<Operation> ListeOperations;

    public Gamme(String RefGamme, ArrayList<Operation> ListeOperations) {
        this.RefGamme = RefGamme;
        this.ListeOperations = ListeOperations;
    }

    // Getter
    public String getRefGamme() {
        return RefGamme;
    }

    public ArrayList<Operation> getListeOperations() {
        return ListeOperations;
    }

    // Retourne la liste unique des équipements référencés par les opérations
    public ArrayList<String> getEquipementsUtilises() {
        ArrayList<String> equipements = new ArrayList<>();
        for (Operation op : ListeOperations) {
            String refEq = op.getRefEquipement();
            if (refEq != null && !equipements.contains(refEq)) {
                equipements.add(refEq);
            }
        }
        return equipements;
    }

    // Importe les gammes depuis le fichier gamme.txt en ressources
    public static List<Gamme> importerGammes() throws IOException {
        String chemin = "/fr/insa/lahorgue/atelierfx/gamme.txt";
        List<List<String>> lignes = Utile.importerMemoire(chemin);
        List<Gamme> gammes = new ArrayList<>();

        for (List<String> ligne : lignes) {
            if (ligne.isEmpty()) continue;

            String refGamme = ligne.get(0);
            ArrayList<Operation> operations = new ArrayList<>();

            if ((ligne.size() - 1) % 4 != 0) {
                System.err.println("Format incorrect dans ligne: " + ligne);
                continue;
            }

            for (int i = 1; i < ligne.size(); i += 4) {
                try {
                    String refOp = ligne.get(i);
                    String desc = ligne.get(i + 1);
                    String refEq = ligne.get(i + 2);
                    float duree = Float.parseFloat(ligne.get(i + 3));
                    operations.add(new Operation(refOp, desc, refEq, duree));
                } catch (Exception e) {
                    System.err.println("Erreur opération: " + e.getMessage());
                }
            }
            gammes.add(new Gamme(refGamme, operations));
        }

        return gammes;
    }

    // Calcule la durée totale de la gamme (somme des durées des opérations)
    public float dureeGamme() {
        float duree = 0;
        for (Operation op : ListeOperations) {
            duree += op.getDureeOperation();
        }
        return duree;
    }
}
