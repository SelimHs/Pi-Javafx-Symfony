package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.esprit.models.Reservation;
import tn.esprit.services.PdfService;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class DetailReservation {

    @FXML private Label titleLabel;
    @FXML private Label reservationIdLabel;
    @FXML private Label reservationUserLabel;
    @FXML private Label reservationEventLabel;
    @FXML private Label reservationDateLabel;
    @FXML private Label reservationStatutLabel;
    @FXML private Button retourButton;
    @FXML private Button exportButton;

    private Reservation reservation;

    public void initData(Reservation reservation) {
        this.reservation = reservation;
        titleLabel.setText("Détails de la Réservation #" + reservation.getIdReservation());
        reservationIdLabel.setText("🔖 ID Réservation : " + reservation.getIdReservation());
        reservationUserLabel.setText("👤 Utilisateur : " + reservation.getIdUser());
        reservationEventLabel.setText("🎉 Événement : " + reservation.getIdEvent());
        reservationDateLabel.setText("📅 Date : " + reservation.getDateReservation());
        reservationStatutLabel.setText("✅ Statut : " + reservation.getStatut());
    }

    @FXML
    private void retourAfficherReservations(ActionEvent actionEvent) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/Acceuil.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }



    public void RetourReservation(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficheReservation.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
