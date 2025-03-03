package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import tn.esprit.models.Produit;
import tn.esprit.services.ServiceProduit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FrontProduit {

    @FXML
    private FlowPane produitCardContainer;

    @FXML
    private Label ideeEvenementText; // Label pour afficher l'idée d'événement

    private final ServiceProduit serviceProduit = new ServiceProduit();
    private final List<Produit> selectedProduits = new ArrayList<>();

    @FXML
    private Button btnAccueil, btnEvenements, btnEspace, btnGenererEvenement;

    @FXML
    public void initialize() {
        displayProduits(); // Afficher les produits au démarrage
    }

    // Afficher les produits dans le conteneur
    public void displayProduits() {
        produitCardContainer.getChildren().clear();

        List<Produit> produits = serviceProduit.getAll();
        for (Produit produit : produits) {
            VBox card = createProduitCard(produit);
            produitCardContainer.getChildren().add(card);
        }
    }

    // 📌 Création d'une carte produit avec le design du Code 1
    private VBox createProduitCard(Produit produit) {
        VBox card = new VBox();
        card.setStyle("-fx-background-color: white; -fx-padding: 15px; -fx-border-radius: 10px; "
                + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                + "-fx-min-width: 250px; -fx-max-width: 250px; -fx-alignment: center; -fx-spacing: 12;");

        // 📌 Image du produit
        ImageView produitImage = new ImageView();
        produitImage.setFitHeight(150);
        produitImage.setFitWidth(230);
        produitImage.setPreserveRatio(true);
        produitImage.setImage(new Image(getClass().getResourceAsStream("/images/produit-placeholder.jpg")));

        // 📌 Labels d'information
        Label title = new Label(produit.getNomProduit());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label prix = new Label("💰 Prix: " + produit.getPrixProduit() + " DT");
        prix.setStyle("-fx-font-size: 15px; -fx-text-fill: #27AE60; -fx-font-weight: bold;");

        Label description = new Label("📝 " + produit.getDescription());
        description.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        Label categorie = new Label("📦 Catégorie: " + produit.getCategorie().name());
        categorie.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        Label quantite = new Label("🔢 Quantité: " + produit.getQuantite());
        quantite.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        Label fournisseur = new Label("🏢 Fournisseur: " + produit.getFournisseur().getNomFournisseur());
        fournisseur.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        // 📌 CheckBox pour sélectionner un produit
        CheckBox checkBox = new CheckBox("Sélectionner");
        checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
                selectedProduits.add(produit);
            } else {
                selectedProduits.remove(produit);
            }
        });

        // 📌 Bouton "Voir Détails"
        Button detailsButton = new Button("Voir Détails");
        detailsButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-border-radius: 5px;");
        detailsButton.setOnAction(e -> showProduitDetails(produit)); // Affiche les détails du produit

        // 📌 Conteneur pour les boutons
        HBox buttonContainer = new HBox(10);
        buttonContainer.setStyle("-fx-alignment: center;");
        buttonContainer.getChildren().addAll(checkBox, detailsButton);

        // 📌 Ajouter les éléments à la carte
        card.getChildren().addAll(produitImage, title, prix, description, categorie, quantite, fournisseur, buttonContainer);

        return card;
    }

    // 📌 Méthode pour afficher les détails d'un produit
    private void showProduitDetails(Produit produit) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails du Produit");
        alert.setHeaderText(produit.getNomProduit());
        alert.setContentText("Prix: " + produit.getPrixProduit() + " DT\n"
                + "Description: " + produit.getDescription() + "\n"
                + "Catégorie: " + produit.getCategorie().name() + "\n"
                + "Quantité: " + produit.getQuantite() + "\n"
                + "Fournisseur: " + produit.getFournisseur().getNomFournisseur());
        alert.showAndWait();
    }

    // 📌 Méthode pour générer une idée d'événement avec OpenAI
    @FXML
    private void genererEvenement() {
        if (selectedProduits.isEmpty()) {
            ideeEvenementText.setText("⚠️ Sélectionnez au moins un produit !");
            return;
        }

        // Appel à l'IA prédictive OpenAI
        String ideeEvenement = AI_Predictive.generateEventIdea(selectedProduits);
        ideeEvenementText.setText(ideeEvenement);
    }

    // 📌 Méthodes de navigation
    public void goToAcceuil(ActionEvent actionEvent) { navigateTo("/FrontAcceuil.fxml", actionEvent); }
    public void goToEvenements(ActionEvent actionEvent) { navigateTo("/Frontend/FrontEvents.fxml", actionEvent); }
    public void goToEspace(ActionEvent actionEvent) { navigateTo("/Frontend/FrontEspace.fxml", actionEvent); }

    private void navigateTo(String fxml, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible d'ouvrir la page demandée.");
        }
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
}
