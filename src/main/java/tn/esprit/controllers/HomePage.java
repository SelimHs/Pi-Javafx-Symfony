package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Users;
import tn.esprit.services.UsersService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        Label nameLabel = new Label("Nom: " + user.getNom());
        nameLabel.getStyleClass().add("user-card-label");

        Label prenomLabel = new Label("Prénom: " + user.getPrenom());
        prenomLabel.getStyleClass().add("user-card-label");

        Label emailLabel = new Label("Email: " + user.getEmail());
        emailLabel.getStyleClass().add("user-card-label");

        Label phoneLabel = new Label("Téléphone: " + user.getNumeroTelephone());
        phoneLabel.getStyleClass().add("user-card-label");

        Label addressLabel = new Label("Adresse: " + user.getAdresse());
        addressLabel.getStyleClass().add("user-card-label");

        Label typeLabel = new Label("Type: " + user.getType());
        typeLabel.getStyleClass().add("user-card-label");

        Label genreLabel = new Label("Genre: " + user.getGenre());
        genreLabel.getStyleClass().add("user-card-label");

        CheckBox selectCheckBox = new CheckBox("Sélectionner");
        selectCheckBox.getStyleClass().add("user-card-checkbox");
        selectCheckBox.setIndeterminate(false);
        selectCheckBox.setOnAction(event -> {
            if (selectCheckBox.isSelected()) {
                selectedUsers.add(user);
            } else {
                selectedUsers.remove(user);
            }
            deleteSelectedButton.setDisable(selectedUsers.isEmpty());
        });

        VBox infoBox = new VBox(nameLabel, prenomLabel, emailLabel, phoneLabel, addressLabel, typeLabel, genreLabel, selectCheckBox);
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Page de connexion");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de déconnecter l'utilisateur.");
        }
    }

    @FXML
    void goToAcceuil(ActionEvent event) {
        try {
            // Charger la page d'accueil (Acceuil.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
            AnchorPane acceuilPage = loader.load();

            // Obtenir la scène actuelle et changer la page
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(acceuilPage));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur de navigation", "Impossible d'ouvrir la page d'accueil.");
        }
    }

    @FXML
    public void buttonHoverEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));  // Une ombre douce
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }
}