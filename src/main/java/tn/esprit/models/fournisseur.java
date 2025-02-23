package tn.esprit.models;

public class fournisseur {

    private int idFournisseur;
    private String nomFournisseur;
    private String description;
    private String type;

    // Constructeur par défaut
    public fournisseur() {
    }

    // Constructeur avec paramètres
    public fournisseur(int idFournisseur, String nomFournisseur, String description, String type) {
        this.idFournisseur = idFournisseur;
        this.nomFournisseur = nomFournisseur;
        this.description = description;
        this.type = type;
    }

    public fournisseur(String nomFournisseur, String description, String type) {
        this.nomFournisseur = nomFournisseur;
        this.description = description;
        this.type = type;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "idFournisseur=" + idFournisseur +
                ", nomFournisseur='" + nomFournisseur + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
