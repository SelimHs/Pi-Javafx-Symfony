package tn.esprit.models;
import java.util.Date;

public class Remise {
    private int idRemise;
    private String codePromo;
    private String description;
    private double pourcentageRemise;
    private String dateExpiration;


    public Remise() {
    }

    public Remise(String codePromo, String description, double pourcentageRemise, String dateExpiration) {
        this.codePromo = codePromo;
        this.description = description;
        this.pourcentageRemise = pourcentageRemise;
        this.dateExpiration = dateExpiration;
    }

    public Remise(int idRemise, String codePromo, String description, double pourcentageRemise, String dateExpiration) {
        this.idRemise = idRemise;
        this.codePromo = codePromo;
        this.description = description;
        this.pourcentageRemise = pourcentageRemise;
        this.dateExpiration = dateExpiration;
    }
    public Remise(int idRemise) {
        this.idRemise = idRemise;

    }
    public int getIdRemise() {
        return idRemise;
    }

    public void setIdRemise(int idRemise) {
        this.idRemise = idRemise;
    }

    public String getCodePromo() {
        return codePromo;
    }

    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPourcentageRemise() {
        return pourcentageRemise;
    }

    public void setPourcentageRemise(double pourcentageRemise) {
        this.pourcentageRemise = pourcentageRemise;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Override
    public String toString() {
        return "Remise{" +
                "idRemise=" + idRemise +
                ", codePromo='" + codePromo + '\'' +
                ", description='" + description + '\'' +
                ", pourcentageRemise=" + pourcentageRemise +
                ", dateExpiration=" + dateExpiration +
                '}';
    }
}
