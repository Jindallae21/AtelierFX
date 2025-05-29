package fr.insa.lahorgue.atelierfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    // Champs de saisie pour créer une machine
    private TextField tfRefMachine = new TextField();
    private TextField tfDesiMachine = new TextField();
    private TextField tfTypeMachine = new TextField();
    private TextField tfXMachine = new TextField();
    private TextField tfYMachine = new TextField();
    private VBox formBox = new VBox(10); // Formulaire affiché dynamiquement
    private Label labelItem4 = new Label("Aucune machine sélectionnée.");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Atelier de Fabrication");

        // Configuration des champs
        tfRefMachine.setPromptText("Référence Machine");
        tfDesiMachine.setPromptText("Désignation Machine");
        tfTypeMachine.setPromptText("Type de Machine");
        tfXMachine.setPromptText("X Machine");
        tfYMachine.setPromptText("Y Machine");

        Button valider = new Button("Valider");
        valider.setOnAction(e -> handleValider());

        formBox.getChildren().addAll(tfRefMachine, tfDesiMachine, tfTypeMachine, tfXMachine, tfYMachine, valider);
        formBox.setAlignment(Pos.CENTER);
        formBox.setVisible(false);

        // Création des menus
        MenuBar menuBar = new MenuBar();
        Menu menuMachine = new Menu("Machine");
        Menu menuPoste = new Menu("Poste");
        Menu menuProduit = new Menu("Produit");
        Menu menuGamme = new Menu("Gamme");
        Menu menuRapport = new Menu("Rapport de Fiabilité");

        menuBar.getMenus().addAll(menuMachine, menuPoste, menuProduit, menuGamme, menuRapport);

        // Items du menu Machine
        MenuItem miAjouterMachine = new MenuItem("Ajouter");
        MenuItem miModifierMachine = new MenuItem("Modifier");
        MenuItem miSupprimerMachine = new MenuItem("Supprimer");
        MenuItem miAfficherMachine = new MenuItem("Afficher");

        menuMachine.getItems().addAll(miAjouterMachine, miModifierMachine, miSupprimerMachine, miAfficherMachine);

        miAjouterMachine.setOnAction(e -> {
            formBox.setVisible(true);
            clearFields();
        });

        miModifierMachine.setOnAction(e -> System.out.println("Modifier une Machine"));
        miSupprimerMachine.setOnAction(e -> System.out.println("Supprimer une Machine"));
        miAfficherMachine.setOnAction(e -> labelItem4.setText("Aucune machine sélectionnée pour l'instant."));

        // Label d'affichage dans VBox
        VBox afficherBox = new VBox(10, labelItem4);
        afficherBox.setAlignment(Pos.CENTER);

        // Bouton de fermeture
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-font-size: 16px; -fx-background-color: red; -fx-text-fill: white;");
        closeButton.setOnAction(e -> primaryStage.close());

        // Barre du haut : menu + bouton fermer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox topBar = new HBox(menuBar, spacer, closeButton);
        topBar.setStyle("-fx-padding: 5px;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        // Layout principal
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(formBox); // par défaut on montre le formulaire (changer selon besoins)

        Scene scene = new Scene(root, 960, 600);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /**
     * Gère le clic sur le bouton "Valider" pour créer une machine.
     */
    private void handleValider() {
        try {
            String ref = tfRefMachine.getText();
            String desi = tfDesiMachine.getText();
            String type = tfTypeMachine.getText();
            float x = Float.parseFloat(tfXMachine.getText());
            float y = Float.parseFloat(tfYMachine.getText());

            Machine machine = new Machine(ref, desi, type, x, y);
            System.out.println("Machine créée : " + machine.getRefMachine() + " - " + machine.getDMachine());

            labelItem4.setText("Machine créée : " + machine.getRefMachine() + ", " + machine.getDMachine());

            clearFields();
            formBox.setVisible(false); // cacher le formulaire

        } catch (NumberFormatException ex) {
            labelItem4.setText("Erreur : X et Y doivent être des nombres !");
        }
    }

    /**
     * Vide les champs de saisie.
     */
    private void clearFields() {
        tfRefMachine.clear();
        tfDesiMachine.clear();
        tfTypeMachine.clear();
        tfXMachine.clear();
        tfYMachine.clear();
    }
}
