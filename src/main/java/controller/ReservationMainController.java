package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceReservation;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class ReservationMainController {

    @FXML
    private TextField searchField;
    @FXML
    private Button modifierBouton;
    @FXML
    private FlowPane reservationCardContainer;
    @FXML
    private Button deleteBouton;

    private ServiceReservation reservationService = new ServiceReservation();

    // Afficher la liste des réservations
    @FXML
    public void displayReservations() {
        reservationCardContainer.getChildren().clear(); // Nettoyer avant de recharger

        List<Reservation> reservations = reservationService.getAll(); // Récupérer les réservations

        for (Reservation reservation : reservations) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

            Label title = new Label("Réservation ID: " + reservation.getIdReservation());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label dateLabel = new Label("📅 " + reservation.getDateReservation());
            Label statutLabel = new Label("📝 Statut: " + reservation.getStatut());

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setOnAction(e -> showReservationDetails(reservation));

            Button supprimerButton = new Button("Supprimer");
            supprimerButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            supprimerButton.setOnAction(e -> {
                supprimerReservation(reservation); // Supprime la réservation
                displayReservations(); // Rafraîchit la liste
            });

            Button modifierButton = new Button("Modifier");
            modifierButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
            modifierButton.setOnAction(e -> openEditReservationWindow(reservation));

            card.getChildren().addAll(title, dateLabel, statutLabel, detailsButton, modifierButton, supprimerButton);
            reservationCardContainer.getChildren().add(card);
        }
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


    private void supprimerReservation(Reservation reservation) {
        reservationService.delete(reservation); // Suppression de la réservation dans le service
    }


    @FXML
    public void showReservationDetails(Reservation reservation) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails de la Réservation");
        alert.setHeaderText("Réservation #" + reservation.getIdReservation());
        alert.setContentText("👤 ID Utilisateur : " + reservation.getIdUser() +
                "\n🎟️ ID Événement : " + reservation.getIdEvent() +
                "\n📅 Date de Réservation : " + reservation.getDateReservation() +
                "\n🔖 Statut : " + reservation.getStatut());

        alert.showAndWait();
    }


    // Mettre à jour une réservation
    @FXML
    public void updateReservation(ActionEvent actionEvent) {
        // Implémentation pour mettre à jour une réservation
    }

    // Rechercher une réservation
    @javafx.fxml.FXML
    public void searchReservation(ActionEvent actionEvent) {

        // Implémentation pour rechercher une réservation par le texte
    }

    // Supprimer une réservation
    @FXML
    public void deleteReservation(ActionEvent actionEvent) {
        // Implémentation pour supprimer une réservation
    }

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
}

