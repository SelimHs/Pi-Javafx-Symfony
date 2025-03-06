package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private Button btnAccueil, btnEvenements,btnEspace,btnProduit;

    @FXML
    public void initialize() {
        applyHoverEffect(btnAccueil);
        applyHoverEffect(btnEvenements);
        applyHoverEffect(btnEspace);
        applyHoverEffect(btnProduit);

        displaySpaces(); // Call the display method when the interface is initialized
    }

    public void displaySpaces() {
        espaceCardContainer.getChildren().clear(); // Clear existing spaces

        List<Espace> espaces = serviceEspace.getAll().stream()
                .filter(Optional::isPresent) // Keep only present values
                .map(Optional::get) // Extract Espace from Optional
                .collect(Collectors.toList()); // Collect to List<Espace>

        int columnCount = 3; // 3 espaces par ligne
        int index = 0;

        HBox rowContainer = new HBox(20); // Conteneur de ligne pour l'affichage horizontal
        rowContainer.setStyle("-fx-alignment: center;");

        for (Espace espace : espaces) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 15px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 250px; -fx-max-width: 250px; -fx-alignment: center; -fx-spacing: 12;");

            // 📌 Image de l’espace
            ImageView espaceImage = new ImageView();
            espaceImage.setFitHeight(150);
            espaceImage.setFitWidth(230);
            espaceImage.setPreserveRatio(true);
            espaceImage.setImage(new Image(getClass().getResourceAsStream("/images/espace-placeholder.jpg"))); // Placeholder

            // 📌 Labels d'information
            Label title = new Label(espace.getNomEspace());
            title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

            Label location = new Label("📍 " + espace.getAdresse());
            location.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

            Label capacity = new Label("👥 Capacité: " + espace.getCapacite() + " personnes");
            capacity.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

            Label price = new Label("💰 Prix: " + espace.getPrix() + " DT");
            price.setStyle("-fx-font-size: 15px; -fx-text-fill: #27AE60; -fx-font-weight: bold;");

            // 📌 Bouton "Voir Détails"
            Button detailsButton = new Button("Voir Détails");
            detailsButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-border-radius: 5px;");
            detailsButton.setOnAction(e -> showEspaceDetails(espace)); // Ouvre les détails de l’espace

            // 📡 Icône "Live" (pour ouvrir le Live de l’espace)
            ImageView liveIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/live-icon.png")));
            liveIcon.setFitWidth(24);
            liveIcon.setFitHeight(24);

            Button liveButton = new Button();
            liveButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            liveButton.setGraphic(liveIcon);
            liveButton.setOnAction(e -> goToLiveEspace(espace)); // Ouvre LiveEspace.fxml

            // 📌 Conteneur pour les boutons (Détails + Live)
            HBox buttonContainer = new HBox(10);
            buttonContainer.setStyle("-fx-alignment: center;");
            buttonContainer.getChildren().addAll(detailsButton, liveButton);

            // 📌 Ajouter les éléments à la carte
            card.getChildren().addAll(espaceImage, title, location, capacity, price, buttonContainer);

            // 📌 Ajouter la carte à la ligne
            rowContainer.getChildren().add(card);
            index++;

            // 📌 Ajouter la ligne complète au conteneur et en créer une nouvelle
            if (index % columnCount == 0 || index == espaces.size()) {
                espaceCardContainer.getChildren().add(rowContainer);
                rowContainer = new HBox(20);
                rowContainer.setStyle("-fx-alignment: center;");
            }
        }
    }

    @FXML
    public void goToLiveEspace(Espace espace) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/LiveEspace.fxml"));
            Parent root = loader.load();

            // 📌 Récupérer le contrôleur de la nouvelle page et lui envoyer les données de l'espace
            LiveEspace controller = loader.getController();
            controller.initData(espace);  // Transmettre les informations de l'espace

            // 📌 Afficher la nouvelle scène
            Stage stage = (Stage) espaceCardContainer.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Erreur lors du chargement de LiveEspace.fxml");
        }
    }



    /**
     * 🔍 Ouvre la page FrontDetailEspace.fxml pour afficher les détails de l'espace sélectionné.
     */
    private void showEspaceDetails(Espace espace) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontDetailEspace.fxml"));
            Parent root = loader.load();

            // 📌 Récupérer le contrôleur de la nouvelle page et lui envoyer les données de l'espace
            FrontDetailEspace controller = loader.getController();
            controller.initData(espace);  // Transmettre les informations de l'espace

            // 📌 Afficher la nouvelle scène
            Stage stage = (Stage) espaceCardContainer.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Erreur lors du chargement de FrontDetailEspace.fxml");
        }
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
