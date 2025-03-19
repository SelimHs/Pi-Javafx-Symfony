package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class EventsController {

    ServiceEvent se = new ServiceEvent();
    Event sx = new Event();

    @FXML
    private TextField eventNom, eventEspace, eventPrix, eventVisiteurs;
    @FXML
    private TextArea eventDetails;
    @FXML
    private DatePicker eventDate;
    @FXML
    private ListView<String> eventListView;
    @FXML
    private ImageView eventImage;
    @FXML
    private Button btnChoisirImage;

    private String imagePath; // 📌 Stocke le chemin de l'image

    // 🔄 Navigation vers la liste des événements
    @FXML
    public void goToEventList(ActionEvent actionEvent) {
        loadScene(actionEvent, "/Events.fxml");
    }

    // 🔄 Navigation vers l’accueil
    @FXML
    public void goToAcceuil(ActionEvent actionEvent) {
        loadScene(actionEvent, "/Acceuil.fxml");
    }

    // ✅ Ajouter un événement avec gestion d'images
    @FXML
    public void addEvent(ActionEvent actionEvent) {
        if (eventNom.getText().isEmpty() || eventPrix.getText().isEmpty() ||
                eventVisiteurs.getText().isEmpty() || eventEspace.getText().isEmpty() ||
                eventDate.getValue() == null || eventDetails.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Tous les champs doivent être remplis !").showAndWait();
            return;
        }

        try {
            Integer.parseInt(eventPrix.getText());
            Integer.parseInt(eventVisiteurs.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Le prix et le nombre de visiteurs doivent être des nombres !").showAndWait();
            return;
        }

        Event e = new Event();
        e.setNomEvent(eventNom.getText());
        e.setDate(eventDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        e.setPrix(Integer.parseInt(eventPrix.getText()));
        e.setNbrVisiteurs(Integer.parseInt(eventVisiteurs.getText()));
        e.setDetails(eventDetails.getText());
        e.setNomEspace(eventEspace.getText());

        if (imagePath != null && !imagePath.isEmpty()) {
            e.setImagePath(imagePath);
        }

        se.add(e);
        // ✅ Réinitialiser les champs après l'ajout
        eventNom.clear();
        eventPrix.clear();
        eventVisiteurs.clear();
        eventEspace.clear();
        eventDate.setValue(null);
        eventDetails.clear();
        imagePath = null; // Supprimer le chemin de l'image sélectionnée
        eventImage.setImage(new Image(getClass().getResourceAsStream("/images/event-placeholder.jpg"))); // Image par défaut

        new Alert(Alert.AlertType.INFORMATION, "Événement ajouté avec succès ! 🎉").showAndWait();

    }

    // 📂 Choisir une image depuis l’ordinateur et la copier vers un dossier sécurisé
    @FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                // Générer un nom unique pour éviter les conflits
                String uniqueName = UUID.randomUUID() + "_" + selectedFile.getName();
                String targetDir = "C:/wamp64/www/img/";

                // Copier l'image vers le dossier cible
                Files.copy(
                        selectedFile.toPath(),
                        Paths.get(targetDir + uniqueName),
                        StandardCopyOption.REPLACE_EXISTING
                );

                // Stocker le chemin complet dans imagePath
                imagePath = targetDir + uniqueName;

                // ✅ Corrigé : Afficher l'image sélectionnée
                eventImage.setImage(new Image(new FileInputStream(imagePath)));

            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger l'image : " + e.getMessage());
            }
        }
    }


    // 🔄 Réinitialiser les champs après l'ajout d'un événement
    private void resetFields() {
        eventNom.clear();
        eventDate.setValue(null);
        eventPrix.clear();
        eventVisiteurs.clear();
        eventDetails.clear();
        eventEspace.clear();
        imagePath = null; // Réinitialiser le chemin de l'image
        eventImage.setImage(new Image(getClass().getResourceAsStream("/images/event-placeholder.jpg"))); // Remettre l'image par défaut
    }

    // 📌 Méthode générique pour changer de scène
    private void loadScene(ActionEvent actionEvent, String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println("❌ Erreur lors du chargement de la scène : " + e.getMessage());
        }
    }

    // 📢 Afficher une alerte
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // 🎨 Effet de survol pour les boutons
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

    // 🖱️ Effet de sortie de bouton
    @FXML
    public void buttonExitEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }
}
