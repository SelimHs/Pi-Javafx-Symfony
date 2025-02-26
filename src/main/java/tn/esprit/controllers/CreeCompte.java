package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Users;
import tn.esprit.services.UsersService;

import java.io.IOException;
import java.util.regex.Pattern;

public class CreeCompte {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private TextField numeroField;
    @FXML private TextArea adresseField;
    @FXML private CheckBox typeAdmin;
    @FXML private CheckBox typeUser;
    @FXML private CheckBox genreMasculin;
    @FXML private CheckBox genreFeminin;
    @FXML private Button createButton;
    @FXML private Label messageLabel;

    private final UsersService usersService = new UsersService();

    // Méthode appelée lors du clic sur le bouton "Créer un compte"
    @FXML
    public void handleCreateAccount() {
        // Vérification des champs vides
        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || emailField.getText().isEmpty() ||
                passwordField.getText().isEmpty() || numeroField.getText().isEmpty() || adresseField.getText().isEmpty()) {
            messageLabel.setText("⚠️ Veuillez remplir tous les champs.");
            return;
        }

        // Validation du nom et prénom
        if (!isValidName(nomField.getText()) || !isValidName(prenomField.getText())) {
            messageLabel.setText("⚠️ Le nom et le prénom ne doivent contenir que des lettres.");
            return;
        }

        // Validation du mot de passe
        if (!isValidPassword(passwordField.getText())) {
            messageLabel.setText("⚠️ Le mot de passe doit contenir au moins une majuscule et un caractère spécial.");
            return;
        }

        // Validation de l'email
        if (!isValidEmail(emailField.getText())) {
            messageLabel.setText("⚠️ L'email doit contenir un @ et un domaine valide (ex: .com, .fr).");
            return;
        }

        // Validation du numéro de téléphone
        if (!isValidPhoneNumber(numeroField.getText())) {
            messageLabel.setText("⚠️ Le numéro de téléphone doit contenir exactement 8 chiffres.");
            return;
        }

        // Vérifier si l'email existe déjà
        String email = emailField.getText();
        if (usersService.isEmailExist(email)) {
            messageLabel.setText("L'email existe déjà.");
            return;
        }

        // Traitement du type d'utilisateur (Admin ou Utilisateur)
        String type = null;
        if (typeAdmin.isSelected() && !typeUser.isSelected()) {
            type = "Admin";
        } else if (!typeAdmin.isSelected() && typeUser.isSelected()) {
            type = "client";
        } else {
            messageLabel.setText("⚠️ Veuillez sélectionner un seul type d'utilisateur.");
            return;
        }

        // Traitement du genre
        String genre = null;
        if (genreMasculin.isSelected() && !genreFeminin.isSelected()) {
            genre = "homme";
        } else if (!genreMasculin.isSelected() && genreFeminin.isSelected()) {
            genre = "femme";
        } else {
            messageLabel.setText("⚠️ Veuillez sélectionner un seul genre.");
            return;
        }

        // Créer l'objet User
        Users user = new Users(nomField.getText(), prenomField.getText(), passwordField.getText(),
                email, numeroField.getText(), adresseField.getText(), type, genre);

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

    @FXML
    void goToLoginPage(ActionEvent event) {
        System.out.println("🔄 Redirection vers la page de connexion...");
        try {
            // Charger la page de connexion (login.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent loginPage = loader.load();  // ✅ Changement de AnchorPane → Parent

            // Obtenir la scène actuelle et changer la page
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loginPage));
            stage.show();
        } catch (IOException e) {
            System.out.println("❌ Erreur de redirection : " + e.getMessage());
            e.printStackTrace();
            showAlert("Erreur de navigation", "Impossible d'ouvrir la page de connexion.");
        }
    }



    // Méthode pour afficher une alerte en cas d'erreur
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}