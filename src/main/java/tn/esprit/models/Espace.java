package tn.esprit.models;

import javafx.beans.property.*;

public class Espace {
    private final IntegerProperty idEspace = new SimpleIntegerProperty();
    private final StringProperty nomEspace = new SimpleStringProperty();
    private final StringProperty adresse = new SimpleStringProperty();
    private final IntegerProperty capacite = new SimpleIntegerProperty();
    private final StringProperty disponibilite = new SimpleStringProperty();
    private final FloatProperty prix = new SimpleFloatProperty();
    private final IntegerProperty idUser = new SimpleIntegerProperty();
    private final StringProperty typeEspace = new SimpleStringProperty();

    private final StringProperty image = new SimpleStringProperty();

    public String getImage() { return image.get(); }
    public void setImage(String img) { this.image.set(img); }
    public StringProperty imageProperty() { return image; }

    public Espace() {}

    public Espace(int idEspace, String nomEspace, String adresse, int capacite, String disponibilite, float prix, int idUser, String typeEspace, String image) {
        this.idEspace.set(idEspace);
        this.nomEspace.set(nomEspace);
        this.adresse.set(adresse);
        this.capacite.set(capacite);
        this.disponibilite.set(disponibilite);
        this.prix.set(prix);
        this.idUser.set(idUser);
        this.typeEspace.set(typeEspace);
        this.image.set(image);
    }


    // ✅ Getters & Setters using JavaFX properties

    public int getIdEspace() { return idEspace.get(); }
    public void setIdEspace(int id) { this.idEspace.set(id); }
    public IntegerProperty idEspaceProperty() { return idEspace; }

    public String getNomEspace() { return nomEspace.get(); }
    public void setNomEspace(String nom) { this.nomEspace.set(nom); }
    public StringProperty nomEspaceProperty() { return nomEspace; }

    public String getAdresse() { return adresse.get(); }
    public void setAdresse(String adr) { this.adresse.set(adr); }
    public StringProperty adresseProperty() { return adresse; }

    public int getCapacite() { return capacite.get(); }
    public void setCapacite(int cap) { this.capacite.set(cap); }
    public IntegerProperty capaciteProperty() { return capacite; }

    public String getDisponibilite() { return disponibilite.get(); }
    public void setDisponibilite(String dispo) { this.disponibilite.set(dispo); }
    public StringProperty disponibiliteProperty() { return disponibilite; }

    public float getPrix() { return prix.get(); }
    public void setPrix(float pr) { this.prix.set(pr); }
    public FloatProperty prixProperty() { return prix; }

    public int getIdUser() { return idUser.get(); }
    public void setIdUser(int user) { this.idUser.set(user); }
    public IntegerProperty idUserProperty() { return idUser; }

    public String getTypeEspace() { return typeEspace.get(); }
    public void setTypeEspace(String type) { this.typeEspace.set(type); }
    public StringProperty typeEspaceProperty() { return typeEspace; }
}
