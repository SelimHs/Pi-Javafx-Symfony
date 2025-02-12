package tn.esprit.models;

public class Produit {

    private int idProduit;
    private String nomProduit;
    private int prixProduit;
    private String description;
    private String categorie;
    private int quantite;
    private int idFournisseur; // Clé étrangère vers Fournisseur

    // Constructeur par défaut
    public Produit() {
    }

    // Constructeur avec paramètres
    public Produit(int idProduit, String nomProduit, int prixProduit, String description, String categorie, int quantite, int idFournisseur) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.description = description;
        this.categorie = categorie;
        this.quantite = quantite;
        this.idFournisseur = idFournisseur;
    }

    // Getters et Setters


    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(int prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "idProduit=" + idProduit +
                ", nomProduit='" + nomProduit + '\'' +
                ", prixProduit=" + prixProduit +
                ", description='" + description + '\'' +
                ", categorie='" + categorie + '\'' +
                ", quantite=" + quantite +
                ", idFournisseur=" + idFournisseur +
                '}';
    }
}
