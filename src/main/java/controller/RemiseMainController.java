package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Remise;
import tn.esprit.services.ServiceRemise;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RemiseMainController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private FlowPane remiseCardContainer;

    private ServiceRemise serviceRemise = new ServiceRemise();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayRemises(); // Afficher les remises dès que la page est chargée
    }

    // Afficher la liste des remises
    @FXML
    public void displayRemises() {
        remiseCardContainer.getChildren().clear(); // Nettoyer avant de recharger

        List<Remise> remises = serviceRemise.getAll(); // Récupérer toutes les remises

        for (Remise remise : remises) {
            VBox card = createRemiseCard(remise);
            remiseCardContainer.getChildren().add(card);
        }
    }

    // Créer une carte de remise
    private VBox createRemiseCard(Remise remise) {
        VBox card = new VBox();
        card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

        Label title = new Label("Code Promo: " + remise.getCodePromo());
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label pourcentageLabel = new Label("💲 Remise: " + remise.getPourcentageRemise() + "%");
        Label expirationLabel = new Label("📅 Expiration: " + remise.getDateExpiration());

        Button detailsButton = new Button("Voir Détails");
        detailsButton.setOnAction(e -> showRemiseDetails(remise));

        Button supprimerButton = new Button("Supprimer");
        supprimerButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        supprimerButton.setOnAction(e -> {
            supprimerRemise(remise); // Supprime la remise
            displayRemises(); // Rafraîchit la liste
        });

        Button modifierButton = new Button("Modifier");
        modifierButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
        modifierButton.setOnAction(e -> {
            modifierRemise(remise, modifierButton); // Modifie la remise
            displayRemises(); // Rafraîchit la liste
        });

        card.getChildren().addAll(title, pourcentageLabel, expirationLabel, detailsButton, modifierButton, supprimerButton);
        return card;
    }

    // Supprimer une remise
    private void supprimerRemise(Remise remise) {
        serviceRemise.delete(remise); // Suppression de la remise dans le service
    }

    // Afficher les détails d'une remise
    @FXML
    public void showRemiseDetails(Remise remise) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails de la Remise");
        alert.setHeaderText("Remise de " + remise.getPourcentageRemise() + "%");
        alert.setContentText("📅 Date d'expiration : " + remise.getDateExpiration() +
                "\n💰 Description : " + remise.getDescription());

        alert.showAndWait();
    }

    // Modifier une remise
    private void modifierRemise(Remise remise, Button sourceButton) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierRemise.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the selected remise
            ModifierRemise modifierRemise = loader.getController();
            modifierRemise.initData(remise);

            // Get the current stage and replace its scene
            Stage stage = (Stage) sourceButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Rechercher des remises dynamiquement
    @FXML
    public void searchRemises() {
        String searchText = searchField.getText().toLowerCase();
        remiseCardContainer.getChildren().clear(); // Nettoyer avant de recharger

        List<Remise> remises = serviceRemise.getAll(); // Récupérer toutes les remises

        // Filtrer les remises en fonction du texte de recherche (code promo ou pourcentage)
        List<Remise> filteredRemises = remises.stream()
                .filter(remise ->
                        remise.getCodePromo().toLowerCase().contains(searchText) || // Recherche par code promo
                                String.valueOf(remise.getPourcentageRemise()).contains(searchText) // Recherche par pourcentage
                )
                .collect(Collectors.toList());

        // Afficher les remises filtrées
        for (Remise remise : filteredRemises) {
            VBox card = createRemiseCard(remise);
            remiseCardContainer.getChildren().add(card);
        }
    }

    // Navigation vers l'ajout de remise
    public void goToAjout(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GestionRemise.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Navigation vers l'accueil
    public void goToAcceuil(ActionEvent actionEvent) {
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

    // Effet de survol pour les boutons
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

    // Effet de sortie pour les boutons
    @FXML
    public void buttonExitEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }

    public void goToAcceuil(javafx.event.ActionEvent actionEvent) {
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

    public void goToAjout(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GestionRemise.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}