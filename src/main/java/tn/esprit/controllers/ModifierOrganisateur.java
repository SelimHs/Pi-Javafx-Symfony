package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Organisateur;
import tn.esprit.services.ServiceOrganisateur;

import java.io.IOException;

public class ModifierOrganisateur {

    @FXML
    private TextField nomOrganisateur, prenomOrganisateur, telefOrganisateur;
    @FXML
    private TextArea descriptionOrganisateur;
    @FXML
    private Button btnModifier, btnRetour;

    private final ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();
    private Organisateur organisateurActuel;

    /**
     * Initialize the form with existing organizer data
     */
    public void initData(Organisateur organisateur) {
        this.organisateurActuel = organisateur;

        nomOrganisateur.setText(organisateur.getNomOrg());
        prenomOrganisateur.setText(organisateur.getPrenomOrg());
        telefOrganisateur.setText(String.valueOf(organisateur.getTelef())); // Convert int to String
        descriptionOrganisateur.setText(organisateur.getDescriptionOrg());
    }

    /**
     * Modify organizer details and return to DetailEspace
     */
    public void modifierOrganisateur(ActionEvent event) {
        if (organisateurActuel == null) {
            afficherAlerte("❌ Erreur", "Aucun organisateur sélectionné !");
            return;
        }

        try {
            organisateurActuel.setNomOrg(nomOrganisateur.getText().trim());
            organisateurActuel.setPrenomOrg(prenomOrganisateur.getText().trim());
            organisateurActuel.setTelef(Integer.parseInt(telefOrganisateur.getText().trim())); // ✅ Vérification correcte
            organisateurActuel.setDescriptionOrg(descriptionOrganisateur.getText().trim());

            serviceOrganisateur.update(organisateurActuel);
            afficherAlerte("✅ Succès", "L'organisateur a été modifié avec succès !");

            retourDetailEspace(event);

        } catch (NumberFormatException e) {
            afficherAlerte("❌ Erreur", "Veuillez entrer un numéro de téléphone valide !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Alert message pop-up
     */
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Return to DetailEspace view after modification
     */
    @FXML
    public void retourDetailEspace(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/DetailEspace.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
