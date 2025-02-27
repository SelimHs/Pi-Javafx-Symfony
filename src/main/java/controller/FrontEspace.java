package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.services.ServiceEspace;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class FrontEspace {

    @FXML
    private FlowPane espaceCardContainer; // UI Container for displaying spaces

    private final ServiceEspace serviceEspace = new ServiceEspace(); // Service for fetching spaces
    @FXML
    private Button btnAccueil, btnEvenements,btnEspace;

    @FXML
    public void initialize() {
        applyHoverEffect(btnAccueil);
        applyHoverEffect(btnEvenements);
        applyHoverEffect(btnEspace);
        displaySpaces(); // Call the display method when the interface is initialized
    }

    public void displaySpaces() {
        espaceCardContainer.getChildren().clear(); // Clear existing spaces

        List<Espace> espaces = serviceEspace.getAll().stream()
                .filter(Optional::isPresent) // Keep only present values
                .map(Optional::get) // Extract Espace from Optional
                .collect(Collectors.toList()); // Collect to List<Espace>

        int columnCount = 3; // Display 3 spaces per row
        int index = 0;

        HBox rowContainer = new HBox(20); // Row container for horizontal spacing
        rowContainer.setStyle("-fx-alignment: center;");

        for (Espace espace : espaces) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 15px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 250px; -fx-max-width: 250px; -fx-alignment: center; -fx-spacing: 12;");

            // Image Handling
            ImageView espaceImage = new ImageView();
            espaceImage.setFitHeight(150);
            espaceImage.setFitWidth(230);
            espaceImage.setPreserveRatio(true);

            // Use real image if available

                espaceImage.setImage(new Image(getClass().getResourceAsStream("/images/espace-placeholder.jpg")));


            // Labels
            Label title = new Label(espace.getNomEspace());
            title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

            Label location = new Label("📍 " + espace.getAdresse());
            location.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

            Label capacity = new Label("👥 Capacité: " + espace.getCapacite() + " personnes");
            capacity.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

            Label price = new Label("💰 Prix: " + espace.getPrix() + " DT");
            price.setStyle("-fx-font-size: 15px; -fx-text-fill: #27AE60; -fx-font-weight: bold;");

            // Details Button
            Button detailsButton = new Button("Voir Détails");
            detailsButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-border-radius: 5px;");
            detailsButton.setOnAction(e -> showEspaceDetails(espace));

            // Add elements to the card
            card.getChildren().addAll(espaceImage, title, location, capacity, price, detailsButton);

            // Add card to row
            rowContainer.getChildren().add(card);
            index++;

            // After adding 3 cards, add the row to the container and start a new row
            if (index % columnCount == 0 || index == espaces.size()) {
                espaceCardContainer.getChildren().add(rowContainer);
                rowContainer = new HBox(20); // Create new row
                rowContainer.setStyle("-fx-alignment: center;");
            }
        }
    }



    // Function to show details (Modify to open a new window if needed)
    private void showEspaceDetails(Espace espace) {
        System.out.println("Afficher les détails de l'espace : " + espace.getNomEspace());
    }

    // Navigation Methods
    public void goToAcceuil(ActionEvent actionEvent) {
        System.out.println("Navigating to Accueil...");
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

    public void goToEvenements(ActionEvent actionEvent) {
        System.out.println("Navigating to Événements...");
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEvents.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: #F39C12; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
    }
}
