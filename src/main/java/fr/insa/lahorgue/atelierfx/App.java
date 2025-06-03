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

        TextField tfDesiPote = new TextField();
        tfDesiMachine.setPromptText("Désignation du poste");
        tfDesiMachine.setStyle(textFieldStyle);

        Button valider = new Button("Valider");
        valider.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10; -fx-padding: 8 16;");
        Button annuler = new Button("Annuler");
        annuler.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10; -fx-padding: 8 16;");

        HBox buttonBox = new HBox(10, valider, annuler);
        buttonBox.setAlignment(Pos.CENTER);

        VBox formBox = new VBox(12, tfRefMachine, tfDesiMachine, tfTypeMachine, tfXMachine, tfYMachine, tfCout, buttonBox, labelMessage);
        formBox.setAlignment(Pos.CENTER);
        formBox.setStyle("-fx-padding: 20;");
        formBox.setVisible(false);

        VBox ligneContainer = new VBox(5);
        ScrollPane scrollPane = new ScrollPane(ligneContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(300);
        scrollPane.setVisible(false);

        TextField tfMotCleSuppression = new TextField();
        tfMotCleSuppression.setPromptText("Référence de la machine");
        tfMotCleSuppression.setStyle(textFieldStyle);
        Button btnSupprimer = new Button("Supprimer");
        btnSupprimer.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight: bold;");
        HBox suppressionBox = new HBox(10, tfMotCleSuppression, btnSupprimer);
        suppressionBox.setAlignment(Pos.CENTER);
        suppressionBox.setVisible(false);

        // Zone pour modification
        TextField tfRefModif = new TextField();
        tfRefModif.setPromptText("Référence machine à modifier");
        tfRefModif.setStyle(textFieldStyle);

        Button btnChargerModif = new Button("Charger");
        btnChargerModif.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox formModifBox = new VBox(10, tfRefModif, btnChargerModif, formBox);
        formModifBox.setAlignment(Pos.CENTER);
        formModifBox.setStyle("-fx-padding: 20;");
        formModifBox.setVisible(false);

        VBox affichageBox = new VBox(10, scrollPane, suppressionBox);
        affichageBox.setAlignment(Pos.CENTER);
        affichageBox.setStyle("-fx-padding: 20;");
        affichageBox.setVisible(false);

        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("Machine");
        Menu menufiable = new Menu("Rapport de fiabilité");
        MenuItem menuItem1 = new MenuItem("Ajouter");
        MenuItem menuItemAfficherSupprimer = new MenuItem("Afficher/Supprimer");
        MenuItem menuItemModifier = new MenuItem("Afficher/Modifier");

        menu1.getItems().addAll(menuItem1, menuItemAfficherSupprimer, menuItemModifier);
        menuBar.getMenus().addAll(menu1, new Menu("Poste"), new Menu("Produit"), new Menu("Gamme"), menufiable);
        
        //BOuton rapport de fiabilité
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
            formBox.setVisible(true);
            formModifBox.setVisible(false);
            affichageBox.setVisible(false);
            boitefiable.setVisible(false);
            labelMessage.setText("");
        });

        // Action du bouton rapport de fiabilité
        affichagefiable.setOnAction(e -> {
            boitefiable.setVisible(true);
            formBox.setVisible(false);
            formModifBox.setVisible(false);
            affichageBox.setVisible(false);
        });

        menuItemAfficherSupprimer.setOnAction(e -> {
            formBox.setVisible(false);
            formModifBox.setVisible(false);
            boitefiable.setVisible(false);
            affichageBox.setVisible(true);
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
                suppressionBox.setVisible(true);
            } catch (IOException ex) {
                ligneContainer.getChildren().add(new Label("Erreur lors de la lecture du fichier : " + ex.getMessage()));
            }
        });

        btnSupprimer.setOnAction(e -> {
            String motCle = tfMotCleSuppression.getText().trim();
            if (!motCle.isEmpty()) {
                try {
                    Utile.supprimerLigne(cheminFichier, motCle);
                    menuItemAfficherSupprimer.fire();
                } catch (IOException ex) {
                    ligneContainer.getChildren().clear();
                    ligneContainer.getChildren().add(new Label("Erreur lors de la suppression : " + ex.getMessage()));
                }
            }
        });

        menuItemModifier.setOnAction(e -> {
            affichageBox.setVisible(false);
            formBox.setVisible(false);
            formModifBox.setVisible(true);
            boitefiable.setVisible(false);
            labelMessage.setText("");
        });

        btnChargerModif.setOnAction(e -> {
            String refCible = tfRefModif.getText().trim();
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
                        formBox.setVisible(true);
                    } else {
                        labelMessage.setStyle("-fx-text-fill: red;");
                        labelMessage.setText("Référence machine non trouvée.");
                        formBox.setVisible(false);
                    }
                } catch (IOException ex) {
                    labelMessage.setStyle("-fx-text-fill: red;");
                    labelMessage.setText("Erreur lecture fichier : " + ex.getMessage());
                    formBox.setVisible(false);
                }
            } else {
                labelMessage.setStyle("-fx-text-fill: red;");
                labelMessage.setText("Veuillez entrer une référence.");
            }
        });

        valider.setOnAction(e -> {
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
                tfRefModif.clear();
                formBox.setVisible(false);
                formModifBox.setVisible(false);

            } catch (NumberFormatException ex) {
                labelMessage.setStyle("-fx-text-fill: red;");
                labelMessage.setText("Erreur : Vérifiez les valeurs numériques.");
            } catch (IOException ex) {
                labelMessage.setStyle("-fx-text-fill: red;");
                labelMessage.setText("Erreur lors de l'écriture dans le fichier : " + ex.getMessage());
            }
        });

        annuler.setOnAction(e -> {
            tfRefMachine.clear();
            tfDesiMachine.clear();
            tfTypeMachine.clear();
            tfXMachine.clear();
            tfYMachine.clear();
            tfCout.clear();
            tfRefModif.clear();
            labelMessage.setText("");
            formBox.setVisible(false);
            formModifBox.setVisible(false);
            boitefiable.setVisible(false);
        });

        VBox mainContent = new VBox(20, boitefiable, formModifBox, affichageBox);
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
