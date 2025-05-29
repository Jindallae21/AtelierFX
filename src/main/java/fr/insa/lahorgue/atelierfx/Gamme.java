package fr.insa.lahorgue.atelierfx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gamme {
    private String refGamme;
    private ArrayList<Operation> listeOperations;

    public Gamme(String refGamme, ArrayList<Operation> listeOperations) {
        this.refGamme = refGamme;
        this.listeOperations = listeOperations;
    }

    public String getRefGamme() {
        return refGamme;
    }

    public ArrayList<Operation> getListeOperations() {
        return listeOperations;
    }

    public ArrayList<String> getEquipementsUtilises() {
        ArrayList<String> equipements = new ArrayList<>();
        for (Operation op : listeOperations) {
            String refEq = op.getRefEquipement();
            if (refEq != null && !equipements.contains(refEq)) {
                equipements.add(refEq);
            }
        }
        return equipements;
    }

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
                    String desi = ligne.get(i + 1);
                    String refEq = ligne.get(i + 2);
                    float duree = Float.parseFloat(ligne.get(i + 3));
                    operations.add(new Operation(refOp, desi, refEq, duree));
                } catch (Exception e) {
                    System.err.println("Erreur opération: " + e.getMessage());
                }
            }
            gammes.add(new Gamme(refGamme, operations));
        }
        return gammes;
    }

    public float dureeGamme() {
        float duree = 0;
        for (Operation op : listeOperations) {
            duree += op.getDureeOperation();
        }
        return duree;
    }
}
