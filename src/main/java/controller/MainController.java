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
     * 🔄 Redirige vers Stripe Checkout et gère l'annulation
     */
    private void redirectToCheckout() {
        Optional<ButtonType> result = showConfirmationDialog("Confirmation", "Voulez-vous continuer le paiement ?");

        if (result.isPresent() && result.get() == ButtonType.OK) {
            String checkoutUrl = "https://checkout.stripe.com/pay"; // 🔗 Remplace avec l'URL réelle
            openWebPage(checkoutUrl);
        } else {
            goToBilletPage(); // 🔥 Annulation : Retour à la page des billets
        }
    }

    /**
     * ✅ Si le paiement est annulé, retourne à la page des billets en gardant les données
     */
    private void goToBilletPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontBillet.fxml"));
            Parent root = loader.load();

            // ✅ Récupérer le contrôleur et remettre les valeurs saisies
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
            Stage stage = (Stage) btnPay.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
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

    private Optional<ButtonType> showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }

    private void openWebPage(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
