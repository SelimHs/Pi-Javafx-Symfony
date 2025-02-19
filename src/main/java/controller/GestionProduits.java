package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import tn.esprit.models.Produit;
import javafx.scene.control.TextField;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;
import tn.esprit.services.ServiceProduit;

import java.awt.*;
import java.awt.event.ActionEvent;

public class GestionProduits {
    @javafx.fxml.FXML
    private ComboBox categorieProduit;
    @javafx.fxml.FXML
    private TextField descriptionProduit;
    @javafx.fxml.FXML
    private TextField quantiteProduit;
    @javafx.fxml.FXML
    private AnchorPane root;
    @javafx.fxml.FXML
    private TextField nomProduit;
    @javafx.fxml.FXML
    private TextField prixProduit;
    @javafx.fxml.FXML
    private TextField fournisseurProduit;

    @FXML
    public void addProduit(javafx.event.ActionEvent actionEvent) {
        try {
            // Vérification des champs obligatoires
            if (nomProduit.getText().isEmpty() || prixProduit.getText().isEmpty() ||
                    descriptionProduit.getText().isEmpty() || categorieProduit.getValue() == null ||
                    quantiteProduit.getText().isEmpty() || fournisseurProduit.getText().isEmpty()) {

                System.out.println("⚠️ Veuillez remplir tous les champs !");
                return;
            }

            Produit p = new Produit();
            ServiceProduit sp = new ServiceProduit();

            p.setNomProduit(nomProduit.getText());
            p.setPrixProduit(Integer.parseInt(prixProduit.getText()));
            p.setDescription(descriptionProduit.getText());
            p.setCategorie(Produit.CategorieProduit.valueOf(categorieProduit.getValue().toString()));
            p.setQuantite(Integer.parseInt(quantiteProduit.getText()));

            // Récupérer le fournisseur directement depuis la base de données
            String fournisseurNom = fournisseurProduit.getText();
            ServiceFournisseur sf = new ServiceFournisseur();
            fournisseur f = sf.getFournisseurFromDatabase(fournisseurNom);

            if (f == null) {
                System.out.println("⚠️ Erreur : Fournisseur introuvable !");
                return;
            }

            p.setFournisseur(f);

            // Ajouter le produit
            sp.add(p);
            System.out.println("✅ Produit ajouté avec succès !");

            // Nettoyage des champs
            nomProduit.clear();
            prixProduit.clear();
            descriptionProduit.clear();
            categorieProduit.setValue(null);
            quantiteProduit.clear();
            fournisseurProduit.clear();

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Erreur : Le prix et la quantité doivent être des nombres valides !");
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Erreur : Catégorie invalide !");
        } catch (Exception e) {
            System.out.println("❌ Une erreur s'est produite : " + e.getMessage());
        }
    }


}
