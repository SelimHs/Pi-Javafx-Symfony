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
import tn.esprit.models.Remise;
import tn.esprit.services.PdfService;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class DetailRemise {

    @FXML private Label titleLabel;
    @FXML private Label remiseCodeLabel;
    @FXML private Label remisePourcentageLabel;
    @FXML private Label remiseExpirationLabel;
    @FXML private Label remiseDescriptionLabel;
    @FXML private Button retourButton;
    @FXML private Button exportButton;

    private Remise remise;

    // Initialiser les données de la remise sélectionnée
    public void initData(Remise remise) {
        this.remise = remise;
        titleLabel.setText("Détails de la Remise : " + remise.getCodePromo());
        remiseCodeLabel.setText("🔖 Code Promo : " + remise.getCodePromo());
        remisePourcentageLabel.setText("💲 Pourcentage : " + remise.getPourcentageRemise() + "%");
        remiseExpirationLabel.setText("📅 Expiration : " + remise.getDateExpiration());
        remiseDescriptionLabel.setText("📝 Description : " + remise.getDescription());
    }

    // Retourner à la liste des remises
    @FXML
    private void retourAfficherRemises(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Acceuil.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RetourRemises(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherRemise.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
