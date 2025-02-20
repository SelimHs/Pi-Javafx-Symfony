package tn.esprit.services;

import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Paiement;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePaiement implements Iservice<Paiement> {

    private Connection cnx;

    public ServicePaiement() {
        cnx = myDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Paiement paiement) {
        String qry = "INSERT INTO `paiement`(`prixTotal`, `modePaiement`, `idBillet`, `idUser`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, paiement.getPrixTotal());
            pstm.setString(2, paiement.getModePaiement().name());
            pstm.setInt(3, paiement.getIdBillet());
            pstm.setInt(4, paiement.getIdUser());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Paiement> getAll() {
        List<Paiement> paiements = new ArrayList<>();
        String qry = "SELECT * FROM `paiement`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Paiement paiement = new Paiement();
                paiement.setIdPaiement(rs.getInt("idPaiement"));
                paiement.setPrixTotal(rs.getInt("prixTotal"));
                paiement.setModePaiement(Paiement.ModePaiement.valueOf(rs.getString("modePaiement")));
                paiement.setIdBillet(rs.getInt("idBillet"));
                paiement.setIdUser(rs.getInt("idUser"));
                paiements.add(paiement);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return paiements;
    }

    @Override
    public void update(Paiement paiement) {
        String qry = "UPDATE `paiement` SET `prixTotal` = ?, `modePaiement` = ?, `idBillet` = ?, `idUser` = ? WHERE `idPaiement` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, paiement.getPrixTotal());
            pstm.setString(2, paiement.getModePaiement().name());
            pstm.setInt(3, paiement.getIdBillet());
            pstm.setInt(4, paiement.getIdUser());
            pstm.setInt(5, paiement.getIdPaiement());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Paiement paiement) {
        String qry = "DELETE FROM `paiement` WHERE `idPaiement` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, paiement.getIdPaiement());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Paiement findById(int id) {
        String qry = "SELECT * FROM `paiement` WHERE `idPaiement` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Paiement paiement = new Paiement();
                paiement.setIdPaiement(rs.getInt("idPaiement"));
                paiement.setPrixTotal(rs.getInt("prixTotal"));
                paiement.setModePaiement(Paiement.ModePaiement.valueOf(rs.getString("modePaiement")));
                paiement.setIdBillet(rs.getInt("idBillet"));
                paiement.setIdUser(rs.getInt("idUser"));
                return paiement;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
