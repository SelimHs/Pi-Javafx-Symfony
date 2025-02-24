package controller;

import com.stripe.model.PaymentIntent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import tn.esprit.models.StripeConfig;
import tn.esprit.services.StripeCheckoutService;
import tn.esprit.services.StripeService;

import java.awt.*;
import java.net.URI;


public class MainController {
    @javafx.fxml.FXML
    private Button btnCheckout;
    @javafx.fxml.FXML
    private Button btnPay;

    public void initialize() {
        StripeConfig.init(); // Initialize Stripe

        btnPay.setOnAction(event -> {
            PaymentIntent paymentIntent = StripeService.createPayment(20.0, "usd");
            if (paymentIntent != null) {
                showAlert("Payment Created", "Payment ID: " + paymentIntent.getId());
            } else {
                showAlert("Error", "Payment creation failed.");
            }
        });
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
