package fr.insa.lahorgue.atelierfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utile {
    
    /**
     * Importe un fichier texte depuis les ressources, avec des champs séparés par des virgules.
     * Chaque ligne est découpée en liste de chaînes.
     *
     * @param fichier chemin relatif (ex: "/fr/insa/lahorgue/atelierfx/machine.txt")
     * @return Liste de lignes, où chaque ligne est une liste de champs
     * @throws IOException si fichier introuvable ou erreur lecture
     */
    public static List<List<String>> importerMemoire(String fichier) throws IOException {
        List<List<String>> tableau = new ArrayList<>();
        
        InputStream input = Utile.class.getResourceAsStream(fichier.startsWith("/") ? fichier : "/" + fichier);
        if (input == null) {
            throw new IOException("Fichier introuvable : " + fichier);
        }
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] valeurs = ligne.split(",");
                List<String> ligneListe = new ArrayList<>();
                for (String val : valeurs) {
                    ligneListe.add(val.trim());
                }
                tableau.add(ligneListe);
            }
        }
        return tableau;
    }
    
    /**
     * Ajoute une ligne (liste de colonnes) à un fichier texte.
     * Crée le fichier et le dossier parent si nécessaire.
     *
     * @param cheminFichier chemin complet vers le fichier
     * @param colonnes liste des valeurs à écrire séparées par des virgules
     * @throws IOException en cas d'erreur d'écriture
     */
    public static void ajouterLigne(String cheminFichier, List<String> colonnes) throws IOException {
        String ligne = String.join(",", colonnes);
        Path path = Paths.get(cheminFichier);
        
        // Crée les dossiers parents s'ils n'existent pas
        if (path.getParent() != null && !Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        
        // Ecrit en ajoutant la ligne, crée le fichier s'il n'existe pas
        Files.writeString(path, ligne + System.lineSeparator(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }
    
    /**
     * Supprime toutes les lignes d'un fichier contenant un texte donné.
     *
     * @param cheminFichier chemin complet du fichier
     * @param texteCible texte à chercher pour identifier les lignes à supprimer
     * @throws IOException en cas d'erreur d'accès ou d'écriture
     */
    public static void supprimerLigne(String cheminFichier, String texteCible) throws IOException {
        Path path = Paths.get(cheminFichier);
        
        List<String> lignes = Files.readAllLines(path);
        
        List<String> lignesFiltrees = lignes.stream()
                .filter(ligne -> !ligne.contains(texteCible))
                .collect(Collectors.toList());
        
        Files.write(path, lignesFiltrees);
    }
    
    /**
     * Modifie une valeur dans une colonne donnée pour la ligne contenant texteCible.
     * Si plusieurs lignes contiennent texteCible, modifie toutes.
     *
     * @param cheminFichier chemin complet du fichier
     * @param texteCible texte à rechercher dans la ligne
     * @param indexColonne indice de la colonne à modifier (0-based)
     * @param nouveau nouvelle valeur à mettre dans la colonne
     * @throws IOException en cas d'erreur d'écriture
     */
    public static void modifierElement(String cheminFichier, String texteCible, int indexColonne, String nouveau) throws IOException {
        Path path = Paths.get(cheminFichier);
        List<String> lignes = Files.readAllLines(path);
        
        List<String> lignesModifiees = new ArrayList<>();
        
        for (String ligne : lignes) {
            if (ligne.contains(texteCible)) {
                String[] colonnes = ligne.split(",");
                if (indexColonne >= 0 && indexColonne < colonnes.length) {
                    colonnes[indexColonne] = nouveau.trim();
                    ligne = String.join(",", colonnes);
                }
            }
            lignesModifiees.add(ligne);
        }
        Files.write(path, lignesModifiees);
    }
    
}
