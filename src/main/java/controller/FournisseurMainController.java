package controller;

import javafx.fxml.FXML;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;

import java.awt.*;
import java.awt.event.ActionEvent;

public class FournisseurMainController {
    @javafx.fxml.FXML
    private TextField searchField;
    @javafx.fxml.FXML
    private Button modifierBouton;
    @javafx.fxml.FXML
    private FlowPane fournisseurCardContainer;
    @javafx.fxml.FXML
    private Button deleteBouton;


    private ServiceFournisseur fournisseurService = new ServiceFournisseur();


    @FXML
    public void displayFournisseurs() {
        fournisseurCardContainer.getChildren().clear(); // Nettoyer avant de recharger

        List<fournisseur> fournisseurs = fournisseurService.getAll(); // Récupérer les fournisseurs

        for (fournisseur fournisseur : fournisseurs) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

            Label title = new Label(fournisseur.getNomFournisseur());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label description = new Label("📝 " + fournisseur.getDescription());
            Label type = new Label("🏷 " + fournisseur.getType());

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setOnAction(e -> showFournisseurDetails(fournisseur));

            card.getChildren().addAll(title, description, type, detailsButton);
            fournisseurCardContainer.getChildren().add(card);
        }
    }

    private void showFournisseurDetails(fournisseur fournisseur) {
        // Implémentation pour afficher les détails d'un fournisseur
    }    @javafx.fxml.FXML
    public void updateFournisseur(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void searchFournisseur(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void deleteFournisseur(ActionEvent actionEvent) {
    }

    @FXML


    public void goToGestionFournisseur(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionFournisseur.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Ajouter un Fournisseur");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("⚠️ Erreur lors du chargement de la page GestionFournisseur.fxml !");
        }
    }
}
