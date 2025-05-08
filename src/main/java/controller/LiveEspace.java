package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tn.esprit.models.Espace;

import java.io.IOException;

public class LiveEspace {
    @FXML private Label espaceDetailsLabel;
    @FXML private WebView liveStreamView;
    @FXML private Button btnAccueil, btnEvenements, btnEspace;
    @FXML private ImageView espaceImage;
    @FXML private Label titleLabel;

    /**
     * Initialise la scène avec les données de l'espace sélectionné.
     */
    public void initData(Espace espace) {
        applyHoverEffect(btnAccueil);
        applyHoverEffect(btnEvenements);
        applyHoverEffect(btnEspace);
        System.out.println("📌 Initialisation de LiveEspace...");

        titleLabel.setText("Détails de l'Espace : " + espace.getNomEspace());

        espaceDetailsLabel.setText(
                "📍 Adresse : " + espace.getAdresse() + "\n" +
                        "🏢 Type : " + espace.getTypeEspace() + "\n" +
                        "👥 Capacité : " + espace.getCapacite()
        );

        // 📡 Définir l'URL du live stream avec la capacité comme port
        String ip = "192.168.137.174"; // Remplace par l’IP de ton téléphone
        int port = espace.getCapacite(); // 🔥 Utilisation de la capacité comme port

        String liveURL = "http://" + ip + ":" + port + "/jsfs.html";

        System.out.println("🎥 Live stream chargé depuis : " + liveURL);

        // 🔗 Charger l'URL du flux vidéo dans WebView
        WebEngine webEngine = liveStreamView.getEngine();
        webEngine.load(liveURL);
    }


    @FXML
    public void retourAfficherEspaces(ActionEvent actionEvent) {
        loadScene(actionEvent, "/FrontAcceuil.fxml");
    }

    @FXML
    public void goToEvenements(ActionEvent actionEvent) {
        loadScene(actionEvent, "/Frontend/FrontEvents.fxml");
    }

    @FXML
    public void goToEspace(ActionEvent actionEvent) {
        loadScene(actionEvent, "/Frontend/FrontEspace.fxml");
    }

    /**
     * Charge une nouvelle scène de manière sécurisée.
     */
    private void loadScene(ActionEvent actionEvent, String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la page : " + fxmlPath);
            e.printStackTrace();
        }
    }
    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: #F39C12; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
    }

    public void handleLogout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Déconnexion");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir vous déconnecter ?");

        // Vérifier si l'utilisateur clique sur "OK"
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("🔒 Déconnexion confirmée...");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                Parent loginPage = loader.load();

                // Obtenir la scène actuelle et changer la page
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(loginPage));
                stage.show();

                System.out.println("✅ Déconnexion réussie !");
            } catch (IOException e) {
                System.out.println("❌ Erreur lors de la déconnexion : " + e.getMessage());
                e.printStackTrace();
                showAlert("Erreur de déconnexion", "Impossible d'ouvrir la page de connexion.");
            }
        }

    }
    // Méthode pour afficher une alerte en cas d'erreur
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void goToProduit(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontProduit.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
