package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.EmailService;

import java.io.IOException;

public class ForgotPassword {

    @FXML private TextField emailField;
    @FXML private Label messageLabel;

    private final EmailService emailService = new EmailService();

    @FXML
    private void handleSendEmail() {
        String email = emailField.getText();

        if (email.isEmpty()) {
            messageLabel.setText("Veuillez entrer votre adresse email.");
            return;
        }

        // Envoyer un email de confirmation avec un code
        String confirmationCode = emailService.sendConfirmationEmail(email);

        if (confirmationCode != null) {
            messageLabel.setText("Un email de confirmation a été envoyé à " + email);

            // Rediriger vers la page de vérification du code
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/verifyCode.fxml"));
                Parent root = loader.load();

                // Passer l'email et le code de confirmation au contrôleur VerifyCode
                VerifyCode verifyCodeController = loader.getController();
                verifyCodeController.setUserData(email, confirmationCode);

                // Log pour vérifier l'email transmis
                System.out.println("Email transmis à VerifyCode : " + email);

                Scene scene = new Scene(root);
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                messageLabel.setText("Erreur lors du chargement de la page de vérification.");
                e.printStackTrace(); // Print the stack trace for debugging
            }
        } else {
            messageLabel.setText("Erreur lors de l'envoi de l'email de confirmation.");
        }
    }

    @FXML
    private void handleBackToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}