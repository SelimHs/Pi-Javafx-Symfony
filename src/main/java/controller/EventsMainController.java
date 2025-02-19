package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class EventsMainController {
    ServiceEvent se = new ServiceEvent();
    ObservableList<Event> eventNames = FXCollections.observableArrayList();
    @FXML
    private TextField searchField;
    @FXML
    private Button modifierBouton;
    @FXML
    private Button deleteBouton;

    @FXML
    private void initialize() {
        displayEvents(); // Affiche les événements au départ
        // Ajoute un écouteur d'événements pour filtrer les événements à chaque modification de texte dans la barre de recherche
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterEvents(newValue));
    }



    //here lies my navigation
    @FXML
    public void goToEventList(javafx.event.ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Events.fxml"));
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

    @FXML
    public void goToAjoutEvent(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ajoutEvent.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    //here lies my extras
    private void showEventDetails(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails de l'Événement");
        alert.setHeaderText(event.getNomEvent());
        alert.setContentText("📅 Date : " + event.getDate() +
                "\n💰 Prix : " + event.getPrix() + " DT" +
                "\n👥 Visiteurs : " + event.getNbrVisiteurs() +
                "\n📍 Lieu : " + event.getNomEspace() +
                "\nℹ️ Détails : " + event.getDetails());

        alert.showAndWait();
    }
    //here lies my core
    @FXML
    private FlowPane eventCardContainer;

    public void displayEvents() {
        eventCardContainer.getChildren().clear(); // Nettoyer avant de recharger

        List<Event> events = se.getAll(); // Récupérer les événements

        for (Event event : events) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

            Label title = new Label(event.getNomEvent());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label date = new Label("📅 " + event.getDate().toString());
            Label price = new Label("💰 " + event.getPrix() + " DT");

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setOnAction(e -> showEventDetails(event));

            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> deleteAndRefreshEvent(event)); // Adding delete action

            Button editButton = new Button("Modifier");
            editButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
            editButton.setOnAction(e -> openEditPopup(event,editButton));

            card.getChildren().addAll(title, date, price, detailsButton,editButton, deleteButton);
            eventCardContainer.getChildren().add(card);
        }
    }







    @FXML
    public void deleteAndRefreshEvent(Event event) {
        ServiceEvent se = new ServiceEvent();
        se.delete(event);
        eventCardContainer.getChildren().clear();
        displayEvents();
    }

    @FXML
    public void openEditPopup(Event event, Button sourceButton){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEvent.fxml"));
        Parent root = loader.load();

        // Get the controller and pass the selected event
        ModifierEventController modifierEventController = loader.getController();
        modifierEventController.initDataEvent(event);

        // Get the current stage and replace its scene
        Stage stage = (Stage) sourceButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode pour afficher une alerte
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void filterEvents(String searchText) {
        eventCardContainer.getChildren().clear(); // Réinitialiser l'affichage des événements

        // Filtrer les événements en fonction du texte dans la barre de recherche
        List<Event> filteredEvents = se.getAll().stream()
                .filter(event ->
                        event.getNomEvent().toLowerCase().contains(searchText.toLowerCase()) ||
                                event.getDate().toString().toLowerCase().contains(searchText.toLowerCase()) ||
                                String.valueOf(event.getPrix()).contains(searchText) ||
                                event.getNomEspace().toLowerCase().contains(searchText.toLowerCase()) ||
                                event.getDetails().toLowerCase().contains(searchText.toLowerCase())
                )
                .toList();

        // Afficher les événements filtrés
        displayFilteredEvents(filteredEvents);
    }

    private void displayFilteredEvents(List<Event> events) {
        for (Event event : events) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

            Label title = new Label(event.getNomEvent());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label date = new Label("📅 " + event.getDate().toString());
            Label price = new Label("💰 " + event.getPrix() + " DT");

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setOnAction(e -> showEventDetails(event));

            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> deleteAndRefreshEvent(event));

            Button editButton = new Button("Modifier");
            editButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
            editButton.setOnAction(e -> openEditPopup(event, editButton));

            card.getChildren().addAll(title, date, price, detailsButton, editButton, deleteButton);
            eventCardContainer.getChildren().add(card);
        }
    }
}
