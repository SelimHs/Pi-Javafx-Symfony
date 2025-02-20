
package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Users;
import service.UsersService;
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
        // Vérification des champs
        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || emailField.getText().isEmpty() ||
                passwordField.getText().isEmpty() || numeroField.getText().isEmpty() || adresseField.getText().isEmpty()) {
            messageLabel.setText("⚠️ Veuillez remplir tous les champs.");
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
            // Charger la scène de la page d'accueil
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/homePage.fxml"));  // Remplacer par le bon chemin vers Home.fxml
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

}

