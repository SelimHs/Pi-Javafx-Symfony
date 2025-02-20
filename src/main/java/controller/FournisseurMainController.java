package controller;

import javafx.fxml.FXML;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;
import javafx.event.ActionEvent;

public class FournisseurMainController {
    @FXML
    private FlowPane fournisseurCardContainer;

    private final ServiceFournisseur fournisseurService = new ServiceFournisseur();

    @FXML
    public void displayFournisseurs() {
        fournisseurCardContainer.getChildren().clear();
        List<fournisseur> fournisseurs = fournisseurService.getAll();

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

            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> deleteFournisseur(fournisseur));

            Button modifyButton = new Button("Modifier");
            modifyButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
            modifyButton.setOnAction(e -> goToModifierFournisseur(fournisseur, e));

            card.getChildren().addAll(title, description, type, detailsButton, modifyButton, deleteButton);
            fournisseurCardContainer.getChildren().add(card);
        }
    }

    private void showFournisseurDetails(fournisseur fournisseur) {
        // Implémentation pour afficher les détails d'un fournisseur
    }

    @FXML
    public void goToModifierFournisseur(fournisseur fournisseur, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierFournisseur.fxml"));
            Parent root = loader.load();

            ModifierFournisseur controller = loader.getController();
            controller.initData(fournisseur);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier un Fournisseur");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("⚠️ Erreur lors du chargement de ModifierFournisseur.fxml !");
        }
    }

    @FXML
    public void deleteFournisseur(fournisseur fournisseur) {
        fournisseurService.delete(fournisseur);
        displayFournisseurs(); // Rafraîchir l'affichage après suppression
    }

    @FXML
    public void goToGestionFournisseur(ActionEvent actionEvent) {
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
