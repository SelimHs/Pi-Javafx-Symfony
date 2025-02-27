package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tn.esprit.services.ServiceEvent;

import javafx.scene.control.TextField;  // ✅ Bon import
import tn.esprit.models.Event;

import java.io.IOException;
import java.util.List;

public class FrontBillet {
    @FXML
    private ComboBox eventSelection;
    @FXML
    public void initialize() {
    loadEvents();
    }
    @FXML
    private TextField prixBillet;

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
    public void setPrixBillet(double prix) {
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
}
