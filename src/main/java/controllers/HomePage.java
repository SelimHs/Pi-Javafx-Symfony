package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.Users;
import service.UsersService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class HomePage {
    @FXML
    private GridPane userCards; // Utiliser GridPane pour organiser les cartes
    @FXML
    private Button logoutButton;
    @FXML
    private TextField searchField;

    private final UsersService usersService = new UsersService();
    private List<Users> allUsers = new ArrayList<>();
    private int columnCount = 3; // Nombre de colonnes par défaut
    @FXML
    public void initialize() {
        loadUsers();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchUsers(newValue));

        // Écouter les changements de taille du GridPane
        userCards.widthProperty().addListener((obs, oldVal, newVal) -> {
            adjustColumnCount(newVal.doubleValue()); // Ajuster le nombre de colonnes
            displayUsers(allUsers); // Rafraîchir l'affichage
        });
    }

    private void adjustColumnCount(double width) {
        // Calculer le nombre de colonnes en fonction de la largeur disponible
        int minCardWidth = 250; // Largeur minimale d'une carte
        columnCount = (int) (width / minCardWidth);

        // Assurer un nombre minimal de colonnes
        if (columnCount < 1) {
            columnCount = 1;
        }
    }

    private void loadUsers() {
        allUsers = usersService.display();
        displayUsers(allUsers);
    }

    private void displayUsers(List<Users> users) {
        userCards.getChildren().clear(); // Vider le GridPane

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
        userCard.setMinWidth(200);  // Largeur minimale de la carte
        userCard.setMaxWidth(Double.MAX_VALUE);  // Permettre à la carte de s'étendre
        userCard.setMinHeight(200); // Hauteur minimale de la carte
        userCard.setSpacing(15);    // Espacement entre les éléments
        userCard.setAlignment(Pos.CENTER); // Centrer le contenu de la carte

        // Labels pour afficher les informations de l'utilisateur
        Label nameLabel = new Label("Nom: " + user.getNom());
        nameLabel.getStyleClass().add("user-card-label");
        nameLabel.setMaxWidth(Double.MAX_VALUE); // Permettre au label de s'étendre

        Label prenomLabel = new Label("Prénom: " + user.getPrenom());
        prenomLabel.getStyleClass().add("user-card-label");
        prenomLabel.setMaxWidth(Double.MAX_VALUE); // Permettre au label de s'étendre

        Label emailLabel = new Label("Email: " + user.getEmail());
        emailLabel.getStyleClass().add("user-card-label");
        emailLabel.setMaxWidth(Double.MAX_VALUE); // Permettre au label de s'étendre

        // Bouton Modifier
        Button editButton = new Button("Modifier");
        editButton.getStyleClass().add("action-button");
        editButton.setStyle("-fx-font-size: 12px; -fx-background-color: #f1c40f; -fx-text-fill: white; -fx-background-radius: 20px; -fx-padding: 5px 10px;");
        editButton.setOnAction(event -> handleEditUser(user)); // Gérer l'action de modification

        // Bouton Supprimer
        Button deleteButton = new Button("Supprimer");
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setStyle("-fx-font-size: 12px; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 20px; -fx-padding: 5px 10px;");
        deleteButton.setOnAction(event -> handleDeleteUser(user)); // Gérer l'action de suppression

        // HBox pour organiser les boutons
        HBox buttonBox = new HBox(editButton, deleteButton);
        buttonBox.setSpacing(10); // Espacement entre les boutons

        // VBox pour organiser les labels et les boutons
        VBox infoBox = new VBox(nameLabel, prenomLabel, emailLabel, buttonBox);
        infoBox.getStyleClass().add("user-card-info");
        infoBox.setSpacing(10); // Espacement entre les éléments dans la VBox
        infoBox.setMaxWidth(Double.MAX_VALUE); // Permettre à la VBox de s'étendre

        userCard.getChildren().add(infoBox);
        return userCard;
    }

    @FXML
    private void handleAddUser() {
        openUserForm(null);
    }

    private void handleEditUser(Users user) {
        openUserForm(user); // Ouvrir le formulaire de modification
    }

    private void handleDeleteUser(Users user) {
        // Créer une boîte de dialogue de confirmation
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation de suppression");
        confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");
        confirmationAlert.setContentText("Cette action est irréversible.");

        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Supprimer l'utilisateur si l'utilisateur confirme
                usersService.delete(user.getIdUser());

                // Supprimer l'utilisateur de la liste locale
                allUsers.remove(user);

                // Rafraîchir l'affichage
                displayUsers(allUsers);

                System.out.println("Utilisateur supprimé : " + user.getNom());
            }
        });
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

            loadUsers(); // Rafraîchir la liste après la fermeture du formulaire
        } catch (IOException e) {
            showAlert("Erreur", "Impossible d'ouvrir le formulaire.");
            e.printStackTrace();
        }
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