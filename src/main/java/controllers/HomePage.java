package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Users;
import service.UsersService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class HomePage {
    @FXML
    private VBox userCardsContainer;
    @FXML
    private Button deleteSelectedButton;
    @FXML
    private Button logoutButton;
    @FXML
    private TextField searchField;

    private final UsersService usersService = new UsersService();
    private final List<Users> selectedUsers = new ArrayList<>();
    private List<Users> allUsers = new ArrayList<>();

    @FXML
    public void initialize() {
        loadUsers(); // Charger tous les utilisateurs
        deleteSelectedButton.setDisable(true); // Désactiver le bouton au départ
        deleteSelectedButton.setOnAction(event -> deleteSelectedUsers()); // Lier le bouton
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchUsers(newValue));
    }

    private void loadUsers() {
        allUsers = usersService.display();
        displayUsers(allUsers);
    }

    @FXML
    private void handleAddUser() {
        openUserForm(null);
    }

    @FXML
    private void handleEditUser() {
        Users selectedUser = getSelectedUser();
        if (selectedUser != null) {
            openUserForm(selectedUser);
        } else {
            showAlert("Modification", "Veuillez sélectionner un utilisateur à modifier !");
        }
    }
    private Users getSelectedUser() {
        return selectedUsers.size() == 1 ? selectedUsers.get(0) : null;
    }

    private void openUserForm(Users user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserForm.fxml"));
            Parent root = loader.load();

            UserForm controller = loader.getController();
            controller.setUser(user);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(user == null ? "Ajouter un utilisateur" : "Modifier un utilisateur");
            stage.showAndWait();

            loadUsers();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible d'ouvrir le formulaire.");
        }
    }

    @FXML
    public void deleteSelectedUsers() {
        if (selectedUsers.isEmpty()) {
            showAlert("Suppression", "Aucun utilisateur sélectionné !");
            return;
        }

        for (Users user : selectedUsers) {
            usersService.delete(user.getIdUser());
        }

        selectedUsers.clear();
        showAlert("Succès", "Utilisateur(s) supprimé(s) avec succès.");
        loadUsers();
    }
    private void searchUsers(String searchText) {
        String searchTerm = searchText.toLowerCase().trim();
        if (allUsers.isEmpty()) return;

        List<Users> filteredUsers = allUsers.stream()
                .filter(user -> user.getNom().toLowerCase().contains(searchTerm) ||
                        user.getPrenom().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());

        displayUsers(filteredUsers);
    }

    private void displayUsers(List<Users> users) {
        userCardsContainer.getChildren().clear();
        selectedUsers.clear();
        deleteSelectedButton.setDisable(true);

        for (Users user : users) {
            userCardsContainer.getChildren().add(createUserCard(user));
        }
    }


    private VBox createUserCard(Users user) {
        VBox userCard = new VBox();
        userCard.getStyleClass().add("user-card");
        userCard.setMinWidth(200);  // Largeur minimale pour bien espacer les cartes
        userCard.setMaxWidth(200);
        userCard.setMinHeight(250); // Hauteur de la carte
        userCard.setSpacing(10);    // Espacement entre les éléments

        Label nameLabel = new Label("Nom: " + user.getNom());
        nameLabel.getStyleClass().add("user-card-label");

        Label prenomLabel = new Label("Prénom: " + user.getPrenom());
        prenomLabel.getStyleClass().add("user-card-label");

        Label emailLabel = new Label("Email: " + user.getEmail());
        emailLabel.getStyleClass().add("user-card-label");

        CheckBox selectCheckBox = new CheckBox("Sélectionner");
        selectCheckBox.getStyleClass().add("user-card-checkbox");
        selectCheckBox.setOnAction(event -> {
            if (selectCheckBox.isSelected()) {
                selectedUsers.add(user);
            } else {
                selectedUsers.remove(user);
            }
            deleteSelectedButton.setDisable(selectedUsers.isEmpty());
        });

        VBox infoBox = new VBox(nameLabel, prenomLabel, emailLabel, selectCheckBox);
        infoBox.getStyleClass().add("user-card-info");

        userCard.getChildren().add(infoBox);

        return userCard;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
@FXML
    private void handleLogout() {
        // Supprime les préférences utilisateur
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        prefs.putBoolean("rememberMe", false);
        prefs.remove("email");
        prefs.remove("password");

        // Redirige vers la page de connexion
        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Connexion");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement de la page de connexion : " + e.getMessage());
        }
    }
}