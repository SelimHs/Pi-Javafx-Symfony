package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.UsersService;
import service.EmailService;

import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {
    @FXML private Button loginButton;
    @FXML private PasswordField password;
    @FXML private TextField text;
    @FXML private CheckBox rememberMeCheckBox;
    @FXML private Button signupButton;

    @FXML
    private ImageView eyeIcon;

    private boolean isPasswordVisible = false;
    @FXML
    private TextField visiblePassword; // Champ de mot de passe en clair
    @FXML
    private Button showPasswordButton; // Bouton pour afficher/masquer le mot de passe
    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());
    private final UsersService usersService = new UsersService();
    private final EmailService emailService = new EmailService();
    private static final String REMEMBER_ME_KEY = "rememberMe";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private Runnable onLoginSuccess;

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }



    @FXML
    public void initialize() {
        loadSavedCredentials();

        // Gestion du bouton "Afficher le mot de passe"
        showPasswordButton.setOnAction(event -> togglePasswordVisibility());

        // Synchronisation des champs de mot de passe
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (password.isVisible()) {
                visiblePassword.setText(newValue);
            }
        });
        visiblePassword.textProperty().bindBidirectional(password.textProperty());

    }

    @FXML
    public void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Masquer le mot de passe
            password.setVisible(true);
            visiblePassword.setVisible(false);
            eyeIcon.setImage(new Image(getClass().getResourceAsStream("/images/eye.png")));
            isPasswordVisible = false;
            password.requestFocus(); // Donner le focus au champ PasswordField
        } else {
            // Afficher le mot de passe
            password.setVisible(false);
            visiblePassword.setVisible(true);
            eyeIcon.setImage(new Image(getClass().getResourceAsStream("/images/eye-slash.png")));
            isPasswordVisible = true;
            visiblePassword.requestFocus(); // Donner le focus au champ TextField
        }

        // Forcer la synchronisation des champs
        if (password.isVisible()) {
            password.setText(visiblePassword.getText());
        } else {
            visiblePassword.setText(password.getText());
        }
    }
    @FXML
    private void handleLoginButtonAction() {
        if (isValidUser()) {
            if (onLoginSuccess != null) {
                onLoginSuccess.run();
            }
            redirectToHomePage();
        }
    }

    private boolean isValidUser() {
        String email = text.getText();
        String enteredPassword = password.getText();

        if (email.isEmpty() || enteredPassword.isEmpty()) {
            showAlert("Champs vides", "Veuillez entrer un email et un mot de passe.");
            return false;
        }

        boolean isAuthenticated = usersService.authenticate(email, enteredPassword);
        if (isAuthenticated) {
            if (rememberMeCheckBox.isSelected()) {
                saveCredentials(email, enteredPassword);
            } else {
                clearCredentials();
            }
            return true;
        } else {
            showAlert("Échec de la connexion", "Email ou mot de passe incorrect.");
            return false;
        }
    }

    @FXML
    private void handleContinueWithMail() {
        // Logique pour continuer avec Mail
        System.out.println("Continuer avec Mail");
    }

    @FXML
    private void handleContinueWithFacebook() {
        // Logique pour continuer avec Facebook
        System.out.println("Continuer avec Facebook");
    }

    @FXML
    private void handleSignupButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cree_compte.fxml"));
            Scene signupScene = new Scene(loader.load());
            Stage stage = (Stage) signupButton.getScene().getWindow();
            stage.setScene(signupScene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page d'inscription.");
        }
    }

    private void saveCredentials(String email, String password) {
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        prefs.putBoolean(REMEMBER_ME_KEY, true);
        prefs.put(EMAIL_KEY, email);
        prefs.put(PASSWORD_KEY, password);
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'enregistrement des préférences", e);
        }
    }

    private void loadSavedCredentials() {
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        boolean rememberMe = prefs.getBoolean(REMEMBER_ME_KEY, false);
        if (rememberMe) {
            text.setText(prefs.get(EMAIL_KEY, ""));
            password.setText(prefs.get(PASSWORD_KEY, ""));
            rememberMeCheckBox.setSelected(true);
        }
    }

    private void clearCredentials() {
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        prefs.remove(REMEMBER_ME_KEY);
        prefs.remove(EMAIL_KEY);
        prefs.remove(PASSWORD_KEY);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void redirectToHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/homePage.fxml"));
            Scene homeScene = new Scene(loader.load());
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(homeScene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page d'accueil.");
        }
    }

    public Button getSignupButton() {
        return signupButton;
    }
    @FXML
    private void handleForgotPassword() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/forgotPassword.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page de récupération de mot de passe.");
        }
    }
}