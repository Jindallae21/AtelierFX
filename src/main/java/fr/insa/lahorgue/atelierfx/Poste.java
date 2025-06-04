package fr.insa.lahorgue.atelierfx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Poste extends Equipement {

    private ArrayList<Machine> listeMachines;

    public Poste(ArrayList<Machine> listeMachines, String refEquipement, String designation) {
        super(refEquipement, designation);
        this.listeMachines = listeMachines;
    }

    public ArrayList<Machine> getListeMachines() {
        return listeMachines;
    }

    public void setListeMachines(ArrayList<Machine> listeMachines) {
        this.listeMachines = listeMachines;
    }

    // Retourne une ligne texte pour fichier
    public List<String> toList() {
        List<String> ligne = new ArrayList<>();
        ligne.add(getRefEquipement());
        ligne.add(getDesignation());
        for (Machine m : listeMachines) {
            ligne.add(m.getRefEquipement());
        }
        return ligne;
    }

    public static List<Poste> chargerPostesDepuisFichier(String chemin) throws IOException {
        List<List<String>> lignes = Utile.importerMemoire(chemin);
        List<Machine> toutesMachines = Machine.chargerMachinesDepuisFichier("/fr/insa/lahorgue/atelierfx/machine.txt");
        List<Poste> postes = new ArrayList<>();

        for (List<String> ligne : lignes) {
            if (ligne.size() < 2) continue;

            String refPoste = ligne.get(0);
            String desiPoste = ligne.get(1);
            ArrayList<Machine> machinesPoste = new ArrayList<>();

            for (int i = 2; i < ligne.size(); i++) {
                String refMachine = ligne.get(i);
                for (Machine m : toutesMachines) {
                    if (m.getRefEquipement().equals(refMachine)) {
                        machinesPoste.add(m);
                        break;
                    }
                }
            }
            postes.add(new Poste(machinesPoste, refPoste, desiPoste));
        }
        return postes;
    }

    public void enregistrerDansFichier(String cheminFichier) throws IOException {
        Utile.ajouterLigne(cheminFichier, this.toList());
    }

    public static void supprimerPoste(String cheminFichier, String refPoste) throws IOException {
        Utile.supprimerLigne(cheminFichier, refPoste);
    }

    public static void modifierPoste(String cheminFichier, String refPoste, int indexColonne, String nouveau) throws IOException {
        Utile.modifierElement(cheminFichier, refPoste, indexColonne, nouveau);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Poste[ref=").append(getRefEquipement())
          .append(", desi=").append(getDesignation())
          .append(", machines=[");
        for (Machine m : listeMachines) {
            sb.append(m.getRefEquipement()).append(", ");
        }
        if (!listeMachines.isEmpty()) sb.setLength(sb.length() - 2);
        sb.append("]]");
        return sb.toString();
    }

    @Override
    public float calculCout() {
        float couttotal = 0;
        for (Machine m : listeMachines) {
            couttotal += m.getCout();
        }
        return couttotal;
    }
}
