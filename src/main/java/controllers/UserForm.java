package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Users;
import service.UsersService;

import java.util.regex.Pattern;

public class UserForm {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private TextField numeroField;
    @FXML private TextArea adresseField;
    @FXML private CheckBox typeAdmin;
    @FXML private CheckBox typeUser;
    @FXML private CheckBox genreMasculin;
    @FXML private CheckBox genreFeminin;
    @FXML private Button createButton;
    @FXML private Label messageLabel;

    private Users user;
    private UsersService userService;

    public UserForm() {
        userService = new UsersService(); // Initialisation de UserService
    }

    // Méthode pour passer les données à ce formulaire (modification ou ajout)
    public void setUser(Users user) {
        this.user = user;
        if (user != null) {
            // Remplir les champs si on est en mode modification
            nomField.setText(user.getNom());
            prenomField.setText(user.getPrenom());
            emailField.setText(user.getEmail());
            numeroField.setText(user.getNumeroTelephone());
            adresseField.setText(user.getAdresse());
            passwordField.setText(user.getPassword()); // Ajout du mot de passe
            // Gérer les CheckBoxes pour type et genre
            typeAdmin.setSelected(user.getType().equals("admin"));
            typeUser.setSelected(user.getType().equals("client"));
            genreMasculin.setSelected(user.getGenre().equals("homme"));
            genreFeminin.setSelected(user.getGenre().equals("femme"));
        }
    }

    @FXML
    private void handleSave() {
        // Vérification des champs vides
        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || emailField.getText().isEmpty() ||
                passwordField.getText().isEmpty() || numeroField.getText().isEmpty() || adresseField.getText().isEmpty()) {
            showAlert("Erreur", "Tous les champs obligatoires doivent être remplis !");
            return;
        }

        // Validation du nom et prénom
        if (!isValidName(nomField.getText()) || !isValidName(prenomField.getText())) {
            showAlert("Erreur", "Le nom et le prénom ne doivent contenir que des lettres.");
            return;
        }

        // Validation du mot de passe
        if (!isValidPassword(passwordField.getText())) {
            showAlert("Erreur", "Le mot de passe doit contenir au moins une majuscule et un caractère spécial.");
            return;
        }

        // Validation de l'email
        if (!isValidEmail(emailField.getText())) {
            showAlert("Erreur", "L'email doit contenir un @ et un domaine valide (ex: .com, .fr).");
            return;
        }

        // Validation du numéro de téléphone
        if (!isValidPhoneNumber(numeroField.getText())) {
            showAlert("Erreur", "Le numéro de téléphone doit contenir exactement 8 chiffres.");
            return;
        }

        // Créer l'objet User
        Users userToSave = new Users();
        userToSave.setNom(nomField.getText());
        userToSave.setPrenom(prenomField.getText());
        userToSave.setPassword(passwordField.getText());
        userToSave.setEmail(emailField.getText());
        userToSave.setNumeroTelephone(numeroField.getText());
        userToSave.setAdresse(adresseField.getText());

        // Gérer les CheckBoxes pour type et genre
        userToSave.setType(typeAdmin.isSelected() ? "admin" : (typeUser.isSelected() ? "client" : ""));
        userToSave.setGenre(genreMasculin.isSelected() ? "homme" : (genreFeminin.isSelected() ? "femme" : ""));

        if (user == null) {
            userService.add(userToSave); // Appel à la méthode pour ajouter un utilisateur
        } else {
            userToSave.setIdUser(user.getIdUser()); // Mise à jour de l'ID si l'utilisateur est existant
            userService.update(userToSave); // Appel à la méthode pour mettre à jour un utilisateur
        }

        showAlert("Succès", "Utilisateur enregistré avec succès.");
        closeWindow();
    }

    @FXML
    private void handleCancel() {
        closeWindow(); // Fermer la fenêtre sans enregistrer
    }

    private void closeWindow() {
        // Fermer la fenêtre après une action (enregistrer ou annuler)
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode pour valider le nom et le prénom
    private boolean isValidName(String name) {
        // Regex : uniquement des lettres et des espaces
        String regex = "^[a-zA-Z\\s]+$";
        return Pattern.matches(regex, name);
    }

    // Méthode pour valider le mot de passe
    private boolean isValidPassword(String password) {
        // Regex : au moins une majuscule et un caractère spécial
        String regex = "^(?=.*[A-Z])(?=.*[!@#$%^&*]).+$";
        return Pattern.matches(regex, password);
    }

    // Méthode pour valider l'email
    private boolean isValidEmail(String email) {
        // Regex : doit contenir un @ et un domaine valide
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    // Méthode pour valider le numéro de téléphone
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regex : exactement 8 chiffres
        String regex = "^\\d{8}$";
        return Pattern.matches(regex, phoneNumber);
    }
}