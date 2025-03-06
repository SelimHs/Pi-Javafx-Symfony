package controller;

import com.stripe.model.PaymentIntent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import tn.esprit.models.Billet;
import tn.esprit.models.StripeConfig;
import tn.esprit.models.Event;
import tn.esprit.services.StripeService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainController {
    @FXML
    private Button btnPay;
    @FXML
    private Button btnCheckout;
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
        btnCheckout.setOnAction(event -> goToBilletPage());
    }

    /**
     * ✅ Set payment details and apply remise if available
     */
    public void setPaymentDetails(int prix, String proprietaire, Billet.TypeBillet type, Event event, FrontBillet controller) {
        this.proprietaire = proprietaire;
        this.typeBillet = type;
        this.selectedEvent = event;
        this.frontBilletController = controller;

        // ✅ Fetch the FINAL updated price after remise
        this.prixBilletFinal = controller.getUpdatedPrixBillet();

        lblMontant.setText("Montant à payer : " + prixBilletFinal + " DT");
    }


    @FXML
    private void processPayment() {
        if (prixBilletFinal == 0) {
            showAlert("Erreur", "Le prix du billet n'est pas défini !");
            return;
        }

        PaymentIntent paymentIntent = StripeService.createPayment(prixBilletFinal, "usd");

        if (paymentIntent != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Paiement réussi !");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Votre paiement de " + prixBilletFinal + " USD a été effectué avec succès !");

            confirmationAlert.showAndWait().ifPresent(response -> createBilletAfterPayment());
        } else {
            showAlert("❌ Erreur", "Échec du paiement.");
        }
    }

    /**
     * 🔄 Handle payment cancellation and return to the billet page
     */
    private void redirectToCheckout() {
        Optional<ButtonType> result = showConfirmationDialog("Confirmation", "Voulez-vous continuer le paiement ?");

        if (result.isPresent() && result.get() == ButtonType.OK) {
            String checkoutUrl = "https://checkout.stripe.com/pay"; // 🔗 Replace with real URL
            openWebPage(checkoutUrl);
        } else {
            goToBilletPage(); // 🔥 If canceled, return to the billet page
        }
    }

    /**
     * ✅ Return to the billet page while keeping entered data
     */
    private void goToBilletPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontBillet.fxml"));
            Parent root = loader.load();

            // ✅ Retrieve the controller and restore values
            FrontBillet billetController = loader.getController();
            billetController.setPrixBillet(prixBilletFinal);
            billetController.setEventSelection(selectedEvent);
            billetController.setNomClient(proprietaire);
            billetController.setTypeBillet(typeBillet);

            Stage stage = (Stage) btnCheckout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de retourner à la page des billets.");
        }
    }

    /**
     * ✅ Create the ticket after successful payment
     */
    private void createBilletAfterPayment() {
        if (frontBilletController == null) {
            showAlert("Erreur", "Impossible de créer le billet après paiement.");
            return;
        }

        // ✅ Création du billet après paiement
        frontBilletController.createBilletAfterPayment(proprietaire, prixBilletFinal, typeBillet, selectedEvent);

        // ✅ Redirection vers la page des événements après paiement
        goToEvents();
    }


    /**
     * 🔄 Redirect to events page after payment
     */
    private void goToEvents() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEvents.fxml"));
            Parent root = loader.load();

            // ✅ Récupérer la fenêtre actuelle de manière plus sûre
            Stage stage = (Stage) btnPay.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible d'ouvrir la liste des événements.");
        }
    }


    /**
     * 📢 Show alert message
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * 🔥 Show confirmation dialog before proceeding with checkout
     */
    private Optional<ButtonType> showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }

    /**
     * 🌐 Open Stripe checkout page in the browser
     */
    private void openWebPage(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
