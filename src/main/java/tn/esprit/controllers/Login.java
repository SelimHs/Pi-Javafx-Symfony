package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.services.UsersService;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class Login {
    @FXML private Button loginButton;
    @FXML private PasswordField password;
    @FXML private TextField emailField;
    @FXML private CheckBox rememberMeCheckBox;
    @FXML private Button signupButton;
    @FXML private ImageView eyeIcon;
    @FXML private ListView<String> emailSuggestions;
    @FXML private TextField visiblePassword;
    @FXML private Button showPasswordButton;

    private boolean isPasswordVisible = false;
    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());
    private final UsersService usersService = new UsersService();
    private static final String REMEMBER_ME_KEY = "rememberMe";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private Runnable onLoginSuccess;
    private String userRole = ""; // Stocke le rôle de l'utilisateur

    private ObservableList<String> savedEmails = FXCollections.observableArrayList();
    private FilteredList<String> filteredEmails = new FilteredList<>(savedEmails);

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    @FXML
    public void initialize() {
        loadSavedCredentials();
        savedEmails.addAll(usersService.getAllEmails());
        emailSuggestions.setItems(filteredEmails);
        emailSuggestions.setVisible(false);

        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                emailSuggestions.setVisible(false);
            } else {
                filteredEmails.setPredicate(email -> email.toLowerCase().contains(newValue.toLowerCase()));
                emailSuggestions.setVisible(true);
            }
        });

        emailSuggestions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                emailField.setText(newValue);
                String savedPassword = usersService.getPasswordByEmail(newValue);
                password.setText(savedPassword);
                emailSuggestions.setVisible(false);
            }
        });

        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                emailSuggestions.setVisible(false);
            }
        });

        showPasswordButton.setOnAction(event -> togglePasswordVisibility());

        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (password.isVisible()) {
                visiblePassword.setText(newValue);
            }
        });
        visiblePassword.textProperty().bindBidirectional(password.textProperty());

        emailField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleLoginButtonAction();
            }
        });

        password.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleLoginButtonAction();
            }
        });

        visiblePassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleLoginButtonAction();
            }
        });
    }

    @FXML
    public void togglePasswordVisibility() {
        if (isPasswordVisible) {
            password.setVisible(true);
            visiblePassword.setVisible(false);
            eyeIcon.setImage(new Image(getClass().getResourceAsStream("/images/eye.png")));
            isPasswordVisible = false;
            password.requestFocus();
        } else {
            password.setVisible(false);
            visiblePassword.setVisible(true);
            eyeIcon.setImage(new Image(getClass().getResourceAsStream("/images/eye-slash.png")));
            isPasswordVisible = true;
            visiblePassword.requestFocus();
        }

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

            if (rememberMeCheckBox.isSelected()) {
                saveCredentials(emailField.getText(), password.getText());
            } else {
                clearCredentials();
            }

            redirectToHomePage();
        }
    }


    private boolean isValidUser() {
        String email = emailField.getText();
        String enteredPassword = password.getText();

        if (email.isEmpty() || enteredPassword.isEmpty()) {
            showAlert("Champs vides", "Veuillez entrer un email et un mot de passe.");
            return false;
        }

        boolean isAuthenticated = usersService.authenticate(email, enteredPassword);
        if (isAuthenticated) {
            userRole = usersService.getUserRoleByEmail(email);
            return true;
        } else {
            showAlert("Échec de la connexion", "Email ou mot de passe incorrect.");
            return false;
        }
    }

    private void redirectToHomePage() {
        String fxmlPath = "";

        if ("admin".equalsIgnoreCase(userRole)) {
            fxmlPath = "/Acceuil.fxml";
        } else if ("client".equalsIgnoreCase(userRole)) {
            fxmlPath = "/FrontAcceuil.fxml";
        } else {
            showAlert("Erreur", "Votre rôle est inconnu, impossible de vous connecter.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene homeScene = new Scene(loader.load());
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(homeScene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page d'accueil.");
        }
    }

    private void saveCredentials(String email, String password) {
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        prefs.putBoolean(REMEMBER_ME_KEY, true);
        prefs.put(EMAIL_KEY, email);
        prefs.put(PASSWORD_KEY, password);
    }

    private void loadSavedCredentials() {
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        boolean rememberMe = prefs.getBoolean(REMEMBER_ME_KEY, false);
        if (rememberMe) {
            emailField.setText(prefs.get(EMAIL_KEY, ""));
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

    @FXML
    private void handleForgotPassword() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/forgotPassword.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page de récupération de mot de passe.");
        }
    }
}
