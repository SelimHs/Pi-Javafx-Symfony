package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceReservation;

public class ModifierReservation {

    @FXML
    private TextField idReservationField;
    @FXML
    private TextField dateReservationField;
    @FXML
    private TextField idUserField;
    @FXML
    private TextField idEventField;
    @FXML
    private ComboBox<String> statutComboBox;

    private ServiceReservation reservationService = new ServiceReservation();
    private Reservation reservation;

    public void initDataReservation(Reservation reservation) {
        this.reservation = reservation;
        idReservationField.setText(String.valueOf(reservation.getIdReservation()));
        idUserField.setText(String.valueOf(reservation.getIdUser()));
        idEventField.setText(String.valueOf(reservation.getIdEvent()));
        dateReservationField.setText(reservation.getDateReservation());

        // Remplir le ComboBox avec les valeurs possibles
        statutComboBox.getItems().setAll("Confirmée", "Annulée", "En attente");

        // Sélectionner la valeur correspondant au statut actuel
        statutComboBox.setValue(reservation.getStatut());
    }

    @FXML
    private void updateReservation() {
        reservation.setDateReservation(dateReservationField.getText());
        reservation.setStatut(statutComboBox.getValue());

        reservationService.update(reservation);

        Stage stage = (Stage) idReservationField.getScene().getWindow();
        stage.close();
    }

    public void saveEvent(ActionEvent actionEvent) {
    }

    public void cancelEdit(ActionEvent actionEvent) {
    }
}
