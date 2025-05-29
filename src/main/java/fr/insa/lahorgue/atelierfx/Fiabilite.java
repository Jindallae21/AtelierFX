/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.lahorgue.atelierfx;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;       
import java.io.BufferedReader;
import java.util.ArrayList;
/**
 *
 * @author llahorgue01 (eh ouais B-) )
 */
public class Fiabilite {
   private static BufferedReader reader = null;
   private static String cheminacces;
   //Constructeur 
    public Fiabilite() {
    }
    public static BufferedReader getReader() {
        return reader;
    }

    public static void setReader(BufferedReader reader) {
        Fiabilite.reader = reader;
    }

    public static String getCheminacces() {
        return cheminacces;
    }

    //GetSet
    public static void setCheminacces(String cheminacces) {
        Fiabilite.cheminacces = cheminacces;
    }

    //Méthodes
    //sous-méthode de lecture
    public static String LectureRapport(int caractererecherche, BufferedReader br) throws IOException {
        int valeur;
        String Chainelue;
        StringBuilder contenu = new StringBuilder();
        while ((valeur = br.read()) != caractererecherche) {
                    contenu.append((char) valeur);                
                }
        Chainelue = contenu.toString();
        System.out.println("Lu : "+Chainelue);
        return Chainelue;
    }
    public void RapportFiabilite(Fiabilite fiabilite) throws IOException {
        try {
            cheminacces = "SuiviMaintenance.txt";
            reader = new BufferedReader(new FileReader(cheminacces));
        }
        catch(FileNotFoundException exc)
        {
            System.out.println("Fichier non trouvé");
        }
        
        int i;
        int j;
        int k;
        String currentread;
        ArrayList<String> tbdate = new ArrayList<String>();
        ArrayList<String> tbheure = new ArrayList<String>();
        ArrayList<String> tbcause = new ArrayList<String>();
        ArrayList<String> tbmachine = new ArrayList<String>();
        ArrayList<String> tboperateur = new ArrayList<String>();
        ArrayList<String> tbevent = new ArrayList<String>();
        ArrayList<String> machexist = new ArrayList<String>();
        machexist.add("Mach_1");
        machexist.add("Mach_2");
        machexist.add("Mach_3");
        machexist.add("Mach_4");
        machexist.add("Mach_5");      
        for (i=0;i<11;i++){
            
            
            for (j=0;j<5;j++) {
                currentread = LectureRapport(59,reader);
                if (j==0) { 
                    tbdate.add(currentread);
                    System.out.println("date");
                    
                }
                if (j==1) {
                    tbheure.add(currentread);
                    System.out.println("heure");
                }
                if (j==2) {
                    boolean exists = false;
                    for (k=0;k<5;k++){
                        if (currentread.equals(machexist.get(k))) {
                            exists = true;
                            tbmachine.add(currentread);
                        }
                    }
                    if (exists = false) {
                    System.out.println("attention : machine non trouvée !!");
                    tbmachine.add("erreur");      
                    }                                      
                    System.out.println("mach");
                    System.out.println(tbmachine.get(i));
                }
                if (j==3) {
                    tbevent.add(currentread); 
                    System.out.println("event");  
                }
                 if (j==4) {
                    tboperateur.add(currentread);  
                    System.out.println("oper");
                    
                }
            }
            if (i==10) {
               currentread = LectureRapport(-1,reader);  
            } else {
               currentread = LectureRapport(10,reader);

            }
            tbcause.add(currentread);                      
                    System.out.println("cause");
                    System.out.println(tbcause.get(i));  
        }

         for (i=0;i<11;i++){

            System.out.print(tbdate.get(i));
            System.out.print(tbheure.get(i));
            System.out.print(tbmachine.get(i));
            System.out.print(tboperateur.get(i));
            System.out.print(tbevent.get(i));            
            System.out.println(tbcause.get(i));
            
        }
    }
}
    

    
   

