package tn.esprit.models;

import java.util.List;

public class Organisateur {
    private int idOrg;
    private String nomOrg;
    private String prenomOrg;
    private String descriptionOrg;
    private int idEspace; // L'organisateur appartient à un ou plusieurs espaces

    public Organisateur() {}

    public Organisateur(int idOrg, String nomOrg, String prenomOrg, String descriptionOrg, int idEspace) {
        this.idOrg = idOrg;
        this.nomOrg = nomOrg;
        this.prenomOrg = prenomOrg;
        this.descriptionOrg = descriptionOrg;
        this.idEspace = idEspace;
    }

    public int getIdOrg() { return idOrg; }
    public void setIdOrg(int idOrg) { this.idOrg = idOrg; }

    public String getNomOrg() { return nomOrg; }
    public void setNomOrg(String nomOrg) { this.nomOrg = nomOrg; }

    public String getPrenomOrg() { return prenomOrg; }
    public void setPrenomOrg(String prenomOrg) { this.prenomOrg = prenomOrg; }

    public String getDescriptionOrg() { return descriptionOrg; }
    public void setDescriptionOrg(String descriptionOrg) { this.descriptionOrg = descriptionOrg; }

    public int getIdEspace() { return idEspace; }
    public void setIdEspace(int idEspace) { this.idEspace = idEspace; }

    @Override
    public String toString() {
        return "Organisateur{" +
                "idOrg=" + idOrg +
                ", nomOrg='" + nomOrg + '\'' +
                ", prenomOrg='" + prenomOrg + '\'' +
                ", descriptionOrg='" + descriptionOrg + '\'' +
                ", idEspace=" + idEspace +
                '}';
    }
}
