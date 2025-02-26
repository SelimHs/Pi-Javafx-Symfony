package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import service.UsersService;
import java.io.IOException;

public class ResetPassword {

    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label messageLabel;
    @FXML private Button resetButton;

    private String userEmail;
    private final UsersService usersService = new UsersService();

    @FXML
    private void handleResetPassword() {
        resetButton.setDisable(true);

        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs.");
            resetButton.setDisable(false);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            messageLabel.setText("Les mots de passe ne correspondent pas.");
            resetButton.setDisable(false);
            return;
        }

        if (!isPasswordValid(newPassword)) {
            resetButton.setDisable(false);
            return;
        }

        // Vérifier que l'email est correctement défini
        System.out.println("Email utilisé pour la mise à jour : " + userEmail);

        // Mise à jour du mot de passe dans la base de données
        try {
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

    private void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/homePage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) messageLabel.getScene().getWindow();
            if (stage != null) {
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

    private boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            messageLabel.setText("Le mot de passe doit contenir au moins 8 caractères.");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            messageLabel.setText("Le mot de passe doit contenir au moins une majuscule.");
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            messageLabel.setText("Le mot de passe doit contenir au moins une minuscule.");
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            messageLabel.setText("Le mot de passe doit contenir au moins un chiffre.");
            return false;
        }
        return true;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
        System.out.println("Email défini dans ResetPassword : " + email);
    }
}