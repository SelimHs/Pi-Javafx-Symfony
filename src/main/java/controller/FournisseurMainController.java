package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;
import javafx.event.ActionEvent;

public class FournisseurMainController implements Initializable {
    @FXML
    private FlowPane fournisseurCardContainer;

    private final ServiceFournisseur fournisseurService = new ServiceFournisseur();

    /**
     * Méthode appelée automatiquement lors du chargement du contrôleur
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayFournisseurs();  // Chargement automatique des fournisseurs
    }

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails du Fournisseur");
        alert.setHeaderText(fournisseur.getNomFournisseur());
        alert.setContentText("🆔 ID : " + fournisseur.getIdFournisseur() +
                "\n📝 Description : " + fournisseur.getDescription() +
                "\n🏷 Type : " + fournisseur.getType());

        alert.showAndWait();
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
}
