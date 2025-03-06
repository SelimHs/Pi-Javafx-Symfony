package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tn.esprit.models.Billet;
import tn.esprit.services.PdfService;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class DetailBillet {

    @FXML private Label titleLabel;
    @FXML private Label billetProprietaireLabel;
    @FXML private Label billetDateLabel;
    @FXML private Label billetPrixLabel;
    @FXML private Label billetTypeLabel;
    @FXML private Label billetEventLabel;
    @FXML private ImageView billetImage;
    @FXML private WebView mapView;
    @FXML private Button retourButton;

    private Billet currentBillet;

    public void initData(Billet billet) {
        this.currentBillet = billet;

        titleLabel.setText("Détails du Billet de " + billet.getProprietaire());
        billetProprietaireLabel.setText("👤 Propriétaire : " + billet.getProprietaire());
        billetDateLabel.setText("📅 Date d'achat : " + billet.getDateAchat());
        billetPrixLabel.setText("💰 Prix : " + billet.getPrix() + " DT");
        billetTypeLabel.setText("🎟 Type : " + billet.getType());
        billetEventLabel.setText("📍 Événement : " + billet.getEvent().getNomEvent());

        //billetImage.setImage(new Image(getClass().getResourceAsStream("/images/billet-placeholder.jpg")));

    }



    @FXML
    private void retourAfficherBillets(javafx.event.ActionEvent actionEvent) {
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

    @FXML
    private void exporterBilletPDF() {
        String pdfUrl = PdfService.generatePdfFromBillet(
                currentBillet,
                currentBillet.getProprietaire(),
                currentBillet.getEvent().toString(),
                currentBillet.getPrix()
        );

        if (pdfUrl != null) {
            try {
                Desktop.getDesktop().browse(new URI(pdfUrl));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Effets de survol pour les boutons
    @FXML
    public void buttonHoverEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }

    public void RetourBillet(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Billet.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
