package tn.esprit.controllers;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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
    @FXML private WebView mapView; // Affichage de la carte Google Maps

    private int idEspace;
    private final ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();

    /**
     * Initialise les données de l'espace et affiche les détails.
     *
     * @param espace L'espace à afficher.
     */
    public void initData(Espace espace) {
        this.idEspace = espace.getIdEspace();
        titleLabel.setText("Détails de l'Espace : " + espace.getNomEspace());

        // Afficher les détails de l'espace
        espaceDetailsLabel.setText(
                "📍 Adresse : " + espace.getAdresse() + "\n" +
                        "👥 Capacité : " + espace.getCapacite() + "\n" +
                        "📅 Disponibilité : " + espace.getDisponibilite() + "\n" +
                        "💰 Prix : " + espace.getPrix() + " DT\n" +
                        "🏢 Type : " + espace.getTypeEspace()
        );

        // Afficher les organisateurs et la carte
        afficherOrganisateurs(idEspace);
        afficherCarte(espace.getAdresse());
    }

    /**
     * Affiche la carte Google Maps en fonction de l'adresse fournie.
     *
     * @param adresse L'adresse à afficher sur la carte.
     */
    private void afficherCarte(String adresse) {
        if (mapView == null) {
            System.out.println("❌ WebView est NULL !");
            return;
        }

        WebEngine webEngine = mapView.getEngine();
        if (webEngine == null) {
            System.out.println("❌ WebEngine est NULL !");
            return;
        }

        Platform.runLater(() -> {
            webEngine.setJavaScriptEnabled(true);

            // Ajouter un écouteur pour détecter les erreurs
            webEngine.setOnError(event -> System.out.println("❌ Erreur WebView : " + event.getMessage()));

            // Ajouter un écouteur de chargement
            webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == Worker.State.SUCCEEDED) {
                    System.out.println("✅ Carte chargée avec succès !");
                } else if (newValue == Worker.State.FAILED) {
                    System.out.println("❌ Échec du chargement de la carte.");
                    System.out.println("Erreur : " + webEngine.getLoadWorker().getException());
                }
            });

            // Chargement de Google Maps avec l'adresse
            String googleMapsUrl = "https://www.google.com/maps/search/?api=1&query=" + adresse.replace(" ", "+");
            webEngine.load(googleMapsUrl);
        });
    }

    /**
     * Affiche les organisateurs associés à l'espace.
     *
     * @param idEspace L'ID de l'espace.
     */
    private void afficherOrganisateurs(int idEspace) {
        organisateurContainer.getChildren().clear();

        List<Organisateur> organisateurs = serviceOrganisateur.getOrganisateursByEspace(idEspace);

        if (organisateurs.isEmpty()) {
            Label noOrganisateur = new Label("❌ Aucun organisateur trouvé.");
            noOrganisateur.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
            organisateurContainer.getChildren().add(noOrganisateur);
        } else {
            for (Organisateur organisateur : organisateurs) {
                HBox card = new HBox(15);
                card.setStyle("-fx-padding: 15px; -fx-background-color: white; -fx-border-radius: 10px; "
                        + "-fx-border-color: #8a2be2; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 5);");
                card.setPrefHeight(100);
                card.setPrefWidth(650);

                // Ajout d'une icône de profil
                Label profileIcon = new Label("🎭");
                profileIcon.setStyle("-fx-font-size: 30px; -fx-text-fill: #8a2be2;");

                // Conteneur des détails
                VBox detailsBox = new VBox(5);
                Label nameLabel = new Label("👤 " + organisateur.getNomOrg() + " " + organisateur.getPrenomOrg());
                nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #4b0082;");

                Label descriptionLabel = new Label("📝 " + organisateur.getDescriptionOrg());
                descriptionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

                Label phoneLabel = new Label("📞 " + organisateur.getTelef());
                phoneLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #27ae60;");

                detailsBox.getChildren().addAll(nameLabel, descriptionLabel, phoneLabel);

                // Conteneur des boutons
                HBox buttonBox = new HBox(10);
                buttonBox.setStyle("-fx-alignment: center-right;");

                Button btnModifier = new Button("✏ Modifier");
                btnModifier.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-border-radius: 8px; -fx-padding: 7px 12px;");
                btnModifier.setOnAction(event -> modifierOrganisateur(organisateur));

                Button btnSupprimer = new Button("🗑 Supprimer");
                btnSupprimer.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-border-radius: 8px; -fx-padding: 7px 12px;");
                btnSupprimer.setOnAction(event -> supprimerOrganisateur(organisateur));

                buttonBox.getChildren().addAll(btnModifier, btnSupprimer);

                card.getChildren().addAll(profileIcon, detailsBox, buttonBox);
                organisateurContainer.getChildren().add(card);
            }
        }
    }

    private void supprimerOrganisateur(Organisateur organisateur) {
        // Ajoutez ici le code de suppression d'un organisateur
    }

    private void modifierOrganisateur(Organisateur organisateur) {
        // Ajoutez ici le code de modification d'un organisateur
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

    @FXML
    private void ajouterOrganisateur(ActionEvent event) {
        // Ajoutez ici le code pour ouvrir la vue de gestion des organisateurs
    }
}
