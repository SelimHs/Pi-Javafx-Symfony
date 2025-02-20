package controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Produit;
import tn.esprit.services.ServiceProduit;
import java.net.URL;
import java.util.ResourceBundle;

public class ProduitMainController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private FlowPane produitCardContainer;

    private final ServiceProduit serviceProduit = new ServiceProduit();

    /**
     * Initialisation automatique lors du chargement de la page.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayProduits();
    }

    /**
     * Affiche tous les produits dynamiquement.
     */
    @FXML
    public void displayProduits() {
        produitCardContainer.getChildren().clear(); // Nettoyer avant de recharger
        List<Produit> produits = serviceProduit.getAll(); // Récupérer tous les produits

        for (Produit produit : produits) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

            Label title = new Label(produit.getNomProduit());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label prix = new Label("💰 Prix: " + produit.getPrixProduit() + " DT");
            Label description = new Label("📝 " + produit.getDescription());
            Label categorie = new Label("📦 Catégorie: " + produit.getCategorie().name());
            Label quantite = new Label("🔢 Quantité: " + produit.getQuantite());
            Label fournisseur = new Label("🏢 Fournisseur: " + (produit.getFournisseur() != null ? produit.getFournisseur().getNomFournisseur() : "Non défini"));

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setOnAction(e -> showProduitDetails(produit));

            Button modifyButton = new Button("Modifier");
            modifyButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
            modifyButton.setOnAction(e -> goToModifierProduit(produit, e));

            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> deleteProduit(produit));

            card.getChildren().addAll(title, prix, description, categorie, quantite, fournisseur, detailsButton, modifyButton, deleteButton);
            produitCardContainer.getChildren().add(card);
        }
    }

    /**
     * Gère l'affichage des détails d'un produit.
     */
    private void showProduitDetails(Produit produit) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails du Produit");
        alert.setHeaderText(produit.getNomProduit());
        alert.setContentText("🆔 ID : " + produit.getIdProduit() +
                "\n💰 Prix : " + produit.getPrixProduit() + " DT" +
                "\n📝 Description : " + produit.getDescription() +
                "\n📦 Catégorie : " + produit.getCategorie().name() +
                "\n🔢 Quantité : " + produit.getQuantite() +
                "\n🏢 Fournisseur : " + (produit.getFournisseur() != null ? produit.getFournisseur().getNomFournisseur() : "Non défini"));

        alert.showAndWait();
    }

    /**
     * Redirige vers la page de modification d'un produit.
     */
    @FXML
    public void goToModifierProduit(Produit produit, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierProduit.fxml"));
            Parent root = loader.load();

            ModifierProduit controller = loader.getController();
            controller.initData(produit);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier un Produit");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("⚠️ Erreur lors du chargement de ModifierProduit.fxml !");
        }
    }

    /**
     * Supprime un produit et rafraîchit l'affichage.
     */
    @FXML
    public void deleteProduit(Produit produit) {
        serviceProduit.delete(produit);
        displayProduits(); // Rafraîchir l'affichage après suppression
    }

    /**
     * Retourne à l'accueil.
     */
    public void goToAcceuil(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Redirige vers la gestion des produits.
     */
    public void goToGestionProduits(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GestionProduits.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
