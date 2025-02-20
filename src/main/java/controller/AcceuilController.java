package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AcceuilController {
    @javafx.fxml.FXML
    public void goToProduitList(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PrincipaleProduits.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void goToFournisseurList(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Principalefournisseur.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
