package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Reservation;
import tn.esprit.services.ExcelDesignService;
import tn.esprit.services.ExcelExportService;
import tn.esprit.services.ServiceReservation;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static tn.esprit.services.ExcelExportService.openExcelFile;

public class ReservationMainController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private FlowPane reservationCardContainer;

    private ServiceReservation reservationService = new ServiceReservation();
    @FXML
    private ComboBox<String> filterCriteriaComboBox;
    @FXML
    private ComboBox<String> sortOrderComboBox;
    private boolean isAscending = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReservations();

        // Initialisation des filtres
        filterCriteriaComboBox.setItems(FXCollections.observableArrayList("Date", "Statut"));
        filterCriteriaComboBox.setValue("Date");

        sortOrderComboBox.setItems(FXCollections.observableArrayList("Croissant", "Décroissant"));
        sortOrderComboBox.setValue("Croissant");

        // Ajout des écouteurs
        filterCriteriaComboBox.setOnAction(event -> updateReservations());
        sortOrderComboBox.setOnAction(event -> updateReservations());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> updateReservations());

        displayReservations(); // Afficher les réservations dès que la page est chargée
    }
    private void loadReservations() {
        updateReservations();
    }
    private void updateReservations() {
        reservationCardContainer.getChildren().clear();
        List<Reservation> reservations = reservationService.getAll();

        // 🔍 Filtrage
        String searchText = searchField.getText().toLowerCase();
        String filterCriteria = filterCriteriaComboBox.getValue();
        List<Reservation> filteredReservations = reservations.stream()
                .filter(reservation -> {
                    if ("Date".equals(filterCriteria)) {
                        return reservation.getDateReservation().toLowerCase().contains(searchText);
                    } else if ("Statut".equals(filterCriteria)) {
                        return reservation.getStatut().toLowerCase().contains(searchText);
                    }
                    return true;
                })
                .collect(Collectors.toList());

        // 🔄 Tri des réservations
        isAscending = "Croissant".equals(sortOrderComboBox.getValue());
        Comparator<Reservation> comparator = "Date".equals(filterCriteria)
                ? Comparator.comparing(Reservation::getDateReservation)
                : Comparator.comparing(Reservation::getStatut);

        filteredReservations.sort(isAscending ? comparator : comparator.reversed());

        // Affichage
        for (Reservation reservation : filteredReservations) {
            reservationCardContainer.getChildren().add(createReservationCard(reservation));
        }
    }

    // Afficher la liste des réservations
    @FXML
    public void displayReservations() {
        reservationCardContainer.getChildren().clear(); // Nettoyer avant de recharger

        List<Reservation> reservations = reservationService.getAll(); // Récupérer les réservations

        for (Reservation reservation : reservations) {
            VBox card = createReservationCard(reservation);
            reservationCardContainer.getChildren().add(card);
        }
    }

    // Créer une carte de réservation
    private VBox createReservationCard(Reservation reservation) {
        VBox card = new VBox();
        card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

        Label title = new Label("📆 Date de Réservation");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label dateLabel = new Label("📅 " + reservation.getDateReservation());
        Label statutLabel = new Label("📝 Statut: " + reservation.getStatut());

        // ✅ Icônes Modifier, Supprimer et Détails
        HBox buttonContainer = new HBox(10);
        buttonContainer.setStyle("-fx-alignment: center;");

        // 🔍 Bouton Voir Détails avec icône
        Button detailsButton = new Button();
        detailsButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        detailsButton.setOnAction(e -> showReservationDetails(reservation));

        ImageView detailsIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/details-icon.png")));
        detailsIcon.setFitWidth(18);
        detailsIcon.setFitHeight(18);
        detailsButton.setGraphic(detailsIcon);


        // ✏️ Bouton Modifier avec icône
        Button modifierButton = new Button();
        modifierButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        modifierButton.setOnAction(e -> openEditReservationWindow(reservation));

        ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/edit-icon.png")));
        editIcon.setFitWidth(18);
        editIcon.setFitHeight(18);
        modifierButton.setGraphic(editIcon);

        // 🗑️ Bouton Supprimer avec icône
        Button supprimerButton = new Button();
        supprimerButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        supprimerButton.setOnAction(e -> {
            supprimerReservation(reservation); // Supprime la réservation
            displayReservations(); // Rafraîchit la liste
        });

        ImageView trashIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/trash-icon.png")));
        trashIcon.setFitWidth(18);
        trashIcon.setFitHeight(18);
        supprimerButton.setGraphic(trashIcon);

        // Ajout des icônes dans le conteneur des boutons
        buttonContainer.getChildren().addAll(detailsButton, modifierButton, supprimerButton);

        card.getChildren().addAll(title, dateLabel, statutLabel, buttonContainer);
        return card;
    }


    // Ouvrir une fenêtre pour modifier une réservation
    private void openEditReservationWindow(Reservation reservation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierReservation.fxml"));
            Parent root = loader.load();

            ModifierReservation controller = loader.getController();
            controller.initDataReservation(reservation); // Passer la réservation à modifier

            Stage stage = new Stage();
            stage.setTitle("Modifier Réservation");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            displayReservations(); // Rafraîchir après la modification
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Supprimer une réservation
    private void supprimerReservation(Reservation reservation) {
        reservationService.delete(reservation); // Suppression de la réservation dans le service
    }

    // Afficher les détails d'une réservation
    @FXML
    public void showReservationDetails(Reservation reservation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailReservation.fxml"));
            Parent root = loader.load();

            // Passer la réservation au contrôleur de détail
            DetailReservation controller = loader.getController();
            controller.initData(reservation);

            // Afficher la nouvelle fenêtre
            Stage stage = new Stage();
            stage.setTitle("Détails de la Réservation");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Rechercher des réservations dynamiquement
    @FXML
    public void searchReservations() {
        String searchText = searchField.getText().toLowerCase();
        reservationCardContainer.getChildren().clear(); // Nettoyer avant de recharger

        List<Reservation> reservations = reservationService.getAll(); // Récupérer toutes les réservations

        // Filtrer les réservations en fonction du texte de recherche (date ou statut)
        List<Reservation> filteredReservations = reservations.stream()
                .filter(reservation ->
                        reservation.getDateReservation().toLowerCase().contains(searchText) || // Recherche par date
                                reservation.getStatut().toLowerCase().contains(searchText) // Recherche par statut
                )
                .collect(Collectors.toList());

        // Afficher les réservations filtrées
        for (Reservation reservation : filteredReservations) {
            VBox card = createReservationCard(reservation);
            reservationCardContainer.getChildren().add(card);
        }
    }

    // Navigation vers l'ajout de réservation
    public void goToAjoutReservation(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GestionReservation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Navigation vers l'accueil
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

    // Effet de survol pour les boutons
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

    // Effet de sortie pour les boutons
    @FXML
    public void buttonExitEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }

    @FXML
    private void exportReservations() {
        List<Reservation> reservations = reservationService.getAll(); // Récupère les réservations

        if (reservations.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune réservation");
            alert.setHeaderText("Pas de données à exporter");
            alert.setContentText("Il n'y a aucune réservation à exporter.");
            alert.showAndWait();
            return;
        }

        String excelFilePath = ExcelDesignService.generateExcelWithStyle(reservations);

        if (excelFilePath != null) {
            // 📌 Ouvrir le fichier Excel automatiquement après l'exportation
            openExcelFile(excelFilePath);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exportation Réussie");
            alert.setHeaderText("Les réservations ont été exportées avec succès !");
            alert.setContentText("Le fichier a été enregistré sous : " + excelFilePath);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de l'exportation");
            alert.setContentText("Impossible de générer le fichier Excel.");
            alert.showAndWait();
        }
    }



}