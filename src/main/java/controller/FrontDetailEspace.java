package controller;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.models.Organisateur;
import tn.esprit.services.ServiceOrganisateur;

import java.io.IOException;
import java.util.List;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class FrontDetailEspace {
    @FXML private ImageView qrCodeImage;

    @FXML private Label titleLabel;
    @FXML private Label espaceDetailsLabel;
    @FXML private VBox organisateurContainer;
    @FXML private WebView mapView;
    @FXML private Button retourButton;
    @FXML private ImageView espaceImage;

    private int idEspace;
    private final ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();
    @FXML
    private Button btnAccueil, btnEvenements, btnEspace;

    /**
     * Initialise les détails de l'espace et charge les organisateurs + la carte.
     * @param espace L'objet Espace à afficher
     */
    public void initData(Espace espace) {
        applyHoverEffect(btnAccueil);
        applyHoverEffect(btnEvenements);
        applyHoverEffect(btnEspace);
        this.idEspace = espace.getIdEspace();
        titleLabel.setText("Détails de l'Espace : " + espace.getNomEspace());

        espaceDetailsLabel.setText(
                "📍 Adresse : " + espace.getAdresse() + "\n" +
                        "👥 Capacité : " + espace.getCapacite() + " personnes\n" +
                        "📅 Disponibilité : " + espace.getDisponibilite() + "\n" +
                        "💰 Prix : " + espace.getPrix() + " DT\n" +
                        "🏢 Type : " + espace.getTypeEspace()
        );

        espaceImage.setImage(new Image(getClass().getResourceAsStream("/images/espace-placeholder.jpg")));

        afficherOrganisateurs(idEspace);
        afficherCarte(espace.getAdresse());
        generateQRCode(espace.getAdresse());
    }

    /**
     * Génère un QR code pour l'adresse de l'espace.
     * @param address L'adresse de l'espace
     */
    private void generateQRCode(String address) {
        String googleMapsUrl = "https://www.google.com/maps/search/?api=1&query=" + address.replace(" ", "+");
        int width = 200;
        int height = 200;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(googleMapsUrl, BarcodeFormat.QR_CODE, width, height);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            Image qrImage = new Image(inputStream);
            qrCodeImage.setImage(qrImage);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Applique un effet de survol aux boutons.
     * @param button Le bouton à styliser
     */
    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: #F39C12; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
    }

    /**
     * Affiche la carte Google Maps avec l'adresse fournie.
     * @param adresse Adresse à afficher sur la carte.
     */
    private void afficherCarte(String adresse) {
        if (mapView == null) {
            System.out.println("❌ Erreur : WebView est NULL !");
            return;
        }

        WebEngine webEngine = mapView.getEngine();
        Platform.runLater(() -> {
            webEngine.setJavaScriptEnabled(true);
            webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == Worker.State.SUCCEEDED) {
                    System.out.println("✅ Carte chargée avec succès !");
                } else if (newValue == Worker.State.FAILED) {
                    System.out.println("❌ Erreur de chargement de la carte.");
                }
            });

            String googleMapsUrl = "https://www.google.com/maps/search/?api=1&query=" + adresse.replace(" ", "+");
            webEngine.load(googleMapsUrl);
        });
    }

    /**
     * Affiche les organisateurs associés à l'espace.
     * @param idEspace L'ID de l'espace.
     */
    private void afficherOrganisateurs(int idEspace) {
        organisateurContainer.getChildren().clear();
        List<Organisateur> organisateurs = serviceOrganisateur.getOrganisateursByEspace(idEspace);

        if (organisateurs.isEmpty()) {
            Label noOrganisateur = new Label("❌ Aucun organisateur trouvé.");
            noOrganisateur.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
            organisateurContainer.getChildren().add(noOrganisateur);
        } else {
            for (Organisateur organisateur : organisateurs) {
                HBox card = new HBox(15);
                card.setStyle("-fx-padding: 15px; -fx-background-color: white; -fx-border-radius: 10px; "
                        + "-fx-border-color: #8a2be2; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 5);");
                card.setPrefHeight(100);
                card.setPrefWidth(650);

                Label profileIcon = new Label("🎭");
                profileIcon.setStyle("-fx-font-size: 30px; -fx-text-fill: #8a2be2;");

                VBox detailsBox = new VBox(5);
                Label nameLabel = new Label("👤 " + organisateur.getNomOrg() + " " + organisateur.getPrenomOrg());
                nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #4b0082;");

                Label descriptionLabel = new Label("📝 " + organisateur.getDescriptionOrg());
                descriptionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

                Label phoneLabel = new Label("📞 " + organisateur.getTelef());
                phoneLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #27ae60;");

                detailsBox.getChildren().addAll(nameLabel, descriptionLabel, phoneLabel);
                card.getChildren().addAll(profileIcon, detailsBox);
                organisateurContainer.getChildren().add(card);
            }
        }
    }

    /**
     * Retour à la page `FrontEspace.fxml`
     * @param event Événement déclenché par un bouton
     */
    @FXML
    private void retourAfficherEspaces(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEspace.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Redirige vers la page des événements.
     * @param event Événement déclenché par un bouton
     */
    public void goToEvenements(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEvents.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Redirige vers la page des espaces.
     * @param event Événement déclenché par un bouton
     */
    public void goToEspace(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEspace.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}