package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Remise;
import tn.esprit.services.ServiceRemise;

public class ModifierRemise {

    @FXML
    private TextField codePromoField;
    @FXML
    private TextField pourcentageRemiseField;
    @FXML
    private TextField expirationDateField; // Utilisation de TextField au lieu de DatePicker
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private Remise remise;

    public void initData(Remise remise) {
        this.remise = remise;
        codePromoField.setText(remise.getCodePromo());
        pourcentageRemiseField.setText(String.valueOf(remise.getPourcentageRemise()));
        expirationDateField.setText(remise.getDateExpiration()); // Affichage direct de la date
    }

    @FXML
    private void saveRemise() {
        remise.setCodePromo(codePromoField.getText());
        remise.setPourcentageRemise(Double.parseDouble(pourcentageRemiseField.getText()));
        remise.setDateExpiration(expirationDateField.getText()); // Stockage direct de la date

        ServiceRemise serviceRemise = new ServiceRemise();
        serviceRemise.update(remise);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification Réussie");
        alert.setHeaderText("La remise a été modifiée avec succès.");
        alert.showAndWait();

        closeWindow();
    }

    @FXML
    private void cancelEdit() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) codePromoField.getScene().getWindow();
        stage.close();
    }
}
