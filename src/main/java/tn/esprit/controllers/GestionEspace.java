package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionEspace implements Initializable {

    @FXML
    private AnchorPane root; // Make sure this ID matches the FXML file

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ✅ Ensure root is not null to avoid errors
        if (root == null) {
            System.out.println("❌ Error: Root AnchorPane is null. Check the FXML file.");
        } else {
            System.out.println("✅ GestionEspace initialized successfully.");
        }
    }
}
