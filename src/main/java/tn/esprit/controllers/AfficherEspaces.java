package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.services.ServiceEspace;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AfficherEspaces {

    @FXML
    private FlowPane espaceCardContainer;
    @FXML
    private TextField searchField;

    private final ServiceEspace serviceEspace = new ServiceEspace();

    @FXML
    public void initialize() {
        afficherEspaces();  // Charger les espaces au démarrage

        // 🔍 Recherche dynamique en temps réel
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchEspace(newValue.trim().toLowerCase());
        });
    }

    @FXML
    private void afficherEspaces() {
        espaceCardContainer.getChildren().clear();

        List<Optional<Espace>> espaces = serviceEspace.getAll();
        for (Optional<Espace> espace : espaces) {
            VBox card = creerCarteEspace(espace.orElse(null));
            espaceCardContainer.getChildren().add(card);
        }
    }

    private VBox creerCarteEspace(Espace espace) {
        VBox card = new VBox();
        card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 2, 2); -fx-spacing: 8px;");

        Button btnModifier = new Button("Modifier");
        btnModifier.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
        btnModifier.setOnAction(e -> modifierEspace(espace));

        Button btnSupprimer = new Button("Supprimer");
        btnSupprimer.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        btnSupprimer.setOnAction(e -> supprimerEspace(espace));

        // 🔎 Nouveau bouton pour afficher les détails
        Button btnDetails = new Button("Détails");
        btnDetails.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        btnDetails.setOnAction(e -> afficherDetailsEspace(espace, e));

        card.getChildren().addAll(
                new Label("🏠 " + espace.getNomEspace()),
                new Label("📍 " + espace.getAdresse()),
                new Label("👥 Capacité: " + espace.getCapacite()),
                new Label("📅 Disponibilité: " + espace.getDisponibilite()),
                new Label("💰 Prix: " + espace.getPrix() + " DT"),
                new Label("🏢 Type: " + espace.getTypeEspace()),
                btnModifier, btnSupprimer, btnDetails // Ajouter le bouton "Détails"
        );

        return card;
    }

    private void supprimerEspace(Espace espace) {
        serviceEspace.delete(espace.getIdEspace());
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "L'espace a été supprimé avec succès !");
        alert.show();
        afficherEspaces();
    }

    private void modifierEspace(Espace espace) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEspace.fxml"));
            Parent root = loader.load();
            ModifierEspace controller = loader.getController();
            controller.initData(espace);

            Stage stage = (Stage) espaceCardContainer.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherDetailsEspace(Espace espace, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailEspace.fxml"));
            Parent root = loader.load();

            DetailEspace controller = loader.getController();
            controller.initData(espace); // Passe l'espace sélectionné

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    public void ajouterEspaceView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GestionEspace.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void retourAjouterEspace(ActionEvent event) throws IOException {
        ajouterEspaceView(event);
    }

    private void searchEspace(String searchText) {
        espaceCardContainer.getChildren().clear();  // Vider les cartes affichées

        List<Optional<Espace>> espaces = serviceEspace.getAll();

        for (Optional<Espace> espace : espaces) {
            if (espace.get().getNomEspace().toLowerCase().contains(searchText)) {
                VBox card = creerCarteEspace(espace.orElse(null));
                espaceCardContainer.getChildren().add(card);
            }
        }

        // 🛑 Message si aucun espace trouvé
        if (espaceCardContainer.getChildren().isEmpty()) {
            Label noResults = new Label("❌ Aucun espace trouvé.");
            noResults.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
            espaceCardContainer.getChildren().add(noResults);
        }
    }

    @FXML
    public void buttonHoverEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 10px 18px; -fx-border-width: 2px; -fx-border-color: white;");
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
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0; -fx-padding: 12px 20px; -fx-border-width: 0px;");
        btn.setEffect(null);

    }
}
