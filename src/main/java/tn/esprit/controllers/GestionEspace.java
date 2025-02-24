package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.services.ServiceEspace;

import java.io.IOException;
import java.util.Optional;

public class GestionEspace {

    @FXML
    private TextField nomEspace, adresseEspace, capaciteEspace, prixEspace, typeEspace;
    @FXML
    private ComboBox<String> disponibiliteEspace;

    private final ServiceEspace serviceEspace = new ServiceEspace();

    // 🔹 Navigation vers l'affichage des espaces
    @FXML
    public void afficherEspaces(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AfficherEspaces.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // 🔹 Retour à la page d'ajout
    @FXML
    public void ajouterEspaceView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GestionEspace.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // 🔹 Ajout d'un nouvel espace avec contrôle de saisie
    @FXML
    public void ajouterEspace(ActionEvent event) {
        try {
            // 🛑 Vérification des champs vides
            if (nomEspace.getText().isEmpty() || adresseEspace.getText().isEmpty() ||
                    capaciteEspace.getText().isEmpty() || prixEspace.getText().isEmpty() ||
                    typeEspace.getText().isEmpty() || disponibiliteEspace.getValue() == null) {
                afficherAlerte("Erreur", "Veuillez remplir tous les champs !");
                return;
            }

            // 🛑 Vérification des valeurs numériques
            int capacite;
            float prix;
            try {
                capacite = Integer.parseInt(capaciteEspace.getText());
                if (capacite <= 0) {
                    afficherAlerte("Erreur", "La capacité doit être un nombre positif.");
                    return;
                }
            } catch (NumberFormatException e) {
                afficherAlerte("Erreur", "La capacité doit être un nombre entier.");
                return;
            }

            try {
                prix = Float.parseFloat(prixEspace.getText());
                if (prix <= 0) {
                    afficherAlerte("Erreur", "Le prix doit être un nombre positif.");
                    return;
                }
            } catch (NumberFormatException e) {
                afficherAlerte("Erreur", "Le prix doit être un nombre décimal.");
                return;
            }

            // 🔹 Création de l'objet Espace
            Espace espace = new Espace();
            espace.setNomEspace(nomEspace.getText());
            espace.setAdresse(adresseEspace.getText());
            espace.setCapacite(capacite);
            espace.setPrix(prix);
            espace.setTypeEspace(typeEspace.getText());
            espace.setDisponibilite(disponibiliteEspace.getValue());
            espace.setIdUser(1); // À remplacer par l'ID utilisateur réel

            // 🔹 Ajout dans la base de données
            serviceEspace.add(Optional.of(espace));

            // ✅ Confirmation
            afficherAlerte("Succès", "L'espace a été ajouté avec succès !");

            // 🗑 Réinitialisation des champs
            viderChamps();

        } catch (Exception e) {
            afficherAlerte("Erreur", "Une erreur s'est produite lors de l'ajout : " + e.getMessage());
        }
    }

    // 📌 Fonction pour afficher des alertes
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // 📌 Fonction pour vider les champs après un ajout réussi
    private void viderChamps() {
        nomEspace.clear();
        adresseEspace.clear();
        capaciteEspace.clear();
        prixEspace.clear();
        typeEspace.clear();
        disponibiliteEspace.getSelectionModel().clearSelection();
    }

    @FXML
    private void retourAccueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Erreur lors du chargement de Acceuil.fxml");
        }
    }

    public void buttonHoverEffect(MouseEvent mouseEvent) {

    }

    public void buttonExitEffect(MouseEvent mouseEvent) {
    }
}
