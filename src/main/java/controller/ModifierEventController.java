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
    private Event newEvent; // This will store the event being edited

    // Method to initialize the form with the selected event data
    public void initDataEvent(Event event) {
        this.newEvent = event;
        nomEventField.setText(event.getNomEvent());
        prixField.setText(String.valueOf(event.getPrix()));
        nbrVisiteursField.setText(String.valueOf(event.getNbrVisiteurs()));
        nomEspaceField.setText(event.getNomEspace());
        detailsField.setText(event.getDetails());
        dateField.setText(event.getDate());
    }

    // Method to save the updated event
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

        // Update the event data from the form
        newEvent.setNomEvent(nomEventField.getText());
        newEvent.setPrix(Integer.parseInt(prixField.getText()));
        newEvent.setNbrVisiteurs(Integer.parseInt(nbrVisiteursField.getText()));
        newEvent.setNomEspace(nomEspaceField.getText());
        newEvent.setDetails(detailsField.getText());
        newEvent.setDate(dateField.getText());

        // Update the event in the database
        ServiceEvent se = new ServiceEvent();
        se.update(newEvent); // Call the update method in your ServiceEvent class

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