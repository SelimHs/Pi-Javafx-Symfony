package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import tn.esprit.controllers.Clock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.Users;
import tn.esprit.services.UsersService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class HomePage {
    @FXML
    private GridPane userCards;
    @FXML
    private ComboBox<String> sortOrderComboBox;
    @FXML
    private Button logoutButton;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private Pane clockPane; // Référence au Pane de l'horloge

    private final UsersService usersService = new UsersService();
    private List<Users> allUsers = new ArrayList<>();
    private int columnCount = 3;

    @FXML
    public void initialize() {
        // Ajouter l'horloge murale au Pane
        Clock clock = new Clock();
        clockPane.getChildren().add(clock);

        loadUsers();

        // Initialiser le ComboBox de filtrage avec les options souhaitées
        filterComboBox.getItems().addAll("Nom", "Prénom", "Adresse");
        filterComboBox.setValue("Nom");

        // Initialiser le ComboBox de tri
        sortOrderComboBox.getItems().addAll("Croissant", "Décroissant");
        sortOrderComboBox.setValue("Croissant");

        // Écouter les changements dans les ComboBox et le champ de recherche
        filterComboBox.valueProperty().addListener((obs, oldVal, newVal) -> applyFilter());
        sortOrderComboBox.valueProperty().addListener((obs, oldVal, newVal) -> applyFilter());
        searchField.textProperty().addListener((obs, oldVal, newVal) -> applyFilter());

        // Écouter les changements de taille du GridPane
        userCards.widthProperty().addListener((obs, oldVal, newVal) -> {
            adjustColumnCount(newVal.doubleValue());
            displayUsers(allUsers);
        });
    }
    private void adjustColumnCount(double width) {
        int minCardWidth = 250;
        columnCount = (int) (width / minCardWidth);

        if (columnCount < 1) {
            columnCount = 1;
        }
    }

    private void loadUsers() {
        allUsers = usersService.display();
        displayUsers(allUsers);
    }

    private void displayUsers(List<Users> users) {
        userCards.getChildren().clear();

        int row = 0;
        int column = 0;

        for (Users user : users) {
            HBox userCard = createUserCard(user);
            userCards.add(userCard, column, row);

            column++;
            if (column >= columnCount) {
                column = 0;
                row++;
            }
        }
    }

    private HBox createUserCard(Users user) {
        HBox userCard = new HBox();
        userCard.getStyleClass().add("user-card");
        userCard.setMinWidth(200);
        userCard.setMaxWidth(Double.MAX_VALUE);
        userCard.setMinHeight(150);
        userCard.setSpacing(15);
        userCard.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Nom: " + user.getNom());
        nameLabel.getStyleClass().add("user-card-label");
        nameLabel.setMaxWidth(Double.MAX_VALUE);

        Label prenomLabel = new Label("Prénom: " + user.getPrenom());
        prenomLabel.getStyleClass().add("user-card-label");
        prenomLabel.setMaxWidth(Double.MAX_VALUE);

        Label emailLabel = new Label("Email: " + user.getEmail());
        emailLabel.getStyleClass().add("user-card-label");
        emailLabel.setMaxWidth(Double.MAX_VALUE);

        // 📌 Conteneur des icônes
        HBox buttonBox = new HBox(10);
        buttonBox.setStyle("-fx-alignment: center-right;");

        // 👁️ Icône Voir Détails
        Button detailsButton = new Button();
        detailsButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        detailsButton.setOnAction(event -> showUserDetails(user));

        ImageView detailsIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/details-icon.png")));
        detailsIcon.setFitWidth(18);
        detailsIcon.setFitHeight(18);
        detailsButton.setGraphic(detailsIcon);

        // ✏️ Icône Modifier
        Button editButton = new Button();
        editButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        editButton.setOnAction(event -> handleEditUser(user));

        ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/edit-icon.png")));
        editIcon.setFitWidth(18);
        editIcon.setFitHeight(18);
        editButton.setGraphic(editIcon);

        // 🗑️ Icône Supprimer
        Button deleteButton = new Button();
        deleteButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        deleteButton.setOnAction(event -> handleDeleteUser(user));

        ImageView trashIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/trash-icon.png")));
        trashIcon.setFitWidth(18);
        trashIcon.setFitHeight(18);
        deleteButton.setGraphic(trashIcon);

        // Ajout des icônes au conteneur
        buttonBox.getChildren().addAll(detailsButton, editButton, deleteButton);

        VBox infoBox = new VBox(nameLabel, prenomLabel, emailLabel, buttonBox);
        infoBox.getStyleClass().add("user-card-info");
        infoBox.setSpacing(10);
        infoBox.setMaxWidth(Double.MAX_VALUE);

        userCard.getChildren().add(infoBox);
        return userCard;
    }

    private void showUserDetails(Users user) {
        Alert detailsAlert = new Alert(Alert.AlertType.INFORMATION);
        detailsAlert.setTitle("Détails de l'utilisateur");
        detailsAlert.setHeaderText("Informations complètes de " + user.getNom() + " " + user.getPrenom());

        VBox content = new VBox(10);
        content.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label("Nom: " + user.getNom());
        Label prenomLabel = new Label("Prénom: " + user.getPrenom());
        Label emailLabel = new Label("Email: " + user.getEmail());
        Label phoneLabel = new Label("Téléphone: " + user.getNumeroTelephone());
        Label addressLabel = new Label("Adresse: " + user.getAdresse());
        Label typeLabel = new Label("Type: " + user.getType());
        Label genreLabel = new Label("Genre: " + user.getGenre());

        content.getChildren().addAll(nameLabel, prenomLabel, emailLabel, phoneLabel, addressLabel, typeLabel, genreLabel);

        detailsAlert.getDialogPane().setContent(content);
        detailsAlert.showAndWait();
    }

    @FXML
    private void handleAddUser() {
        openUserForm(null);
    }

    private void handleEditUser(Users user) {
        openUserForm(user);
    }

    private void handleDeleteUser(Users user) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation de suppression");
        confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");
        confirmationAlert.setContentText("Cette action est irréversible.");

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                usersService.delete(user.getIdUser());
                allUsers.remove(user);
                displayUsers(allUsers);
                System.out.println("Utilisateur supprimé : " + user.getNom());
            }
        });
    }

    private void openUserForm(Users user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserForm.fxml"));
            Parent root = loader.load();

            tn.esprit.controllers.UserForm controller = loader.getController();
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

    private void applyFilter() {
        String selectedFilter = filterComboBox.getValue();
        String searchText = searchField.getText().toLowerCase().trim();
        String sortOrder = sortOrderComboBox.getValue();

        List<Users> filteredUsers = allUsers.stream()
                .filter(user -> {
                    switch (selectedFilter) {
                        case "Nom":
                            return user.getNom().toLowerCase().contains(searchText);
                        case "Prénom":
                            return user.getPrenom().toLowerCase().contains(searchText);
                        case "Adresse":
                            return user.getAdresse().toLowerCase().contains(searchText);
                        default:
                            return true;
                    }
                })
                .collect(Collectors.toList());

        if (sortOrder != null) {
            Comparator<Users> comparator = null;

            switch (selectedFilter) {
                case "Nom":
                    comparator = Comparator.comparing(Users::getNom, String.CASE_INSENSITIVE_ORDER);
                    break;
                case "Prénom":
                    comparator = Comparator.comparing(Users::getPrenom, String.CASE_INSENSITIVE_ORDER);
                    break;
                case "Adresse":
                    comparator = Comparator.comparing(Users::getAdresse, String.CASE_INSENSITIVE_ORDER);
                    break;
            }

            if (comparator != null) {
                if ("Décroissant".equals(sortOrder)) {
                    comparator = comparator.reversed();
                }
                filteredUsers.sort(comparator);
            }
        }

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

    @FXML
    public void buttonHoverEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }

    public void goToAcceuil(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
    }
}