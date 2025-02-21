package controller;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.io.IOException;


public class ModifierEventController {
    @FXML
    private TextField nomEventField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField prixField;

    @FXML
    private TextField nbrVisiteursField;

    @FXML
    private TextField nomEspaceField;

    @FXML
    private TextArea detailsField;
    private Event newEvent;

    //put selected event data in new form
    public void initDataEvent(Event event) {
        this.newEvent = event;
        nomEventField.setText(event.getNomEvent());
        prixField.setText(String.valueOf(event.getPrix()));
        nbrVisiteursField.setText(String.valueOf(event.getNbrVisiteurs()));
        nomEspaceField.setText(event.getNomEspace());
        detailsField.setText(event.getDetails());
        dateField.setText(event.getDate());
    }

    @FXML
    public void saveEvent(javafx.event.ActionEvent actionEvent) {
        // Validate fields before saving
        if (nomEventField.getText().isEmpty() || prixField.getText().isEmpty() ||
                nbrVisiteursField.getText().isEmpty() || nomEspaceField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        if (nomEventField.getText() == null || nomEventField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Nom de l'événement' ne peut pas être vide.").showAndWait();
            return;
        }


        String selectedDate = dateField.getText().toString();
        if (selectedDate == null) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Date de l'événement' ne peut pas être vide.").showAndWait();
            return;
        }

        if (prixField == null || prixField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Prix' ne peut pas être vide.").showAndWait();
            return;
        }

        try {
            int prix = Integer.parseInt(prixField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Prix' doit être un entier valide.").showAndWait();
            return;
        }



        if (nbrVisiteursField.getText() == null || nbrVisiteursField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Nombre de visiteurs' ne peut pas être vide.").showAndWait();
            return;
        }



        if (detailsField.getText() == null || detailsField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Détails' ne peut pas être vide.").showAndWait();
            return;
        }


        if (nomEspaceField.getText() == null || nomEspaceField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Espace' ne peut pas être vide.").showAndWait();
            return;
        }

        try {
            int nbrVisiteurs = Integer.parseInt(nbrVisiteursField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Nombre de visiteurs' doit être un entier valide.").showAndWait();
            return;
        }



        newEvent.setNomEvent(nomEventField.getText());
        newEvent.setPrix(Integer.parseInt(prixField.getText()));
        newEvent.setNbrVisiteurs(Integer.parseInt(nbrVisiteursField.getText()));
        newEvent.setNomEspace(nomEspaceField.getText());
        newEvent.setDetails(detailsField.getText());
        newEvent.setDate(dateField.getText());


        ServiceEvent se = new ServiceEvent();
        se.update(newEvent);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Events.fxml"));
            Parent root = fxmlLoader.load();
            Stage stageReturn = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stageReturn.setScene(scene);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}