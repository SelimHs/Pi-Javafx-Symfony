package tn.esprit.services;

import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Event;
import tn.esprit.utils.myDatabase;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ServiceEvent implements Iservice<Event> {

    private Connection cnx ;


    public ServiceEvent(){
        cnx = myDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Event event) {
        String qry = "INSERT INTO `event`(`nomEvent`, `date`, `prix`, `nbrVisiteurs`, `nomEspace`, `details`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, event.getNomEvent());
            pstm.setString(2, event.getDate());
            pstm.setInt(3, event.getPrix());
            pstm.setInt(4, event.getNbrVisiteurs());
            pstm.setString(5, event.getNomEspace());
            pstm.setString(6, event.getDetails());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        String qry = "SELECT * FROM `event`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Event event = new Event();
                event.setIdEvent(rs.getInt("idEvent"));
                event.setNomEvent(rs.getString("nomEvent"));
                event.setDate(rs.getString("date"));
                event.setPrix(rs.getInt("prix"));
                event.setNbrVisiteurs(rs.getInt("nbrVisiteurs"));
                event.setNomEspace(rs.getString("nomEspace"));
                event.setDetails(rs.getString("details"));
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return events;
    }

    @Override
    public void update(Event event) {
        String qry = "UPDATE `event` SET `nomEvent` = ?, `date` = ?, `prix` = ?, `nbrVisiteurs` = ?, `nomEspace` = ?, `details` = ? WHERE `idEvent` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, event.getNomEvent());
            pstm.setString(2, event.getDate());
            pstm.setInt(3, event.getPrix());
            pstm.setInt(4, event.getNbrVisiteurs());
            pstm.setString(5, event.getNomEspace());
            pstm.setString(6, event.getDetails());
            pstm.setInt(7, event.getIdEvent());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Event event) {
        String qry = "DELETE FROM `event` WHERE `idEvent` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, event.getIdEvent());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Event findById(int id) {
        String qry = "SELECT * FROM `event` WHERE `idEvent` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Event event = new Event();
                event.setIdEvent(rs.getInt("idEvent"));
                event.setNomEvent(rs.getString("nomEvent"));
                event.setDate(rs.getString("date"));
                event.setPrix(rs.getInt("prix"));
                event.setNbrVisiteurs(rs.getInt("nbrVisiteurs"));
                event.setNomEspace(rs.getString("nomEspace"));
                event.setDetails(rs.getString("details"));
                return event;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
