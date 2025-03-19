package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ModifierFournisseur {

    @FXML
    private TextField nomFournisseur, descriptionFournisseur, typeFournisseur, telephoneFournisseur;
    @FXML
    private ImageView fournisseurImage; // ✅ Image du fournisseur
    @FXML
    private Button btnModifier, btnRetour;

    private final ServiceFournisseur fournisseurService = new ServiceFournisseur();
    private fournisseur fournisseurActuel;
    private String imagePath; // ✅ Chemin de la nouvelle image sélectionnée

    /**
     * Initialise les champs avec les données du fournisseur sélectionné.
     */
    public void initData(fournisseur f) {
        if (f == null) {
            System.out.println("⚠ Erreur: Aucun fournisseur sélectionné !");
            return;
        }

        this.fournisseurActuel = f;

        // Remplir les champs avec les valeurs actuelles
        nomFournisseur.setText(f.getNomFournisseur());
        descriptionFournisseur.setText(f.getDescription());
        typeFournisseur.setText(f.getType());
        telephoneFournisseur.setText(f.getTelephone());

        // 📷 Charger l'image si elle existe
        if (f.getImagePath() != null && !f.getImagePath().isEmpty()) {
            File imageFile = new File(f.getImagePath());
            if (imageFile.exists()) {
                fournisseurImage.setImage(new Image(imageFile.toURI().toString()));
            } else {
                System.out.println("⚠ Image introuvable : " + f.getImagePath());
                fournisseurImage.setImage(new Image(getClass().getResourceAsStream("/images/fournisseur-placeholder.jpg")));
            }
        } else {
            // Placeholder si pas d'image
            fournisseurImage.setImage(new Image(getClass().getResourceAsStream("/images/fournisseur-placeholder.jpg")));
        }

        System.out.println("✅ Données du fournisseur chargées : " + f);
    }

    /**
     * Permet de choisir une nouvelle image pour le fournisseur
     */
    @FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image pour le fournisseur");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            try {
                // 📂 Copier l'image vers un dossier local
                String targetDir = "C:/wamp64/www/img/";
                String fileName = UUID.randomUUID() + "_" + selectedFile.getName();
                File destination = new File(targetDir + fileName);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // 📝 Mettre à jour l'ImageView
                imagePath = destination.getAbsolutePath();
                fournisseurImage.setImage(new Image(destination.toURI().toString()));
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de copier l'image : " + e.getMessage());
            }
        }
    }

    /**
     * Met à jour les informations du fournisseur dans la base de données.
     */
    @FXML
    public void modifierFournisseur(ActionEvent event) {
        if (fournisseurActuel == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Aucun fournisseur sélectionné !");
            return;
        }

        try {
            // Mise à jour des valeurs
            fournisseurActuel.setNomFournisseur(nomFournisseur.getText());
            fournisseurActuel.setDescription(descriptionFournisseur.getText());
            fournisseurActuel.setType(typeFournisseur.getText());
            fournisseurActuel.setTelephone(telephoneFournisseur.getText());

            // 📷 Mettre à jour l'image si on en a choisi une
            if (imagePath != null && !imagePath.isEmpty()) {
                fournisseurActuel.setImagePath(imagePath);
            }

            // Mise à jour dans la base de données
            fournisseurService.update(fournisseurActuel);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Le fournisseur a été modifié avec succès !");
            retourAfficherFournisseurs(event);

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite : " + e.getMessage());
        }
    }

    /**
     * Retourne à l'affichage des fournisseurs après modification.
     */
    @FXML
    public void retourAfficherFournisseurs(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principalefournisseur.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("⚠ Erreur: Impossible de charger Principalefournisseur.fxml !");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
