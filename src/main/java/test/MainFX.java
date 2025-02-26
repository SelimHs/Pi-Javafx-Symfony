package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import service.EmailService;

import java.io.IOException;
import java.net.URL;
import java.util.prefs.Preferences;

public class MainFX extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        String code = emailService.sendConfirmationEmail("destinataire@gmail.com");
        System.out.println("Code de confirmation : " + code);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        if (isUserRemembered()) {
            showHomePage();
        } else {
            showLoginPage();
        }
    }

    private boolean isUserRemembered() {
        Preferences prefs = Preferences.userNodeForPackage(controllers.Login.class);
        return prefs.getBoolean("rememberMe", false) && !prefs.get("email", "").isEmpty();
    }

    private void showLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Page de Connexion");

            controllers.Login loginController = loader.getController();
            if (loginController != null) {
                loginController.getSignupButton().setOnAction(event -> showCreateAccountPage());
                loginController.setOnLoginSuccess(() -> showHomePage()); // Redirection après connexion réussie
            }

            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Erreur de chargement de la page de connexion : " + e.getMessage());
        }
    }

    private void showCreateAccountPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cree_compte.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Créer un Compte");
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Erreur de chargement de la page d'inscription : " + e.getMessage());
        }
    }

    private void showHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/homePage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Page d'Accueil");

            // Récupérer le bouton de déconnexion dans la page d'accueil
            Button logoutButton = (Button) scene.lookup("#logoutButton");
            if (logoutButton != null) {
                logoutButton.setOnAction(event -> {
                    logoutUser();  // Efface les préférences et redirige vers la page de connexion
                    showLoginPage();
                });
            }

            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Erreur de chargement de la page d'accueil : " + e.getMessage());
        }
    }

    private void logoutUser() {
        Preferences prefs = Preferences.userNodeForPackage(controllers.Login.class);
        prefs.putBoolean("rememberMe", false); // Désactiver "Rester connecté"
        prefs.remove("email");
        prefs.remove("password");
        try {
            prefs.flush();
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression des préférences : " + e.getMessage());
        }
    }
}