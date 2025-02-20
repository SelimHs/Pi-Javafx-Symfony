package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.Remise;
import tn.esprit.services.ServiceRemise;

public class GestionRemise {

    @FXML
    private TextField codePromo;
    @FXML
    private TextField description;
    @FXML
    private TextField pourcentageRemise;
    @FXML
    private DatePicker dateExpiration;
    @FXML
    private Button ajouterRemiseButton;

    @FXML
    private void addRemise(javafx.event.ActionEvent event) {
        if (codePromo.getText().isEmpty() || description.getText().isEmpty() ||
                pourcentageRemise.getText().isEmpty() || dateExpiration.getValue() == null) {
            showAlert(AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs !");
            return;
        }

        try {
            Remise remise = new Remise();
            ServiceRemise sr = new ServiceRemise();

            remise.setCodePromo(codePromo.getText());
            remise.setDescription(description.getText());
            remise.setPourcentageRemise(Double.parseDouble(pourcentageRemise.getText()));
            remise.setDateExpiration(dateExpiration.getValue().toString());

            sr.add(remise);

            showAlert(AlertType.INFORMATION, "Succès", "Remise ajoutée avec succès !");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Erreur", "Le pourcentage de remise doit être un nombre valide !");
        }
    }


    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        codePromo.clear();
        description.clear();
        pourcentageRemise.clear();
        dateExpiration.setValue(null);
    }

    public void displayRemises(ActionEvent actionEvent) {

    }
}
