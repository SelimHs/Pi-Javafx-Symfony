package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FacceuilController {
    @FXML
    private Button btnAccueil, btnEvenements,btnEspace;

    @FXML
    public void initialize() {
        applyHoverEffect(btnAccueil);
        applyHoverEffect(btnEvenements);
        applyHoverEffect(btnEspace);
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: #F39C12; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
    }

    public void goToAcceuil(ActionEvent actionEvent) {
    }

    @FXML
    public void goToEvents(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEvents.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void goToEspaces(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEspace.fxml"));
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
