package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Users;
import service.UsersService;

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
        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || emailField.getText().isEmpty() || numeroField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            showAlert("Erreur", "Tous les champs obligatoires doivent être remplis !");
            return;
        }

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
}