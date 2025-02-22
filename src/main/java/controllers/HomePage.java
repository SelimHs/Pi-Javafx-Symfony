package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private GridPane userCards; // Déclarer userCards comme GridPane
   // @FXML
    //private FlowPane userCards; // Utiliser FlowPane pour disposer les cartes
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
        loadUsers();
        deleteSelectedButton.setDisable(true);
        deleteSelectedButton.setOnAction(event -> deleteSelectedUsers());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchUsers(newValue));
    }

    private void loadUsers() {
        allUsers = usersService.display();
        displayUsers(allUsers);
    }

    private void displayUsers(List<Users> users) {
        userCards.getChildren().clear(); // Vider le GridPane
        selectedUsers.clear();
        deleteSelectedButton.setDisable(true);

        int columnCount = 3; // Nombre de colonnes dans le GridPane
        int row = 0;
        int column = 0;

        for (Users user : users) {
            HBox userCard = createUserCard(user); // Créer la carte d'utilisateur
            userCards.add(userCard, column, row); // Ajouter la carte au GridPane

            column++;
            if (column >= columnCount) { // Passer à la ligne suivante après avoir rempli les colonnes
                column = 0;
                row++;
            }
        }
    }

    private HBox createUserCard(Users user) {
        HBox userCard = new HBox();
        userCard.getStyleClass().add("user-card");
        userCard.setMinWidth(250);  // Largeur minimale de la carte
        userCard.setMaxWidth(250);  // Largeur maximale de la carte
        userCard.setMinHeight(200); // Hauteur de la carte
        userCard.setSpacing(15);    // Espacement entre les éléments
        userCard.setAlignment(Pos.CENTER); // Centrer le contenu de la carte

        // Labels pour afficher les informations de l'utilisateur
        Label nameLabel = new Label("Nom: " + user.getNom());
        nameLabel.getStyleClass().add("user-card-label");

        Label prenomLabel = new Label("Prénom: " + user.getPrenom());
        prenomLabel.getStyleClass().add("user-card-label");

        Label emailLabel = new Label("Email: " + user.getEmail());
        emailLabel.getStyleClass().add("user-card-label");

        // Checkbox pour sélectionner l'utilisateur
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

        // VBox pour organiser les labels et la checkbox
        VBox infoBox = new VBox(nameLabel, prenomLabel, emailLabel, selectCheckBox);
        infoBox.getStyleClass().add("user-card-info");
        infoBox.setSpacing(10); // Espacement entre les éléments dans la VBox

        userCard.getChildren().add(infoBox);
        return userCard;
    } @FXML
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
            showAlert("Erreur", "Impossible d'ouvrir le formulaire.");
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteSelectedUsers() {
        if (selectedUsers.isEmpty()) {
            showAlert("Suppression", "Aucun utilisateur sélectionné !");
            return;
        }

        selectedUsers.forEach(user -> usersService.delete(user.getIdUser()));
        selectedUsers.clear();
        showAlert("Succès", "Utilisateur(s) supprimé(s) avec succès.");
        loadUsers();
    }

    private void searchUsers(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            displayUsers(allUsers);
            return;
        }

        String searchTerm = searchText.toLowerCase().trim();
        List<Users> filteredUsers = allUsers.stream()
                .filter(user -> user.getNom().toLowerCase().contains(searchTerm) ||
                        user.getPrenom().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());

        displayUsers(filteredUsers);
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
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        prefs.putBoolean("rememberMe", false);
        prefs.remove("email");
        prefs.remove("password");

        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Connexion");
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page de connexion.");
            e.printStackTrace();
        }
    }
}