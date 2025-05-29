package fr.insa.lahorgue.atelierfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App extends Application {

    private final String cheminFichier = Paths.get("data", "machine.txt").toString();

    @Override
    public void start(Stage primaryStage) {
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

        VBox affichageBox = new VBox(10, scrollPane, suppressionBox);
        affichageBox.setAlignment(Pos.CENTER);
        affichageBox.setStyle("-fx-padding: 20;");

        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("Machine");
        MenuItem menuItem1 = new MenuItem("Ajouter");
        MenuItem menuItemAfficherSupprimer = new MenuItem("Afficher/Supprimer");
        menu1.getItems().addAll(menuItem1, menuItemAfficherSupprimer);
        menuBar.getMenus().addAll(menu1, new Menu("Poste"), new Menu("Produit"), new Menu("Gamme"), new Menu("Rapport de Fiabilité"));

        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-font-size: 16px; -fx-background-color: red; -fx-text-fill: white;");
        closeButton.setOnAction(e -> primaryStage.close());
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox topBar = new HBox(menuBar, spacer, closeButton);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-padding: 5px;");

        menuItem1.setOnAction(e -> {
            formBox.setVisible(true);
            affichageBox.setVisible(false);
            labelMessage.setText("");
        });

        menuItemAfficherSupprimer.setOnAction(e -> {
            formBox.setVisible(false);
            affichageBox.setVisible(true);
            scrollPane.setVisible(true);
            ligneContainer.getChildren().clear();

            try {
                List<List<String>> lignes = Machine.chargerMachinesDepuisFichier();
                for (List<String> ligne : lignes) {
                    HBox ligneHBox = new HBox(20);
                    for (String cellule : ligne) {
                        Label label = new Label(cellule);
                        label.setMinWidth(150);
                        ligneHBox.getChildren().add(label);
                    }
                    ligneContainer.getChildren().add(ligneHBox);
                }
                suppressionBox.setVisible(true);
            } catch (IOException ex) {
                ligneContainer.getChildren().add(new Label("Erreur lors de la lecture du fichier."));
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

                // Ajout dans le fichier via la méthode générique Utile
                Utile.ajouterLigne(cheminFichier, machine.toList());

                labelMessage.setStyle("-fx-text-fill: green;");
                labelMessage.setText("Machine ajoutée avec succès.");

                // Reset formulaire
                tfRefMachine.clear();
                tfDesiMachine.clear();
                tfTypeMachine.clear();
                tfXMachine.clear();
                tfYMachine.clear();
                tfCout.clear();

                formBox.setVisible(false);

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
            labelMessage.setText("");
            formBox.setVisible(false);
        });

        VBox mainContent = new VBox(20, formBox, affichageBox);
        mainContent.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 960, 600);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
