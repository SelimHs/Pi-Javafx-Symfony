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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.models.Organisateur;
import tn.esprit.services.ServiceOrganisateur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    @FXML private ImageView espaceImageView;

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

        // Afficher les détails
        espaceDetailsLabel.setText(
                "📍 Adresse : " + espace.getAdresse() + "\n" +
                        "👥 Capacité : " + espace.getCapacite() + "\n" +
                        "📅 Disponibilité : " + espace.getDisponibilite() + "\n" +
                        "💰 Prix : " + espace.getPrix() + " DT\n" +
                        "🏢 Type : " + espace.getTypeEspace()
        );

        // Charger et afficher l'image
        afficherImage(espace.getImage());

        afficherOrganisateurs(idEspace);
        afficherCarte(espace.getAdresse());
    }
    private void afficherImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            File file = new File(imagePath);
            if (file.exists()) {
                try {
                    espaceImageView.setImage(new Image(new FileInputStream(file)));
                } catch (FileNotFoundException e) {
                    System.out.println("Erreur chargement image : " + e.getMessage());
                    espaceImageView.setImage(null); // Ne rien afficher en cas d'erreur
                }
            } else {
                espaceImageView.setImage(null); // Ne rien afficher si le fichier n'existe pas
            }
        } else {
            espaceImageView.setImage(null); // Ne rien afficher si le chemin est vide
        }
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

                // 📌 Conteneur des icônes
                HBox buttonBox = new HBox(10);
                buttonBox.setStyle("-fx-alignment: center-right;");

                // ✏️ Icône Modifier
                Button btnModifier = new Button();
                btnModifier.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                btnModifier.setOnAction(event -> modifierOrganisateur(organisateur));

                ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/edit-icon.png")));
                editIcon.setFitWidth(18);
                editIcon.setFitHeight(18);
                btnModifier.setGraphic(editIcon);

                // 🗑️ Icône Supprimer
                Button btnSupprimer = new Button();
                btnSupprimer.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                btnSupprimer.setOnAction(event -> supprimerOrganisateur(organisateur));

                ImageView trashIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/trash-icon.png")));
                trashIcon.setFitWidth(18);
                trashIcon.setFitHeight(18);
                btnSupprimer.setGraphic(trashIcon);

                // Ajout des icônes au conteneur
                buttonBox.getChildren().addAll(btnModifier, btnSupprimer);

                card.getChildren().addAll(profileIcon, detailsBox, buttonBox);
                organisateurContainer.getChildren().add(card);
            }
        }
    }


    /**
     * Supprime un organisateur après confirmation.
     *
     * @param organisateur L'organisateur à supprimer.
     */
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

    /**
     * Ouvre la vue "GestionOrganisateur" pour ajouter un organisateur.
     *
     * @param event L'événement de clic sur le bouton.
     */
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
    public void buttonHoverEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));  // Une ombre douce
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }
}
