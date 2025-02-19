package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.models.Organisateur;
import tn.esprit.services.ServiceOrganisateur;

import java.io.IOException;
import java.util.List;

public class DetailEspace {

    @FXML private Label titleLabel;
    @FXML private Label espaceDetailsLabel;
    @FXML private VBox organisateurContainer;
    @FXML private Button retourButton;
    @FXML private Button ajouterOrganisateurButton;

    private int idEspace; // ID de l’espace sélectionné
    private final ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();

    public void initData(Espace espace) {
        this.idEspace = espace.getIdEspace(); // Stocker l'ID de l'espace sélectionné
        titleLabel.setText("Détails de l'Espace : " + espace.getNomEspace());

        espaceDetailsLabel.setText(
                "📍 Adresse : " + espace.getAdresse() + "\n" +
                        "👥 Capacité : " + espace.getCapacite() + "\n" +
                        "📅 Disponibilité : " + espace.getDisponibilite() + "\n" +
                        "💰 Prix : " + espace.getPrix() + " DT\n" +
                        "🏢 Type : " + espace.getTypeEspace()
        );

        // 🚀 Afficher les organisateurs liés à cet espace
        afficherOrganisateurs(idEspace);
    }

    private void afficherOrganisateurs(int idEspace) {
        organisateurContainer.getChildren().clear(); // Nettoyer avant de charger

        List<Organisateur> organisateurs = serviceOrganisateur.getOrganisateursByEspace(idEspace);

        if (organisateurs.isEmpty()) {
            Label noOrganisateur = new Label("❌ Aucun organisateur pour cet espace.");
            noOrganisateur.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
            organisateurContainer.getChildren().add(noOrganisateur);
        } else {
            for (Organisateur organisateur : organisateurs) {
                Label label = new Label("👤 " + organisateur.getNomOrg() + " " + organisateur.getPrenomOrg() +
                        " - 📝 " + organisateur.getDescriptionOrg());
                label.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-padding: 5px;");
                organisateurContainer.getChildren().add(label);
            }
        }
    }

    @FXML
    private void ajouterOrganisateur(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionOrganisateur.fxml"));
            Parent root = loader.load();

            GestionOrganisateur controller = loader.getController();
            controller.initData(idEspace);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Erreur lors du chargement de GestionOrganisateur.fxml");
        }
    }

    @FXML
    private void retourAfficherEspaces(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherEspaces.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
