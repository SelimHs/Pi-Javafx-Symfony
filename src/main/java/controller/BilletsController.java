package controller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionEvent;

public class BilletsController {
    @javafx.fxml.FXML
    private ComboBox billetEvent;
    @javafx.fxml.FXML
    private ComboBox billetType;
    @javafx.fxml.FXML
    private TextField billetPrix;
    @javafx.fxml.FXML
    private TextField billetProprietaire;
    @javafx.fxml.FXML
    private DatePicker billetDateAchat;

    @javafx.fxml.FXML
    public void goToBilletList(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addBillet(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void goToAjoutBillet(ActionEvent actionEvent) {
    }
}
