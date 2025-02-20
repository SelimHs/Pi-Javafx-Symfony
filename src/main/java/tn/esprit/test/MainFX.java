package tn.esprit.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args); // Démarrer l'application JavaFX
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // ✅ Chargement du fichier FXML (vérifiez que le fichier est bien placé dans /resources/tn/esprit/views/)
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/AfficheReservation.fxml")));
            Parent root = loader.load();

            // ✅ Création et affichage de la scène
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion des Réservations"); // Nom de la fenêtre
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("❌ Erreur lors du chargement de l'interface : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
