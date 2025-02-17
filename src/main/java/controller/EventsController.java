package controller;

import javafx.fxml.FXML;
import java.awt.*;
import java.awt.event.ActionEvent;
import tn.esprit.services.ServiceEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EventsController {

    @FXML
    private TextField tfName;
    @FXML
    private Label labelDisplay;
    ServiceEvent sp = new ServiceEvent();

    @FXML
    public void displayEvents(javafx.event.ActionEvent actionEvent) {
        labelDisplay.setText(sp.getAll().toString());
    }

}
