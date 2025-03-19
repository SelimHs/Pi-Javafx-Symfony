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
import tn.esprit.models.fournisseur;

import java.io.File;
import java.io.IOException;

public class DetailFournisseur {

    @FXML
    private Label titleLabel;
    @FXML
    private Label fournisseurNomLabel;
    @FXML
    private Label fournisseurTypeLabel;
    @FXML
    private Label fournisseurTelephoneLabel;
    @FXML
    private Label fournisseurDescriptionLabel;
    @FXML
    private Button retourButton;
    @FXML
    private ImageView fournisseurImage;

    private fournisseur selectedFournisseur;

    public void initData(fournisseur f) {
        this.selectedFournisseur = f;
        titleLabel.setText("Détails du Fournisseur : " + f.getNomFournisseur());
        fournisseurNomLabel.setText("🏢 Nom : " + f.getNomFournisseur());
        fournisseurTypeLabel.setText("🏷 Type : " + f.getType());
        fournisseurTelephoneLabel.setText("📞 Téléphone : " + f.getTelephone());
        fournisseurDescriptionLabel.setText("📝 Description : " + f.getDescription());

        // 📷 Vérifier si l'image existe et l'afficher
        if (f.getImagePath() != null && !f.getImagePath().isEmpty()) {
            File imageFile = new File(f.getImagePath());
            if (imageFile.exists()) {
                fournisseurImage.setImage(new Image(imageFile.toURI().toString()));
            } else {
                System.out.println("⚠ Fichier image introuvable : " + f.getImagePath());
                fournisseurImage.setImage(new Image(getClass().getResourceAsStream("/images/fournisseur-placeholder.jpg")));
            }
        } else {
            // Image par défaut si aucun chemin
            fournisseurImage.setImage(new Image(getClass().getResourceAsStream("/images/fournisseur-placeholder.jpg")));
        }
    }


    @FXML
    private void retourFournisseurs(ActionEvent actionEvent) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/Principalefournisseur.fxml"));
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
