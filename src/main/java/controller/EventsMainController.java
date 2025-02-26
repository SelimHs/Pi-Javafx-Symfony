package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;


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

    //here lies my init
    @FXML
    private void initialize() {
        displayEvents();

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
        eventCardContainer.getChildren().clear();
        List<Event> events = se.getAll();

        for (Event event : events) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 8;");

            Label title = new Label(event.getNomEvent());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label date = new Label("📅 " + event.getDate().toString());
            Label price = new Label("💰 " + event.getPrix() + " DT");

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setOnAction(e -> showEventDetails(event));

            // 📌 Conteneur horizontal pour les boutons (réduction maximale de l’espace)
            HBox buttonContainer = new HBox(2); // 🔥 Espacement encore plus petit
            buttonContainer.setStyle("-fx-alignment: left; -fx-min-width: 100%;");

            // 📝 Bouton Modifier avec icône crayon
            Button editButton = new Button();
            editButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-padding: 2px;");
            editButton.setOnAction(e -> openEditPopup(event, editButton));

            ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/edit-icon.png")));
            editIcon.setFitWidth(18); // 🔥 Icône légèrement réduite pour plus d'harmonie
            editIcon.setFitHeight(18);
            editButton.setGraphic(editIcon);

            // 🗑️ Bouton Supprimer avec icône poubelle
            Button deleteButton = new Button();
            deleteButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-padding: 2px;");
            deleteButton.setOnAction(e -> deleteAndRefreshEvent(event));

            ImageView trashIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/trash-icon.png")));
            trashIcon.setFitWidth(18); // 🔥 Icône légèrement réduite pour plus d'harmonie
            trashIcon.setFitHeight(18);
            deleteButton.setGraphic(trashIcon);

            // Ajouter les boutons dans la HBox (avec moins d’espace)
            buttonContainer.getChildren().addAll(editButton, deleteButton);

            // Ajouter les éléments à la carte
            card.getChildren().addAll(title, date, price, detailsButton, buttonContainer);
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

        // pass selected event to controller
        ModifierEventController modifierEventController = loader.getController();
        modifierEventController.initDataEvent(event);

        // replace scene of current stage
        Stage stage = (Stage) sourceButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterEvents(String searchText) {
        eventCardContainer.getChildren().clear();


        List<Event> filteredEvents = se.getAll().stream()
                .filter(event ->
                        event.getNomEvent().toLowerCase().contains(searchText.toLowerCase()) ||
                                event.getDate().toString().toLowerCase().contains(searchText.toLowerCase()) ||
                                String.valueOf(event.getPrix()).contains(searchText) ||
                                event.getNomEspace().toLowerCase().contains(searchText.toLowerCase()) ||
                                event.getDetails().toLowerCase().contains(searchText.toLowerCase())
                )
                .toList();


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

            // 📝 Bouton Modifier avec une icône crayon
            Button editButton = new Button();
            editButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-padding: 5px;");
            editButton.setOnAction(e -> openEditPopup(event, editButton));

// Ajout de l'icône crayon
            ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/edit-icon.png")));
            editIcon.setFitWidth(24);
            editIcon.setFitHeight(24);
            editButton.setGraphic(editIcon);

// 🗑️ Bouton Supprimer avec une icône poubelle
            Button deleteButton = new Button();
            deleteButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-padding: 5px;");
            deleteButton.setOnAction(e -> deleteAndRefreshEvent(event));

// Ajout de l'icône poubelle
            ImageView trashIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/trash-icon.png")));
            trashIcon.setFitWidth(24);
            trashIcon.setFitHeight(24);
            deleteButton.setGraphic(trashIcon);


            card.getChildren().addAll(title, date, price, detailsButton, editButton, deleteButton);
            eventCardContainer.getChildren().add(card);
        }
    }

    @FXML
    public void buttonHoverEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));  // Une ombre douce
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }
}
