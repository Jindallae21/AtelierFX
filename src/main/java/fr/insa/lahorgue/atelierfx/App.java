package fr.insa.lahorgue.atelierfx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Atelier de Fabrication");
       
        //INSTAURATION DES CLASSES EXTERNES
       
        TextField tfRefMachine = new TextField();
        tfRefMachine.setPromptText("Référence Machine");
        
        TextField tfDesiMachine = new TextField();
        tfDesiMachine.setPromptText("Designation Machine");
        
        TextField tfTypeMachine = new TextField();
        tfTypeMachine.setPromptText("Type de Machine");

        TextField tfXMachine = new TextField();
        tfXMachine.setPromptText("X Machine");

        TextField tfYMachine = new TextField();
        tfYMachine.setPromptText("Y Machine");

        
        
        
        MenuBar menuBar = new MenuBar();
        
        
        Label labelItem4 = new Label();
        
        TextField champTexteMachine = new TextField();
        champTexteMachine.setVisible(false);
        
        
       
         Label labelItem4 = new Label();

        
        Menu menu1 = new Menu("Machine");
        Menu menu2 = new Menu("Poste");
        Menu menu3 = new Menu("Produit");
        Menu menu4 = new Menu("Gamme");
        Menu menu5 = new Menu("Rapport de Fiabilite");
        
       
        
        MenuItem menuItem1 = new MenuItem("Ajouter");
        MenuItem menuItem2 = new MenuItem("Modifier");
        MenuItem menuItem3 = new MenuItem("Supprimer");
        MenuItem menuItem4 = new MenuItem("Afficher");
        MenuItem menuItem5 = new MenuItem("Ajouter");
        MenuItem menuItem6 = new MenuItem("Modifier");
        MenuItem menuItem7 = new MenuItem("Supprimer");
        MenuItem menuItem8 = new MenuItem("Afficher");

        menuBar.getMenus().addAll(menu1, menu2, menu3, menu4, menu5);

        SeparatorMenuItem separator = new SeparatorMenuItem();

        //Vbox de la zone de saisie
        VBox formBox = new VBox(10, tfRefMachine, tfDesiMachine, tfTypeMachine, tfXMachine, tfYMachine, valider);
        formBox.setAlignment(Pos.BASELINE_CENTER);
        formBox.setVisible(false);
        
        
        Button valider = new Button("Valider");
        valider.setOnAction(e -> {
            try{
                String refEquipement = tfRefMachine.getText();
                String dEquipement = tfDesiMachine.getText();
                String type = tfTypeMachine.getText();
                float x = tfXMachine.getText();
                float y = tfYMachine.getText();
                
                Machine machine = new Machine(refEquipement, dEquipement, type, x, y);
                System.out.println("Machine cree : "+machine.getRefMachine());
                
                tfRefMachine.clear(); tfDesiMachine.clear(); tfTypeMachine.clear(); tfXMachine.clear(); tfYMachine.clear();  // vider les champs
                formBox.setVisible(false); // masquer toutes les zones de saisie
            } catch (NumberFormatException ex){
                System.out.println("Erreur de saisie");
            }
        valider.setVisible(false);
        });
        
        
        
        menu1.getItems().add(menuItem1);
        menu1.getItems().add(menuItem2);
        menu1.getItems().add(menuItem3);
        menu1.getItems().add(menuItem4);
        menuItem1.setOnAction(e -> {
            formBox.setVisible(true);
            valider.setVisible(true);
        });
        menuItem2.setOnAction(e -> System.out.println("Modifier une Machine"));
        menuItem3.setOnAction(e -> System.out.println("Supprimer une Machine"));
        
        menuItem4.setOnAction(e -> labelItem4.setText("Bonjour, tu as cliqué !"));

        
        
        menu4.getItems().add(menuItem5);
        menu4.getItems().add(menuItem6);
        menu4.getItems().add(menuItem7);
        menu4.getItems().add(menuItem8);
        menuItem5.setOnAction(e -> System.out.println("Ajouter une Gamme"));
        menuItem6.setOnAction(e -> System.out.println("Modifier une Gamme"));

        /*<<<<<<< HEAD
        
        =======*/
        //Vbox de menuItem4
         VBox afficher = new VBox(10, labelItem4);
        afficher.setAlignment(Pos.CENTER);

        //>>>>>>> origin/master
        
         // === Bouton de fermeture ===
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-font-size: 16px; -fx-background-color: red; -fx-text-fill: white;");
        closeButton.setOnAction(e -> primaryStage.close());

        // === Barre horizontale : Menu + croix ===
        Region spacer = new Region(); // espace extensible
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox topBar = new HBox(menuBar, spacer, closeButton);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-padding: 5px;");

        // === Layout principal ===
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        /*<<<<<<< HEAD*/
        root.setCenter(layout);
        /*=======*/        
        root.setCenter(afficher);
/*>>>>>>> origin/master*/
        
        
        Scene scene = new Scene(root, 960, 600);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        
    }
}
