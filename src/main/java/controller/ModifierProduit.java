package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.Produit;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;
import tn.esprit.services.ServiceProduit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

public class ModifierProduit {

    @FXML
    private TextField nomProduit, prixProduit, descriptionProduit, quantiteProduit;
    @FXML
    private ComboBox<String> categorieProduit; // On stocke la valeur .name() de l'enum
    @FXML
    private ComboBox<String> fournisseurProduit;
    @FXML
    private Button btnModifier, btnRetour;

    @FXML
    private ImageView produitImage; // ✅ Affichage de l'image

    private final ServiceProduit serviceProduit = new ServiceProduit();
    private final ServiceFournisseur serviceFournisseur = new ServiceFournisseur();

    private Produit produitActuel;
    private String imagePath; // ✅ Chemin de la nouvelle image, s'il y en a une

    @FXML
    public void initialize() {
        // Charger la liste de tous les fournisseurs
        List<fournisseur> fournisseurs = serviceFournisseur.getAll();
        fournisseurProduit.getItems().clear();
        for (fournisseur f : fournisseurs) {
            fournisseurProduit.getItems().add(f.getNomFournisseur());
        }

        // Charger la liste des catégories
        categorieProduit.getItems().clear();
        for (Produit.CategorieProduit cat : Produit.CategorieProduit.values()) {
            categorieProduit.getItems().add(cat.name());
        }
    }

    // Méthode pour initialiser les champs du produit à modifier
    public void initData(Produit produit) {
        if (produit == null) {
            System.out.println("⚠ Erreur: Aucun produit sélectionné !");
            return;
        }

        this.produitActuel = produit;

        // Remplir les champs avec les valeurs actuelles
        nomProduit.setText(produit.getNomProduit());
        prixProduit.setText(String.valueOf(produit.getPrixProduit()));
        descriptionProduit.setText(produit.getDescription());
        quantiteProduit.setText(String.valueOf(produit.getQuantite()));

        if (produit.getCategorie() != null) {
            categorieProduit.setValue(produit.getCategorie().name());
        }

        if (produit.getFournisseur() != null) {
            fournisseurProduit.setValue(produit.getFournisseur().getNomFournisseur());
        }

        // ✅ Afficher l'image du produit s'il y en a une
        if (produit.getImagePath() != null && !produit.getImagePath().isEmpty()) {
            File imageFile = new File(produit.getImagePath());
            if (imageFile.exists()) {
                produitImage.setImage(new Image(imageFile.toURI().toString()));
            } else {
                // Placeholder si l'image n'existe pas
                produitImage.setImage(new Image(getClass().getResourceAsStream("/images/produit-placeholder.jpg")));
            }
        } else {
            // Placeholder si pas d'image
            produitImage.setImage(new Image(getClass().getResourceAsStream("/images/produit-placeholder.jpg")));
        }
    }

    // Choisir une nouvelle image
    @FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image pour le produit");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            try {
                String targetDir = "C:/wamp64/www/img/";
                String fileName = UUID.randomUUID() + "_" + selectedFile.getName();
                File destination = new File(targetDir + fileName);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                imagePath = destination.getAbsolutePath();
                produitImage.setImage(new Image(destination.toURI().toString()));

            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de télécharger l'image : " + e.getMessage());
            }
        }
    }

    // Méthode pour modifier le produit
    @FXML
    public void modifierProduit(ActionEvent event) {
        if (produitActuel == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Aucun produit sélectionné !");
            return;
        }

        try {
            // Mettre à jour le produit
            produitActuel.setNomProduit(nomProduit.getText());
            produitActuel.setPrixProduit(Integer.parseInt(prixProduit.getText()));
            produitActuel.setDescription(descriptionProduit.getText());
            produitActuel.setQuantite(Integer.parseInt(quantiteProduit.getText()));

            // Mise à jour de la catégorie
            if (categorieProduit.getValue() != null) {
                produitActuel.setCategorie(Produit.CategorieProduit.valueOf(categorieProduit.getValue()));
            }

            // Vérifier et assigner le fournisseur
            fournisseur selectedFournisseur = serviceFournisseur.getFournisseurFromDatabase(fournisseurProduit.getValue());
            if (selectedFournisseur == null) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Fournisseur introuvable !");
                return;
            }
            produitActuel.setFournisseur(selectedFournisseur);

            // Si une nouvelle image a été choisie
            if (imagePath != null && !imagePath.isEmpty()) {
                produitActuel.setImagePath(imagePath);
            }

            // Mettre à jour dans la base
            serviceProduit.update(produitActuel);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Le produit a été modifié avec succès !");

            // Retourner à la liste
            retourAfficherProduits(event);

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le prix et la quantité doivent être des nombres !");
        }
    }

    @FXML
    public void retourAfficherProduits(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PrincipaleProduits.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger PrincipaleProduits.fxml !");
        }
    }

    // Méthode utilitaire pour afficher des Alert
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
