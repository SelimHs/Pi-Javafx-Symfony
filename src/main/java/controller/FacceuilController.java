package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FacceuilController {
    @FXML
    private Button btnAccueil, btnEvenements, btnBillets, btnReservations,btnEspace;

    @FXML
    public void initialize() {
        applyHoverEffect(btnAccueil);
        applyHoverEffect(btnEvenements);
        applyHoverEffect(btnBillets);
        applyHoverEffect(btnReservations);
        applyHoverEffect(btnEspace);
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: #F39C12; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
    }

}
