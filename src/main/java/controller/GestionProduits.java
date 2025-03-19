package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.Produit;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;
import tn.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

public class GestionProduits {

    private final ServiceFournisseur serviceFournisseur = new ServiceFournisseur();

    @FXML
    private ComboBox<Produit.CategorieProduit> categorieProduit;
    @FXML
    private TextField descriptionProduit;
    @FXML
    private TextField quantiteProduit;
    @FXML
    private TextField nomProduit;
    @FXML
    private TextField prixProduit;
    @FXML
    private ComboBox<fournisseur> fournisseurProduit;
    @FXML
    private ImageView imageProduit; // ✅ Ajout du champ ImageView
    private String imagePath; // ✅ Stocke le chemin de l'image

    @FXML
    public void initialize() {
        // Charger la liste des catégories
        categorieProduit.setItems(FXCollections.observableArrayList(Produit.CategorieProduit.values()));

        // Charger les fournisseurs depuis la base de données
        List<fournisseur> fournisseurs = serviceFournisseur.getAll();
        fournisseurProduit.setItems(FXCollections.observableArrayList(fournisseurs));

        // Définir un texte par défaut pour les ComboBox
        categorieProduit.setPromptText("Sélectionner une catégorie");
        fournisseurProduit.setPromptText("Sélectionner un fournisseur");
    }

    @FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            try {
                String targetDir = "C:/wamp64/www/img/";
                String fileName = UUID.randomUUID() + "_" + selectedFile.getName();
                File destination = new File(targetDir + fileName);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                imagePath = destination.getAbsolutePath();
                imageProduit.setImage(new Image(destination.toURI().toString()));

            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de télécharger l'image.");
            }
        }
    }

    @FXML
    public void addProduit(ActionEvent actionEvent) {
        if (nomProduit.getText().isEmpty() || prixProduit.getText().isEmpty() ||
                descriptionProduit.getText().isEmpty() || categorieProduit.getValue() == null ||
                quantiteProduit.getText().isEmpty() || fournisseurProduit.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Erreur", "⚠️ Veuillez remplir tous les champs !");
            return;
        }

        try {
            Produit produit = new Produit();
            ServiceProduit sp = new ServiceProduit();

            produit.setNomProduit(nomProduit.getText());
            produit.setPrixProduit(Integer.parseInt(prixProduit.getText()));
            produit.setDescription(descriptionProduit.getText());
            produit.setCategorie(categorieProduit.getValue());
            produit.setQuantite(Integer.parseInt(quantiteProduit.getText()));
            produit.setFournisseur(fournisseurProduit.getValue());

            if (imagePath != null) {
                produit.setImagePath(imagePath); // ✅ Ajout de l’image
            }

            sp.add(produit);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "✅ Produit ajouté avec succès !");

            // Réinitialiser les champs après l'ajout
            nomProduit.clear();
            prixProduit.clear();
            descriptionProduit.clear();
            categorieProduit.getSelectionModel().clearSelection();
            quantiteProduit.clear();
            fournisseurProduit.getSelectionModel().clearSelection();
            imageProduit.setImage(null); // ✅ Réinitialisation de l’image

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "⚠️ Le prix et la quantité doivent être des nombres valides !");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "❌ Une erreur s'est produite : " + e.getMessage());
        }
    }
    // 📢 Afficher une alerte
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void goToProduitsList(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PrincipaleProduits.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
    }

    @FXML
    public void goToAcceuil(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("❌ Erreur : " + e.getMessage());
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
