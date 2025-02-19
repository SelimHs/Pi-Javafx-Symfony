package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.models.Organisateur;
import tn.esprit.services.ServiceEspace;
import tn.esprit.services.ServiceOrganisateur;

import java.io.IOException;
import java.util.List;

public class DetailEspace {

    @FXML private Label titleLabel;
    @FXML private Label espaceDetailsLabel;
    @FXML private VBox organisateurContainer;
    @FXML private Button retourButton;
    @FXML private Button modifierButton;
    @FXML private Button supprimerButton;

    private final ServiceEspace serviceEspace = new ServiceEspace();
    private final ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();
    private Espace espace;

    public void initData(Espace espace) {
        this.espace = espace;

        if (espaceDetailsLabel == null) {
            System.out.println("❌ Erreur : espaceDetailsLabel est NULL. Vérifiez le FXML.");
            return;
        }

        // 🏠 Remplissage des détails de l'espace
        titleLabel.setText("Détails de l'Espace : " + espace.getNomEspace());

        espaceDetailsLabel.setText(
                "📍 Adresse : " + espace.getAdresse() + "\n" +
                        "👥 Capacité : " + espace.getCapacite() + "\n" +
                        "📅 Disponibilité : " + espace.getDisponibilite() + "\n" +
                        "💰 Prix : " + espace.getPrix() + " DT\n" +
                        "🏢 Type : " + espace.getTypeEspace()
        );

        // 📌 Chargement des organisateurs associés à l'espace
        afficherOrganisateurs();
    }

    private void afficherOrganisateurs() {
        organisateurContainer.getChildren().clear();
        List<Organisateur> organisateurs = serviceOrganisateur.getAll();

        if (organisateurs.isEmpty()) {
            Label noOrganisateur = new Label("Aucun organisateur disponible.");
            noOrganisateur.setStyle("-fx-font-size: 14px; -fx-text-fill: grey;");
            organisateurContainer.getChildren().add(noOrganisateur);
        } else {
            for (Organisateur organisateur : organisateurs) {
                Label orgLabel = new Label("👤 " + organisateur.getNomOrg() + " " + organisateur.getPrenomOrg() +
                        " - 📧 " + organisateur.getDescriptionOrg());
                orgLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-padding: 5px;");
                organisateurContainer.getChildren().add(orgLabel);
            }
        }
    }

    // 🛠 Modifier l'espace
    @FXML
    private void modifierEspace() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEspace.fxml"));
            Parent root = loader.load();
            ModifierEspace controller = loader.getController();
            controller.initData(espace);

            Stage stage = (Stage) titleLabel.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 🗑 Supprimer l'espace
    @FXML
    private void supprimerEspace() {
        serviceEspace.delete(espace.getIdEspace());
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "L'espace a été supprimé avec succès !");
        alert.show();

        retourAfficherEspaces(); // Retour à l'affichage des espaces après suppression
    }

    // ⬅ Retourner à l'affichage des espaces
    @FXML
    private void retourAfficherEspaces() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEspaces.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) retourButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button ajouterOrganisateurButton;

    @FXML
    private void ajouterOrganisateur(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionOrganisateur.fxml"));
            Parent root = loader.load();

            // Obtenir la scène actuelle et afficher la nouvelle interface
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("❌ Erreur : Impossible de charger l'interface GestionOrganisateur.fxml");
            e.printStackTrace();
        }
    }





}
