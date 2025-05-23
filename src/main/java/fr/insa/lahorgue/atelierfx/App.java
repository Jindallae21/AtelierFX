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
        // === MenuBar ===
        MenuBar menuBar = new MenuBar();

        Menu menu1 = new Menu("Machine");
        Menu menu2 = new Menu("Poste");
        Menu menu3 = new Menu("Produit");
        Menu menu4 = new Menu("Gamme");
        Menu menu5 = new Menu("Rapport de Fiabilite");

        MenuItem menuItem1 = new MenuItem("Ajouter");
        MenuItem menuItem2 = new MenuItem("Modifier");
        MenuItem menuItem3 = new MenuItem("Supprimer");
        MenuItem menuItem4 = new MenuItem("Afficher");
        menuBar.getMenus().addAll(menu1, menu2, menu3, menu4, menu5);

        SeparatorMenuItem separator = new SeparatorMenuItem();

        menu1.getItems().add(menuItem1);
        menu1.getItems().add(menuItem2);
        menu1.getItems().add(menuItem3);
        menu1.getItems().add(menuItem4);
        menuItem1.setOnAction(e -> System.out.println("Ajouter une Machine"));
        menuItem2.setOnAction(e -> System.out.println("Modifier une Machine"));

       
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

        Scene scene = new Scene(root, 960, 600);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}