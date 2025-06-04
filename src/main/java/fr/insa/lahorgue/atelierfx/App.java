package fr.insa.lahorgue.atelierfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class App extends Application {

    private final String cheminFichier = Paths.get("data", "machine.txt").toString();

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Atelier de Fabrication");

        // S'assurer que le dossier "data" existe
        try {
            Path dossierData = Paths.get("data");
            if (!Files.exists(dossierData)) {
                Files.createDirectories(dossierData);
            }
        } catch (IOException e) {
            System.err.println("Erreur création dossier data : " + e.getMessage());
        }

        String textFieldStyle = "-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5 10 5 10; -fx-background-color: #f0f0f0; -fx-border-color: #ccc;";

        TextField tfRefMachine = new TextField();
        tfRefMachine.setPromptText("Référence de la machine");
        tfRefMachine.setStyle(textFieldStyle);

        TextField tfDesiMachine = new TextField();
        tfDesiMachine.setPromptText("Désignation de la machine");
        tfDesiMachine.setStyle(textFieldStyle);

        TextField tfTypeMachine = new TextField();
        tfTypeMachine.setPromptText("Type de machine");
        tfTypeMachine.setStyle(textFieldStyle);

        TextField tfXMachine = new TextField();
        tfXMachine.setPromptText("Position X de la machine");
        tfXMachine.setStyle(textFieldStyle);

        TextField tfYMachine = new TextField();
        tfYMachine.setPromptText("Position Y de la machine");
        tfYMachine.setStyle(textFieldStyle);

        TextField tfCout = new TextField();
        tfCout.setPromptText("Coût horaire de la machine");
        tfCout.setStyle(textFieldStyle);

        Label labelMessage = new Label();
        labelMessage.setStyle("-fx-text-fill: red;");
        
        TextField tfRefPoste = new TextField();
        tfRefMachine.setPromptText("Référence du poste");
        tfRefMachine.setStyle(textFieldStyle);

        TextField tfDesiPoste = new TextField();
        tfDesiMachine.setPromptText("Désignation du poste");
        tfDesiMachine.setStyle(textFieldStyle);
        
        TextField tfListeMachine = new TextField();
        tfDesiMachine.setPromptText("Liste de Machines, séparées par ';'");
        tfDesiMachine.setStyle(textFieldStyle);

        Button validerMachine = new Button("Valider");
        validerMachine.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10; -fx-padding: 8 16;");
        Button annulerMachine = new Button("Annuler");
        annulerMachine.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10; -fx-padding: 8 16;");

        HBox buttonBoxMachine = new HBox(10, validerMachine, annulerMachine);
        buttonBoxMachine.setAlignment(Pos.CENTER);
        
         Button validerPoste = new Button("Valider");
        validerMachine.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10; -fx-padding: 8 16;");
        Button annulerPoste = new Button("Annuler");
        annulerMachine.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10; -fx-padding: 8 16;");

        HBox buttonBoxPoste= new HBox(10, validerPoste, annulerPoste);
        buttonBoxMachine.setAlignment(Pos.CENTER);

        VBox machineBox = new VBox(12, tfRefMachine, tfDesiMachine, tfTypeMachine, tfXMachine, tfYMachine, tfCout, buttonBoxMachine, labelMessage);
        machineBox.setAlignment(Pos.CENTER);
        machineBox.setStyle("-fx-padding: 20;");
        machineBox.setVisible(false);
        
        VBox posteBox = new VBox(12, tfRefPoste, tfDesiPoste, tfListeMachine, buttonBoxPoste, labelMessage);
        posteBox.setAlignment(Pos.CENTER);
        posteBox.setStyle("-fx-padding: 20;");
        posteBox.setVisible(false);

        VBox ligneContainer = new VBox(5);
        ScrollPane scrollPane = new ScrollPane(ligneContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(300);
        scrollPane.setVisible(false);

        TextField tfMotCleSuppressionMachine = new TextField();
        tfMotCleSuppressionMachine.setPromptText("Référence de la machine");
        tfMotCleSuppressionMachine.setStyle(textFieldStyle);
        Button btnSupprimerMachine = new Button("Supprimer");
        btnSupprimerMachine.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight: bold;");
        HBox suppressionBoxMachine = new HBox(10, tfMotCleSuppressionMachine, btnSupprimerMachine);
        suppressionBoxMachine.setAlignment(Pos.CENTER);
        suppressionBoxMachine.setVisible(false);
        
        TextField tfMotCleSuppressionPoste = new TextField();
        tfMotCleSuppressionPoste.setPromptText("Référence de la machine");
        tfMotCleSuppressionPoste.setStyle(textFieldStyle);
        Button btnSupprimerPoste = new Button("Supprimer");
        btnSupprimerPoste.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight: bold;");
        HBox suppressionBoxPoste = new HBox(10, tfMotCleSuppressionPoste, btnSupprimerPoste);
        suppressionBoxPoste.setAlignment(Pos.CENTER);
        suppressionBoxPoste.setVisible(false);

        // Zone pour modification machine et poste
        TextField tfRefModifMachine = new TextField();
        tfRefModifMachine.setPromptText("Référence machine à modifier");
        tfRefModifMachine.setStyle(textFieldStyle);

        Button btnChargerModifMachine = new Button("Charger");
        btnChargerModifMachine.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox formModifBoxMachine = new VBox(10, tfRefModifMachine, btnChargerModifMachine, machineBox);
        formModifBoxMachine.setAlignment(Pos.CENTER);
        formModifBoxMachine.setStyle("-fx-padding: 20;");
        formModifBoxMachine.setVisible(false);

        VBox affichageBoxMachine = new VBox(10, scrollPane, suppressionBoxMachine);
        affichageBoxMachine.setAlignment(Pos.CENTER);
        affichageBoxMachine.setStyle("-fx-padding: 20;");
        affichageBoxMachine.setVisible(false);
        
        //Partie Poste
        
        TextField tfRefModifPoste = new TextField();
        tfRefModifPoste.setPromptText("Référence post à modifier");
        tfRefModifPoste.setStyle(textFieldStyle);

        Button btnChargerModifPoste = new Button("Charger");
        btnChargerModifPoste.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox formModifBoxPoste = new VBox(10, tfRefModifMachine, btnChargerModifMachine, machineBox);
        formModifBoxPoste.setAlignment(Pos.CENTER);
        formModifBoxPoste.setStyle("-fx-padding: 20;");
        formModifBoxPoste.setVisible(false);

        VBox affichageBoxPoste = new VBox(10, scrollPane, suppressionBoxMachine);
        affichageBoxPoste.setAlignment(Pos.CENTER);
        affichageBoxPoste.setStyle("-fx-padding: 20;");
        affichageBoxPoste.setVisible(false);
        
        //définition des menus

        MenuBar menuBar = new MenuBar();
        Menu menufiable = new Menu("Rapport de fiabilité");
        
        Menu menu1 = new Menu("Machine");
        MenuItem menuItem1 = new MenuItem("Ajouter");
        MenuItem menuItemAfficherSupprimerMachine = new MenuItem("Afficher/Supprimer");
        MenuItem menuItemModifierMachine = new MenuItem("Afficher/Modifier");
        
        Menu menu2 = new Menu("Poste");
        MenuItem menuItem2 = new MenuItem("Ajouter");
        MenuItem menuItemAfficherSupprimerPoste = new MenuItem("Afficher/Supprimer");
        MenuItem menuItemModifierPoste = new MenuItem("Afficher/Modifier");

        menu1.getItems().addAll(menuItem1, menuItemAfficherSupprimerMachine, menuItemModifierMachine);
        menu2.getItems().addAll(menuItem2, menuItemAfficherSupprimerPoste, menuItemModifierPoste);
        menuBar.getMenus().addAll(menu1, menu2, new Menu("Produit"), new Menu("Gamme"), menufiable);
        
        //Bouton rapport de fiabilité
        MenuItem affichagefiable = new MenuItem("Afficher le rapport");
        menufiable.getItems().add(affichagefiable);
        Fiabilite instancefiable = new Fiabilite();
        ArrayList<String> newhome = new ArrayList<>(instancefiable.RapportFiabilite(instancefiable));
        StringBuilder rapportfinal = new StringBuilder();
        for (String ligne : newhome) {
            rapportfinal.append(ligne).append("\n");
        }
        Text labelfiable1 = new Text("Lecture du fichier SuiviMaintenance.txt\nRécupération des données du fichier\nCalcul des fiabilités de chaque machine\nClassement des machines...");
        Text labelfiable2 = new Text(rapportfinal.toString());
        labelfiable1.setWrappingWidth(600);
        labelfiable2.setWrappingWidth(600);
        labelfiable1.setVisible(true);
        labelfiable2.setVisible(true);
        VBox boitefiable = new VBox(10, labelfiable1, labelfiable2);     
        boitefiable.setStyle("-fx-padding: 20;");
        boitefiable.setAlignment(Pos.CENTER);
        boitefiable.setVisible(false);

        // Suppression du bouton X et spacer
        HBox topBar = new HBox(menuBar);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-padding: 5px;");

        menuItem1.setOnAction(e -> {
            machineBox.setVisible(true);
            formModifBoxMachine.setVisible(false);
            affichageBoxMachine.setVisible(false);
            boitefiable.setVisible(false);
            labelMessage.setText("");
        });
        
        menuItem2.setOnAction(e -> {
            
            machineBox.setVisible(false);
            formModifBoxMachine.setVisible(false);
            affichageBoxMachine.setVisible(false);
            posteBox.setVisible(true);
            formModifBoxPoste.setVisible(false);
            affichageBoxPoste.setVisible(false);
            boitefiable.setVisible(false);
            labelMessage.setText("");
        });

        // Action du bouton rapport de fiabilité
        affichagefiable.setOnAction(e -> {
            boitefiable.setVisible(true);
            machineBox.setVisible(false);
            formModifBoxMachine.setVisible(false);
            affichageBoxMachine.setVisible(false);
            posteBox.setVisible(false);
            formModifBoxPoste.setVisible(false);
            affichageBoxPoste.setVisible(false);
        });

        menuItemAfficherSupprimerMachine.setOnAction(e -> {
            machineBox.setVisible(false);
            formModifBoxMachine.setVisible(false);
            boitefiable.setVisible(false);
            affichageBoxMachine.setVisible(true);
            posteBox.setVisible(false);
            formModifBoxPoste.setVisible(false);
            affichageBoxPoste.setVisible(false);
            scrollPane.setVisible(true);
            ligneContainer.getChildren().clear();

            try {
                List<Machine> machines = Machine.chargerMachinesDepuisFichier(cheminFichier);

                for (Machine machine : machines) {
                    HBox ligneHBox = new HBox(20);

                    Label labelRef = new Label(machine.getRefEquipement());
                    Label labelDesi = new Label(machine.getDesignation());
                    Label labelType = new Label(machine.getType());
                    Label labelX = new Label(String.valueOf(machine.getX()));
                    Label labelY = new Label(String.valueOf(machine.getY()));
                    Label labelCout = new Label(String.valueOf(machine.getCout()));

                    labelRef.setMinWidth(150);
                    labelDesi.setMinWidth(150);
                    labelType.setMinWidth(150);
                    labelX.setMinWidth(100);
                    labelY.setMinWidth(100);
                    labelCout.setMinWidth(100);

                    ligneHBox.getChildren().addAll(labelRef, labelDesi, labelType, labelX, labelY, labelCout);
                    ligneContainer.getChildren().add(ligneHBox);
                }
                suppressionBoxMachine.setVisible(true);
            } catch (IOException ex) {
                ligneContainer.getChildren().add(new Label("Erreur lors de la lecture du fichier : " + ex.getMessage()));
            }
        });
        
        menuItemAfficherSupprimerPoste.setOnAction(e -> {
            machineBox.setVisible(false);
            formModifBoxMachine.setVisible(false);
            boitefiable.setVisible(false);
            affichageBoxMachine.setVisible(true);
            scrollPane.setVisible(true);
            posteBox.setVisible(true);
            formModifBoxPoste.setVisible(false);
            affichageBoxPoste.setVisible(true);
            ligneContainer.getChildren().clear();

            try {
                List<Poste> postes = Poste.chargerPostesDepuisFichier(cheminFichier);

                for (Poste poste : postes) {
                    HBox ligneHBox = new HBox(20);

                    Label labelRefPoste = new Label(poste.getRefEquipement());
                    Label labelDesiPoste = new Label(poste.getDesignation());
                    int i;
                    String listemachine = "";
                    for (i=0;i<poste.getListeMachines().size();i++ ) {
                        listemachine = listemachine + poste.getListeMachines().get(i)+" " ;
                    }
                    Label labelListeMachine = new Label(listemachine);

                    labelRefPoste.setMinWidth(150);
                    labelDesiPoste.setMinWidth(150);
                    labelListeMachine.setMinWidth(150);

                    ligneHBox.getChildren().addAll(labelRefPoste, labelDesiPoste, labelListeMachine);
                    ligneContainer.getChildren().add(ligneHBox);
                }
                suppressionBoxMachine.setVisible(true);
            } catch (IOException ex) {
                ligneContainer.getChildren().add(new Label("Erreur lors de la lecture du fichier : " + ex.getMessage()));
            }
        });

        btnSupprimerMachine.setOnAction(e -> {
            String motCleMachine = tfMotCleSuppressionMachine.getText().trim();
            if (!motCleMachine.isEmpty()) {
                try {
                    Utile.supprimerLigne(cheminFichier, motCleMachine);
                    menuItemAfficherSupprimerMachine.fire();
                } catch (IOException ex) {
                    ligneContainer.getChildren().clear();
                    ligneContainer.getChildren().add(new Label("Erreur lors de la suppression : " + ex.getMessage()));
                }
            }
        });
        
        btnSupprimerPoste.setOnAction(e -> {
            String motClePoste = tfMotCleSuppressionPoste.getText().trim();
            if (!motClePoste.isEmpty()) {
                try {
                    Utile.supprimerLigne(cheminFichier, motClePoste);
                    menuItemAfficherSupprimerPoste.fire();
                } catch (IOException ex) {
                    ligneContainer.getChildren().clear();
                    ligneContainer.getChildren().add(new Label("Erreur lors de la suppression : " + ex.getMessage()));
                }
            }
        });

        menuItemModifierMachine.setOnAction(e -> {
            affichageBoxMachine.setVisible(false);
            machineBox.setVisible(false);
            formModifBoxMachine.setVisible(true);
            posteBox.setVisible(true);
            formModifBoxPoste.setVisible(false);
            affichageBoxPoste.setVisible(false);
            boitefiable.setVisible(false);
            labelMessage.setText("");
        });
        
        menuItemModifierPoste.setOnAction(e -> {
            affichageBoxMachine.setVisible(false);
            machineBox.setVisible(false);
            formModifBoxMachine.setVisible(true);
            posteBox.setVisible(true);
            formModifBoxPoste.setVisible(true);
            affichageBoxPoste.setVisible(false);
            boitefiable.setVisible(false);
            labelMessage.setText("");
        });

        btnChargerModifMachine.setOnAction(e -> {
            String refCible = tfRefModifMachine.getText().trim();
            if (!refCible.isEmpty()) {
                try {
                    List<Machine> machines = Machine.chargerMachinesDepuisFichier(cheminFichier);
                    Machine machineTrouvee = null;
                    for (Machine m : machines) {
                        if (m.getRefEquipement().equals(refCible)) {
                            machineTrouvee = m;
                            break;
                        }
                    }
                    if (machineTrouvee != null) {
                        // Remplir les champs avec les valeurs trouvées
                        tfRefMachine.setText(machineTrouvee.getRefEquipement());
                        tfDesiMachine.setText(machineTrouvee.getDesignation());
                        tfTypeMachine.setText(machineTrouvee.getType());
                        tfXMachine.setText(String.valueOf(machineTrouvee.getX()));
                        tfYMachine.setText(String.valueOf(machineTrouvee.getY()));
                        tfCout.setText(String.valueOf(machineTrouvee.getCout()));
                        labelMessage.setText("Machine chargée, modifiez les champs et cliquez sur Valider.");
                        machineBox.setVisible(true);
                    } else {
                        labelMessage.setStyle("-fx-text-fill: red;");
                        labelMessage.setText("Référence machine non trouvée.");
                        machineBox.setVisible(false);
                    }
                } catch (IOException ex) {
                    labelMessage.setStyle("-fx-text-fill: red;");
                    labelMessage.setText("Erreur lecture fichier : " + ex.getMessage());
                    machineBox.setVisible(false);
                }
            } else {
                labelMessage.setStyle("-fx-text-fill: red;");
                labelMessage.setText("Veuillez entrer une référence.");
            }
        });

        btnChargerModifPoste.setOnAction(e -> {
            String refCiblePoste = tfRefModifMachine.getText().trim();
            if (!refCiblePoste.isEmpty()) {
                try {
                    List<Poste> postes = Poste.chargerPostesDepuisFichier(cheminFichier);
                    Poste posteTrouvee = null;
                    for (Poste p : postes) {
                        if (p.getRefEquipement().equals(refCiblePoste)) {
                            posteTrouvee = p;
                            break;
                        }
                    }
                    if (posteTrouvee != null) {
                        // Remplir les champs avec les valeurs trouvées
                        tfRefPoste.setText(posteTrouvee.getRefEquipement());
                        tfDesiPoste.setText(posteTrouvee.getDesignation());
                        int i;
                        String listemachines = "";
                        for (i=0;i<posteTrouvee.getListeMachines().size();i++){
                           listemachines = listemachines + posteTrouvee.getListeMachines().get(i)+" ; ";
                        }
                        tfListeMachine.setText(listemachines);
                        labelMessage.setText("Poste chargé, modifiez les champs et cliquez sur Valider.");
                        posteBox.setVisible(true);
                    } else {
                        labelMessage.setStyle("-fx-text-fill: red;");
                        labelMessage.setText("Référence poste non trouvée.");
                        posteBox.setVisible(false);
                    }
                } catch (IOException ex) {
                    labelMessage.setStyle("-fx-text-fill: red;");
                    labelMessage.setText("Erreur lecture fichier : " + ex.getMessage());
                    posteBox.setVisible(false);
                }
            } else {
                labelMessage.setStyle("-fx-text-fill: red;");
                labelMessage.setText("Veuillez entrer une référence.");
            }
        });
        
        validerMachine.setOnAction(e -> {
            try {
                String refEquipement = tfRefMachine.getText().trim();
                String dEquipement = tfDesiMachine.getText().trim();
                String type = tfTypeMachine.getText().trim();
                float x = Float.parseFloat(tfXMachine.getText().trim());
                float y = Float.parseFloat(tfYMachine.getText().trim());
                float cout = Float.parseFloat(tfCout.getText().trim());

                Machine machine = new Machine(refEquipement, dEquipement, type, x, y);
                machine.setCout(cout);

                // Vérifier si la machine existe déjà
                List<Machine> machines = Machine.chargerMachinesDepuisFichier(cheminFichier);
                boolean existe = false;
                for (Machine m : machines) {
                    if (m.getRefEquipement().equals(refEquipement)) {
                        existe = true;
                        break;
                    }
                }

                if (existe) {
                    // Modifier la machine dans le fichier (remplacer ligne)
                    // On va utiliser une méthode Utile.modifierElement pour chaque colonne
                    // Format fichier : ref;designation;type;x;y;cout

                    Utile.modifierElement(cheminFichier, refEquipement, 0, refEquipement);
                    Utile.modifierElement(cheminFichier, refEquipement, 1, dEquipement);
                    Utile.modifierElement(cheminFichier, refEquipement, 2, type);
                    Utile.modifierElement(cheminFichier, refEquipement, 3, String.valueOf(x));
                    Utile.modifierElement(cheminFichier, refEquipement, 4, String.valueOf(y));
                    Utile.modifierElement(cheminFichier, refEquipement, 5, String.valueOf(cout));

                    labelMessage.setStyle("-fx-text-fill: green;");
                    labelMessage.setText("Machine modifiée avec succès.");
                } else {
                    // Ajouter la nouvelle machine
                    Utile.ajouterLigne(cheminFichier, machine.toList());
                    labelMessage.setStyle("-fx-text-fill: green;");
                    labelMessage.setText("Machine ajoutée avec succès.");
                }

                // Reset formulaire et cacher la zone modif
                tfRefMachine.clear();
                tfDesiMachine.clear();
                tfTypeMachine.clear();
                tfXMachine.clear();
                tfYMachine.clear();
                tfCout.clear();
                tfRefModifMachine.clear();
                machineBox.setVisible(false);
                formModifBoxMachine.setVisible(false);

            } catch (NumberFormatException ex) {
                labelMessage.setStyle("-fx-text-fill: red;");
                labelMessage.setText("Erreur : Vérifiez les valeurs numériques.");
            } catch (IOException ex) {
                labelMessage.setStyle("-fx-text-fill: red;");
                labelMessage.setText("Erreur lors de l'écriture dans le fichier : " + ex.getMessage());
            }
        });
        
        validerPoste.setOnAction(e -> {
            try {
                String refEquipementPoste = tfRefPoste.getText().trim();
                String dEquipementPoste= tfDesiPoste.getText().trim();
                ArrayList<Machine> listemachine = new ArrayList<Machine>(Machine.chargerMachinesDepuisFichier(cheminFichier));
                String listemachinestring = "";
                int i;
                for (i=0;i<listemachine.size();i++) {
                    listemachinestring = listemachinestring + listemachine.get(i).getDesignation() + ";";
                }                      
                Poste poste = new Poste(listemachine, refEquipementPoste, dEquipementPoste);

                // Vérifier si le poste existe déjà
                List<Poste> postes = Poste.chargerPostesDepuisFichier(cheminFichier);
                boolean existe = false;
                for (Poste p : postes) {
                    if (p.getRefEquipement().equals(refEquipementPoste)) {
                        existe = true;
                        break;
                    }
                }

                if (existe) {
                    // Modifier le poste dans le fichier (remplacer ligne)
                    // On va utiliser une méthode Utile.modifierElement pour chaque colonne
                    // Format fichier : ref;designation;listemachine

                    Utile.modifierElement(cheminFichier, refEquipementPoste, 0, refEquipementPoste);
                    Utile.modifierElement(cheminFichier, refEquipementPoste, 1, dEquipementPoste);
                    Utile.modifierElement(cheminFichier, refEquipementPoste, 2, listemachinestring);

                    labelMessage.setStyle("-fx-text-fill: green;");
                    labelMessage.setText("Machine modifiée avec succès.");
                } else {
                    // Ajouter le nouveau poste
                    Utile.ajouterLigne(cheminFichier, poste.toList());
                    labelMessage.setStyle("-fx-text-fill: green;");
                    labelMessage.setText("Machine ajoutée avec succès.");
                }

                // Reset formulaire et cacher la zone modif
                tfRefPoste.clear();
                tfDesiPoste.clear();
                tfListeMachine.clear();
                posteBox.setVisible(false);
                formModifBoxPoste.setVisible(false);

            } catch (NumberFormatException ex) {
                labelMessage.setStyle("-fx-text-fill: red;");
                labelMessage.setText("Erreur : Vérifiez les valeurs numériques.");
            } catch (IOException ex) {
                labelMessage.setStyle("-fx-text-fill: red;");
                labelMessage.setText("Erreur lors de l'écriture dans le fichier : " + ex.getMessage());
            }
        });
        annulerMachine.setOnAction(e -> {
            tfRefMachine.clear();
            tfDesiMachine.clear();
            tfTypeMachine.clear();
            tfXMachine.clear();
            tfYMachine.clear();
            tfCout.clear();
            tfRefModifMachine.clear();
            labelMessage.setText("");
            machineBox.setVisible(false);
            formModifBoxMachine.setVisible(false);
            boitefiable.setVisible(false);
        });

        VBox mainContent = new VBox(20, boitefiable, machineBox, posteBox, formModifBoxMachine, affichageBoxMachine, formModifBoxPoste, affichageBoxPoste);
        mainContent.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainContent);
        
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
