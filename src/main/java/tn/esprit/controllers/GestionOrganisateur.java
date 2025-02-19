package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionOrganisateur implements Initializable {

    @FXML
    private AnchorPane root; // Lien avec l'interface

    @FXML
    private TextField nomOrganisateur, emailOrganisateur, telOrganisateur, societeOrganisateur;

    @FXML
    private PasswordField mdpOrganisateur;

    @FXML
    private Button btnAjouterOrganisateur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (btnAjouterOrganisateur != null) {
            btnAjouterOrganisateur.setOnAction(event -> ajouterOrganisateur());
        } else {
            System.out.println("⚠️ Erreur : btnAjouterOrganisateur est NULL !");
        }
    }


    /**
     * 🚀 Méthode pour ajouter un organisateur
     */
    private void ajouterOrganisateur() {
        // Récupération des valeurs des champs
        String nom = nomOrganisateur.getText().trim();
        String email = emailOrganisateur.getText().trim();
        String tel = telOrganisateur.getText().trim();
        String societe = societeOrganisateur.getText().trim();
        String motDePasse = mdpOrganisateur.getText().trim();

        // 🔍 Vérification des champs
        if (nom.isEmpty() || email.isEmpty() || tel.isEmpty() || societe.isEmpty() || motDePasse.isEmpty()) {
            afficherAlerte("⚠️ Champs vides", "Veuillez remplir tous les champs !");
            return;
        }

        // 🔢 Vérifier si le téléphone contient bien 8 chiffres
        if (!tel.matches("\\d{8}")) {
            afficherAlerte("📞 Numéro invalide", "Le numéro de téléphone doit contenir 8 chiffres.");
            return;
        }

        // ✅ Ajout simulé (à remplacer avec une insertion dans la base de données)
        System.out.println("✅ Organisateur ajouté : " + nom + " | " + email + " | " + tel + " | " + societe);

        // ✅ Afficher un message de succès
        afficherAlerte("✅ Succès", "L'organisateur a été ajouté avec succès !");

        // 🔄 Nettoyer les champs après l'ajout
        viderChamps();
    }

    /**
     * ⚠️ Méthode pour afficher une alerte
     */
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * 🔄 Méthode pour vider les champs après ajout
     */
    private void viderChamps() {
        nomOrganisateur.clear();
        emailOrganisateur.clear();
        telOrganisateur.clear();
        societeOrganisateur.clear();
        mdpOrganisateur.clear();
    }
}
