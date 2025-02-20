package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Billet;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceBillet;
import tn.esprit.services.ServiceEvent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ModifierBilletController {

    @FXML
    private TextField billetProprietaire;

    @FXML
    private TextField billetPrix;

    @FXML
    private ComboBox billetType;

    @FXML
    private ComboBox<Event> billetEvent;

    private Billet selectedBillet;
    private ServiceBillet serviceBillet = new ServiceBillet();
    private ServiceEvent serviceEvent = new ServiceEvent();

    @FXML
    public void initialize() {
        List<Event> events = serviceEvent.getAll();

        // Mettre à jour la ComboBox avec les événements
        billetEvent.setItems(FXCollections.observableArrayList(events));

        // Optionnel : si tu veux afficher un texte dans la ComboBox (par exemple "Sélectionner un événement")
        billetEvent.setPromptText("Sélectionner un événement");
    }

    // Initialiser le formulaire avec les données du billet sélectionné
    public void initDataBillet(Billet billet) {
        this.selectedBillet = billet;
        billetProprietaire.setText(billet.getProprietaire());
        billetPrix.setText(String.valueOf(billet.getPrix()));
        billetType.setValue(billet.getType());
        billetEvent.setValue(billet.getEvent());
    }

    @FXML
    public void updateBillet(javafx.event.ActionEvent actionEvent) {


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
        // Mise à jour des valeurs du billet
        selectedBillet.setProprietaire(billetProprietaire.getText());
        selectedBillet.setPrix(Integer.parseInt(billetPrix.getText()));
        selectedBillet.setType(Billet.TypeBillet.valueOf(billetType.getValue().toString()));
        selectedBillet.setEvent(billetEvent.getValue());

        // Mise à jour du billet dans la base de données
        serviceBillet.update(selectedBillet);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Billets.fxml"));
            Parent root = fxmlLoader.load();
            Stage stageReturn = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stageReturn.setScene(scene);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
