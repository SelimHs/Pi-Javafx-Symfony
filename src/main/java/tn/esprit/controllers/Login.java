package tn.esprit.controllers;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;  // Importer correctement Node de JavaFX

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.services.UsersService;

import java.io.IOException;

public class Login {
    @FXML
    private Circle ballon;
    @FXML
    private Rectangle panier;
    @FXML
    private Button signupButton;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField password;
    @FXML
    private TextField text;

    private UsersService usersService = new UsersService();
    private Runnable onLoginSuccess;
    @FXML
    private ImageView bearImage;
    @FXML
    private ImageView bearEyesImage;  // Une autre image pour les yeux de l'ours

    @FXML
    public void initialize() {
        // Lorsque l'utilisateur commence à taper dans le champ mot de passe
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            // Vérifier si un mot de passe a été saisi
            if (!newValue.isEmpty()) {

            }
        });
    }




    @FXML
    void handleLoginButtonAction() {
        String email = text.getText();
        String enteredPassword = password.getText();

        System.out.println("Email entered: " + email);
        System.out.println("Password entered: " + enteredPassword);

        if (email.isEmpty() || enteredPassword.isEmpty()) {
            showAlert("Champs vides", "Veuillez entrer un email et un mot de passe.");
        } else {
            boolean isAuthenticated = usersService.authenticate(email, enteredPassword);
            if (isAuthenticated) {
                showAlert("Connexion réussie", "Vous êtes connecté avec succès.");


                // Redirection vers la page d'accueil après une connexion réussie
                redirectToHomePage();
            } else {
                showAlert("Échec de la connexion", "Email ou mot de passe incorrect.");
            }
        }
    }




    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void redirectToHomePage() {
        try {
            // Charger le fichier FXML de la page d'accueil (homePage.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
            AnchorPane homePage = loader.load();

            // Créer une nouvelle scène avec la page d'accueil et la définir sur la scène actuelle
            Scene homeScene = new Scene(homePage);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(homeScene);
            stage.show();  // Afficher la nouvelle scène
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers la page d'accueil.");
        }
    }

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    public Button getSignupButton() {
        return signupButton;
    }


    @FXML
    public void animateButton(MouseEvent mouseEvent) {
        Node button = (Node) mouseEvent.getSource();  // Récupère le bouton

        // Création de la transition de mise à l'échelle
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), button);
        scaleTransition.setByX(0.1);  // Agrandir en X
        scaleTransition.setByY(0.1);  // Agrandir en Y
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);  // Effet d'accélération/décélération

        // Création de la transition de rotation (ajoute un effet moderne)
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(300), button);
        rotateTransition.setByAngle(5);  // Légère rotation
        rotateTransition.setCycleCount(1);

        // Applique un effet de surbrillance au bouton
        button.setEffect(new DropShadow(20, Color.rgb(255, 255, 255, 0.3)));

        // Lancer les deux transitions en même temps
        scaleTransition.play();
        rotateTransition.play();
    }

    @FXML
    public void resetButton(MouseEvent mouseEvent) {
        Node button = (Node) mouseEvent.getSource();  // Récupère le bouton

        // Réinitialiser l'effet de surbrillance
        button.setEffect(null);

        // Création de la transition de mise à l'échelle inverse
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setByX(-0.1);  // Réduit en X
        scaleTransition.setByY(-0.1);  // Réduit en Y
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);  // Accélère/décélère

        // Réinitialisation de la rotation
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(200), button);
        rotateTransition.setByAngle(-5);  // Annule la rotation précédente
        rotateTransition.setCycleCount(1);

        // Lancer les deux transitions
        scaleTransition.play();
        rotateTransition.play();
    }

    @FXML
    void handleCreecompte(ActionEvent event) {
        try {
            // Charger la page de création de compte
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cree_compte.fxml"));
            AnchorPane signupPage = loader.load();

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Afficher la nouvelle page
            stage.setScene(new Scene(signupPage));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur de navigation", "Impossible d'ouvrir la page de création de compte.");
        }
    }

}
