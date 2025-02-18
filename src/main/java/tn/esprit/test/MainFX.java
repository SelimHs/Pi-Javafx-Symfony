package tn.esprit.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // ✅ Correctly Load FXML
            URL fxmlLocation = getClass().getResource("/GestionEspace.fxml");
            if (fxmlLocation == null) {
                throw new IllegalArgumentException("❌ FXML file not found! Check the path.");
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion des Espaces");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("❌ Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
