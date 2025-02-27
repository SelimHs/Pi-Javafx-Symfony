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
        BilletsMainController billetController = new BilletsMainController(); // ✅ Create an instance
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Billet billet = new Billet();
        ServiceBillet sb = new ServiceBillet();

        // ✅ Vérification du champ "Nom complet"
        if (nomClient.getText() == null || nomClient.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Nom complet' ne peut pas être vide.").showAndWait();
            return;
        }


        // ✅ Vérification du champ "Type de billet"
        if (typeBillet.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Type de billet' ne peut pas être vide.").showAndWait();
            return;
        }

        // ✅ Vérification du champ "Événement sélectionné"
        Event selectedEvent = (Event) eventSelection.getSelectionModel().getSelectedItem();

        // ✅ Remplir les détails du billet
        billet.setProprietaire(nomClient.getText());
        billet.setPrix(Integer.parseInt(prixBillet.getText().replace(" DT", "").trim()));
        billet.setDateAchat(LocalDateTime.now());
        billet.setType(Billet.TypeBillet.valueOf(typeBillet.getValue().toString()));
        billet.setEvent(selectedEvent);

        // ✅ Ajouter le billet en base de données
        sb.add(billet);
        int billetId = sb.getBilletId(billet.getProprietaire(), billet.getPrix(), billet.getDateAchat(), billet.getType(), billet.getEvent().getIdEvent());

// Set the ID in the billet object
        billet.setIdBillet(billetId);

// Now export to PDF with the correct ID
        billetController.exportBilletToPdf(billet);

        // ✅ Afficher un message de confirmation
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Billet réservé !");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Votre billet pour l'événement '" + selectedEvent.getNomEvent() + "' a été réservé avec succès !");
        confirmationAlert.showAndWait();

        // ✅ Réinitialiser les champs après ajout
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
