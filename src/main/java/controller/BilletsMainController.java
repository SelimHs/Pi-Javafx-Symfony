package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Billet;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceBillet;
import tn.esprit.services.ServiceEvent;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class BilletsMainController {

    ServiceBillet sb = new ServiceBillet();

    @javafx.fxml.FXML
    private ListView billetListView;
    ObservableList<Billet> billetNames = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    private Button modifierBouton;
    @javafx.fxml.FXML
    private Button deleteBouton;

    @FXML
    public void initialize() {
        displayBillets(null);
    }

    //Here lies my navigation
    @FXML
    public void goToAjoutBillet(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ajoutBillet.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @javafx.fxml.FXML
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

    //Here lies my extras
    private void showBilletDetails(Billet billet) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails du Billet");
        alert.setHeaderText("Billet de " + billet.getProprietaire());
        alert.setContentText("📅 Date d'achat : " + billet.getDateAchat() +
                "\n💰 Prix : " + billet.getPrix() + " DT" +
                "\n🎟 Type : " + billet.getType() +
                "\n📍 Événement : " + billet.getEvent());

        alert.showAndWait();
    }


    @FXML
    public void deleteAndRefreshBillet(Billet billet) {
        ServiceBillet se = new ServiceBillet();
        se.delete(billet);
        billetCardContainer.getChildren().clear();
        displayBillets(null);
    }


    //here lies my core
    @FXML
    private FlowPane billetCardContainer;
    @javafx.fxml.FXML


    public void displayBillets(javafx.event.ActionEvent actionEvent) {
        billetCardContainer.getChildren().clear();

        List<Billet> billets = sb.getAll();
        for (Billet billet : billets) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

            Label title = new Label("Ticket de " + billet.getProprietaire());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label name = new Label(billet.getProprietaire());
            name.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
            Label price = new Label("💰 " + billet.getPrix() + " DT");
            Label eventName = new Label("🎉 " + billet.getEvent());

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setOnAction(b -> showBilletDetails(billet));

            // Delete button
            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> deleteAndRefreshBillet(billet));

            card.getChildren().addAll(title, name, price, eventName, detailsButton, deleteButton);
            billetCardContainer.getChildren().add(card);

        }
    }



    @javafx.fxml.FXML
    public void deleteBillet(ActionEvent actionEvent) {
    }


    @javafx.fxml.FXML
    public void updateBillet(ActionEvent actionEvent) {
    }
}
