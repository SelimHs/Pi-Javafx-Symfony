package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Remise;
import tn.esprit.services.ServiceRemise;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.EventObject;
import java.util.List;

public class RemiseMainController {

    @FXML
    private TextField searchField;
    @FXML
    private FlowPane remiseCardContainer;
    @FXML
    private VBox remiseVBox;

    @FXML
    public void displayRemises() {
        remiseCardContainer.getChildren().clear(); // Nettoyer avant de recharger

        ServiceRemise serviceRemise = new ServiceRemise();
        List<Remise> remises = serviceRemise.getAll(); // Récupérer les remises

        for (Remise remise : remises) {
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
                supprimerRemise(remise); // Méthode à définir pour supprimer la remise
                displayRemises(); // Recharger la liste après suppression
            });

            Button modifierButton = new Button("Modifier");
            modifierButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
            modifierButton.setOnAction(e -> {
                modifierRemise(remise,modifierButton); // Méthode à définir pour modifier la remise
                displayRemises(); // Recharger la liste après modification
            });

            card.getChildren().addAll(title, pourcentageLabel, expirationLabel, detailsButton, modifierButton, supprimerButton);
            remiseCardContainer.getChildren().add(card);
        }
    }

    private void supprimerRemise(Remise remise) {
        ServiceRemise sr = new ServiceRemise();
        sr.delete(remise);
        remiseCardContainer.getChildren().clear();
        displayRemises();
    }

    @FXML
    public void showRemiseDetails(Remise remise) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails de la Remise");
        alert.setHeaderText("Remise de " + remise.getPourcentageRemise());
        alert.setContentText("📅 Date d'expiration : " + remise.getDateExpiration() +
                "\n💰 Description : " + remise.getDescription());

        alert.showAndWait();
    }

    private void modifierRemise(Remise remise,Button sourceButton) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierRemise.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the selected event
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
}
