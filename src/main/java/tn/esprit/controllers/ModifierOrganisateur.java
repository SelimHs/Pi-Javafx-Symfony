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
import tn.esprit.models.Espace;
import tn.esprit.models.Organisateur;
import tn.esprit.services.ServiceOrganisateur;
import tn.esprit.services.ServiceEspace;

import java.io.IOException;
import java.util.Optional;

public class ModifierOrganisateur {

    @FXML
    private TextField nomOrganisateur, prenomOrganisateur, telefOrganisateur;
    @FXML
    private TextArea descriptionOrganisateur;
    @FXML
    private Button btnModifier, btnRetour;

    private final ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();
    private final ServiceEspace serviceEspace = new ServiceEspace(); // Ajout pour récupérer l'espace
    private Organisateur organisateurActuel;
    private int idEspace; // ✅ Ajout de l'ID de l'espace pour revenir aux détails après modification

    /**
     * Initialize the form with existing organizer data
     */
    public void initData(Organisateur organisateur) {
        this.organisateurActuel = organisateur;
        this.idEspace = organisateur.getIdEspace(); // ✅ Stocker l'ID de l'espace

        nomOrganisateur.setText(organisateur.getNomOrg());
        prenomOrganisateur.setText(organisateur.getPrenomOrg());
        telefOrganisateur.setText(String.valueOf(organisateur.getTelef())); // Convert int to String
        descriptionOrganisateur.setText(organisateur.getDescriptionOrg());
    }

    /**
     * Modify organizer details and return to DetailEspace
     */
    @FXML
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

            retourDetailEspace(event); // ✅ Retour après modification

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailEspace.fxml"));
        Parent root = loader.load();

        DetailEspace controller = loader.getController();

        // ✅ Charger l'espace associé
        Optional<Espace> espace = serviceEspace.findById(idEspace);
        espace.ifPresent(controller::initData);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
