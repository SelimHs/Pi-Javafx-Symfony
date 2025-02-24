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

    @Override
    public void add(fournisseur fournisseur) {
        String qry = "INSERT INTO `fournisseur`(`nomFournisseur`, `description`, `type`) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, fournisseur.getNomFournisseur());
            pstm.setString(2, fournisseur.getDescription());
            pstm.setString(3, fournisseur.getType());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<fournisseur> getAll() {
        List<fournisseur> fournisseurs = new ArrayList<>();
        String qry = "SELECT * FROM `fournisseur`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                fournisseur fournisseur = new fournisseur();
                fournisseur.setIdFournisseur(rs.getInt("idFournisseur"));
                fournisseur.setNomFournisseur(rs.getString("nomFournisseur"));
                fournisseur.setDescription(rs.getString("description"));
                fournisseur.setType(rs.getString("type"));
                fournisseurs.add(fournisseur);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fournisseurs;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(fournisseur fournisseur) {
        String qry = "UPDATE `fournisseur` SET `nomFournisseur` = ?, `description` = ?, `type` = ? WHERE `idFournisseur` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, fournisseur.getNomFournisseur());
            pstm.setString(2, fournisseur.getDescription());
            pstm.setString(3, fournisseur.getType());
            pstm.setInt(4, fournisseur.getIdFournisseur());
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

    @Override
    public fournisseur findById(int id) {
        String qry = "SELECT * FROM `fournisseur` WHERE `idFournisseur` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                fournisseur fournisseur = new fournisseur();
                fournisseur.setIdFournisseur(rs.getInt("idFournisseur"));
                fournisseur.setNomFournisseur(rs.getString("nomFournisseur"));
                fournisseur.setDescription(rs.getString("description"));
                fournisseur.setType(rs.getString("type"));
                return fournisseur;
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
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
        return f;
    }

}
