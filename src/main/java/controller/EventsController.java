package controller;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
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

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tester.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public void goToAjoutEvent(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ajoutEvent.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
