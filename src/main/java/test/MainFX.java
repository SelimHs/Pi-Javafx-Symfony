package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class MainFX extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginPage();
    }

    private void showLoginPage() {
        try {
            URL fxmlLocation = getClass().getResource("/login.fxml");
            if (fxmlLocation == null) {
                throw new IOException("login.fxml introuvable");
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            Scene scene = new Scene(root);

            URL cssLocation = getClass().getResource("/style.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
            }

            primaryStage.setScene(scene);
            primaryStage.setTitle("Page de Connexion");

            controllers.Login loginController = loader.getController();
            if (loginController != null && loginController.getSignupButton() != null) {
                loginController.getSignupButton().setOnAction(event -> showCreateAccountPage());
            } else {
                System.err.println("Erreur: Impossible de récupérer le contrôleur ou le bouton de création de compte.");
            }

            // Ajout de la redirection après la connexion réussie
            loginController.setOnLoginSuccess(() -> showHomePage());

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur de chargement de la page de connexion : " + e.getMessage());
        }
    }

    private void showCreateAccountPage() {
        try {
            URL fxmlLocation = getClass().getResource("/cree_compte.fxml");
            if (fxmlLocation == null) {
                throw new IOException("cree_compte.fxml introuvable");
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cree_compte.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            URL cssLocation = getClass().getResource("/cree_compte.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
            }

            primaryStage.setScene(scene);
            primaryStage.setTitle("Créer un Compte");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur de chargement de la page de création de compte : " + e.getMessage());
        }
    }

    // Méthode pour afficher la page d'accueil
    private void showHomePage() {
        try {
            URL fxmlLocation = getClass().getResource("/homePage.fxml");
            if (fxmlLocation == null) {
                throw new IOException("homePage.fxml introuvable");
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            Scene scene = new Scene(root);

            URL cssLocation = getClass().getResource("/style.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
            }

            primaryStage.setScene(scene);
            primaryStage.setTitle("Page d'Accueil");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur de chargement de la page d'accueil : " + e.getMessage());
        }
    }
}
