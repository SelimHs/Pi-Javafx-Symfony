package tn.esprit.services;

import tn.esprit.interfaces.Iservice;
import tn.esprit.models.fournisseur;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceFournisseur implements Iservice<fournisseur> {

    private Connection cnx;

    public ServiceFournisseur() {
        cnx = myDatabase.getInstance().getConnection();
    }

    public void add(fournisseur fournisseur) {
        String qry = "INSERT INTO fournisseur(nomFournisseur, description, type, telephone, imagePath) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, fournisseur.getNomFournisseur());
            pstm.setString(2, fournisseur.getDescription());
            pstm.setString(3, fournisseur.getType());
            pstm.setString(4, fournisseur.getTelephone());
            pstm.setString(5, fournisseur.getImagePath()); // ✅ Ajout de l'image
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    public List<fournisseur> getAll() {
        List<fournisseur> fournisseurs = new ArrayList<>();
        String qry = "SELECT * FROM `fournisseur`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                fournisseur fourn = new fournisseur();
                fourn.setIdFournisseur(rs.getInt("idFournisseur"));
                fourn.setNomFournisseur(rs.getString("nomFournisseur"));
                fourn.setDescription(rs.getString("description"));
                fourn.setType(rs.getString("type"));
                fourn.setTelephone(rs.getString("telephone"));
                fourn.setImagePath(rs.getString("imagePath")); // ✅ Récupérer l'image
                fournisseurs.add(fourn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fournisseurs;
    }



    @Override
    public void delete(int id) {

    }

    public void update(fournisseur fournisseur) {
        String qry = "UPDATE fournisseur SET nomFournisseur=?, description=?, type=?, telephone=?, imagePath=? WHERE idFournisseur=?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, fournisseur.getNomFournisseur());
            pstm.setString(2, fournisseur.getDescription());
            pstm.setString(3, fournisseur.getType());
            pstm.setString(4, fournisseur.getTelephone());
            pstm.setString(5, fournisseur.getImagePath()); // ✅ Mettre à jour l'image
            pstm.setInt(6, fournisseur.getIdFournisseur());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    @Override
    public void delete(fournisseur fournisseur) {
        String qry = "DELETE FROM `fournisseur` WHERE `idFournisseur` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, fournisseur.getIdFournisseur());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public fournisseur findById(int id) {
        String qry = "SELECT * FROM `fournisseur` WHERE `idFournisseur` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                fournisseur f = new fournisseur();
                f.setIdFournisseur(rs.getInt("idFournisseur"));
                f.setNomFournisseur(rs.getString("nomFournisseur"));
                f.setDescription(rs.getString("description"));
                f.setType(rs.getString("type"));
                f.setTelephone(rs.getString("telephone"));
                f.setImagePath(rs.getString("imagePath"));
                return f;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public fournisseur getFournisseurFromDatabase(String fournisseurNom) {
        fournisseur f = null;
        try {
            String query = "SELECT * FROM fournisseur WHERE nomFournisseur = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, fournisseurNom);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                f = new fournisseur();
                f.setIdFournisseur(rs.getInt("idFournisseur"));
                f.setNomFournisseur(rs.getString("nomFournisseur"));
                f.setDescription(rs.getString("description"));
                f.setType(rs.getString("type"));
                f.setTelephone(rs.getString("telephone")); // Ajouter téléphone
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
        return f;
    }


}
