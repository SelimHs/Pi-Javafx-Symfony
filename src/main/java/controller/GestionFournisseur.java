package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;

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
    public void addFournisseur(ActionEvent actionEvent) {
        fournisseur f = new fournisseur();
        ServiceFournisseur sf = new ServiceFournisseur();


        f.setNomFournisseur(nomFournisseur.getText());
        f.setDescription(descriptionFournisseur.getText());
        f.setType(typeFournisseur.getText());

        if (!idFournisseur.getText().isEmpty()) {
            f.setIdFournisseur(Integer.parseInt(idFournisseur.getText()));
        }

        sf.add(f);

        nomFournisseur.clear();
        descriptionFournisseur.clear();
        typeFournisseur.clear();
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
}
