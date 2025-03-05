package controller;

import com.stripe.model.PaymentIntent;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import tn.esprit.models.Billet;
import tn.esprit.models.StripeConfig;
import tn.esprit.models.Event;
import tn.esprit.services.StripeService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button btnPay;
    @FXML
    private Label lblMontant;

    private int prixBilletFinal;
    private String proprietaire;
    private Billet.TypeBillet typeBillet;
    private Event selectedEvent;
    private FrontBillet frontBilletController;

    public void initialize() {
        StripeConfig.init();

        btnPay.setOnAction(event -> processPayment());
    }

    public void setPaymentDetails(int prix, String proprietaire, Billet.TypeBillet type, Event event, FrontBillet controller) {
        this.prixBilletFinal = prix;
        this.proprietaire = proprietaire;
        this.typeBillet = type;
        this.selectedEvent = event;
        this.frontBilletController = controller;

        lblMontant.setText("Montant à payer : " + prix + " DT");
    }

    @FXML
    private void processPayment() {
        if (prixBilletFinal == 0) {
            showAlert("Erreur", "Le prix du billet n'est pas défini !");
            return;
        }

        PaymentIntent paymentIntent = StripeService.createPayment(prixBilletFinal, "usd");

        if (paymentIntent != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Paiement réussi !");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Votre paiement de " + prixBilletFinal + " USD a été effectué avec succès !");

            confirmationAlert.showAndWait().ifPresent(response -> createBilletAfterPayment());
        } else {
            showAlert("❌ Erreur", "Échec du paiement.");
        }
    }

    private void createBilletAfterPayment() {
        if (frontBilletController == null) {
            showAlert("Erreur", "Impossible de créer le billet après paiement.");
            return;
        }

        frontBilletController.createBilletAfterPayment(proprietaire, prixBilletFinal, typeBillet, selectedEvent);
        goToEvents();
    }

    private void goToEvents() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEvents.fxml"));
            Parent root = loader.load();

            // ✅ Animation de fondu (transition douce)
            root.setOpacity(0);
            Stage stage = (Stage) btnPay.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(800), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible d'ouvrir la liste des événements.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
