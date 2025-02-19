package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tn.esprit.models.Billet;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceBillet;
import tn.esprit.services.ServiceEvent;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class BilletsMainController {

    ServiceBillet sb = new ServiceBillet();

    @javafx.fxml.FXML
    private ListView billetListView;
    ObservableList<Billet> billetNames = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    private Button modifierBouton;
    @javafx.fxml.FXML
    private Button deleteBouton;

    @FXML
    public void goToAjoutBillet(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ajoutBillet.fxml"));
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



    @javafx.fxml.FXML
    public void displayBillets(javafx.event.ActionEvent actionEvent) {
        List<Billet> events = sb.getAll();  // Supposons que sp.getAll() retourne une liste d'objets Event
        billetListView.getItems().clear();

        for (Billet b : events) {
            billetNames.add(b);
        }
        billetListView.setItems(billetNames);
    }


    @javafx.fxml.FXML
    public void deleteBillet(ActionEvent actionEvent) {
    }


    @javafx.fxml.FXML
    public void updateBillet(ActionEvent actionEvent) {
    }
}
