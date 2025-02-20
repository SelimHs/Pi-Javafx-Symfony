package tn.esprit.models;
import java.util.Objects;

public class Paiement {

        private int idPaiement;
        private int prixTotal;
        private ModePaiement modePaiement;
        private int idBillet;
        private int idUser;

        public Paiement() {

        }

        public Paiement(int idPaiement, int prixTotal, ModePaiement modePaiement, int idBillet, int idUser) {
            this.idPaiement = idPaiement;
            this.prixTotal = prixTotal;
            this.modePaiement = modePaiement;
            this.idBillet = idBillet;
            this.idUser = idUser;
        }

    public Paiement(int prixTotal, ModePaiement modePaiement, int idBillet, int idUser) {
        this.prixTotal = prixTotal;
        this.modePaiement = modePaiement;
        this.idBillet = idBillet;
        this.idUser = idUser;
    }

    public int getIdPaiement() {
            return idPaiement;
        }

        public void setIdPaiement(int idPaiement) {
            this.idPaiement = idPaiement;
        }

        public int getPrixTotal() {
            return prixTotal;
        }

        public void setPrixTotal(int prixTotal) {
            this.prixTotal = prixTotal;
        }

        public ModePaiement getModePaiement() {
            return modePaiement;
        }

        public void setModePaiement(ModePaiement modePaiement) {
            this.modePaiement = modePaiement;
        }

        public int getIdBillet() {
            return idBillet;
        }

        public void setIdBillet(int idBillet) {
            this.idBillet = idBillet;
        }

        public int getIdUser() {
            return idUser;
        }

        public void setIdUser(int idUser) {
            this.idUser = idUser;
        }

        @Override
        public String toString() {
            return "Paiement{" +
                    "idPaiement=" + idPaiement +
                    ", prixTotal=" + prixTotal +
                    ", modePaiement=" + modePaiement +
                    ", idBillet=" + idBillet +
                    ", idUser=" + idUser +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Paiement paiement = (Paiement) o;
            return idPaiement == paiement.idPaiement && prixTotal == paiement.prixTotal &&
                    idBillet == paiement.idBillet && idUser == paiement.idUser &&
                    modePaiement == paiement.modePaiement;
        }

        @Override
        public int hashCode() {
            return Objects.hash(idPaiement, prixTotal, modePaiement, idBillet, idUser);
        }
        public enum ModePaiement {
            ESPECE,
            CHEQUE,
            CARTEBANCAIRE,
            VIREMENT
        }
}
