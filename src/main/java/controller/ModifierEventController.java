package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ModifierEventController {

    @FXML private TextField nomEventField, dateField, prixField, nbrVisiteursField, nomEspaceField;
    @FXML private TextArea detailsField;
    @FXML private Button saveEvent, cancelEvent;
    @FXML private ImageView eventImage;

    private Event newEvent;
    private String imagePath;

    @FXML
    public void initialize() {
        System.out.println("🔹 ModifierEventController chargé avec succès !");
        if (detailsField == null) {
            System.out.println("❌ ERREUR: detailsField est NULL, vérifie ton fichier FXML !");
        }
    }

    public void initDataEvent(Event event) {
        try {
            this.newEvent = event;

            if (nomEventField != null) nomEventField.setText(event.getNomEvent());
            if (prixField != null) prixField.setText(String.valueOf(event.getPrix()));
            if (nbrVisiteursField != null) nbrVisiteursField.setText(String.valueOf(event.getNbrVisiteurs()));
            if (nomEspaceField != null) nomEspaceField.setText(event.getNomEspace());

            if (detailsField != null) {
                detailsField.setText(event.getDetails());
            } else {
                System.out.println("⚠ detailsField est NULL !");
            }

            if (dateField != null) dateField.setText(event.getDate());

            // Charger l'image existante
            if (event.getImagePath() != null && !event.getImagePath().isEmpty()) {
                File imageFile = new File(event.getImagePath());
                if (imageFile.exists()) {
                    eventImage.setImage(new Image(imageFile.toURI().toString()));
                } else {
                    eventImage.setImage(new Image(getClass().getResourceAsStream("/images/event-placeholder.jpg")));
                }
            } else {
                eventImage.setImage(new Image(getClass().getResourceAsStream("/images/event-placeholder.jpg")));
            }
        } catch (Exception e) {
            System.out.println("❌ Erreur dans initDataEvent: " + e.getMessage());
        }
    }

    @FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            try {
                String targetDir = "C:/wamp64/www/img/";
                String fileName = UUID.randomUUID() + "_" + selectedFile.getName();
                File destination = new File(targetDir + fileName);

                if (!destination.exists()) {
                    Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }

                imagePath = destination.getAbsolutePath();
                eventImage.setImage(new Image(destination.toURI().toString()));

            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de télécharger l'image.");
            }
        }
    }

    @FXML
    public void saveEvent(ActionEvent actionEvent) {
        try {
            // Vérifier si l'événement est bien initialisé
            if (newEvent == null) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Aucun événement à modifier !");
                return;
            }

            // Vérifier chaque champ avant de l'écraser
            if (!nomEventField.getText().isEmpty()) {
                newEvent.setNomEvent(nomEventField.getText());
            }

            if (!prixField.getText().isEmpty()) {
                try {
                    int prix = Integer.parseInt(prixField.getText());
                    newEvent.setPrix(prix);
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Le prix doit être un nombre !");
                    return;
                }
            }

            if (!nbrVisiteursField.getText().isEmpty()) {
                try {
                    int nbrVisiteurs = Integer.parseInt(nbrVisiteursField.getText());
                    newEvent.setNbrVisiteurs(nbrVisiteurs);
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Le nombre de visiteurs doit être un nombre !");
                    return;
                }
            }

            if (!nomEspaceField.getText().isEmpty()) {
                newEvent.setNomEspace(nomEspaceField.getText());
            }

            if (detailsField != null && !detailsField.getText().isEmpty()) {
                newEvent.setDetails(detailsField.getText());
            }

            if (!dateField.getText().isEmpty()) {
                newEvent.setDate(dateField.getText());
            }

            // Mettre à jour uniquement si une nouvelle image est sélectionnée
            if (imagePath != null) {
                newEvent.setImagePath(imagePath);
            }

            // Mettre à jour l'événement dans la base de données
            ServiceEvent se = new ServiceEvent();
            se.update(newEvent);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "L'événement a été modifié avec succès ! 🎉");

            retourAfficherEvents(actionEvent);

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue lors de la modification !");
            System.out.println("❌ Erreur lors de la modification : " + e.getMessage());
        }
    }


    @FXML
    private void retourAfficherEvents(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) saveEvent.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Events.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
