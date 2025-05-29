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
// [v2] Cette classe est la version à jour et fonctionelle de la classe Fiabilite, pour la lecture du document uniquement pour le moment.
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
     //l'utilisation ici d'un BufferedReader en tant qu'argument évite de devoir en créer un nouveau à chaque fois, qui relirait le fchier du début.   
        int valeur;
        String Chainelue;
        StringBuilder contenu = new StringBuilder();
        while ((valeur = br.read()) != caractererecherche) {
                    contenu.append((char) valeur);
                    //Le reader lit tous les caractères jusqu'à un caractère donné (représenté par son unicode)
                }
        Chainelue = contenu.toString();
        System.out.println("Lu : "+Chainelue);
        //Le StringBuilder convertit les caractères lus en une chaîne. 
        return Chainelue;
    }
    public void RapportFiabilite(Fiabilite fiabilite) throws IOException {
        //PREMIERE MOITIEE : Lectur du fichier suivi maintenance
        try {
            cheminacces = "SuiviMaintenance.txt";
            reader = new BufferedReader(new FileReader(cheminacces));
        }
        catch(FileNotFoundException exc)
        {
            System.out.println("Fichier non trouvé");
        }
        //le try-catch évite un problème en cas de fichier manquant. La classe IOException gère ce problème.
        int i;
        int j;
        int k;
        int l;
        //ce sont des itérateurs ici.
        String currentread;
        ArrayList<String> tbdate = new ArrayList<String>();
        ArrayList<String> tbheure = new ArrayList<String>();
        ArrayList<String> tbcause = new ArrayList<String>();
        ArrayList<String> tbmachine = new ArrayList<String>();
        ArrayList<String> tboperateur = new ArrayList<String>();
        ArrayList<String> tbevent = new ArrayList<String>();
        //Ces tableaux seront remplis par la méthode progressivement, et seront ensuite utilisés par le programme pour calculer la fiabilité
        ArrayList<String> machexist = new ArrayList<String>();
        //machexiste servira de référence à l'existence ou non d'un id de machine lu par le programme
        machexist.add("Mach_1");
        machexist.add("Mach_2");
        machexist.add("Mach_3");
        machexist.add("Mach_4");
        machexist.add("Mach_5");      
        for (i=0;i<11;i++){
            //une répétition pour chaque ligne du programme.  
            for (j=0;j<5;j++) {
                //une répétition pour chaque élément de la ligne (la cause étant en fin de ligne, elle est traitée à part
                currentread = LectureRapport(59,reader);
                //selon la valeur de j, i.e la position de la chaîne lue dans la ligne, la chaîne est attribué à la liste correspondante.
                if (j==0) { 
                    tbdate.add(currentread);                  
                }
                if (j==1) {
                    tbheure.add(currentread);
                }
                if (j==2) {
                    boolean exists = false;
                    for (k=0;k<5;k++){
                        if (currentread.equals(machexist.get(k))) {
                            exists = true;
                            tbmachine.add(currentread);
                            //il vérifie que la machine lue existe. Il cherche pour cela si elle est présente dans une lsite de référence "machexist"
                        }
                    }
                    if (exists = false) {
                    System.out.println("attention : machine non trouvée !!");
                    tbmachine.add("erreur");      
                    //il renvoie erreur si la machine n'existe pas. Le mot "erreur" prend la place de l'identifiant de la machine dans la liste, si elle existait
                    }                                      
                    System.out.println(tbmachine.get(i));
                }
                if (j==3) {
                    tbevent.add(currentread);  
                }
                 if (j==4) {
                    tboperateur.add(currentread);      
                }
            }
            if (i==10) {
               currentread = LectureRapport(-1,reader);  
            } else {
               currentread = LectureRapport(10,reader);
//La cause étant placé en fin de ligne, la lectue ce fait jusqu'au de ligne (représenté en unicode par le chiffre 10). Sur la dernière ligne du programme, le reader lit jusqu'à la fin du texte entier, représenté par un -1
            }
            tbcause.add(currentread);                       
        }
         for (i=0;i<11;i++){

            System.out.print(tbdate.get(i));
            System.out.print(tbheure.get(i));
            System.out.print(tbmachine.get(i));
            System.out.print(tboperateur.get(i));
            System.out.print(tbevent.get(i));            
            System.out.println(tbcause.get(i));
            
        }
         //Deuxième moititée : calcul de la fiabilité de chaque machine
         //1 : identification des "indices" des machines
         int machcount = machexist.size();
         ArrayList<int[]> indicesmachine = new ArrayList<int[]>();
         //Les "indices" se réfèrent à des évennements, i.e. des lignes du fciher. l'indice 0, par exemple, est la première ligne du fichier texte et se refère à cet évenement.
         for (i=0;i<machcount;i++) {
             int[] indiceliste = new int[4]; //Limite technique choisie pour optimiser le programme. Aucune machine n'a plus de 4 événements liés à elle dans le programme.
             k=0;
             for (j=0;j<tbmachine.size();j++) {
               if (tbmachine.get(j).equals(machexist.get(i))) {
                 indiceliste[k]=j;
                 k++;
                 //on profite du fait que tbmachine a été crée dans l'ordre des indices. Ainsi, la machine en position 4 de l'Arraylist est celle impliquée dans le 4ème évenement. ça sauve du temps de recherche.
               }
             }
             indicesmachine.add(indiceliste);
             
         }
         //désormais on a une arraylist dont l'élément i est une liste qui recenscie les positions des évennements impliquant la machine i}
         
//2 : extraction des données utiles : les heures et les types de problème
         //pour simplifier, j'ai choisi de ne pas prendre en compte les dates. On étudiera la fiabilité d'une machine sur 1 jour. Si la machine a un problèmes pendant 2 jours différents, la fiabilite totale sera la moyenne de ces deux jours.
         ArrayList<int[]> heuresuivi = new ArrayList<int[]>();
         ArrayList<String[]> causesuivi = new ArrayList<String[]>();
         //Les heures et cause des ArrayList seront rangés aux exactes mêmes positions que les indices des machines.
         
    }
}
    

    
   

