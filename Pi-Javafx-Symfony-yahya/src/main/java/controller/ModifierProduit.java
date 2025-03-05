package controller;

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
import tn.esprit.models.Produit;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;
import tn.esprit.services.ServiceProduit;

import java.io.IOException;
import java.util.List;

public class ModifierProduit {

    @FXML
    private TextField nomProduit, prixProduit, descriptionProduit, quantiteProduit;
    @FXML
    private ComboBox<String> categorieProduit;
    @FXML
    private ComboBox<String> fournisseurProduit;
    @FXML
    private Button btnModifier, btnRetour;

    private final ServiceProduit serviceProduit = new ServiceProduit();
    private final ServiceFournisseur serviceFournisseur = new ServiceFournisseur();
    private Produit produitActuel;

    public void initData(Produit produit) {
        if (produit == null) {
            System.out.println("⚠ Erreur: Aucun produit sélectionné !");
            return;
        }

        this.produitActuel = produit;
        nomProduit.setText(produit.getNomProduit());
        prixProduit.setText(String.valueOf(produit.getPrixProduit()));
        descriptionProduit.setText(produit.getDescription());
        quantiteProduit.setText(String.valueOf(produit.getQuantite()));
        categorieProduit.setValue(produit.getCategorie().name());

        // Charger la liste des fournisseurs
        List<fournisseur> fournisseurs = serviceFournisseur.getAll();
        fournisseurProduit.getItems().clear();
        for (fournisseur f : fournisseurs) {
            fournisseurProduit.getItems().add(f.getNomFournisseur());
        }

        if (produit.getFournisseur() != null) {
            fournisseurProduit.setValue(produit.getFournisseur().getNomFournisseur());
        }
    }

    @FXML
    public void modifierProduit(ActionEvent event) {
        if (produitActuel == null) {
            new Alert(Alert.AlertType.ERROR, "Aucun produit sélectionné !").show();
            return;
        }

        try {
            produitActuel.setNomProduit(nomProduit.getText());
            produitActuel.setPrixProduit(Integer.parseInt(prixProduit.getText()));
            produitActuel.setDescription(descriptionProduit.getText());
            produitActuel.setQuantite(Integer.parseInt(quantiteProduit.getText()));
            produitActuel.setCategorie(Produit.CategorieProduit.valueOf(categorieProduit.getValue()));

            // Vérifier et assigner le fournisseur
            fournisseur selectedFournisseur = serviceFournisseur.getFournisseurFromDatabase(fournisseurProduit.getValue());
            if (selectedFournisseur == null) {
                new Alert(Alert.AlertType.ERROR, "Fournisseur introuvable !").show();
                return;
            }
            produitActuel.setFournisseur(selectedFournisseur);

            serviceProduit.update(produitActuel);
            new Alert(Alert.AlertType.INFORMATION, "Le produit a été modifié avec succès !").show();

            retourAfficherProduits(event);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Veuillez entrer des valeurs valides pour le prix et la quantité.").show();
        }
    }

    @FXML
    public void retourAfficherProduits(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PrincipaleProduits.fxml")); // Chemin correct !
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "⚠ Erreur: Impossible de charger ProduitMain.fxml !").show();
        }
    }
}
