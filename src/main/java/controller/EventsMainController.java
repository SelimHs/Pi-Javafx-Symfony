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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private ComboBox<String> filterCriteriaComboBox; // Pour le filtrage
    @FXML
    private ComboBox<String> sortOrderComboBox; // Pour le tri

    // Initialisation
    @FXML
    private void initialize() {
        // Initialisation des options de filtrage
        filterCriteriaComboBox.setItems(FXCollections.observableArrayList(
                "Nom", "Date", "Prix", "Lieu", "Détails", "Visiteurs"
        ));
        filterCriteriaComboBox.setValue("Nom"); // Valeur par défaut

        // Initialisation des options de tri
        sortOrderComboBox.setItems(FXCollections.observableArrayList(
                "Croissant", "Décroissant"
        ));
        sortOrderComboBox.setValue("Croissant"); // Valeur par défaut

        // Ajouter des écouteurs pour le filtrage et le tri
        filterCriteriaComboBox.setOnAction(event -> applyFilterAndSort());
        sortOrderComboBox.setOnAction(event -> applyFilterAndSort());

        // Ajouter un écouteur pour la recherche dynamique
        searchField.textProperty().addListener((observable, oldValue, newValue) -> applyFilterAndSort());

        // Charger et afficher les événements
        displayEvents();
    }
    private void applyFilterAndSort() {
        eventCardContainer.getChildren().clear();

        // Récupérer tous les événements
        List<Event> events = se.getAll();

        // 🔍 Filtrage
        String searchText = searchField.getText().toLowerCase();
        String filterCriteria = filterCriteriaComboBox.getValue();

        List<Event> filteredEvents = events.stream()
                .filter(event -> {
                    switch (filterCriteria) {
                        case "Nom":
                            return event.getNomEvent().toLowerCase().contains(searchText);
                        case "Date":
                            return event.getDate().toString().toLowerCase().contains(searchText);
                        case "Prix":
                            return String.valueOf(event.getPrix()).contains(searchText);
                        case "Lieu":
                            return event.getNomEspace().toLowerCase().contains(searchText);
                        case "Détails":
                            return event.getDetails().toLowerCase().contains(searchText);
                        case "Visiteurs":
                            return String.valueOf(event.getNbrVisiteurs()).contains(searchText);
                        default:
                            return true; // Aucun filtre appliqué
                    }
                })
                .collect(Collectors.toList());

        // 🔄 Tri
        String sortOrder = sortOrderComboBox.getValue();
        Comparator<Event> comparator = switch (filterCriteria) {
            case "Nom" -> Comparator.comparing(Event::getNomEvent, String.CASE_INSENSITIVE_ORDER);
            case "Date" -> Comparator.comparing(Event::getDate);
            case "Prix" -> Comparator.comparingDouble(Event::getPrix);
            case "Lieu" -> Comparator.comparing(Event::getNomEspace, String.CASE_INSENSITIVE_ORDER);
            case "Détails" -> Comparator.comparing(Event::getDetails, String.CASE_INSENSITIVE_ORDER);
            case "Visiteurs" -> Comparator.comparingInt(Event::getNbrVisiteurs);
            default -> Comparator.comparing(Event::getNomEvent, String.CASE_INSENSITIVE_ORDER); // Par défaut, tri par nom
        };

        // Inverser l'ordre si "Décroissant" est sélectionné
        if ("Décroissant".equals(sortOrder)) {
            comparator = comparator.reversed();
        }

        // Appliquer le tri
        filteredEvents.sort(comparator);

        // Afficher les événements filtrés et triés
        displayFilteredEvents(filteredEvents);
    }

    // Navigation
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

    @FXML
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

    // Affichage des détails de l'événement
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

    // Affichage des événements
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

            // Boutons Modifier et Supprimer
            HBox buttonContainer = new HBox(2);
            buttonContainer.setStyle("-fx-alignment: left; -fx-min-width: 100%;");

            Button editButton = new Button();
            editButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-padding: 2px;");
            editButton.setOnAction(e -> openEditPopup(event, editButton));

            ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/edit-icon.png")));
            editIcon.setFitWidth(18);
            editIcon.setFitHeight(18);
            editButton.setGraphic(editIcon);

            Button deleteButton = new Button();
            deleteButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-padding: 2px;");
            deleteButton.setOnAction(e -> deleteAndRefreshEvent(event));

            ImageView trashIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/trash-icon.png")));
            trashIcon.setFitWidth(18);
            trashIcon.setFitHeight(18);
            deleteButton.setGraphic(trashIcon);

            buttonContainer.getChildren().addAll(editButton, deleteButton);
            card.getChildren().addAll(title, date, price, detailsButton, buttonContainer);
            eventCardContainer.getChildren().add(card);
        }
    }

    // Supprimer un événement
    @FXML
    public void deleteAndRefreshEvent(Event event) {
        se.delete(event);
        eventCardContainer.getChildren().clear();
        displayEvents();
    }

    // Ouvrir la fenêtre de modification
    @FXML
    public void openEditPopup(Event event, Button sourceButton) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEvent.fxml"));
            Parent root = loader.load();

            ModifierEventController modifierEventController = loader.getController();
            modifierEventController.initDataEvent(event);

            Stage stage = (Stage) sourceButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Filtrer les événements
    private void filterEvents(String searchText) {
        eventCardContainer.getChildren().clear();

        List<Event> events = se.getAll();

        List<Event> filteredEvents = events.stream()
                .filter(event -> {
                    return event.getNomEvent().toLowerCase().contains(searchText.toLowerCase()) ||
                            event.getDate().toString().toLowerCase().contains(searchText.toLowerCase()) ||
                            String.valueOf(event.getPrix()).contains(searchText) ||
                            event.getNomEspace().toLowerCase().contains(searchText.toLowerCase()) ||
                            event.getDetails().toLowerCase().contains(searchText.toLowerCase()) ||
                            String.valueOf(event.getNbrVisiteurs()).contains(searchText);
                })
                .toList();

        displayFilteredEvents(filteredEvents);
    }

    // Afficher les événements filtrés
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

            Button editButton = new Button();
            editButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-padding: 5px;");
            editButton.setOnAction(e -> openEditPopup(event, editButton));

            ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/edit-icon.png")));
            editIcon.setFitWidth(24);
            editIcon.setFitHeight(24);
            editButton.setGraphic(editIcon);

            Button deleteButton = new Button();
            deleteButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-padding: 5px;");
            deleteButton.setOnAction(e -> deleteAndRefreshEvent(event));

            ImageView trashIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/trash-icon.png")));
            trashIcon.setFitWidth(24);
            trashIcon.setFitHeight(24);
            deleteButton.setGraphic(trashIcon);

            card.getChildren().addAll(title, date, price, detailsButton, editButton, deleteButton);
            eventCardContainer.getChildren().add(card);
        }
    }

    // Effets de survol pour les boutons
    @FXML
    public void buttonHoverEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }
}