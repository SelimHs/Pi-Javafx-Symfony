package controller;
import javafx.scene.control.Label;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import com.stripe.model.PaymentIntent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import tn.esprit.models.StripeConfig;
import tn.esprit.services.StripeService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.awt.*;
import java.net.URI;

public class MainController {
    @FXML
    private Button btnPay;
    @FXML
    private Button btnCheckout;
    @FXML
    private Label lblMontant; // Ajouter ce Label dans le contrôleur


    private int prixBilletFinal = 0; // Stocker le prix réel du billet

    public void initialize() {
        StripeConfig.init(); // Initialisation de Stripe

        btnPay.setOnAction(event -> processPayment());
        btnCheckout.setOnAction(event -> redirectToCheckout());
    }

    /**
     * 🔥 Mettre à jour le prix du billet à payer
     */
    public void setPrixBilletFinal(int prix) {
        this.prixBilletFinal = prix;
        lblMontant.setText("Montant à payer : " + prix + " DT"); // ✅ Mise à jour du texte
    }

    /**
     * ✅ Processus de paiement Stripe
     */
    private void processPayment() {
        if (prixBilletFinal == 0) {
            showAlert("Erreur", "Le prix du billet n'est pas défini !");
            return;
        }

        double montantStripe = prixBilletFinal; // Utiliser le prix réel
        PaymentIntent paymentIntent = StripeService.createPayment(montantStripe, "usd");

        if (paymentIntent != null) {
            // ✅ Afficher un message de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Paiement réussi !");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Votre paiement de " + montantStripe + " USD a été effectué avec succès !");

            // ✅ Redirection après que l'utilisateur clique sur "OK"
            confirmationAlert.showAndWait().ifPresent(response -> {
                goToEvents(); // 🔥 Rediriger vers la liste des événements
            });
        } else {
            showAlert("❌ Erreur", "Échec du paiement.");
        }
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
    /**
     * 🔄 Redirige vers Stripe Checkout
     */
    private void redirectToCheckout() {
        String checkoutUrl = "https://checkout.stripe.com/pay"; // 🔗 Remplace avec l'URL réelle
        openWebPage(checkoutUrl);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
