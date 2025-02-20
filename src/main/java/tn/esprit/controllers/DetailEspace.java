package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.models.Organisateur;
import tn.esprit.services.ServiceOrganisateur;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DetailEspace {

    @FXML private Label titleLabel;
    @FXML private Label espaceDetailsLabel;
    @FXML private VBox organisateurContainer;
    @FXML private Button retourButton;
    @FXML private Button ajouterOrganisateurButton;

    private int idEspace;
    private final ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();

    public void initData(Espace espace) {
        this.idEspace = espace.getIdEspace();
        titleLabel.setText("Détails de l'Espace : " + espace.getNomEspace());

        espaceDetailsLabel.setText(
                "📍 Adresse : " + espace.getAdresse() + "\n" +
                        "👥 Capacité : " + espace.getCapacite() + "\n" +
                        "📅 Disponibilité : " + espace.getDisponibilite() + "\n" +
                        "💰 Prix : " + espace.getPrix() + " DT\n" +
                        "🏢 Type : " + espace.getTypeEspace()
        );

        afficherOrganisateurs(idEspace);
    }

    private void afficherOrganisateurs(int idEspace) {
        organisateurContainer.getChildren().clear();

        List<Organisateur> organisateurs = serviceOrganisateur.getOrganisateursByEspace(idEspace);

        if (organisateurs.isEmpty()) {
            Label noOrganisateur = new Label("❌ Aucun organisateur pour cet espace.");
            noOrganisateur.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
            organisateurContainer.getChildren().add(noOrganisateur);
        } else {
            for (Organisateur organisateur : organisateurs) {
                HBox card = new HBox(15);
                card.setStyle("-fx-padding: 15px; -fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-border-color: #8a2be2;"
                        + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5);");
                card.setPrefHeight(80);

                VBox detailsBox = new VBox(5);
                Label nameLabel = new Label("👤 " + organisateur.getNomOrg() + " " + organisateur.getPrenomOrg());
                nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #4b0082;");

                Label descriptionLabel = new Label("📝 " + organisateur.getDescriptionOrg());
                descriptionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

                Label phoneLabel = new Label("📞 " + organisateur.getTelef());
                phoneLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #27ae60;");

                detailsBox.getChildren().addAll(nameLabel, descriptionLabel, phoneLabel);

                // Boutons Modifier et Supprimer
                HBox buttonBox = new HBox(10);
                Button btnModifier = new Button("✏ Modifier");
                btnModifier.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-border-radius: 8px; -fx-padding: 5px 10px;");
                btnModifier.setOnAction(event -> modifierOrganisateur(organisateur));

                Button btnSupprimer = new Button("🗑 Supprimer");
                btnSupprimer.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-border-radius: 8px; -fx-padding: 5px 10px;");
                btnSupprimer.setOnAction(event -> supprimerOrganisateur(organisateur));

                buttonBox.getChildren().addAll(btnModifier, btnSupprimer);

                card.getChildren().addAll(detailsBox, buttonBox);
                organisateurContainer.getChildren().add(card);
            }
        }
    }


    private void modifierOrganisateur(Organisateur organisateur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierOrganisateur.fxml"));
            Parent root = loader.load();

            ModifierOrganisateur controller = loader.getController();
            controller.initData(organisateur);

            // Récupérer la scène actuelle et remplacer le contenu
            Stage stage = (Stage) organisateurContainer.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Erreur lors de l'ouverture de ModifierOrganisateur.fxml");
        }
    }



    private void supprimerOrganisateur(Organisateur organisateur) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de Suppression");
        alert.setHeaderText("Suppression de l'organisateur");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer " + organisateur.getNomOrg() + " ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            serviceOrganisateur.delete(organisateur.getIdOrg());
            afficherOrganisateurs(idEspace); // Rafraîchir l'affichage après suppression
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
