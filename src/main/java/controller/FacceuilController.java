package controller;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.services.GeminiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FacceuilController {
    @FXML
    private Button btnAccueil, btnEvenements,btnEspace;
    private static final List<HBox> chatHistory = new ArrayList<>();

    @FXML
    public void initialize() {
        restoreChatHistory();
        applyHoverEffect(btnAccueil);
        applyHoverEffect(btnEvenements);
        applyHoverEffect(btnEspace);
        sendButton.setOnAction(event -> sendMessage());
        chatInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });
        scrollToBottom();
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: #F39C12; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
    }

    public void goToAcceuil(ActionEvent actionEvent) {
    }

    @FXML
    public void goToEvents(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEvents.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void goToEspaces(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEspace.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleLogout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Déconnexion");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir vous déconnecter ?");

        // Vérifier si l'utilisateur clique sur "OK"
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("🔒 Déconnexion confirmée...");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                Parent loginPage = loader.load();

                // Obtenir la scène actuelle et changer la page
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(loginPage));
                stage.show();

                System.out.println("✅ Déconnexion réussie !");
            } catch (IOException e) {
                System.out.println("❌ Erreur lors de la déconnexion : " + e.getMessage());
                e.printStackTrace();
                showAlert("Erreur de déconnexion", "Impossible d'ouvrir la page de connexion.");
            }
        }
    }

    // Méthode pour afficher une alerte en cas d'erreur
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private TextField chatInput;
    @FXML
    private Button sendButton;

    @FXML
    private VBox chatBox; // Remplace chatArea par chatBox pour afficher les messages

    private final GeminiService geminiService = new GeminiService();

    @FXML
    private void sendMessage() {
        String userInput = chatInput.getText().trim();
        if (userInput.isEmpty()) return;

        // 📌 Création du message utilisateur
        ImageView userAvatar = new ImageView(new Image("images/user.png"));
        userAvatar.setFitWidth(30);
        userAvatar.setFitHeight(30);

        Label userMessage = new Label(userInput);
        userMessage.setStyle("-fx-background-color: #dcdcdc; -fx-padding: 10px; -fx-background-radius: 10px; -fx-text-fill: black;");
        userMessage.setWrapText(true);
        userMessage.setMaxWidth(350);

        HBox userMessageContainer = new HBox(userMessage, userAvatar);
        userMessageContainer.setAlignment(Pos.CENTER_RIGHT);
        userMessageContainer.setSpacing(10);
        chatBox.getChildren().add(userMessageContainer);
        chatHistory.add(userMessageContainer); // ✅ Ajout du message utilisateur à l'historique
        scrollToBottom();

        chatInput.clear();

        // 📌 Message temporaire "L'Assistant rédige une réponse..."
        Label typingLabel = new Label("L'Assistant rédige une réponse...");
        typingLabel.setStyle("-fx-text-fill: #888888; -fx-font-style: italic;");

        HBox typingContainer = new HBox(typingLabel);
        typingContainer.setAlignment(Pos.CENTER_LEFT);
        chatBox.getChildren().add(typingContainer); // ✅ Ajout temporaire (mais PAS dans chatHistory)
        scrollToBottom();

        new Thread(() -> {
            try {
                Thread.sleep(1000); // Simule un délai
                String response = geminiService.getResponse(userInput);

                Platform.runLater(() -> {
                    chatBox.getChildren().remove(typingContainer); // ✅ Suppression immédiate AVANT sauvegarde

                    // 📌 Message du Chatbot
                    ImageView botAvatar = new ImageView(new Image("images/bot.png"));
                    botAvatar.setFitWidth(30);
                    botAvatar.setFitHeight(30);

                    Label botMessage = new Label(response);
                    botMessage.setStyle("-fx-background-color: rgba(30,30,46,0.9); -fx-padding: 10px; -fx-background-radius: 10px; -fx-text-fill: white;");
                    botMessage.setWrapText(true);
                    botMessage.setMaxWidth(350);

                    HBox botMessageContainer = new HBox(botAvatar, botMessage);
                    botMessageContainer.setAlignment(Pos.CENTER_LEFT);
                    botMessageContainer.setSpacing(10);
                    chatBox.getChildren().add(botMessageContainer);
                    chatHistory.add(botMessageContainer); // ✅ Ajout du message chatbot à l'historique

                    scrollToBottom();
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    chatBox.getChildren().remove(typingContainer); // ✅ Suppression immédiate même en cas d'erreur

                    Label errorLabel = new Label("Erreur : Impossible de contacter l'API.");
                    errorLabel.setStyle("-fx-background-color: red; -fx-padding: 10px; -fx-background-radius: 10px; -fx-text-fill: white;");

                    HBox errorContainer = new HBox(errorLabel);
                    errorContainer.setAlignment(Pos.CENTER_LEFT);

                    chatBox.getChildren().add(errorContainer);
                    chatHistory.add(errorContainer); // ✅ Seuls les messages permanents sont sauvegardés
                    scrollToBottom();
                });
                e.printStackTrace();
            }
        }).start();
    }


    @FXML
    ScrollPane chatScrollPane;
    @FXML
    // Défilement automatique amélioré
    private void scrollToBottom() {
        Platform.runLater(() -> {
            chatScrollPane.layout();
            chatScrollPane.setVvalue(chatScrollPane.getVmax());
        });
    }
    @FXML
    private void restoreChatHistory() {
        chatBox.getChildren().clear(); // Nettoie le chat avant de recharger l'historique

        for (HBox message : chatHistory) {
            if (!message.getChildren().toString().contains("L'Assistant rédige une réponse...")) {
                chatBox.getChildren().add(message); // ✅ Recharge seulement les messages valides
            }
        }
    }





}