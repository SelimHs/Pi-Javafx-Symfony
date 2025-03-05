package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;

import java.io.IOException;

public class ModifierFournisseur {

    @FXML
    private TextField nomFournisseur, descriptionFournisseur, typeFournisseur,telephoneFournisseur;
    @FXML
    private Button btnModifier, btnRetour;

    private final ServiceFournisseur fournisseurService = new ServiceFournisseur();
    private fournisseur fournisseurActuel;

    /**
     * Initialise les champs avec les données du fournisseur sélectionné.
     */
    public void initData(fournisseur fournisseur) {
        if (fournisseur == null) {
            System.out.println("⚠ Erreur: Aucun fournisseur sélectionné !");
            return;
        }

        this.fournisseurActuel = fournisseur;

        nomFournisseur.setText(fournisseur.getNomFournisseur());
        descriptionFournisseur.setText(fournisseur.getDescription());
        typeFournisseur.setText(fournisseur.getType());
        telephoneFournisseur.setText(fournisseur.getTelephone()); // Ajout du téléphone

        System.out.println("✅ Données du fournisseur chargées : " + fournisseur);
    }


    /**
     * Met à jour les informations du fournisseur dans la base de données.
     */
    @FXML
    public void modifierFournisseur(ActionEvent event) {
        if (fournisseurActuel == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucun fournisseur sélectionné !");
            alert.show();
            return;
        }

        // Mise à jour des valeurs
        fournisseurActuel.setNomFournisseur(nomFournisseur.getText());
        fournisseurActuel.setDescription(descriptionFournisseur.getText());
        fournisseurActuel.setType(typeFournisseur.getText());
        fournisseurActuel.setTelephone(telephoneFournisseur.getText()); // Mise à jour du téléphone

        // Mise à jour dans la base de données
        fournisseurService.update(fournisseurActuel);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le fournisseur a été modifié avec succès !");
        alert.show();

        // Retourner à l'affichage des fournisseurs après modification
        retourAfficherFournisseurs(event);
    }


    /**
     * Retourne à l'affichage des fournisseurs après modification.
     */
    @FXML
    public void retourAfficherFournisseurs(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principalefournisseur.fxml"));
            Parent root = loader.load();

            // Obtenir le stage actuel à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Liste des Fournisseurs");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("⚠ Erreur: Impossible de charger FournisseurMain.fxml !");
        }
    }


}
