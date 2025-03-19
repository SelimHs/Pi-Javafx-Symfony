package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class FrontEventsController {

    @FXML
    private FlowPane eventCardContainer;

    private final ServiceEvent eventService = new ServiceEvent(); // Service pour récupérer les événements

    @FXML
    private Button btnAccueil, btnEvenements, btnEspace,btnProduit;

    @FXML
    public void initialize() {
        applyHoverEffect(btnAccueil);
        applyHoverEffect(btnEvenements);
        applyHoverEffect(btnEspace);
        applyHoverEffect(btnProduit);

        displayEvents();
    }

    public void displayEvents() {
        eventCardContainer.getChildren().clear(); // Nettoyer avant d'ajouter de nouveaux événements
        List<Event> events = eventService.getAll(); // Récupération des événements depuis la BD

        for (Event event : events) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 220px; -fx-max-width: 220px; -fx-alignment: center; -fx-spacing: 10;");

            // 📷 ImageView pour afficher l'image de l'événement
            ImageView eventImage = new ImageView();
            eventImage.setFitHeight(120);
            eventImage.setFitWidth(200);
            eventImage.setPreserveRatio(true);

            // Vérifier si l'événement a une image enregistrée
            if (event.getImagePath() != null && !event.getImagePath().isEmpty()) {
                File imageFile = new File(event.getImagePath());
                if (imageFile.exists()) {
                    eventImage.setImage(new Image(imageFile.toURI().toString())); // Charger l'image réelle
                } else {
                    eventImage.setImage(new Image(getClass().getResourceAsStream("/images/event-placeholder.jpg"))); // Placeholder si l'image n'existe pas
                }
            } else {
                eventImage.setImage(new Image(getClass().getResourceAsStream("/images/event-placeholder.jpg"))); // Image par défaut
            }

            Label title = new Label(event.getNomEvent());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");

            Label date = new Label("📅 " + event.getDate().toString());
            date.setStyle("-fx-text-fill: #666;");

            Label price = new Label("💰 " + event.getPrix() + " DT");
            price.setStyle("-fx-text-fill: #27AE60; -fx-font-weight: bold;");

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 5px;");
            detailsButton.setOnAction(e -> showEventDetails(event));

            // ✅ Bouton Réserver
            Button reserverButton = new Button("Réserver");
            reserverButton.setStyle("-fx-background-color: #2ECC71; -fx-text-fill: white; -fx-border-radius: 5px;");
            reserverButton.setOnAction(e -> reserverEvent(event));

            HBox buttonContainer = new HBox(10);
            buttonContainer.setStyle("-fx-alignment: center;");
            buttonContainer.getChildren().addAll(detailsButton, reserverButton);

            card.getChildren().addAll(eventImage, title, date, price, buttonContainer);
            eventCardContainer.getChildren().add(card);
        }
    }

    // ✅ Redirige vers la page de détails de l'événement
    private void showEventDetails(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontDetailEvents.fxml"));
            Parent root = loader.load();

            // ✅ Récupérer le contrôleur et envoyer les données de l'événement
            FrontDetailEvents controller = loader.getController();
            controller.initData(event);

            // ✅ Changer la scène pour afficher les détails de l'événement
            Stage stage = (Stage) eventCardContainer.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Erreur lors du chargement de FrontDetailEvents.fxml");
        }
    }

    // 🎯 Gérer la réservation d'un événement
    private void reserverEvent(Event event) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation de réservation");
        confirmationDialog.setHeaderText("Réserver l'événement : " + event.getNomEvent());
        confirmationDialog.setContentText("Voulez-vous vraiment réserver cet événement ?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontBillet.fxml"));
                Parent root = loader.load();

                // ✅ Obtenir le contrôleur de FrontBillet et lui envoyer les données
                FrontBillet billetController = loader.getController();
                billetController.setPrixBillet(event.getPrix());
                billetController.setEventSelection(event);

                // ✅ Afficher la nouvelle interface
                Stage stage = (Stage) eventCardContainer.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void goToAcceuil(ActionEvent actionEvent) {
        changeScene(actionEvent, "/FrontAcceuil.fxml");
    }

    public void goToEspace(ActionEvent actionEvent) {
        changeScene(actionEvent, "/Frontend/FrontEspace.fxml");
    }

    private void changeScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Erreur lors du chargement de " + fxmlPath);
        }
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: #F39C12; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
    }

    public void handleLogout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Déconnexion");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir vous déconnecter ?");

        // Vérifier si l'utilisateur clique sur "OK"
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("🔒 Déconnexion confirmée...");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                Parent loginPage = loader.load();

                // Obtenir la scène actuelle et changer la page
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(loginPage));
                stage.show();

                System.out.println("✅ Déconnexion réussie !");
            } catch (IOException e) {
                System.out.println("❌ Erreur lors de la déconnexion : " + e.getMessage());
                e.printStackTrace();
                showAlert("Erreur de déconnexion", "Impossible d'ouvrir la page de connexion.");
            }
        }
    }
    // Méthode pour afficher une alerte en cas d'erreur
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void goToProduit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontProduit.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}