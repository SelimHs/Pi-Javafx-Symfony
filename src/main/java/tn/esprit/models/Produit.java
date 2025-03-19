package tn.esprit.models;

public class Produit {

    public enum CategorieProduit {
        ELECTRONIQUE,
        VETEMENTS,
        ALIMENTAIRE,
        MEUBLES,
        SPORT
    }

    private int idProduit;
    private String nomProduit;
    private int prixProduit;
    private String description;
    private CategorieProduit categorie;
    private int quantite;
    private fournisseur fournisseur;
    private String imagePath; // ✅ Nouveau champ pour stocker l'image

    public Produit() {}

    public Produit(int idProduit, String nomProduit, int prixProduit, String description, CategorieProduit categorie, int quantite, fournisseur fournisseur, String imagePath) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.description = description;
        this.categorie = categorie;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
        this.imagePath = imagePath; // ✅ Ajouter l’image
    }

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

    public CategorieProduit getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieProduit categorie) {
        this.categorie = categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "idProduit=" + idProduit +
                ", nomProduit='" + nomProduit + '\'' +
                ", prixProduit=" + prixProduit +
                ", description='" + description + '\'' +
                ", categorie=" + categorie +
                ", quantite=" + quantite +
                ", fournisseur=" + fournisseur +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
