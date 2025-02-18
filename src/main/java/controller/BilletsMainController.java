package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import tn.esprit.models.Billet;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceBillet;
import tn.esprit.services.ServiceEvent;

import java.awt.event.ActionEvent;
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

    @javafx.fxml.FXML
    public void deleteBillet(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void goToBilletList(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updateBillet(ActionEvent actionEvent) {
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
    public void goToAjoutBillet(ActionEvent actionEvent) {
    }
}
