package tn.esprit.services;

import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Billet;
import tn.esprit.models.Event;
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
            pstm.setInt(5, billet.getEvent().getIdEvent());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Billet> getAll() {
        List<Billet> billets = new ArrayList<>();
        String qry = "SELECT b.*, e.nomEvent, e.date FROM billet b INNER JOIN event e ON b.idEvent = e.idEvent";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("idEvent"),
                        rs.getString("nomEvent"),
                        rs.getString("date")
                );

                Billet billet = new Billet(
                        rs.getInt("idBillet"),
                        rs.getString("proprietaire"),
                        rs.getInt("prix"),
                        rs.getTimestamp("dateAchat").toLocalDateTime(),
                        Billet.TypeBillet.valueOf(rs.getString("type")),
                        event
                );
                billets.add(billet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return billets;
    }

    @Override
<<<<<<< HEAD
=======
    public void delete(int id) {

    }

    @Override
>>>>>>> c026506 ( integration comp)
    public void update(Billet billet) {
        String qry = "UPDATE `billet` SET `proprietaire` = ?, `prix` = ?, `dateAchat` = ?, `type` = ?, `idEvent` = ? WHERE `idBillet` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, billet.getProprietaire());
            pstm.setInt(2, billet.getPrix());
            pstm.setString(3, billet.getDateAchat().format(formatter));
            pstm.setString(4, billet.getType().name());
            pstm.setInt(5, billet.getEvent().getIdEvent());
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
        String qry = "SELECT b.*, e.nomEvent, e.date FROM billet b INNER JOIN event e ON b.idEvent = e.idEvent WHERE b.idBillet = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Event event = new Event(
                        rs.getInt("idEvent"),
                        rs.getString("nomEvent"),
                        rs.getString("date")
                );

                return new Billet(
                        rs.getInt("idBillet"),
                        rs.getString("proprietaire"),
                        rs.getInt("prix"),
                        rs.getTimestamp("dateAchat").toLocalDateTime(),
                        Billet.TypeBillet.valueOf(rs.getString("type")),
                        event
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
