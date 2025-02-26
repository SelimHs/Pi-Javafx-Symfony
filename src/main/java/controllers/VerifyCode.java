package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class VerifyCode {

    @FXML private TextField codeField;
    @FXML private TextField captchaInput;
    @FXML private ImageView captchaImage;
    @FXML private Label messageLabel;

    private String generatedCaptchaText;
    private String userEmail; // Variable pour stocker l'email

    @FXML
    public void initialize() {
        generateCaptcha();
    }

    private void generateCaptcha() {
        // Générer un texte aléatoire pour le CAPTCHA
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length())));
        }
        generatedCaptchaText = captcha.toString();

        // Générer l'image du CAPTCHA
        generateCaptchaImage(generatedCaptchaText);
    }

    private void generateCaptchaImage(String text) {
        int width = 200, height = 80;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // Arrière-plan blanc
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Ajouter du bruit (lignes aléatoires)
        g2d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 10; i++) {
            int x1 = (int) (Math.random() * width);
            int y1 = (int) (Math.random() * height);
            int x2 = (int) (Math.random() * width);
            int y2 = (int) (Math.random() * height);
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Texte du CAPTCHA
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString(text, 30, 50);
        g2d.dispose();

        // Sauvegarder l'image temporairement
        File captchaFile = new File("captcha.png");
        try {
            ImageIO.write(bufferedImage, "png", captchaFile);
            captchaImage.setImage(new Image(captchaFile.toURI().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVerifyCode() {
        String enteredCode = codeField.getText();
        String enteredCaptcha = captchaInput.getText();

        if (!enteredCaptcha.equalsIgnoreCase(generatedCaptchaText)) {
            messageLabel.setText("CAPTCHA incorrect. Veuillez réessayer.");
            generateCaptcha(); // Régénérer le CAPTCHA
            return;
        }

        if (enteredCode.isEmpty()) {
            messageLabel.setText("Veuillez entrer le code de confirmation.");
            return;
        }

        // Vérifier le code de confirmation ici
        messageLabel.setText("Code vérifié avec succès !");
        switchToResetPassword();
    }

    private void switchToResetPassword() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resetPassword.fxml"));
            Parent root = loader.load();

            // Passer l'email au contrôleur ResetPassword
            ResetPassword resetPasswordController = loader.getController();
            resetPasswordController.setUserEmail(this.userEmail); // Transmettre l'email

            // Log pour vérifier l'email transmis
            System.out.println("Email transmis à ResetPassword : " + this.userEmail);

            Stage stage = (Stage) messageLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserData(String email, String confirmationCode) {
        this.userEmail = email; // Stocker l'email
        System.out.println("Email reçu dans VerifyCode : " + email); // Log pour débogage
    }
}