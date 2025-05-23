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
public class Fiabilitetest {
   private static BufferedReader reader = null;
   private static String cheminacces;
   //Constructeur 
    public Fiabilitetest() {
    }
    public static BufferedReader getReader() {
        return reader;
    }

    public static void setReader(BufferedReader reader) {
        Fiabilitetest.reader = reader;
    }

    public static String getCheminacces() {
        return cheminacces;
    }

    //GetSet
    public static void setCheminacces(String cheminacces) {
        Fiabilitetest.cheminacces = cheminacces;
    }

    //Méthodes
    public void RapportFiabilite(Fiabilitetest fiabilite) throws IOException {
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
        int valeur;
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
        for (i=0;i<12;i++){
            
            StringBuilder contenu = new StringBuilder();
            for (j=0;j<5;j++) {
                while ((valeur = reader.read()) != 59) {
                    contenu.append((char) valeur);
                    System.out.println("boucle");
                }
                if (j==0) {                    
                    tbdate.add(contenu.toString()); 
                    System.out.println(tbdate.get(i));
                }
                if (j==1) {
                    tbheure.add(contenu.toString());
                    System.out.println(tbheure.get(i));
                }
                if (j==2) {
                    String currentmach = contenu.toString();
                    boolean exists = false;
                    for (k=0;k<5;k++){
                        if (currentmach.equals(machexist.get(k))) {
                            exists = true;
                            tbmachine.add(currentmach);
                        }
                    }
                    if (exists = false) {
                    System.out.println("attention : machine non trouvée !!");
                    tbmachine.add("erreur");      
                    }
                    System.out.println(tbmachine.get(i));
                }
                if (j==3) {
                    tbevent.add(contenu.toString()); 
                    System.out.println(tbevent.get(i));
                }
                 if (j==4) {
                    tboperateur.add(contenu.toString());  
                    System.out.println(tboperateur.get(i));
                }
            
            }
            while (((valeur = reader.read()) != 10)&&((valeur = reader.read()) != -1)) {
                    contenu.append((char) valeur);
                    tbcause.add(contenu.toString());
                    System.out.println("boucle2");
                }
            System.out.println(tbcause.get(i));

        }
        for (i=0;i<12;i++){

            System.out.println(tbdate.get(i));
            System.out.println(tbheure.get(i));
            System.out.println(tbmachine.get(i));
            System.out.println(tboperateur.get(i));
            System.out.println(tbevent.get(i));            
            System.out.println(tbcause.get(i));
            
        }
    }
}
    
   

