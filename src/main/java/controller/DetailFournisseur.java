package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;

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

    private fournisseur selectedFournisseur;

    public void initData(fournisseur f) {
        this.selectedFournisseur = f;
        titleLabel.setText("Détails du Fournisseur : " + f.getNomFournisseur());
        fournisseurNomLabel.setText("🏢 Nom : " + f.getNomFournisseur());
        fournisseurTypeLabel.setText("🏷 Type : " + f.getType());
        fournisseurTelephoneLabel.setText("📞 Téléphone : " + f.getTelephone());
        fournisseurDescriptionLabel.setText("📝 Description : " + f.getDescription());
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
