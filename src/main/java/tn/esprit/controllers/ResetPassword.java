package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.services.UsersService;
import java.io.IOException;
import java.util.Objects;

public class ResetPassword {

    @FXML private PasswordField newPasswordField;
    @FXML private TextField newPasswordTextField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField confirmPasswordTextField;
    @FXML private Label messageLabel;
    @FXML private ImageView eyeConfirmIcon;
    @FXML private Button resetButton;
    @FXML private ImageView eyeIcon;
    @FXML private Button showPasswordButton;
    @FXML private Button showConfirmPasswordButton;

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;
    private String userEmail;
    private final UsersService usersService = new UsersService();

    @FXML
    public void initialize() {
        // Vérifiez que les champs sont bien injectés
        if (newPasswordTextField == null || confirmPasswordTextField == null) {
            System.err.println("Erreur : Les TextField ne sont pas initialisés.");
            return;
        }

        // Load eye and eye-slash icons
        Image eyeImage = null;
        Image eyeSlashImage = null;
        try {
            eyeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/eye.png")));
            eyeSlashImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/eye-slash.png")));
        } catch (NullPointerException e) {
            System.err.println("Erreur : Les icônes eye.png ou eye-slash.png sont introuvables.");
        }

        // Set initial icons
        eyeIcon.setImage(eyeImage);
        eyeConfirmIcon.setImage(eyeImage);

        // Add event handlers for show password buttons
        Image finalEyeImage = eyeImage;
        Image finalEyeSlashImage = eyeSlashImage;
        showPasswordButton.setOnAction(event -> {
            isPasswordVisible = !isPasswordVisible;
            togglePasswordVisibility(newPasswordField, newPasswordTextField, eyeIcon, finalEyeImage, finalEyeSlashImage, isPasswordVisible);
        });

        Image finalEyeImage1 = eyeImage;
        Image finalEyeSlashImage1 = eyeSlashImage;
        showConfirmPasswordButton.setOnAction(event -> {
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            togglePasswordVisibility(confirmPasswordField, confirmPasswordTextField, eyeConfirmIcon, finalEyeImage1, finalEyeSlashImage1, isConfirmPasswordVisible);
        });
    }
    private void togglePasswordVisibility(PasswordField passwordField, TextField textField, ImageView iconView, Image eyeImage, Image eyeSlashImage, boolean isVisible) {
        if (isVisible) {
            // Show password
            textField.setText(passwordField.getText());
            textField.setVisible(true);
            passwordField.setVisible(false);
            iconView.setImage(eyeSlashImage);
        } else {
            // Hide password
            passwordField.setText(textField.getText());
            passwordField.setVisible(true);
            textField.setVisible(false);
            iconView.setImage(eyeImage);
        }
    }

    @FXML
    private void handleResetPassword() {
        resetButton.setDisable(true);

        try {
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                messageLabel.setText("Veuillez remplir tous les champs.");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                messageLabel.setText("Les mots de passe ne correspondent pas.");
                return;
            }

            if (!isPasswordValid(newPassword)) {
                return;
            }

            boolean isUpdated = usersService.updatePassword(userEmail, newPassword);
            if (isUpdated) {
                messageLabel.setText("Mot de passe réinitialisé avec succès !");
                loadHomePage();
            } else {
                messageLabel.setText("Erreur lors de la mise à jour du mot de passe.");
            }
        } catch (Exception e) {
            messageLabel.setText("Une erreur inattendue s'est produite.");
            e.printStackTrace();
        } finally {
            resetButton.setDisable(false);
        }
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            messageLabel.setText("Le mot de passe doit contenir au moins 8 caractères.");
            return false;
        }
        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$")) {
            messageLabel.setText("Le mot de passe doit contenir au moins une majuscule, une minuscule et un chiffre.");
            return false;
        }
        return true;
    }

    private void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/homePage.fxml"));
            Parent root = loader.load();

            Scene currentScene = messageLabel.getScene();
            if (currentScene != null) {
                Stage stage = (Stage) currentScene.getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Page d'accueil");
                stage.show();
            } else {
                System.err.println("Erreur : La scène est null.");
            }
        } catch (IOException e) {
            messageLabel.setText("Erreur lors du chargement de la page d'accueil.");
            e.printStackTrace();
        }
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
        System.out.println("Email défini dans ResetPassword : " + email);
    }
}