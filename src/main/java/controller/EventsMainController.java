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
    public void initialize(){
        displayEvents();
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



    /*@FXML
    public void updateEvent(javafx.event.ActionEvent actionEvent) {
        Event eventSelectionne = (Event) eventListView.getSelectionModel().getSelectedItem();
        if (eventSelectionne != null) {
            // Load the FXML for the event modification form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEvent.fxml"));
            Parent root = null;
            try {
                root = loader.load();
                ModifierEventController modifierEventController = loader.getController();

                // Pass the selected event to the controller to initialize the form fields
                modifierEventController.initData(eventSelectionne);

                // Show the modification window
                Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            // Show an alert if no event is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un événement à modifier.");
            alert.showAndWait();
        }
    }*/
}
