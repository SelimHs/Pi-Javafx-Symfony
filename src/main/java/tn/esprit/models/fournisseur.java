package tn.esprit.models;

public class fournisseur {

    private int idFournisseur;
    private String nomFournisseur;
    private String description;
    private String type;
    private String telephone;  // Ajout de l'attribut téléphone
    private String imagePath; // Stocke le chemin de l'image

    // Constructeur par défaut
    public fournisseur() {}

    // Constructeur avec paramètres
    public fournisseur(int idFournisseur, String nomFournisseur, String description, String type, String telephone, String imagePath) {
        this.idFournisseur = idFournisseur;
        this.nomFournisseur = nomFournisseur;
        this.description = description;
        this.type = type;
        this.telephone = telephone;
        this.imagePath = imagePath;
    }


    public fournisseur(int idFournisseur, String nomFournisseur, String description, String type) {
        this.idFournisseur = idFournisseur;
        this.nomFournisseur = nomFournisseur;
        this.description = description;
        this.type = type;
        this.telephone = telephone; // <-- Ce code n'ajoute rien, la variable "telephone" n'est pas passée.
    }



    // Getters et Setters
    public int getIdFournisseur() { return idFournisseur; }
    public void setIdFournisseur(int idFournisseur) { this.idFournisseur = idFournisseur; }

    public String getNomFournisseur() { return nomFournisseur; }
    public void setNomFournisseur(String nomFournisseur) { this.nomFournisseur = nomFournisseur; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "idFournisseur=" + idFournisseur +
                ", nomFournisseur='" + nomFournisseur + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
