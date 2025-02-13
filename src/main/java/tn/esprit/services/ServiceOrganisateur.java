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

    // ✅ Ajouter un organisateur
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

        String qry = "INSERT INTO organisateur (nom_org, prenom_org, description_org, idEspace) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, organisateur.getNomOrg());
            pstm.setString(2, organisateur.getPrenomOrg());
            pstm.setString(3, organisateur.getDescriptionOrg());
            pstm.setInt(4, organisateur.getIdEspace());
            pstm.executeUpdate();
            System.out.println("✅ Organisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'organisateur : " + e.getMessage());
        }
    }

    // ✅ Obtenir tous les organisateurs
    public List<Organisateur> getAll() {
        List<Organisateur> organisateurs = new ArrayList<>();
        String qry = "SELECT * FROM organisateur";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Organisateur organisateur = new Organisateur(
                        rs.getInt("id_org"),
                        rs.getString("nom_org"),
                        rs.getString("prenom_org"),
                        rs.getString("description_org"),
                        rs.getInt("idEspace")
                );
                organisateurs.add(organisateur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des organisateurs : " + e.getMessage());
        }
        return organisateurs;
    }

    // ✅ Trouver un organisateur par ID
    public Optional<Organisateur> findById(int id) {
        String qry = "SELECT * FROM organisateur WHERE id_org = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return Optional.of(new Organisateur(
                        rs.getInt("id_org"),
                        rs.getString("nom_org"),
                        rs.getString("prenom_org"),
                        rs.getString("description_org"),
                        rs.getInt("idEspace")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'organisateur : " + e.getMessage());
        }
        return Optional.empty();
    }

    // ✅ Mettre à jour un organisateur
    public void update(Organisateur organisateur) {
        String qry = "UPDATE organisateur SET nom_org = ?, prenom_org = ?, description_org = ?, idEspace = ? WHERE id_org = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, organisateur.getNomOrg());
            pstm.setString(2, organisateur.getPrenomOrg());
            pstm.setString(3, organisateur.getDescriptionOrg());
            pstm.setInt(4, organisateur.getIdEspace());
            pstm.setInt(5, organisateur.getIdOrg());
            pstm.executeUpdate();
            System.out.println("✅ Organisateur mis à jour avec succès !");
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
            pstm.executeUpdate();
            System.out.println("✅ Organisateur supprimé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'organisateur : " + e.getMessage());
        }
    }
}
