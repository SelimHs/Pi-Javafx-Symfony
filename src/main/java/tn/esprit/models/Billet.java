package tn.esprit.models;

import java.time.LocalDateTime;


public class Billet {

    public enum TypeBillet {
        SIMPLE, DUO, VIP;
    }
    private int idBillet, prix;
    private String proprietaire;
    private LocalDateTime dateAchat; // Utilisation de LocalDateTime au lieu de Date
    private TypeBillet type;
    private Event event;

    // Constructeur par défaut
    public Billet() {
        // Initialisation de la date d'achat à la date actuelle
        this.dateAchat = LocalDateTime.now();
    }

    // Constructeur avec paramètres
    public Billet(int idBillet, String proprietaire, int prix, LocalDateTime dateAchat, TypeBillet type, Event event) {
        this.idBillet = idBillet;
        this.proprietaire = proprietaire;
        this.prix = prix;
        this.dateAchat = dateAchat;
        this.type = type;
        this.event = event;
    }

    public Billet(int prix, Event event, String proprietaire, TypeBillet type) {
        this.prix = prix;
        this.event = event;
        this.proprietaire = proprietaire;
        this.type = type;
        this.dateAchat = LocalDateTime.now(); // Initialisation de la date à la création de l'objet
    }

    // Getters et Setters
    public int getIdBillet() {
        return idBillet;
    }

    public void setIdBillet(int idBillet) {
        this.idBillet = idBillet;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public LocalDateTime getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDateTime dateAchat) {
        this.dateAchat = dateAchat;
    }

    public TypeBillet getType() {
        return type;
    }

    public void setType(TypeBillet type) {
        this.type = type;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return "Billet{" +
                "idBillet=" + idBillet +
                ", proprietaire='" + proprietaire + '\'' +
                ", prix=" + prix +
                ", dateAchat=" + dateAchat +
                ", type=" + type +
                ", event=" + event +
                '}';
    }
}
