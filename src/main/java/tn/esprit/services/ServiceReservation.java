package tn.esprit.services;

import model.Users;
import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Remise;
import tn.esprit.models.Reservation;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceReservation implements Iservice<Reservation> {

    private Connection cnx;

    public ServiceReservation() {
        cnx = myDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Reservation reservation) {
        String qry = "INSERT INTO `reservation`(`dateReservation`, `statut`, `idUser`, `idEvent`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, reservation.getDateReservation());
            pstm.setString(2, reservation.getStatut());
            pstm.setInt(3, reservation.getIdUser());
            pstm.setInt(4, reservation.getIdEvent());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        String qry = "SELECT * from reservation";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {

                // Création de l'objet Reservation avec l'objet Remise associé
                Reservation reservation = new Reservation(
                        rs.getInt("idReservation"),
                        rs.getString("dateReservation"),
                        rs.getString("statut"),
                        rs.getInt("idUser"),
                        rs.getInt("idEvent")// Remise associée à la réservation
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reservations;
    }

    @Override
    public void delete(int id) {

    }
    @Override
    public void update(Reservation reservation) {
        String qry = "UPDATE `reservation` SET `dateReservation` = ?, `statut` = ?, `idUser` = ?, `idEvent` = ? WHERE `idReservation` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, reservation.getDateReservation());
            pstm.setString(2, reservation.getStatut());
            pstm.setInt(3, reservation.getIdUser());
            pstm.setInt(4, reservation.getIdEvent());
            pstm.setInt(5, reservation.getIdReservation());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Reservation reservation) {
        String qry = "DELETE FROM `reservation` WHERE `idReservation` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, reservation.getIdReservation());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Reservation findById(int id) {
        String qry = "SELECT res.*, rem.idRemise, rem.codePromo, rem.description, rem.pourcentageRemise, rem.dateExpiration " +
                "FROM reservation res " +
                "LEFT JOIN remise rem ON res.idRemise = rem.idRemise " +
                "WHERE res.idReservation = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                // Création de l'objet Remise (si elle existe)

                // Retourne un objet Reservation
                return new Reservation(
                        rs.getInt("idReservation"),
                        rs.getString("dateReservation"),
                        rs.getString("statut"),
                        rs.getInt("idUser"),
                        rs.getInt("idEvent")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static String getUserNameById(int userId) {
        String userName = "Utilisateur inconnu"; // Default value if user not found
        String query = "SELECT * FROM `user` WHERE idUser = ?"; // Ensure your table name is correct

        try (Connection conn = myDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {  // Each query gets a fresh ResultSet
                if (rs.next()) {
                    userName = rs.getString("nom"); // Fetch user name from DB
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userName;
    }



    public List<Reservation> search(String searchText) {
        return null;
    }
}
