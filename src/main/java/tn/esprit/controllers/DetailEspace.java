package tn.esprit.controllers;

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
    @FXML private WebView mapView; // Affichage de la carte HERE

    private int idEspace;
    private final ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();
    private static final String HERE_API_KEY = "VOTRE_API_KEY_HERE"; // 🔑 Remplacez par votre clé API HERE

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
     * Affiche la carte HERE en fonction de l'adresse fournie.
     *
     * @param adresse L'adresse à afficher sur la carte.
     */
    private void afficherCarte(String adresse) {
        if (adresse == null || adresse.trim().isEmpty()) {
            System.out.println("📌 Aucune adresse fournie. Impossible d'afficher la carte.");
            return;
        }

        WebEngine webEngine = mapView.getEngine();

        // Ajouter un écouteur d'erreurs
        webEngine.setOnError(event -> {
            System.out.println("❌ Erreur lors du chargement de la carte : " + event.getMessage());
        });

        // Ajouter un écouteur pour vérifier l'état du chargement
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                System.out.println("✅ Carte chargée avec succès !");
                // Exécuter du JavaScript pour vérifier que la carte est initialisée
                webEngine.executeScript("console.log('Carte initialisée avec succès');");
            } else if (newValue == Worker.State.FAILED) {
                System.out.println("❌ Échec du chargement de la carte.");
            }
        });

        // Contenu HTML pour afficher la carte
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"fr\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <script src=\"https://js.api.here.com/v3/3.1/mapsjs-core.js\"></script>\n" +
                "    <script src=\"https://js.api.here.com/v3/3.1/mapsjs-service.js\"></script>\n" +
                "    <script src=\"https://js.api.here.com/v3/3.1/mapsjs-ui.js\"></script>\n" +
                "    <script src=\"https://js.api.here.com/v3/3.1/mapsjs-mapevents.js\"></script>\n" +
                "    <script>\n" +
                "    function initMap() {\n" +
                "        var platform = new H.service.Platform({\n" +
                "            'apikey': '" + HERE_API_KEY + "'\n" +
                "        });\n" +
                "\n" +
                "        var defaultLayers = platform.createDefaultLayers();\n" +
                "        var map = new H.Map(\n" +
                "            document.getElementById('mapContainer'),\n" +
                "            defaultLayers.vector.normal.map,\n" +
                "            {\n" +
                "                zoom: 12,\n" +
                "                center: { lat: 36.8065, lng: 10.1815 } // 🗺 Position par défaut : Tunis\n" +
                "            }\n" +
                "        );\n" +
                "\n" +
                "        var ui = H.ui.UI.createDefault(map, defaultLayers);\n" +
                "        var service = platform.getSearchService();\n" +
                "\n" +
                "        service.geocode({ q: '" + adresse + "' }, function(result) {\n" +
                "            if (result.items.length > 0) {\n" +
                "                var location = result.items[0].position;\n" +
                "                map.setCenter(location);\n" +
                "                var marker = new H.map.Marker(location);\n" +
                "                map.addObject(marker);\n" +
                "            } else {\n" +
                "                console.log(\"📌 Adresse introuvable !\");\n" +
                "            }\n" +
                "        }, alert);\n" +
                "    }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body onload=\"initMap()\">\n" +
                "    <div id=\"mapContainer\" style=\"width: 100%; height: 400px;\"></div>\n" +
                "</body>\n" +
                "</html>";

        // Charger le contenu HTML dans le WebView
        webEngine.loadContent(htmlContent);
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
                // 📌 Création d'une carte pour chaque organisateur
                HBox card = new HBox(15);
                card.setStyle("-fx-padding: 15px; -fx-background-color: white; -fx-border-radius: 10px; "
                        + "-fx-border-color: #8a2be2; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 5);");
                card.setPrefHeight(100);
                card.setPrefWidth(650);

                // 🖼 Ajout d'une icône de profil
                Label profileIcon = new Label("🎭");
                profileIcon.setStyle("-fx-font-size: 30px; -fx-text-fill: #8a2be2;");

                // 📌 Conteneur des détails
                VBox detailsBox = new VBox(5);
                Label nameLabel = new Label("👤 " + organisateur.getNomOrg() + " " + organisateur.getPrenomOrg());
                nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #4b0082;");

                Label descriptionLabel = new Label("📝 " + organisateur.getDescriptionOrg());
                descriptionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

                Label phoneLabel = new Label("📞 " + organisateur.getTelef());
                phoneLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #27ae60;");

                detailsBox.getChildren().addAll(nameLabel, descriptionLabel, phoneLabel);

                // 📌 Conteneur des boutons
                HBox buttonBox = new HBox(10);
                buttonBox.setStyle("-fx-alignment: center-right;");

                Button btnModifier = new Button("✏ Modifier");
                btnModifier.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-border-radius: 8px; -fx-padding: 7px 12px;");
                btnModifier.setOnAction(event -> modifierOrganisateur(organisateur));

                Button btnSupprimer = new Button("🗑 Supprimer");
                btnSupprimer.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-border-radius: 8px; -fx-padding: 7px 12px;");
                btnSupprimer.setOnAction(event -> supprimerOrganisateur(organisateur));

                buttonBox.getChildren().addAll(btnModifier, btnSupprimer);

                // 📌 Ajout des éléments à la carte
                card.getChildren().addAll(profileIcon, detailsBox, buttonBox);
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

    /**
     * Retourne à la vue "AfficherEspaces".
     *
     * @param event L'événement de clic sur le bouton.
     */
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
}