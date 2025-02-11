package tn.esprit.services;

import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Billet;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServiceBillet implements Iservice<Billet> {

    private Connection cnx;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ServiceBillet() {
        cnx = myDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Billet billet) {
        String qry = "INSERT INTO `billet`(`proprietaire`, `prix`, `dateAchat`, `type`, `idEvent`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, billet.getProprietaire());
            pstm.setInt(2, billet.getPrix());
            pstm.setString(3, billet.getDateAchat().format(formatter));
            pstm.setString(4, billet.getType().name()); // Enregistrer le type sous forme de chaîne
            pstm.setInt(5, billet.getIdEvent());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Billet> getAll() {
        List<Billet> billets = new ArrayList<>();
        String qry = "SELECT * FROM `billet`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Billet billet = new Billet();
                billet.setIdBillet(rs.getInt("idBillet"));
                billet.setProprietaire(rs.getString("proprietaire"));
                billet.setPrix(rs.getInt("prix"));
                billet.setDateAchat(LocalDateTime.parse(rs.getString("dateAchat"), formatter));
                billet.setType(Billet.TypeBillet.valueOf(rs.getString("type")));
                billet.setIdEvent(rs.getInt("idEvent"));
                billets.add(billet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return billets;
    }


    @Override
    public void update(Billet billet) {
        String qry = "UPDATE `billet` SET `proprietaire` = ?, `prix` = ?, `dateAchat` = ?, `type` = ?, `idEvent` = ? WHERE `idBillet` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, billet.getProprietaire());
            pstm.setInt(2, billet.getPrix());
            pstm.setString(3, billet.getDateAchat().format(formatter));
            pstm.setString(4, billet.getType().name());
            pstm.setInt(5, billet.getIdEvent());
            pstm.setInt(6, billet.getIdBillet());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Billet billet) {
        String qry = "DELETE FROM `billet` WHERE `idBillet` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, billet.getIdBillet());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Billet findById(int id) {
        String qry = "SELECT * FROM `billet` WHERE `idBillet` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Billet billet = new Billet();
                billet.setIdBillet(rs.getInt("idBillet"));
                billet.setProprietaire(rs.getString("proprietaire"));
                billet.setPrix(rs.getInt("prix"));
                billet.setDateAchat(LocalDateTime.parse(rs.getString("dateAchat"), formatter));
                billet.setType(Billet.TypeBillet.valueOf(rs.getString("type")));
                billet.setIdEvent(rs.getInt("idEvent"));
                return billet;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
