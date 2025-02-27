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
import service.EmailService;

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
    @FXML private TextField emailField; // Champ pour l'email
    @FXML private CheckBox rememberMeCheckBox;
    @FXML private Button signupButton;

    @FXML
    private ImageView eyeIcon;

    @FXML
    private ListView<String> emailSuggestions; // Liste des suggestions d'emails
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

    // Liste des emails enregistrés
    private ObservableList<String> savedEmails = FXCollections.observableArrayList();
    private FilteredList<String> filteredEmails = new FilteredList<>(savedEmails);

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    @FXML
    public void initialize() {
        loadSavedCredentials();

        // Charger les emails depuis la base de données
        savedEmails.addAll(usersService.getAllEmails()); // Méthode à implémenter dans UsersService

        // Configurer la ListView pour les suggestions d'emails
        emailSuggestions.setItems(filteredEmails);
        emailSuggestions.setVisible(false);

        // Filtrer les emails en fonction de ce que l'utilisateur tape
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                emailSuggestions.setVisible(false);
            } else {
                filteredEmails.setPredicate(email ->
                        email.toLowerCase().contains(newValue.toLowerCase())
                );
                emailSuggestions.setVisible(true);
            }
        });

        // Remplir automatiquement le mot de passe lorsque l'utilisateur sélectionne un email
        emailSuggestions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                emailField.setText(newValue); // Remplir le champ email avec l'email sélectionné
                String savedPassword = usersService.getPasswordByEmail(newValue); // Méthode à implémenter dans UsersService
                password.setText(savedPassword); // Remplir le champ de mot de passe
                emailSuggestions.setVisible(false); // Masquer la liste des suggestions
            }
        });

        // Masquer la liste des suggestions lorsque l'utilisateur clique en dehors
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                emailSuggestions.setVisible(false);
            }
        });

        // Gestion du bouton "Afficher le mot de passe"
        showPasswordButton.setOnAction(event -> togglePasswordVisibility());

        // Synchronisation des champs de mot de passe
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (password.isVisible()) {
                visiblePassword.setText(newValue);
            }
        });
        visiblePassword.textProperty().bindBidirectional(password.textProperty());

        // Ajouter un écouteur d'événements pour la touche "Entrée"
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

            // Demander à l'utilisateur s'il souhaite enregistrer le mot de passe
            if (rememberMeCheckBox.isSelected()) {
                saveCredentials(emailField.getText(), password.getText());
            } else {
                clearCredentials();
            }

            redirectToHomePage();
        }
    }

    private boolean askToSavePassword() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Enregistrer le mot de passe");
        alert.setHeaderText("Voulez-vous enregistrer le mot de passe ?");
        alert.setContentText("Choisissez 'Oui' pour enregistrer le mot de passe pour la prochaine connexion.");

        ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        java.util.Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonTypeYes;
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
            LOGGER.info("Informations enregistrées dans les préférences : " + email);
        } catch (BackingStoreException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'enregistrement des préférences", e);
        }

        // Mettre à jour le champ `enregistrer` dans la base de données
        String query = "UPDATE user SET enregistrer = TRUE WHERE email = ?";
        try (PreparedStatement pst = usersService.getConnection().prepareStatement(query)) {
            pst.setString(1, email);
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                LOGGER.info("Champ 'enregistrer' mis à jour avec succès pour l'email : " + email);
            } else {
                LOGGER.warning("Aucun utilisateur trouvé avec l'email : " + email);
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la mise à jour du champ 'enregistrer' : " + e.getMessage());
        }

        // Ajouter l'email à la liste des emails enregistrés
        if (!savedEmails.contains(email)) {
            savedEmails.add(email);
        }
    }
    private void loadSavedCredentials() {
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        boolean rememberMe = prefs.getBoolean(REMEMBER_ME_KEY, false);
        if (rememberMe) {
            String savedEmail = prefs.get(EMAIL_KEY, "");
            String savedPassword = prefs.get(PASSWORD_KEY, "");
            if (!savedEmail.isEmpty()) {
                // Vérifier si l'utilisateur a choisi d'enregistrer ses informations
                String query = "SELECT enregistrer FROM user WHERE email = ?";
                try (PreparedStatement pst = usersService.getConnection().prepareStatement(query)) {
                    pst.setString(1, savedEmail);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next() && rs.getBoolean("enregistrer")) {
                        emailField.setText(savedEmail);
                        password.setText(savedPassword);
                        rememberMeCheckBox.setSelected(true);
                        LOGGER.info("Informations chargées depuis les préférences : " + savedEmail);
                    }
                } catch (SQLException e) {
                    LOGGER.severe("Erreur lors de la vérification de la colonne 'enregistrer' : " + e.getMessage());
                }
            }
        }
    }

    private void clearCredentials() {
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        prefs.remove(REMEMBER_ME_KEY);
        prefs.remove(EMAIL_KEY);
        prefs.remove(PASSWORD_KEY);

        // Mettre à jour le champ `enregistrer` dans la base de données
        String query = "UPDATE user SET enregistrer = FALSE WHERE email = ?";
        try (PreparedStatement pst = usersService.getConnection().prepareStatement(query)) {
            pst.setString(1, emailField.getText());
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                LOGGER.info("Champ 'enregistrer' mis à jour avec succès pour l'email : " + emailField.getText());
            } else {
                LOGGER.warning("Aucun utilisateur trouvé avec l'email : " + emailField.getText());
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la mise à jour du champ 'enregistrer' : " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void redirectToHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
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