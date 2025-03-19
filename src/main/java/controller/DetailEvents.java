package controller;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.Event;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DetailEvents {

    @FXML private Label titleLabel;
    @FXML private Label eventDetailsLabel;
    @FXML private Label eventDateLabel;
    @FXML private Label eventPriceLabel;
    @FXML private Label eventLocationLabel;
    @FXML private ImageView eventImage;
    @FXML private WebView mapView;
    @FXML private Button retourButton;
    @FXML
    private String imagePath;


    public void initData(Event event) {
        titleLabel.setText("Détails de l'Événement : " + event.getNomEvent());
        eventDetailsLabel.setText(event.getDetails());
        eventDateLabel.setText("📅 Date : " + event.getDate());
        eventPriceLabel.setText("💰 Prix : " + event.getPrix() + " DT");
        eventLocationLabel.setText("📍 Lieu : " + event.getNomEspace());

        // Vérifier si l'événement a une image et l'afficher
        if (event.getImagePath() != null && !event.getImagePath().isEmpty()) {
            File file = new File(event.getImagePath());
            if (file.exists()) {
                eventImage.setImage(new Image(file.toURI().toString()));
            } else {
                eventImage.setImage(new Image(getClass().getResourceAsStream("/images/event-placeholder.jpg")));
            }
        } else {
            eventImage.setImage(new Image(getClass().getResourceAsStream("/images/event-placeholder.jpg")));
        }
    }
    @FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            try {
                String targetDir = "C:/wamp64/www/img/";
                String fileName = System.currentTimeMillis() + "_" + selectedFile.getName();
                File destination = new File(targetDir + fileName);
                Files.copy(selectedFile.toPath(), destination.toPath());

                imagePath = destination.getAbsolutePath();
                eventImage.setImage(new Image(destination.toURI().toString()));

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors du téléchargement de l'image.");
                alert.show();
            }
        }
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
