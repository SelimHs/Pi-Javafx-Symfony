package controller;

import javafx.event.ActionEvent;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import tn.esprit.models.Produit;
import tn.esprit.services.ServiceProduit;

public class ProduitMainController {
    @javafx.fxml.FXML
    private AnchorPane root;
    @javafx.fxml.FXML
    private Button modifierBouton;
    @javafx.fxml.FXML
    private Button deleteBouton;
    @javafx.fxml.FXML
    private FlowPane produitCardContainer;

    @FXML
    public void displayProduits(ActionEvent actionEvent) {
        produitCardContainer.getChildren().clear(); // Nettoyer avant de recharger

        ServiceProduit serviceProduit = new ServiceProduit();
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
            //detailsButton.setOnAction(e -> showProduitDetails(produit));

            card.getChildren().addAll(title, prix, description, categorie, quantite, fournisseur, detailsButton);
            produitCardContainer.getChildren().add(card);
        }
    }

}
