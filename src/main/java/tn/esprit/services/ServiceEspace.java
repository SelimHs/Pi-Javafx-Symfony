package tn.esprit.services;

import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Espace;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceEspace implements Iservice<Optional<Espace>> {

    private Connection cnx;

    public ServiceEspace() {
        cnx = myDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Optional<Espace> espace) {
        String qry = "INSERT INTO `espace`(`nomEspace`, `adresse`, `capacite`, `disponibilite`, `prix`, `idUser`, `Type_espace`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, espace.get().getNomEspace());
            pstm.setString(2, espace.get().getAdresse());
            pstm.setInt(3, espace.get().getCapacite());
            pstm.setString(4, espace.get().getDisponibilite());
            pstm.setFloat(5, espace.get().getPrix());
            pstm.setInt(6, espace.get().getIdUser());
            pstm.setString(7, espace.get().getTypeEspace());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    @Override
    public List<Optional<Espace>> getAll() {
        List<Optional<Espace>> espaces = new ArrayList<>();
        String qry = "SELECT * FROM `espace`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Optional<Espace> espace = Optional.of(new Espace(
                        rs.getInt("idEspace"),
                        rs.getString("nomEspace"),
                        rs.getString("adresse"),
                        rs.getInt("capacite"),
                        rs.getString("disponibilite"),
                        rs.getFloat("prix"),
                        rs.getInt("idUser"),
                        rs.getString("Type_espace") // Récupération du champ Type_espace
                ));
                espaces.add(espace);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération : " + e.getMessage());
        }
        return espaces;
    }

    @Override
    public void update(Optional<Espace> espace) {
        String qry = "UPDATE `espace` SET `nomEspace` = ?, `adresse` = ?, `capacite` = ?, `disponibilite` = ?, `prix` = ?, `idUser` = ?, `Type_espace` = ? WHERE `idEspace` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, espace.get().getNomEspace());
            pstm.setString(2, espace.get().getAdresse());
            pstm.setInt(3, espace.get().getCapacite());
            pstm.setString(4, espace.get().getDisponibilite());
            pstm.setFloat(5, espace.get().getPrix());
            pstm.setInt(6, espace.get().getIdUser());
            pstm.setString(7, espace.get().getTypeEspace());
            pstm.setInt(8, espace.get().getIdEspace());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String qry = "DELETE FROM `espace` WHERE `idEspace` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    @Override
    public Optional<Espace> findById(int id) {
        String qry = "SELECT * FROM `espace` WHERE `idEspace` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return Optional.of(new Espace(
                        rs.getInt("idEspace"),
                        rs.getString("nomEspace"),
                        rs.getString("adresse"),
                        rs.getInt("capacite"),
                        rs.getString("disponibilite"),
                        rs.getFloat("prix"),
                        rs.getInt("idUser"),
                        rs.getString("Type_espace") // Ajout de Type_espace dans findById
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche : " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void delete(Optional<Espace> espace) {

    }
}
