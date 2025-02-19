    package tn.esprit.models;

    public class Espace {
        private int idEspace, capacite, idUser;
        private String nomEspace, adresse, disponibilite, typeEspace; // Ajout de typeEspace
        private float prix;

        public Espace() {}

        public Espace(int idEspace, String nomEspace, String adresse, int capacite, String disponibilite, float prix, int idUser, String typeEspace) {
            this.idEspace = idEspace;
            this.nomEspace = nomEspace;
            this.adresse = adresse;
            this.capacite = capacite;
            this.disponibilite = disponibilite;
            this.prix = prix;
            this.idUser = idUser;
            this.typeEspace = typeEspace;
        }

        public int getIdEspace() { return idEspace; }
        public void setIdEspace(int idEspace) { this.idEspace = idEspace; }

        public String getNomEspace() { return nomEspace; }
        public void setNomEspace(String nomEspace) { this.nomEspace = nomEspace; }

        public String getAdresse() { return adresse; }
        public void setAdresse(String adresse) { this.adresse = adresse; }

        public int getCapacite() { return capacite; }
        public void setCapacite(int capacite) { this.capacite = capacite; }

        public String getDisponibilite() { return disponibilite; }
        public void setDisponibilite(String disponibilite) { this.disponibilite = disponibilite; }

        public float getPrix() { return prix; }
        public void setPrix(float prix) { this.prix = prix; }

        public int getIdUser() { return idUser; }
        public void setIdUser(int idUser) { this.idUser = idUser; }

        public String getTypeEspace() { return typeEspace; }
        public void setTypeEspace(String typeEspace) { this.typeEspace = typeEspace; }

        @Override
        public String toString() {
            return "Espace{" +
                    "idEspace=" + idEspace +
                    ", nomEspace='" + nomEspace + '\'' +
                    ", adresse='" + adresse + '\'' +
                    ", capacite=" + capacite +
                    ", disponibilite='" + disponibilite + '\'' +
                    ", prix=" + prix +
                    ", idUser=" + idUser +
                    ", typeEspace='" + typeEspace + '\'' +
                    '}';
        }
    }
