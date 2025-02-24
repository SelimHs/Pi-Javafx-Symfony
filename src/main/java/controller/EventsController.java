package controller;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import tn.esprit.models.Event;

import tn.esprit.services.ServiceEvent;



public class EventsController {

    ServiceEvent se = new ServiceEvent();
    Event sx = new Event();
    @FXML
    private TextField eventNom;
    @FXML
    private TextArea eventDetails;
    @FXML
    private TextField eventEspace;
    @FXML
    private TextField eventPrix;
    @FXML
    private TextField eventVisiteurs;
    @FXML
    private DatePicker eventDate;

    @FXML
    private ListView<String> eventListView;


    //here lies my navigation
    @FXML
    public void goToEventList(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Events.fxml"));
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


    //here lies my core
    @FXML
    public void addEvent(javafx.event.ActionEvent actionEvent) {

        if (eventNom.getText() == null || eventNom.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Nom de l'événement' ne peut pas être vide.").showAndWait();
            return;
        }


        LocalDate selectedDate = eventDate.getValue();
        if (selectedDate == null) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Date de l'événement' ne peut pas être vide.").showAndWait();
            return;
        }

        if (eventPrix == null || eventPrix.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Prix' ne peut pas être vide.").showAndWait();
            return;
        }

        try {
            int prix = Integer.parseInt(eventPrix.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Prix' doit être un entier valide.").showAndWait();
            return;
        }



        if (eventVisiteurs.getText() == null || eventVisiteurs.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Nombre de visiteurs' ne peut pas être vide.").showAndWait();
            return;
        }



        if (eventDetails.getText() == null || eventDetails.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Détails' ne peut pas être vide.").showAndWait();
            return;
        }


        if (eventEspace.getText() == null || eventEspace.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Espace' ne peut pas être vide.").showAndWait();
            return;
        }

        try {
            int nbrVisiteurs = Integer.parseInt(eventVisiteurs.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Le champ 'Nombre de visiteurs' doit être un entier valide.").showAndWait();
            return;
        }

        Event e = new Event();
        selectedDate = eventDate.getValue();
        e.setNomEvent(eventNom.getText());
        e.setDate(selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        e.setPrix(Integer.parseInt(eventPrix.getText()));
        e.setNbrVisiteurs(Integer.parseInt(eventVisiteurs.getText()));
        e.setDetails(eventDetails.getText());
        e.setNomEspace(eventEspace.getText());

        se.add(e);

        eventNom.clear();
        eventDate.setValue(null);
        eventPrix.clear();
        eventVisiteurs.clear();
        eventDetails.clear();
        eventEspace.clear();
    }


}
