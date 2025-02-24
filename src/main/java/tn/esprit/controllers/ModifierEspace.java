package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.services.ServiceEspace;

import java.io.IOException;
import java.util.Optional;

public class ModifierEspace {

    @FXML
    private TextField nomEspace, adresseEspace, capaciteEspace, prixEspace, typeEspace;
    @FXML
    private ComboBox<String> disponibiliteEspace;
    @FXML
    private Button btnModifier, btnRetour;

    private final ServiceEspace serviceEspace = new ServiceEspace();
    private Espace espaceActuel;

    public void initData(Espace espace) {
        this.espaceActuel = espace;

        nomEspace.setText(espace.getNomEspace());
        adresseEspace.setText(espace.getAdresse());
        capaciteEspace.setText(String.valueOf(espace.getCapacite()));
        prixEspace.setText(String.valueOf(espace.getPrix()));
        typeEspace.setText(espace.getTypeEspace());
        disponibiliteEspace.setValue(espace.getDisponibilite());
    }

    @FXML
    public void modifierEspace(ActionEvent event) {
        if (espaceActuel == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucun espace sélectionné !");
            alert.show();
            return;
        }

        try {
            espaceActuel.setNomEspace(nomEspace.getText());
            espaceActuel.setAdresse(adresseEspace.getText());
            espaceActuel.setCapacite(Integer.parseInt(capaciteEspace.getText()));
            espaceActuel.setPrix(Float.parseFloat(prixEspace.getText()));
            espaceActuel.setTypeEspace(typeEspace.getText());
            espaceActuel.setDisponibilite(disponibiliteEspace.getValue());

            serviceEspace.update(Optional.ofNullable(espaceActuel));

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "L'espace a été modifié avec succès !");
            alert.show();

            retourAfficherEspaces(event);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez entrer des valeurs valides.");
            alert.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void retourAfficherEspaces(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AfficherEspaces.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
