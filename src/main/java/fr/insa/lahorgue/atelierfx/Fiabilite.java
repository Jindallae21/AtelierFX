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
   private ArrayList<String> tbmachine;
   private ArrayList<String> tbdates;
   private ArrayList<String> tbheure;
   private ArrayList<String> tboperateur;
   private ArrayList<String> tbevent;
   private ArrayList<String> tbcause;
   private static BufferedReader reader = null;
   private static String cheminacces;
   //Constructeur 
    public Fiabilite() {
    }
    //GetSet
public ArrayList<String> getTbmachine(){
        return tbmachine;
}

    public void setTbmachine(ArrayList<String> tbmachine) {
        this.tbmachine = tbmachine;
    }

    public ArrayList<String> getTbdates() {
        return tbdates;
    }

    public void setTbdates(ArrayList<String> tbdates) {
        this.tbdates = tbdates;
    }

    public ArrayList<String> getTbheure() {
        return tbheure;
    }

    public void setTbheure(ArrayList<String> tbheure) {  
        this.tbheure = tbheure;
    }

    public ArrayList<String> getTboperateur() {
        return tboperateur;
    }

    public void setTboperateur(ArrayList<String> tboperateur) {
        this.tboperateur = tboperateur;
    }

    public ArrayList<String> getTbevent() {
        return tbevent;
    }

    public void setTbevent(ArrayList<String> tbevent) {
        this.tbevent = tbevent;
    }

    public ArrayList<String> getTbcause() {
        return tbcause;
    }

    public void setTbcause(ArrayList<String> tbcause) {
        this.tbcause = tbcause;
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
        int valeur;
        int position = 1;
        ArrayList<String> temptb = new ArrayList <String>();
        ArrayList<String> machexist = new ArrayList<String>();
        machexist.add("Mach_1");
        machexist.add("Mach_2");
        machexist.add("Mach_3");
        machexist.add("Mach_4");
        machexist.add("Mach_5");      
        for (i=0;i<12;i++){
            StringBuilder contenu = new StringBuilder();
            for (j=0; j<5;j++){
                while ((valeur = reader.read()) != 59) {
                    contenu.append((char) valeur);
                }
                if (j==0) {
                    temptb = getTbdates();
                    temptb.add(contenu.toString());
                    setTbdates(temptb);  
                }
                if (j==1) {
                    temptb = getTbheure();
                    temptb.add(contenu.toString());
                    setTbheure(temptb);  
                }
                if (j==2) {
                    String currentmach = contenu.toString();
                    boolean exists = false;
                    for (k=0;k<5;k++){
                        if (currentmach.equals(machexist.get(k))) {
                            exists = true;
                        }
                    }
                     temptb = getTbmachine();
                    if (exists==true) {                    
                    temptb.add(currentmach);
                        } else {
                        System.out.println("attention : machine non trouvée !!");
                    temptb.add("erreur");      
                    }
                 setTbmachine(temptb);
                }
                if (j==3) {
                    temptb = getTbevent();
                    temptb.add(contenu.toString());
                    setTbevent(temptb);  
                }
                 if (j==4) {
                    temptb = getTboperateur();
                    temptb.add(contenu.toString());
                    setTboperateur(temptb);  
                }
                
            }
            while ((valeur = reader.read()) != -1) {
                    contenu.append((char) valeur);
                    temptb = getTbcause();
                    temptb.add(contenu.toString());
                    setTbcause(temptb);
                }

        }
        for (i=0;i<12;i++){
            System.out.println(getTbcause());
            System.out.println(getTbdates());
            System.out.println(getTbheure());
            System.out.println(getTbmachine());
            System.out.println(getTboperateur());
            System.out.println(getTbevent());
            
        }
    }
}
    
   

