package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Organisateur;
import tn.esprit.services.ServiceOrganisateur;

import java.io.IOException;

public class GestionOrganisateur {

    @FXML private TextField nomOrganisateur, prenomOrganisateur, telef;
    @FXML private TextArea descriptionOrganisateur;
    @FXML private Button btnAjouterOrganisateur, btnRetour;

    private final ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();
    private int idEspace; // ID de l'espace récupéré

    public void initData(int idEspace) {
        this.idEspace = idEspace;
        System.out.println("✅ ID de l'espace reçu pour l'ajout : " + idEspace);
    }

    @FXML
    private void initialize() {
        if (btnAjouterOrganisateur != null) {
            btnAjouterOrganisateur.setOnAction(event -> ajouterOrganisateur());
        } else {
            System.out.println("⚠️ Erreur : btnAjouterOrganisateur est NULL !");
        }
    }


    @FXML
    private void ajouterOrganisateur() {
        System.out.println("✅ Bouton Ajouter cliqué !");

        String nom = nomOrganisateur.getText().trim();
        String prenom = prenomOrganisateur.getText().trim();
        String telStr = telef.getText().trim(); // ✅ Récupérer la valeur sous forme de String
        String description = descriptionOrganisateur.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || telStr.isEmpty() || description.isEmpty()) {
            afficherAlerte("⚠️ Champs vides", "Veuillez remplir tous les champs !");
            return;
        }

        if (!telStr.matches("\\d{8}")) {
            afficherAlerte("⚠️ Numéro invalide", "Le numéro de téléphone doit contenir 8 chiffres.");
            return;
        }

        int tel = Integer.parseInt(telStr); // ✅ Convertir en `int`

        // Vérification de l'ID de l'espace
        if (idEspace <= 0) {
            afficherAlerte("❌ Erreur", "Aucun espace sélectionné !");
            return;
        }

        System.out.println("🆕 Ajout d'un organisateur pour l'espace ID: " + idEspace);

        Organisateur organisateur = new Organisateur(0, nom, prenom, description, idEspace, tel); // ✅ Utiliser `tel` converti
        serviceOrganisateur.add(organisateur);

        afficherAlerte("✅ Succès", "L'organisateur a été ajouté avec succès !");
        viderChamps();
    }


    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void viderChamps() {
        nomOrganisateur.clear();
        prenomOrganisateur.clear();
        telef.clear();
        descriptionOrganisateur.clear();
    }

    @FXML
    private void retourAfficherEspaces() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEspaces.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnRetour.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
