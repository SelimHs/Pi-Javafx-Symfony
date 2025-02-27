package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Users;
import tn.esprit.services.UsersService;
import service.EmailService;

import java.io.IOException;
import java.util.regex.Pattern;

public class CreeCompte {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private TextField numeroField;
    @FXML private TextArea adresseField;
    @FXML private CheckBox genreMasculin;
    @FXML private CheckBox genreFeminin;
    @FXML private Button createButton;
    @FXML private Label messageLabel;
    @FXML private TextField confirmationCodeField;

    private final UsersService usersService = new UsersService();
    private final EmailService emailService = new EmailService();
    private String generatedConfirmationCode; // Code de confirmation généré

    @FXML
    public void handleCreateAccount() {
        // Vérification des champs vides et validations...

        // Vérifier si l'email existe déjà
        String email = emailField.getText();
        if (usersService.isEmailExist(email)) { // Appel de la méthode publique
            messageLabel.setText("L'email existe déjà.");
            return;
        }

        // Envoyer le code de confirmation
        generatedConfirmationCode = emailService.sendConfirmationEmail(email);
        messageLabel.setText("Un code de confirmation a été envoyé à " + email);

        // Rediriger vers la page de validation du code
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/validationMail.fxml"));
            Parent root = loader.load();

            // Passer le code de confirmation au contrôleur de la page de validation
            ValidationMail validationController = loader.getController();
            validationController.setGeneratedConfirmationCode(generatedConfirmationCode);

            // Passer les informations du formulaire au contrôleur de validation
            validationController.setUserData(
                    nomField.getText(),
                    prenomField.getText(),
                    passwordField.getText(),
                    emailField.getText(),
                    numeroField.getText(),
                    adresseField.getText(),
                    getSelectedGenre()
            );

            // Obtenir la scène actuelle et la modifier
            Scene scene = new Scene(root);
            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();  // Afficher la nouvelle scène
        } catch (IOException e) {
            messageLabel.setText("❌ Erreur lors du chargement de la page de validation.");
            e.printStackTrace();
        }
    }
    @FXML
    private void handleConfirmCode() {
        String enteredCode = confirmationCodeField.getText();

        if (enteredCode.equals(generatedConfirmationCode)) {
            // Créer l'objet User
            Users user = new Users(nomField.getText(), prenomField.getText(), passwordField.getText(),
                    emailField.getText(), numeroField.getText(), adresseField.getText(), "client", getSelectedGenre());

            try {
                // Ajouter l'utilisateur à la base de données
                usersService.add(user);
                messageLabel.setText("✅ Compte créé avec succès !");

                // Redirection vers la page d'accueil après la création du compte
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/homePage.fxml"));
                Parent root = loader.load();

                // Obtenir la scène actuelle et la modifier
                Scene scene = new Scene(root);
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();  // Afficher la nouvelle scène

            } catch (Exception e) {
                messageLabel.setText("❌ Une erreur s'est produite lors de la création du compte.");
                e.printStackTrace(); // Afficher l'erreur pour le débogage
            }
        } else {
            messageLabel.setText("⚠️ Code de confirmation incorrect.");
        }
    }

    private String getSelectedGenre() {
        if (genreMasculin.isSelected() && !genreFeminin.isSelected()) {
            return "homme";
        } else if (!genreMasculin.isSelected() && genreFeminin.isSelected()) {
            return "femme";
        } else {
            return null;
        }
    }

    // Méthode pour valider le nom et le prénom
    private boolean isValidName(String name) {
        // Regex : uniquement des lettres et des espaces
        String regex = "^[a-zA-Z\\s]+$";
        return Pattern.matches(regex, name);
    }

    // Méthode pour valider le mot de passe
    private boolean isValidPassword(String password) {
        // Regex : au moins une majuscule et un caractère spécial
        String regex = "^(?=.*[A-Z])(?=.*[!@#$%^&*]).+$";
        return Pattern.matches(regex, password);
    }

    // Méthode pour valider l'email
    private boolean isValidEmail(String email) {
        // Regex : doit contenir un @ et un domaine valide
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    // Méthode pour valider le numéro de téléphone
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regex : exactement 8 chiffres
        String regex = "^\\d{8}$";
        return Pattern.matches(regex, phoneNumber);
    }
}