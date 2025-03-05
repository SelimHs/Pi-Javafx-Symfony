package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;
import java.util.Map;

import javafx.scene.control.Button;
import tn.esprit.services.ServiceBillet;
import tn.esprit.services.ServiceEspace;

public class AcceuilController {
    @FXML
    private BarChart<String, Number> eventStatsChart;
    @FXML
    private PieChart pieChartEspaces;
    @FXML
    public void goToBilletList(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Billets.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void initialize() {
        chargerStatistiques();
        chargerStatistiquesEspaces();
        // Ajouter un écouteur sur la propriété scene de l'un des éléments graphiques
        eventStatsChart.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                // La scène est maintenant disponible, on peut accéder à la fenêtre
                Stage stage = (Stage) newScene.getWindow();

                // Ajouter des écouteurs de redimensionnement
                stage.widthProperty().addListener((obsWidth, oldVal, newVal) -> {
                    eventStatsChart.setPrefWidth(newVal.doubleValue() * 0.4); // 40% de la largeur
                    pieChartEspaces.setPrefWidth(newVal.doubleValue() * 0.4); // 40% de la largeur
                });

                stage.heightProperty().addListener((obsHeight, oldVal, newVal) -> {
                    eventStatsChart.setPrefHeight(newVal.doubleValue() * 0.6); // 60% de la hauteur
                    pieChartEspaces.setPrefHeight(newVal.doubleValue() * 0.6); // 60% de la hauteur
                });
            }
        });
    }


    @FXML
    public void goToEventList(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Events.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
    public void goToReservation(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AfficheReservation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void goToRemise(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AfficherRemise.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void goToProduitList(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PrincipaleProduits.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void goToFournisseurList(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Principalefournisseur.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void goToEspace(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AfficherEspace.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void goToHomePage(ActionEvent event) {
        try {
            // Charger la page d'accueil
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/homepage.fxml"));
            AnchorPane homePage = loader.load();

            // Obtenir la scène actuelle et changer la page
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(homePage));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur de navigation", "Impossible d'ouvrir la page d'accueil.");
        }
    }



    @FXML
    void handleLogout(ActionEvent event) {
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



    @FXML
    public void buttonHoverEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));  // Une ombre douce
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);

    }


    @FXML
    ServiceBillet sb = new ServiceBillet();

    private void chargerStatistiques() {
        // 📌 Récupérer les données des statistiques
        Map<String, Integer> stats = sb.getBilletStatsParEvenement();

        // Créer une série de données pour le graphique
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Billets par événement");

        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Ajouter la série au BarChart
        eventStatsChart.getData().clear();
        eventStatsChart.getData().add(series);
    }


    ServiceEspace se = new ServiceEspace();

    private void chargerStatistiquesEspaces() {
        // 📌 Récupérer les statistiques des espaces groupés par adresse
        Map<String, Integer> statsEspaces = se.getNombreEspacesParAdresse();

        // Effacer les anciennes données avant d'ajouter les nouvelles
        pieChartEspaces.getData().clear();

        // Calculer le total des espaces
        int total = statsEspaces.values().stream().mapToInt(Integer::intValue).sum();

        // Ajouter les données avec pourcentage
        for (Map.Entry<String, Integer> entry : statsEspaces.entrySet()) {
            String adresse = entry.getKey();
            int count = entry.getValue();
            double percentage = ((double) count / total) * 100;

            // 🎯 Modifier l'affichage pour inclure le pourcentage
            PieChart.Data slice = new PieChart.Data(adresse + " (" + String.format("%.1f", percentage) + "%)", count);
            pieChartEspaces.getData().add(slice);
        }
    }





}
