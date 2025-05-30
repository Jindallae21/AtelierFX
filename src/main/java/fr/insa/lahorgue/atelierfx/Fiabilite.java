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
 * @author llahorgue01 (le seul et unique !)
 */
// [v6] Cette classe est la version à jour et fonctionelle de la classe Fiabilite, pour la lecture du document et le calcul de la fiabilité de chaque machine ainsi que leur classement.
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
        //Le StringBuilder convertit les caractères lus en une chaîne. 
        return Chainelue;
    }
    public int Conversionheures(String heure){
        String[] heuresplit = heure.split(":");
            int minutecount = Integer.parseInt(heuresplit[1])+60*Integer.parseInt(heuresplit[0]);
            return minutecount;
        }
        
    
    public ArrayList<String> RapportFiabilite(Fiabilite fiabilite) throws IOException {
        //PREMIERE PARTIE : Lecture du fichier de suivi
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
        //machexiste servira de référence à l'existence ou non d'un id de machine lu dans le fichier
        machexist.add("Mach_1");
        machexist.add("Mach_2");
        machexist.add("Mach_3");
        machexist.add("Mach_4");
        machexist.add("Mach_5");      
        for (i=0;i<11;i++){
            //une répétition pour chaque ligne du programme.  
            for (j=0;j<5;j++) {
                //une répétition pour chaque élément de la ligne (la cause étant en fin de ligne, elle est traitée à part)
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
         //FIN DE LA PREMIERE PARTIE 
         //Deuxième partie : calcul de la fiabilité de chaque machine
         //1 : identification des "indices" des machines
         int machcount = machexist.size();
         ArrayList<int[]> indicesmachine = new ArrayList<int[]>();
         
         //Les "indices" se réfèrent à des évennements, i.e. des lignes du fciher. l'indice 0, par exemple, est la première ligne du fichier texte et se refère à cet évenement.
         for (i=0;i<machcount;i++) {
             int[] indiceliste = new int[4]; 
             // 4 est une limite technique choisie pour optimiser le programme. Aucune machine n'a plus de 4 événements liés à elle dans le fichier texte.
             k=0;
             for (j=0;j<tbmachine.size();j++) {
               if (tbmachine.get(j).equals(machexist.get(i))) {
                 indiceliste[k]=j;
                 k++;
                 //on profite du fait que tbmachine a été crée dans l'ordre des indices. Ainsi, la machine en position 4 de l'Arraylist est celle impliquée dans le 4ème évenement. ça sauve du temps de recherche.
               }
                  
               }
             
         for (j=1;j<4;j++) {
             if (indiceliste[j] == 0) {
                 indiceliste[j] = -1;
                 //Cela évitera des confusions dans la suite du programme : tous les espaces vides ont la valeur -1 pour éviter une confusion avec l'indice 0
             }
         }
             indicesmachine.add(indiceliste);
             
         }
         //désormais on a une arraylist dont l'élément i est une liste qui recenscie les positions des évennements impliquant la machine i}
         
//2 : extraction des données utiles : les heures et les "event" (marche ou arrêt)
         //pour simplifier, j'ai choisi de ne pas prendre en compte les dates. On étudiera la fiabilité d'une machine sur 1 jour. Si la machine a un problèmes pendant 2 jours différents, la fiabilite totale sera la moyenne de ces deux jours.
         ArrayList<int[]> indicesheure = new ArrayList<int[]>();
         ArrayList<String[]> indicesevent = new ArrayList<String[]>();
         //Les heures et event des ArrayList seront rangés aux exactes mêmes positions que les indices des machines.
         for (i=0;i<machcount;i++) {
             int[] tempheure = new int[4];
             String[] tempevent = new String[4];
             int [] indiceliste = indicesmachine.get(i);
             for (j=0;j<4;j++) {
                 if (indiceliste[j] != -1) {
                     tempevent[j] = tbevent.get(indiceliste[j]); 
                     tempheure[j] = Conversionheures(tbheure.get(indiceliste[j]));
                 } else {
                    tempevent[j] = "E";
                    tempheure[j] = -1;
                    //les valeurs de "E" pour l'event et "-1" pour l'heure sont choisies pour ne pas être lues par des boucles if au sein du programme.
                  }
             }
             indicesevent.add(tempevent);
             indicesheure.add(tempheure);
         }
         
         //3. Déterminer les durées de non-fonctionnement des machines.
                 ArrayList<Integer> dureearrets = new ArrayList<Integer>(); 
                 //on met integer au lieu de int, il est en effet impossible de créer des ArrayList de int.
        for (i=0;i<machcount;i++) {
            int[] tempheure = indicesheure.get(i); 
            String[] tempevent = indicesevent.get(i); 
            int nbheureD = 0;
            int nbheureA = 0;
            //ces deux variables serviront à distinguer les différentes situations selon le nombre de pannes et de réparations rescencées.
            int dureearret = 0;
            for (j=0;j<4;j++) {
                //on va ici partir du principe que la durée d'inactivité se calcule par "heure de remise en marche - heure d'arrêt". Les heures sont ici comptées en minutes.
                if ("A".equals(tempevent[j])) {
                    dureearret = dureearret - tempheure[j];
                    nbheureA ++;
                }
                if ("D".equals(tempevent[j])) {
                    dureearret = dureearret + tempheure[j];
                    nbheureD ++;
                }
            }
            if (nbheureA == 2) {
                if (nbheureD == 2) {
                    dureearret = dureearret/2; 
                    //C'est le cas où la machine a eu 2 pannes. On fait la moyenne de ces deux pannes (en supposant qu'elles n'arrivent jamais dans la même journée !!)
                }
                if (nbheureD == 1) {
                    dureearret = (dureearret + 20*60)/2 ; 
                    //c'est le cas où il y a eu 1 panne réparée + une panne non réparée. On considère que la deuxième panne se finit à 20h puis on fait la moyenne.
                }
            }
            if (nbheureA == 1) {
                //le cas nbheureA = 1 et nbheureD = 1 n'a pas besoin d'être spécifié, il n'y a pas de changement à faire sur la durée d'arrêt déjà calculée.
                if (nbheureD == 0) {
                    dureearret = dureearret + 20*60; 
                    //1 seule panne non résolue, l'observation s'arrête à 20h.
                }
            }
            dureearrets.add(dureearret); 
         }
         //4. Calculs de fiabilite.
         int dureetotale = (20*60)-(6*60);
         ArrayList<Double> pourcentagefiabilite = new ArrayList <Double>(); //ici aussi, Double au lieu de double
         for (i=0;i<dureearrets.size();i++) {
             double currentdureearret = (double) dureearrets.get(i);
             pourcentagefiabilite.add((double) ((dureetotale - currentdureearret)*100/dureetotale)); //Ici la formule est durée active / duree totale, où durée active = duree totale - durée d'arrêt. On multiplie par 100 pour avoir un pourcentage.
         }
         // FIN DE LA PARTIE 2
         
         //PARTIE 3 : CLASSEMENT DES MACHINES
         ArrayList<Double> copiefiabilite = new ArrayList(pourcentagefiabilite);
         //cette copie est nécessaire car la première boucle for doit toujours être basée sur la taille initiale de pourcentagefiabilite, or le programme nécessite que certains de ces éléments soit retirés !
         ArrayList<Integer> ordreindex = new ArrayList<Integer>();
         int indexation = 0;
         Double currentmax;
         int nbfiabilite = pourcentagefiabilite.size();
         for (i=0;i<nbfiabilite;i++) {
             currentmax=0.0;
             for (j=0;j<copiefiabilite.size();j++) {
                if (copiefiabilite.get(j) >= currentmax) {
                    currentmax = copiefiabilite.get(j); 
                    indexation = j;
                    //programme de tri simple : il compare chaque valeur de fiabilite au maximum courant, et prend sa place si il lui est supérieur.
                 }
             }
             ordreindex.add(indexation);
             //ordreindex correspond aux numéros de placement des machines, rangés dans l'ordre de la meilleure à la moins bonne fiabilité. Il sert à venir chercher les bonnes informations dans les différentes Arraylists ordonées par id.
             copiefiabilite.set(indexation,0.0);
             //Cela évite à la même valeur d'être désignée plusieurs fois comme maxmimum.
         }
         //FIN PARTIE 3
         //ANNONCE DES RESULTATS (à adapter sur l'interface graphique !)
        for (i=0;i<nbfiabilite;i++) {
            System.out.println("la machine en position "+(i+1)+" est la machine "+machexist.get(ordreindex.get(i))+" avec une fiabilite de "+pourcentagefiabilite.get(ordreindex.get(i)));        
            //le choix de machexist vient du fait qu'il contient les id des machines de manière ordonée.
        }
       ArrayList<String> finaldestination = new ArrayList<String>();
       for (i=0;i<nbfiabilite;i++) {
            finaldestination.add("la machine en position "+(i+1)+" est la machine "+machexist.get(ordreindex.get(i))+" avec une fiabilite de "+pourcentagefiabilite.get(ordreindex.get(i)));
            
       }
       return finaldestination;
    }
}
    

    
   

