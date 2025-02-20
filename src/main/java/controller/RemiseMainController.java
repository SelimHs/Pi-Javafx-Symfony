package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import tn.esprit.models.Remise;
import tn.esprit.services.ServiceRemise;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.List;


import java.awt.*;

public class RemiseMainController {
    @javafx.fxml.FXML
    private TextField searchField;
    @javafx.fxml.FXML
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

            card.getChildren().addAll(title, pourcentageLabel, expirationLabel, detailsButton, supprimerButton);
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

}
