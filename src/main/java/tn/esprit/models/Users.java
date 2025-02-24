package model;

public class Users {
    private int idUser;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String numeroTelephone;
    private String adresse;
    private String type;
    private String genre;

    public Users() {
    }

    public Users(int idUser, String nom, String prenom, String password, String email, String numeroTelephone, String adresse, String type, String genre) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
        this.adresse = adresse;
        this.type = type;
        this.genre = genre;
    }



    public Users(String nom, String prenom , String password, String email, String numeroTelephone, String adresse, String type, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
        this.adresse = adresse;
        this.type = type;
        this.genre = genre;
    }

    public Users(String name, String email, String phone) {
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom= prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUser=" + idUser +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", adresse=" + adresse +
                ", type='" + type + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}