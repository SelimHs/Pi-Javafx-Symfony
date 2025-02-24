package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;

import java.io.IOException;

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
    private TextField telephoneFournisseur; // Champ pour le téléphone


    @FXML
    public void addFournisseur(ActionEvent actionEvent) {
        fournisseur f = new fournisseur();
        ServiceFournisseur sf = new ServiceFournisseur();

        f.setNomFournisseur(nomFournisseur.getText());
        f.setDescription(descriptionFournisseur.getText());
        f.setType(typeFournisseur.getText());
        f.setTelephone(telephoneFournisseur.getText()); // Ajout du téléphone

        if (!idFournisseur.getText().isEmpty()) {
            f.setIdFournisseur(Integer.parseInt(idFournisseur.getText()));
        }

        sf.add(f);

        nomFournisseur.clear();
        descriptionFournisseur.clear();
        typeFournisseur.clear();
        telephoneFournisseur.clear();
        idFournisseur.clear();
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
