package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class EventsMainController {
    ServiceEvent se = new ServiceEvent();
    @javafx.fxml.FXML
    private ListView eventListView;
    @FXML
    private Button delete;
    ObservableList<Event> eventNames = FXCollections.observableArrayList();

    @FXML
    public void displayEvents(javafx.event.ActionEvent actionEvent) {
        List<tn.esprit.models.Event> events = se.getAll();  // Supposons que sp.getAll() retourne une liste d'objets Event


        for (Event e : events) {
            eventNames.add(e); // Ajuste selon tes attributs
        }

        eventListView.setItems(eventNames);
    }

    @javafx.fxml.FXML
    public void goToEventList(ActionEvent actionEvent) {
    }

    @FXML
    public void goToEventList(javafx.event.ActionEvent actionEvent) {

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

    @FXML
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


    public void delete(javafx.event.ActionEvent actionEvent) {
        {
            Event eventSelectionne = (Event) eventListView.getSelectionModel().getSelectedItem();
            if (eventSelectionne != null) {
                ServiceEvent se = new ServiceEvent();
                se.delete(eventSelectionne);
                eventNames.remove(eventSelectionne);
            }
            else {
                // Afficher une alerte si aucun utilisateur n'est sélectionné
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aucune sélection");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner un utilisateur à supprimer.");
                alert.showAndWait();
            }
        }
    }
}
