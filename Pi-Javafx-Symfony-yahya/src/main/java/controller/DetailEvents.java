package controller;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import tn.esprit.models.Event;

import java.io.IOException;

public class DetailEvents {

    @FXML private Label titleLabel;
    @FXML private Label eventDetailsLabel;
    @FXML private Label eventDateLabel;
    @FXML private Label eventPriceLabel;
    @FXML private Label eventLocationLabel;
    @FXML private ImageView eventImage;
    @FXML private WebView mapView;
    @FXML private Button retourButton;

    public void initData(Event event) {
        titleLabel.setText("Détails de l'Événement : " + event.getNomEvent());
        eventDetailsLabel.setText(event.getDetails());
        eventDateLabel.setText("📅 Date : " + event.getDate());
        eventPriceLabel.setText("💰 Prix : " + event.getPrix() + " DT");
        eventLocationLabel.setText("📍 Lieu : " + event.getNomEspace());

        eventImage.setImage(new Image(getClass().getResourceAsStream("/images/event-placeholder.jpg")));

        afficherCarte(event.getNomEspace());
    }

    private void afficherCarte(String adresse) {
        WebEngine webEngine = mapView.getEngine();
        Platform.runLater(() -> webEngine.load("https://www.google.com/maps/search/?api=1&query=" + adresse.replace(" ", "+")));
    }

    @FXML
    private void retourAfficherEvents() throws IOException {
        Stage stage = (Stage) retourButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Events.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
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
}
