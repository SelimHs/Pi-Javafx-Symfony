package tn.esprit.test;

import tn.esprit.models.fournisseur;
import tn.esprit.models.Produit;
import tn.esprit.services.ServiceFournisseur;
import tn.esprit.services.ServiceProduit;
import tn.esprit.utils.myDatabase;

public class Main {

    public static void main(String[] args) {
        // Création des services
        ServiceFournisseur sf = new ServiceFournisseur();
        ServiceProduit serviceProduit = new ServiceProduit();

        // Ajout d'une personne

        // Ajout d'un fournisseur
        fournisseur fournisseur = new fournisseur("Mohamed", "Fournisseur de fromage", "Fournisseur Permanent");
        sf.add(fournisseur);

        // Récupération du fournisseur inséré (si besoin d'un ID auto-généré)
        fournisseur lastFournisseur = sf.getAll().get(sf.getAll().size() - 1); // Dernier fournisseur ajouté

        // Ajout d'un produit
        Produit produit = new Produit(0, "Fromage Brie", 50, "Fromage de haute qualité", "Alimentaire", 100, lastFournisseur.getIdFournisseur());
        serviceProduit.add(produit);

        // Affichage des données
        System.out.println("Liste des fournisseurs : " + sf.getAll());
        System.out.println("Liste des produits : " + serviceProduit.getAll());
    }
}
