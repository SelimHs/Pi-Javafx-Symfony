package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Organisateur;
import tn.esprit.services.ServiceOrganisateur;

import java.io.IOException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class GestionOrganisateur {
    private static final String ACCOUNT_SID = "AC745e6096f1f338d0d79fb1e9fc399cba";
    private static final String AUTH_TOKEN = "87ac1853393e24fa6f2aef72e9240556"; // Remplace par ton vrai Auth Token
    private static final String TWILIO_PHONE_NUMBER = "+12293744841"; // Numéro Twilio valide

    @FXML private TextField nomOrganisateur, prenomOrganisateur, telef;
    @FXML private TextArea descriptionOrganisateur;
    @FXML private Button btnAjouterOrganisateur, btnRetour;

    private final ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();
    private int idEspace;

    public void initData(int idEspace) {
        this.idEspace = idEspace;
        System.out.println("✅ ID de l'espace reçu pour l'ajout : " + idEspace);
    }

    @FXML
    private void initialize() {
        if (btnAjouterOrganisateur != null) {
            btnAjouterOrganisateur.setOnAction(event -> ajouterOrganisateur());
        } else {
            System.out.println("⚠️ Erreur : btnAjouterOrganisateur est NULL !");
        }
    }

    @FXML
    private void ajouterOrganisateur() {
        System.out.println("✅ Bouton Ajouter cliqué !");

        String nom = nomOrganisateur.getText().trim();
        String prenom = prenomOrganisateur.getText().trim();
        String telStr = telef.getText().trim();
        String description = descriptionOrganisateur.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || telStr.isEmpty() || description.isEmpty()) {
            afficherAlerte("⚠️ Champs vides", "Veuillez remplir tous les champs !");
            return;
        }

        if (!telStr.matches("\\+216\\s\\d{8}")) {
            afficherAlerte("⚠️ Numéro invalide", "Le numéro de téléphone doit être au format +216 XXXXXXXX.");
            return;
        }

        if (idEspace <= 0) {
            afficherAlerte("❌ Erreur", "Aucun espace sélectionné !");
            return;
        }

        Organisateur organisateur = new Organisateur(0, nom, prenom, description, idEspace, Integer.parseInt(telStr.replace("+216 ", "")));
        serviceOrganisateur.add(organisateur);

        afficherAlerte("✅ Succès", "L'organisateur a été ajouté avec succès !");
        viderChamps();

        // Envoi du SMS de confirmation
        sendSms(telStr, nom, prenom);
    }

    private void sendSms(String phoneNumber, String firstName, String lastName) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            System.out.println("⚠️ Numéro vide. SMS non envoyé.");
            return;
        }

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try {
            String messageBody = "Cher(e) " + firstName + " " + lastName + ",\n\n"
                    + "Nous sommes ravis de vous informer que votre inscription en tant qu'organisateur dans notre système a été validée avec succès. 🎉\n\n"
                    + "Bienvenue dans notre communauté d'organisateurs d'événements ! Nous sommes impatients de collaborer avec vous pour créer des expériences inoubliables.\n\n"
                    + "📅 Restez à l'affût des prochains événements et opportunités.\n\n"
                    + "Cordialement,\n"
                    + "L'équipe de gestion des événements.";

            Message message = Message.creator(
                            new PhoneNumber(phoneNumber),
                            new PhoneNumber(TWILIO_PHONE_NUMBER),
                            messageBody)
                    .create();
            System.out.println("✅ SMS envoyé avec succès. SID: " + message.getSid());
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de l'envoi du SMS: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void viderChamps() {
        nomOrganisateur.clear();
        prenomOrganisateur.clear();
        telef.clear();
        descriptionOrganisateur.clear();
    }

    @FXML
    private void retourAfficherEspaces() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEspaces.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnRetour.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonHoverEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }
}
