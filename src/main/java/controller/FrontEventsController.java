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

import java.io.IOException;
import java.util.List;

public class FrontEventsController {

    @FXML
    private FlowPane eventCardContainer;

    private ServiceEvent eventService = new ServiceEvent(); // Service pour récupérer les événements

    @FXML
    public void initialize() {
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

            ImageView eventImage = new ImageView();
            eventImage.setFitHeight(120);
            eventImage.setFitWidth(200);
            eventImage.setPreserveRatio(true);
            eventImage.setImage(new Image(getClass().getResourceAsStream("/images/event-placeholder.jpg"))); // Placeholder si pas d'image

            Label title = new Label(event.getNomEvent());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");

            Label date = new Label("📅 " + event.getDate().toString());
            date.setStyle("-fx-text-fill: #666;");

            Label price = new Label("💰 " + event.getPrix() + " DT");
            price.setStyle("-fx-text-fill: #27AE60; -fx-font-weight: bold;");

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 5px;");
            detailsButton.setOnAction(e -> showEventDetails(event));

            // ✅ Nouveau bouton Réserver
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

    // 🎯 Méthode pour gérer la réservation d'un événement
    private void reserverEvent(Event event) {
        System.out.println("Réservation pour l'événement : " + event.getNomEvent());
        // Ajoute ici la logique de réservation, ex: ouvrir une popup, stocker dans la BD, etc.
    }


    private void showEventDetails(Event event) {
        System.out.println("Afficher les détails de l'événement : " + event.getNomEvent());
    }

    private void openEditPopup(Event event) {
        System.out.println("Modifier l'événement : " + event.getNomEvent());
    }

    private void deleteAndRefreshEvent(Event event) {
        System.out.println("Supprimer l'événement : " + event.getNomEvent());
        eventService.delete(event);
        displayEvents(); // Rafraîchir après suppression
    }

    public void goToAcceuil(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontAcceuil.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
