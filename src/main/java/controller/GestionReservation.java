package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceReservation;

import java.io.IOException;

public class GestionReservation {

    @FXML
    private TextField userIdField;
    @FXML
    private TextField eventIdField;
    @FXML
    private DatePicker dateReservationPicker;
    @FXML
    private ComboBox<String> statutComboBox;
    @FXML
    private Button ajoutreserv;

    @FXML
    private void ajouterReservation() {
        Reservation reservation = new Reservation();
        ServiceReservation sr = new ServiceReservation();

        reservation.setIdUser(Integer.parseInt(userIdField.getText()));
        reservation.setIdEvent(Integer.parseInt(eventIdField.getText()));
        reservation.setDateReservation(dateReservationPicker.getValue().toString());
        reservation.setStatut(statutComboBox.getValue());
        sr.add(reservation);
        /*if (userId.isEmpty() || eventId.isEmpty() || date.isEmpty() || statut == null) {
            showAlert(AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs !");
            return;
        }*/

        //System.out.println("Réservation ajoutée : Utilisateur " + userId + ", Événement " + eventId + ", Date " + date + ", Statut " + statut);
        showAlert(AlertType.INFORMATION, "Succès", "Réservation ajoutée avec succès !");

        clearFields();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        userIdField.clear();
        eventIdField.clear();
        dateReservationPicker.setValue(null);
        statutComboBox.setValue(null);
    }

    public void navigateToReservations(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AfficheReservation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
