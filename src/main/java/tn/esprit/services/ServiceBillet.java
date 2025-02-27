package tn.esprit.services;

import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Billet;
import tn.esprit.models.Event;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void delete(int id) {

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

    public Map<String, Integer> getBilletStatsParEvenement() {
        Map<String, Integer> stats = new HashMap<>();

        String query = "SELECT e.nomEvent, COUNT(b.idBillet) AS nombreBillets " +
                "FROM billet b " +
                "JOIN event e ON b.idEvent = e.idEvent " +
                "GROUP BY e.nomEvent";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String nomEvenement = resultSet.getString("nomEvent");
                int nombreBillets = resultSet.getInt("nombreBillets");
                stats.put(nomEvenement, nombreBillets);
            }

        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des statistiques 📊 : " + e.getMessage());
        }
        return stats;
    }

    public int getBilletId(String proprietaire, int prix, LocalDateTime dateAchat, Billet.TypeBillet type, int eventId) {
        int billetId = 0; // Default value (0 means not found)
        try {
            String query = "SELECT idBillet FROM billet WHERE proprietaire = ? AND prix = ? AND dateAchat = ? AND type = ? AND event_id = ?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, proprietaire);
            ps.setInt(2, prix);
            ps.setTimestamp(3, Timestamp.valueOf(dateAchat));
            ps.setString(4, type.toString());
            ps.setInt(5, eventId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                billetId = rs.getInt("idBillet"); // Get the billet ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billetId;
    }

    public Billet findBilletByDateAchat(LocalDateTime dateAchat) {
        Billet billet = new Billet();
        String query = "SELECT * FROM billet WHERE dateAchat = ?";

        try {
             PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setTimestamp(1, Timestamp.valueOf(dateAchat));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                billet = new Billet();
                billet.setIdBillet(rs.getInt("idBillet"));
                billet.setProprietaire(rs.getString("proprietaire"));
                billet.setPrix(rs.getInt("prix"));
                billet.setDateAchat(rs.getTimestamp("dateAchat").toLocalDateTime());
                billet.setType(Billet.TypeBillet.valueOf(rs.getString("type")));

                // Fetch associated event
                int eventId = rs.getInt("idEvent");
                billet.setEvent(new ServiceEvent().findById(eventId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billet;
    }


}
