    package controllers;

    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
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
    }