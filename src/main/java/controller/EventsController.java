package controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
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

    /*@FXML
    public void displayEvents(javafx.event.ActionEvent actionEvent) {
        labelDisplay.setText(sp.getAll().toString());
    }*/

    @Deprecated
    public void displayEvents(javafx.event.ActionEvent actionEvent) {
        List<Event> events = se.getAll();  // Supposons que sp.getAll() retourne une liste d'objets Event
        ObservableList<String> eventNames = FXCollections.observableArrayList();

        for (Event e : events) {
            eventNames.add("Event: "+ e.getNomEvent() + " -Date: " + e.getDate() + " -Prix: " + e.getPrix() + " -Lieu: " + e.getNomEspace() + " -Détails: " + e.getDetails()); // Ajuste selon tes attributs
        }

        eventListView.setItems(eventNames);
    }

    @FXML
    public void addEvent(ActionEvent actionEvent) {
        Event e = new Event();
        LocalDate selectedDate = eventDate.getValue();
        e.setNomEvent(eventNom.getText());
        e.setDate(selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        e.setPrix(Integer.parseInt(eventPrix.getText()));
        e.setNbrVisiteurs(Integer.parseInt(eventVisiteurs.getText()));
        e.setDetails(eventDetails.getText());
        e.setNomEspace(eventEspace.getText());

        se.add(e);
    }

    @FXML
    public void goToEventList(ActionEvent actionEvent) {
    }
}
