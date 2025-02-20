package tn.esprit.services;

import tn.esprit.models.Organisateur;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceOrganisateur {

    private Connection cnx;

    public ServiceOrganisateur() {
        cnx = myDatabase.getInstance().getConnection();
    }

    // ✅ Ajouter un organisateur avec "telef"
    public void add(Organisateur organisateur) {
        String checkEspace = "SELECT * FROM espace WHERE idEspace = ?";
        try {
            PreparedStatement checkStmt = cnx.prepareStatement(checkEspace);
            checkStmt.setInt(1, organisateur.getIdEspace());
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                System.out.println("❌ Erreur : L'espace associé n'existe pas !");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'espace : " + e.getMessage());
            return;
        }

        String qry = "INSERT INTO organisateur (nom_org, prenom_org, telef, description_org, idEspace) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, organisateur.getNomOrg());
            pstm.setString(2, organisateur.getPrenomOrg());
            pstm.setInt(3, organisateur.getTelef()); // ✅ Ajout du numéro de téléphone
            pstm.setString(4, organisateur.getDescriptionOrg());
            pstm.setInt(5, organisateur.getIdEspace());

            pstm.executeUpdate();
            System.out.println("✅ Organisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'organisateur : " + e.getMessage());
        }
    }

    // ✅ Récupérer les organisateurs liés à un espace avec "telef"
    public List<Organisateur> getOrganisateursByEspace(int idEspace) {
        List<Organisateur> organisateurs = new ArrayList<>();
        String qry = "SELECT * FROM organisateur WHERE idEspace = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, idEspace);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Organisateur organisateur = new Organisateur(
                        rs.getInt("id_org"),
                        rs.getString("nom_org"),
                        rs.getString("prenom_org"),
                        rs.getString("description_org"),
                        rs.getInt("idEspace"),
                        rs.getInt("telef") // ✅ Récupération du numéro de téléphone
                );
                organisateurs.add(organisateur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des organisateurs : " + e.getMessage());
        }
        return organisateurs;
    }


    // ✅ Trouver un organisateur par ID avec "telef"
    public Optional<Organisateur> findById(int id) {
        String qry = "SELECT * FROM organisateur WHERE id_org = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Organisateur organisateur = new Organisateur(
                        rs.getInt("id_org"),
                        rs.getString("nom_org"),
                        rs.getString("prenom_org"),
                        rs.getString("description_org"),
                        rs.getInt("idEspace"),
                        rs.getInt("telef") // ✅ Ajout du champ téléphone
                );
                return Optional.of(organisateur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'organisateur : " + e.getMessage());
        }
        return Optional.empty();
    }

    // ✅ Mettre à jour un organisateur avec "telef"
    public void update(Organisateur organisateur) {
        String qry = "UPDATE organisateur SET nom_org = ?, prenom_org = ?, telef = ?, description_org = ? WHERE id_org = ?";

        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, organisateur.getNomOrg());
            pstm.setString(2, organisateur.getPrenomOrg());
            pstm.setInt(3, organisateur.getTelef()); // ✅ Mise à jour du téléphone
            pstm.setString(4, organisateur.getDescriptionOrg());
            pstm.setInt(5, organisateur.getIdOrg());

            int rowsUpdated = pstm.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Organisateur mis à jour avec succès !");
            } else {
                System.out.println("❌ Erreur : Aucune mise à jour effectuée !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'organisateur : " + e.getMessage());
        }
    }

    // ✅ Supprimer un organisateur
    public void delete(int id) {
        String qry = "DELETE FROM organisateur WHERE id_org = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            int affectedRows = pstm.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("✅ Organisateur supprimé avec succès !");
            } else {
                System.out.println("⚠️ Aucun organisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'organisateur : " + e.getMessage());
        }
    }

    // ✅ Trouver un espace par ID (optionnel)
    public Optional<Object> findEspaceById(int idEspace) {
        return Optional.empty(); // 🚀 Amélioration possible : Ajouter une requête SQL pour récupérer l'espace
    }


    public Organisateur[] getAll() {
        return null;
    }
}
