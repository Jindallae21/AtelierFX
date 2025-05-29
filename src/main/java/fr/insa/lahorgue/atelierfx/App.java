package fr.insa.lahorgue.atelierfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Atelier de Fabrication");

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

        Button valider = new Button("Valider");
        valider.setStyle(
            "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;" +
            "-fx-background-radius: 10; -fx-padding: 8 16;"
        );

        Button annuler = new Button("Annuler");
        annuler.setStyle(
            "-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;" +
            "-fx-background-radius: 10; -fx-padding: 8 16;"
        );

        HBox buttonBox = new HBox(10, valider, annuler);
        buttonBox.setAlignment(Pos.CENTER);

        VBox formBox = new VBox(12, tfRefMachine, tfDesiMachine, tfTypeMachine, tfXMachine, tfYMachine, tfCout, buttonBox);
        formBox.setAlignment(Pos.CENTER);
        formBox.setStyle("-fx-padding: 20;");
        formBox.setVisible(false);

        MenuBar menuBar = new MenuBar();

        Menu menu1 = new Menu("Machine");
        Menu menu2 = new Menu("Poste");
        Menu menu3 = new Menu("Produit");
        Menu menu4 = new Menu("Gamme");
        Menu menu5 = new Menu("Rapport de Fiabilité");

        MenuItem menuItem1 = new MenuItem("Ajouter");
        MenuItem menuItem2 = new MenuItem("Modifier");
        MenuItem menuItem3 = new MenuItem("Supprimer");
        MenuItem menuItem4 = new MenuItem("Afficher");

        menu1.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4);
        menuBar.getMenus().addAll(menu1, menu2, menu3, menu4, menu5);

        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-font-size: 16px; -fx-background-color: red; -fx-text-fill: white;");
        closeButton.setOnAction(e -> primaryStage.close());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox topBar = new HBox(menuBar, spacer, closeButton);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-padding: 5px;");

        menuItem1.setOnAction(e -> formBox.setVisible(true));

        valider.setOnAction(e -> {
            try {
                String refEquipement = tfRefMachine.getText();
                String dEquipement = tfDesiMachine.getText();
                String type = tfTypeMachine.getText();
                float x = Float.parseFloat(tfXMachine.getText());
                float y = Float.parseFloat(tfYMachine.getText());
                float cout = Float.parseFloat(tfCout.getText());

                Machine machine = new Machine(refEquipement, dEquipement, type, x, y);
                machine.setCout(cout);

                System.out.println("Machine créée : " + machine.getRefEquipement() + ", Cout : " + machine.getCout());

                tfRefMachine.clear();
                tfDesiMachine.clear();
                tfTypeMachine.clear();
                tfXMachine.clear();
                tfYMachine.clear();
                tfCout.clear();
                formBox.setVisible(false);

            } catch (NumberFormatException ex) {
                System.out.println("Erreur de conversion numérique. Vérifiez les valeurs de X, Y ou Coût.");
            }
        });

        annuler.setOnAction(e -> {
            tfRefMachine.clear();
            tfDesiMachine.clear();
            tfTypeMachine.clear();
            tfXMachine.clear();
            tfYMachine.clear();
            tfCout.clear();
            formBox.setVisible(false);
        });

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(formBox);

        Scene scene = new Scene(root, 960, 600);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
