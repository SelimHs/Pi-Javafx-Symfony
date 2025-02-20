package tn.esprit.models;

public class Reservation {
    private int idReservation, idUser, idEvent;
    private String dateReservation;
    private String statut;

    public Reservation() {
    }

    public Reservation(int idReservation, String dateReservation, String statut, int idUser, int idEvent) {
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.idEvent = idEvent;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    public Reservation(int idUser, int idEvent, String dateReservation, String statut) {
        this.idUser = idUser;
        this.idEvent = idEvent;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    public Reservation(int idReservation, int idUser, int idEvent, String dateReservation, String statut) {
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.idEvent = idEvent;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", idUser=" + idUser +
                ", idEvent=" + idEvent +
                ", dateReservation='" + dateReservation + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}


