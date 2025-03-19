package controller;

import com.twilio.exception.ApiException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

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
    @FXML
    private ImageView fournisseurImage; // Afficher l'image
    private String imagePath;           // Chemin de l'image sélectionnée

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

        // ✅ Assigner l'idFournisseur si nécessaire
        if (!idFournisseur.getText().isEmpty()) {
            f.setIdFournisseur(Integer.parseInt(idFournisseur.getText()));
        }

        // ✅ Assigner l'image si elle a été choisie
        if (imagePath != null && !imagePath.isEmpty()) {
            f.setImagePath(imagePath);
        }

        // Insertion en base
        sf.add(f);

        // Envoi du SMS (optionnel)
        sendSms(telephoneFournisseur.getText(), nomFournisseur.getText());

        // Réinitialiser les champs
        nomFournisseur.clear();
        descriptionFournisseur.clear();
        typeFournisseur.clear();
        telephoneFournisseur.clear();
        idFournisseur.clear();
        fournisseurImage.setImage(null); // Retirer l'image
        imagePath = null;
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

    @FXML
    public void buttonHoverEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));  // Une ombre douce
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }
    @FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image du fournisseur");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            try {
                String targetDir = "C:/wamp64/www/img/";
                String fileName = UUID.randomUUID() + "_" + selectedFile.getName();
                File destination = new File(targetDir + fileName);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                imagePath = destination.getAbsolutePath();
                fournisseurImage.setImage(new Image(destination.toURI().toString()));

            } catch (IOException e) {
                System.out.println("Erreur lors de la copie de l'image : " + e.getMessage());
            }
        }
    }

}