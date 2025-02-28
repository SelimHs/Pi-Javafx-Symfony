package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tn.esprit.models.Billet;
import tn.esprit.services.ServiceBillet;
import tn.esprit.services.ServiceEvent;
import controller.BilletsMainController;

import javafx.scene.control.TextField;  // ✅ Bon import
import tn.esprit.models.Event;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class FrontBillet {
    @FXML
    private ComboBox eventSelection;
    @FXML
    private TextField nomClient;
    @FXML
    private ComboBox typeBillet;
    @FXML
    private Button btnAccueil, btnEvenements,btnEspace;
    @FXML
    public void initialize() {
        applyHoverEffect(btnAccueil);
        applyHoverEffect(btnEvenements);
        applyHoverEffect(btnEspace);
        loadEvents();
    }
    @FXML
    private TextField prixBillet;
    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: #F39C12; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
    }

    public void goToAcceuil(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontAcceuil.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setPrixBillet(int prix) {
        prixBillet.setText(String.valueOf(prix) + " DT");
        prixBillet.setDisable(true);
    }
    public void loadEvents(){
        ServiceEvent serviceEvent = new ServiceEvent();
        List<Event> events = serviceEvent.getAll();
        eventSelection.setItems(FXCollections.observableArrayList(events));
    }
    public void setEventSelection(Event selectedEvent) {
        loadEvents(); // Charger les événements avant de sélectionner
        eventSelection.setValue(selectedEvent); // Sélectionner l'événement
        eventSelection.setDisable(true); // 🔒 Désactiver la modification
    }

    @FXML
    public void createBilletFront(ActionEvent actionEvent) {
        BilletsMainController billetController = new BilletsMainController();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Billet billet = new Billet();
        ServiceBillet sb = new ServiceBillet();

        // ✅ Vérification des champs obligatoires
        if (nomClient.getText().isEmpty() || typeBillet.getValue() == null) {
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        Event selectedEvent = (Event) eventSelection.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            alert.setContentText("Veuillez sélectionner un événement.");
            alert.showAndWait();
            return;
        }

        // ✅ Remplir les détails du billet
        billet.setProprietaire(nomClient.getText());
        billet.setPrix(Integer.parseInt(prixBillet.getText().replace(" DT", "").trim()));
        billet.setDateAchat(LocalDateTime.now());
        billet.setType(Billet.TypeBillet.valueOf(typeBillet.getValue().toString()));
        billet.setEvent(selectedEvent);

        // ✅ Ajouter le billet et récupérer son ID
        int billetId = sb.addd(billet);
        if (billetId == -1) {
            alert.setContentText("Erreur lors de l'ajout du billet.");
            alert.showAndWait();
            return;
        }
        billet.setIdBillet(billetId); // ✅ Mise à jour de l'ID du billet

        // ✅ Vérifier que l'ID du billet n'est pas null avant d'exporter
        if (billet.getIdBillet() > 0) {
            billetController.exportBilletToPdf(billet);
        } else {
            alert.setContentText("Impossible de générer le PDF : ID du billet invalide.");
            alert.showAndWait();
        }

        // ✅ Message de confirmation
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Billet réservé !");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Votre billet pour l'événement '" + selectedEvent.getNomEvent() + "' a été réservé avec succès !");
        confirmationAlert.showAndWait();

        // ✅ Réinitialisation des champs
        nomClient.clear();
        typeBillet.getSelectionModel().clearSelection();
    }


    public void goToEvents(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontEvents.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontEspace.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}