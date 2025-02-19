package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import tn.esprit.models.Espace;
import tn.esprit.services.ServiceEspace;

public class GestionEspace {

    @FXML
    private TextField nomEspace;
    @FXML
    private TextField adresseEspace;
    @FXML
    private TextField capaciteEspace;
    @FXML
    private ComboBox<String> disponibiliteEspace;
    @FXML
    private TextField prixEspace;
    @FXML
    private TextField typeEspace;

    // Service pour gérer les espaces
    private final ServiceEspace serviceEspace = new ServiceEspace();

    @FXML
    public void ajouterEspace(ActionEvent event) {
        try {
            // ✅ Vérification des champs
            if (nomEspace.getText().isEmpty() || adresseEspace.getText().isEmpty() ||
                    capaciteEspace.getText().isEmpty() || disponibiliteEspace.getValue() == null ||
                    prixEspace.getText().isEmpty() || typeEspace.getText().isEmpty()) {

                afficherAlerte("⚠️ Champs manquants", "Veuillez remplir tous les champs.");
                return;
            }

            // ✅ Création d'un nouvel espace
            Espace espace = new Espace();
            espace.setNomEspace(nomEspace.getText());
            espace.setAdresse(adresseEspace.getText());
            espace.setCapacite(Integer.parseInt(capaciteEspace.getText()));
            espace.setDisponibilite(disponibiliteEspace.getValue());
            espace.setPrix(Float.parseFloat(prixEspace.getText()));
            espace.setTypeEspace(typeEspace.getText());
            espace.setIdUser(1); // À modifier selon ton système d'authentification

            // ✅ Ajout dans la base de données
            serviceEspace.add(espace);

            // ✅ Message de confirmation
            afficherAlerte("✅ Succès", "Espace ajouté avec succès !");
            viderChamps();

        } catch (NumberFormatException e) {
            afficherAlerte("❌ Erreur", "Vérifiez que Capacité et Prix sont des nombres valides.");
        } catch (Exception e) {
            afficherAlerte("❌ Erreur", "Une erreur est survenue : " + e.getMessage());
        }
    }

    // ✅ Fonction pour afficher une alerte
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // ✅ Fonction pour vider les champs après ajout
    private void viderChamps() {
        nomEspace.clear();
        adresseEspace.clear();
        capaciteEspace.clear();
        disponibiliteEspace.setValue(null);
        prixEspace.clear();
        typeEspace.clear();
    }
}
