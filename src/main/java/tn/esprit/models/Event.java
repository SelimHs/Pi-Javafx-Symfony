package tn.esprit.models;


public class Event {
    private int idEvent, prix, nbrVisiteurs;
    private String nomEvent, nomEspace, details,date;

    public Event() {
    }

    public Event(int idEvent, int prix, int nbrVisiteurs, String nomEvent, String nomEspace, String details, String date) {
        this.idEvent = idEvent;
        this.prix = prix;
        this.nbrVisiteurs = nbrVisiteurs;
        this.nomEvent = nomEvent;
        this.nomEspace = nomEspace;
        this.details = details;
        this.date = date;
    }

    public Event(int prix, int nbrVisiteurs, String nomEvent, String nomEspace, String details, String date) {
        this.prix = prix;
        this.nbrVisiteurs = nbrVisiteurs;
        this.nomEvent = nomEvent;
        this.nomEspace = nomEspace;
        this.details = details;
        this.date = date;
    }

    public Event(int idEvent, String nomEvent, String date) {
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.date = date;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbrVisiteurs() {
        return nbrVisiteurs;
    }

    public void setNbrVisiteurs(int nbrVisiteurs) {
        this.nbrVisiteurs = nbrVisiteurs;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getNomEspace() {
        return nomEspace;
    }

    public void setNomEspace(String nomEspace) {
        this.nomEspace = nomEspace;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", nomEvent='" + nomEvent + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
