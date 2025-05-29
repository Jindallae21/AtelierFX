/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.lahorgue.atelierfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pierr
 */
public class Utile {
    
    
    public static List<List<String>> importerMemoire (String fichier) throws IOException{
            
           List<List<String>> tableau = new ArrayList<>();
           
           InputStream input = Utile.class.getResourceAsStream(fichier.startsWith("/") ? fichier : "/" + fichier);

        
        if (input==null){
            throw new IOException("Fichier introuvable : "+fichier);
        }
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))){
            String ligne;
            while ((ligne = reader.readLine())!=null){
                String[] valeurs = ligne.split(",");
                List<String>ligneListe = new ArrayList<>();
                for (String val : valeurs) {
                    ligneListe.add(val.trim());
                }
            tableau.add(ligneListe);
            }
        }
    return tableau;    
    }

    
    public static void supprimerLigne(String cheminFichier, String texteCible) throws IOException {
        Path path = Path.of(cheminFichier);

        // Lire toutes les lignes
        List<String> lignes = Files.readAllLines(path);

        // Filtrer pour ne garder que les lignes qui ne contiennent pas le texte cible
        List<String> lignesFiltrees;
        lignesFiltrees = lignes.stream()
                .filter(ligne -> !ligne.contains(texteCible))
                .collect(Collectors.toList());

        // Réécrire le fichier avec les lignes filtrées
        Files.write(path, lignesFiltrees);
    }
}
