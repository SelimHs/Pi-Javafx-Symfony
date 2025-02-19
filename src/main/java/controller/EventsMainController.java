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

    @FXML
    private FlowPane eventCardContainer;

    @FXML
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

            card.getChildren().addAll(title, date, price, detailsButton);
            eventCardContainer.getChildren().add(card);
        }
    }






    @FXML
    public void delete(javafx.event.ActionEvent actionEvent) {
        // Obtenir le bouton qui a été cliqué
        Node source = (Node) actionEvent.getSource();

        // Remonter dans la hiérarchie pour trouver la carte
        Parent parent = source.getParent();
        while (parent != null && !(parent instanceof VBox)) { // Change VBox selon ton conteneur réel
            parent = parent.getParent();
        }

        if (parent instanceof VBox) {
            VBox card = (VBox) parent; // Ta carte contenant l'événement

            // Récupérer l'événement stocké dans la carte (si setUserData(event) a été utilisé)
            Event eventSelectionne = (Event) card.getUserData();

            if (eventSelectionne != null) {
                // Supprimer de la base de données
                ServiceEvent se = new ServiceEvent();
                se.delete(eventSelectionne);

                // Supprimer la carte de l'affichage
                ((Pane) card.getParent()).getChildren().remove(card);
            } else {
                afficherAlerte("Aucune sélection", "Veuillez sélectionner un évènement à supprimer.");
            }
        } else {
            afficherAlerte("Erreur", "Impossible de trouver la carte contenant l'évènement.");
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
