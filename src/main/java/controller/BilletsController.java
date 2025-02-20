package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Billet;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceBillet;
import tn.esprit.services.ServiceEvent;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class BilletsController {
    private ServiceEvent serviceEvent = new ServiceEvent();

    @FXML
    private ComboBox<Event> billetEvent;
    @javafx.fxml.FXML
    private ComboBox billetType;
    @javafx.fxml.FXML
    private TextField billetPrix;
    @javafx.fxml.FXML
    private TextField billetProprietaire;
    @javafx.fxml.FXML
    private DatePicker billetDateAchat;

    @javafx.fxml.FXML
    public void goToBilletList(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Billets.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @javafx.fxml.FXML
    public void goToAcceuil(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }




    @FXML
    public void initialize() {
        // Récupérer la liste des événements depuis la base de données
        List<Event> events = serviceEvent.getAll();

        // Mettre à jour la ComboBox avec les événements
        billetEvent.setItems(FXCollections.observableArrayList(events));

        // Optionnel : si tu veux afficher un texte dans la ComboBox (par exemple "Sélectionner un événement")
        billetEvent.setPromptText("Sélectionner un événement");
    }

    @FXML
    public void addBillet(javafx.event.ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // Création du nouvel objet Billet
        Billet billet = new Billet();
        ServiceBillet sb = new ServiceBillet();



        if (billetProprietaire.getText() == null || billetProprietaire.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Propriétaire' ne peut pas être vide.").showAndWait();
            return;
        }

        if (billetPrix == null || billetPrix.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Prix' ne peut pas être vide.").showAndWait();
            return;
        }

        String prixText = billetPrix.getText();
        if (prixText == null || prixText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Prix' ne peut pas être vide.").showAndWait();
            return;
        }
        try {
            int prix = Integer.parseInt(billetPrix.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Prix' doit être un entier valide.").showAndWait();
            return;
        }

        if (billetType.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Type' ne peut pas être vide.").showAndWait();
            return;
        }

        if (billetEvent.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "L'événement sélectionné ne peut pas être vide.").showAndWait();
            return;
        }

        // Récupération des valeurs saisies dans les champs
        String proprietaire = billetProprietaire.getText();
        int prix = Integer.parseInt(billetPrix.getText());
        LocalDateTime dateAchat = LocalDateTime.now(); // Date et heure actuelles
        Billet.TypeBillet type = Billet.TypeBillet.valueOf(billetType.getValue().toString()); // Conversion du type sélectionné
        Event selectedEvent = billetEvent.getValue(); // L'événement sélectionné dans le ComboBox

        // Attribution des valeurs à l'objet Billet
        billet.setProprietaire(proprietaire);
        billet.setPrix(prix);
        billet.setDateAchat(dateAchat);
        billet.setType(type);
        billet.setEvent(selectedEvent);

        sb.add(billet);

        // Réinitialiser les champs après l'ajout
        billetProprietaire.clear();
        billetPrix.clear();
        billetType.getSelectionModel().clearSelection();
        billetEvent.getSelectionModel().clearSelection();
    }



}
