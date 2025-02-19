package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    @FXML
    private TextField searchField;
    @javafx.fxml.FXML
    private Button modifierBouton;
    @javafx.fxml.FXML
    private Button deleteBouton;

    @FXML
    public void initialize() {
        // Affiche les billets au démarrage
        displayBillets(null);

        // Ajoute un écouteur d'événements pour filtrer les billets à chaque modification de texte
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterBillets(newValue));
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

            Button editButton = new Button("Modifier");
            editButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
            editButton.setOnAction(e -> openEditPopup(billet,editButton));

            card.getChildren().addAll(title, name, price, eventName, detailsButton,editButton, deleteButton);
            billetCardContainer.getChildren().add(card);

        }
    }

    @FXML
    public void openEditPopup(Billet billet, Button sourceButton){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierBillet.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the selected event
            ModifierBilletController modifierBilletController = loader.getController();
            modifierBilletController.initDataBillet(billet);

            // Get the current stage and replace its scene
            Stage stage = (Stage) sourceButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void filterBillets(String searchText) {
        billetCardContainer.getChildren().clear();  // Réinitialiser l'affichage des billets

        List<Billet> filteredBillets = sb.getAll().stream()
                .filter(billet ->
                        billet.getProprietaire().toLowerCase().contains(searchText.toLowerCase()) ||
                                billet.getDateAchat().toString().toLowerCase().contains(searchText.toLowerCase()) ||
                                String.valueOf(billet.getPrix()).contains(searchText) ||
                                billet.getType().toString().toLowerCase().contains(searchText.toLowerCase()) ||
                                billet.getEvent().getNomEvent().toLowerCase().contains(searchText.toLowerCase())
                )
                .toList();

        // Affiche les billets filtrés
        displayFilteredBillets(filteredBillets);
    }
    private void displayFilteredBillets(List<Billet> billets) {
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
            Label eventName = new Label("🎉 " + billet.getEvent().getNomEvent());

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setOnAction(b -> showBilletDetails(billet));

            // Bouton supprimer
            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> deleteAndRefreshBillet(billet));

            Button editButton = new Button("Modifier");
            editButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
            editButton.setOnAction(e -> openEditPopup(billet, editButton));

            card.getChildren().addAll(title, name, price, eventName, detailsButton, editButton, deleteButton);
            billetCardContainer.getChildren().add(card);
        }
    }
}
