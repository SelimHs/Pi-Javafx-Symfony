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
import javafx.stage.Stage;
import tn.esprit.models.Produit;

import java.io.IOException;

public class DetailProduit {

    @FXML
    private Label titleLabel;
    @FXML
    private Label produitNomLabel;
    @FXML
    private Label produitPrixLabel;
    @FXML
    private Label produitCategorieLabel;
    @FXML
    private Label produitQuantiteLabel;
    @FXML
    private Label produitDescriptionLabel;
    @FXML
    private Label produitFournisseurLabel;
    @FXML
    private ImageView produitImage;
    @FXML
    private Button retourButton;

    private Produit selectedProduit;

    public void initData(Produit produit) {
        this.selectedProduit = produit;
        titleLabel.setText("Détails du Produit : " + produit.getNomProduit());
        produitNomLabel.setText("🛒 Nom : " + produit.getNomProduit());
        produitPrixLabel.setText("💰 Prix : " + produit.getPrixProduit() + " DT");
        produitCategorieLabel.setText("📦 Catégorie : " + produit.getCategorie().name());
        produitQuantiteLabel.setText("🔢 Quantité : " + produit.getQuantite());
        produitDescriptionLabel.setText("📝 Description : " + produit.getDescription());
        produitFournisseurLabel.setText("🏢 Fournisseur : " +
                (produit.getFournisseur() != null ? produit.getFournisseur().getNomFournisseur() : "Non défini"));


    }

    @FXML
    private void retourProduits(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/PrincipaleProduits.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void retourAccueil(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Acceuil.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
