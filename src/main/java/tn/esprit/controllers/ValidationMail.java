package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Users;
import tn.esprit.services.UsersService;

public class ValidationMail {

    @FXML
    private TextField codeField; // Champ pour entrer le code de vérification

    @FXML
    private Label messageLabel; // Label pour afficher les messages d'erreur ou de succès

    private String generatedConfirmationCode; // Code de confirmation généré

    // Données de l'utilisateur
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String numero;
    private String adresse;
    private String genre;

    // Méthode pour définir le code de confirmation généré
    public void setGeneratedConfirmationCode(String code) {
        this.generatedConfirmationCode = code;
    }

    // Méthode pour définir les données de l'utilisateur
    public void setUserData(String nom, String prenom, String password, String email, String numero, String adresse, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.numero = numero;
        this.adresse = adresse;
        this.genre = genre;
    }

    // Méthode pour valider le code de confirmation
    @FXML
    private void handleValidateCode() {
        String enteredCode = codeField.getText();

        if (enteredCode.equals(generatedConfirmationCode)) {
            // Créer l'objet User
            Users user = new Users(nom, prenom, password, email, numero, adresse, "client", genre);

            try {
                // Ajouter l'utilisateur à la base de données
                UsersService usersService = new UsersService();
                usersService.add(user);
                messageLabel.setText("✅ Compte créé avec succès !");

                // Redirection vers la page d'accueil après la création du compte
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/homePage.fxml"));
                Parent root = loader.load();

                // Obtenir la scène actuelle et la modifier
                Scene scene = new Scene(root);
                Stage stage = (Stage) codeField.getScene().getWindow();
                stage.setScene(scene);
                stage.show();  // Afficher la nouvelle scène

            } catch (Exception e) {
                messageLabel.setText("❌ Une erreur s'est produite lors de la création du compte.");
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("⚠️ Code de vérification incorrect.");
        }
    }
}