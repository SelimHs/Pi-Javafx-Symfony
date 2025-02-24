package controller;

import com.twilio.exception.ApiException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;

import java.io.IOException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class GestionFournisseur {

    @FXML
    private TextField idFournisseur;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField nomFournisseur;
    @FXML
    private TextField typeFournisseur;
    @FXML
    private TextField descriptionFournisseur;
    @FXML
    private TextField telephoneFournisseur;

    // Twilio credentials
    private static final String ACCOUNT_SID = "AC805dd6d0c1103969ade5ad32ff09b34a";
    private static final String AUTH_TOKEN = "a229d0549855331bcd1182b2cfcc76f0"; // Replace with your actual Auth Token
    private static final String TWILIO_PHONE_NUMBER = "+17622525081"; // Replace with your Twilio phone number

    @FXML
    public void addFournisseur(ActionEvent actionEvent) {
        fournisseur f = new fournisseur();
        ServiceFournisseur sf = new ServiceFournisseur();

        f.setNomFournisseur(nomFournisseur.getText());
        f.setDescription(descriptionFournisseur.getText());
        f.setType(typeFournisseur.getText());
        f.setTelephone(telephoneFournisseur.getText());

        if (!idFournisseur.getText().isEmpty()) {
            f.setIdFournisseur(Integer.parseInt(idFournisseur.getText()));
        }

        sf.add(f);

        // Send SMS
        sendSms(telephoneFournisseur.getText(), nomFournisseur.getText());

        nomFournisseur.clear();
        descriptionFournisseur.clear();
        typeFournisseur.clear();
        telephoneFournisseur.clear();
        idFournisseur.clear();
    }

    private void sendSms(String phoneNumber, String fournisseurName) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            System.out.println("Phone number is empty. SMS not sent.");
            return;
        }
        if (fournisseurName == null || fournisseurName.isEmpty()) {
            System.out.println("Fournisseur name is empty. SMS not sent.");
            return;
        }

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try {
            String messageBody = "Cher fournisseur " + fournisseurName + ",\n\n" +
                    "Nous sommes ravis de vous informer que vous avez été ajouté avec succès à notre système en tant que partenaire privilégié.\n\n" +
                    "Bienvenue dans notre réseau de fournisseurs de confiance !";

            Message message = Message.creator(
                            new PhoneNumber(phoneNumber),
                            new PhoneNumber(TWILIO_PHONE_NUMBER),
                            messageBody)
                    .create();
            System.out.println("SMS sent successfully. SID: " + message.getSid());
        } catch (ApiException e) {
            System.out.println("Error sending SMS: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Deprecated
    void onSupprimer(ActionEvent event) {
    }

    @Deprecated
    void onModifier(ActionEvent event) {
    }

    @Deprecated
    void onAfficher(ActionEvent event) {
    }

    @FXML
    public void goToPrincipaleFournisseur(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PrincipaleFournisseur.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Liste des Fournisseurs");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("⚠️ Erreur lors du chargement de la page PrincipaleFournisseur.fxml !");
        }
    }

    public void goToGestionFournisseur(ActionEvent actionEvent) {
    }

    public void goToAcceuil(ActionEvent actionEvent) {
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
}