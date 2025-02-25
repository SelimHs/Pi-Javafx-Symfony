package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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

    //here lies my navigation
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



    //here lies my initilization
    @FXML
    public void initialize() {

        List<Event> events = serviceEvent.getAll();

        billetEvent.setItems(FXCollections.observableArrayList(events));

        billetEvent.setPromptText("Sélectionner un événement");
    }

    //here lies my core
    @FXML
    public void addBillet(javafx.event.ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
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


        String proprietaire = billetProprietaire.getText();
        int prix = Integer.parseInt(billetPrix.getText());
        LocalDateTime dateAchat = LocalDateTime.now(); // Date et heure actuelles
        Billet.TypeBillet type = Billet.TypeBillet.valueOf(billetType.getValue().toString()); // Conversion du type sélectionné
        Event selectedEvent = billetEvent.getValue(); // L'événement sélectionné dans le ComboBox


        billet.setProprietaire(proprietaire);
        billet.setPrix(prix);
        billet.setDateAchat(dateAchat);
        billet.setType(type);
        billet.setEvent(selectedEvent);

        sb.add(billet);


        billetProprietaire.clear();
        billetPrix.clear();
        billetType.getSelectionModel().clearSelection();
        billetEvent.getSelectionModel().clearSelection();
    }


    @FXML
    public void buttonHoverEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));  // Une ombre douce
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);

    }
}
