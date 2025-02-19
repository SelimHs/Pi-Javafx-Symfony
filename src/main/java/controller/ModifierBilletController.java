package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Billet;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceBillet;

import java.io.IOException;
import java.time.LocalDateTime;

public class ModifierBilletController {

    @FXML
    private TextField billetProprietaire;

    @FXML
    private TextField billetPrix;

    @FXML
    private ComboBox<Billet.TypeBillet> billetType;

    @FXML
    private ComboBox<Event> billetEvent;

    private Billet selectedBillet;
    private ServiceBillet serviceBillet = new ServiceBillet();

    // Initialiser le formulaire avec les données du billet sélectionné
    public void initDataBillet(Billet billet) {
        this.selectedBillet = billet;
        billetProprietaire.setText(billet.getProprietaire());
        billetPrix.setText(String.valueOf(billet.getPrix()));
        billetType.setValue(billet.getType());
        billetEvent.setValue(billet.getEvent());
    }

    @FXML
    public void updateBillet(javafx.event.ActionEvent actionEvent) {
        // Validation des champs
        if (billetProprietaire.getText().isEmpty() || billetPrix.getText().isEmpty() ||
                billetType.getValue() == null || billetEvent.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        // Mise à jour des valeurs du billet
        selectedBillet.setProprietaire(billetProprietaire.getText());
        selectedBillet.setPrix(Integer.parseInt(billetPrix.getText()));
        selectedBillet.setType(billetType.getValue());
        selectedBillet.setEvent(billetEvent.getValue());

        // Mise à jour du billet dans la base de données
        serviceBillet.update(selectedBillet);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Billets.fxml"));
            Parent root = fxmlLoader.load();
            Stage stageReturn = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stageReturn.setScene(scene);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
