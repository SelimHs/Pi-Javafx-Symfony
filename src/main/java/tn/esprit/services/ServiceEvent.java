package tn.esprit.services;

import javafx.scene.control.Alert;
import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Event;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEvent implements Iservice<Event> {

    private final Connection cnx;

    public ServiceEvent() {
        cnx = myDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Event event) {
        String qry = "INSERT INTO `event`(`nomEvent`, `date`, `prix`, `nbrVisiteurs`, `nomEspace`, `details`, `image`) VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement pstm = cnx.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, event.getNomEvent());
            pstm.setString(2, event.getDate());
            pstm.setInt(3, event.getPrix());
            pstm.setInt(4, event.getNbrVisiteurs());
            pstm.setString(5, event.getNomEspace());
            pstm.setString(6, event.getDetails());

            // Vérification et insertion du chemin de l'image
            if (event.getImagePath() != null && !event.getImagePath().isEmpty()) {
                pstm.setString(7, event.getImagePath());
                System.out.println("📷 Image enregistrée : " + event.getImagePath()); // Débogage
            } else {
                pstm.setNull(7, java.sql.Types.VARCHAR);
                System.out.println("⚠️ Aucun fichier image fourni.");
            }

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    event.setIdEvent(generatedId); // Mise à jour de l'objet Event avec son ID
                    System.out.println("✅ Événement ajouté avec ID : " + generatedId);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'ajout de l'événement : " + e.getMessage());
        }
    }


    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        String qry = "SELECT * FROM `event`";
        try (Statement stm = cnx.createStatement(); ResultSet rs = stm.executeQuery(qry)) {
            while (rs.next()) {
                Event event = new Event();
                event.setIdEvent(rs.getInt("idEvent"));
                event.setNomEvent(rs.getString("nomEvent"));
                event.setDate(rs.getString("date"));
                event.setPrix(rs.getInt("prix"));
                event.setNbrVisiteurs(rs.getInt("nbrVisiteurs"));
                event.setNomEspace(rs.getString("nomEspace"));
                event.setDetails(rs.getString("details"));

                // Ajout de la récupération de l'image
                event.setImagePath(rs.getString("image"));

                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la récupération des événements : " + e.getMessage());
        }
        return events;
    }

    @Override
    public void update(Event event) {
        String qry = "UPDATE `event` SET `nomEvent` = ?, `date` = ?, `prix` = ?, `nbrVisiteurs` = ?, `nomEspace` = ?, `details` = ?, `image` = ? WHERE `idEvent` = ?";
        try (PreparedStatement pstm = cnx.prepareStatement(qry)) {
            pstm.setString(1, event.getNomEvent());
            pstm.setString(2, event.getDate());
            pstm.setInt(3, event.getPrix());
            pstm.setInt(4, event.getNbrVisiteurs());
            pstm.setString(5, event.getNomEspace());
            pstm.setString(6, event.getDetails());

            // 📌 Mise à jour de l'image seulement si elle existe
            if (event.getImagePath() != null && !event.getImagePath().isEmpty()) {
                pstm.setString(7, event.getImagePath());
            } else {
                pstm.setNull(7, Types.VARCHAR);
            }

            pstm.setInt(8, event.getIdEvent());

            int rowsUpdated = pstm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Événement mis à jour avec succès !");
            } else {
                System.out.println("❌ Aucun événement mis à jour !");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la mise à jour de l'événement : " + e.getMessage());
        }
    }


    @Override
    public void delete(Event event) {
        String qry = "DELETE FROM `event` WHERE `idEvent` = ?";
        try (PreparedStatement pstm = cnx.prepareStatement(qry)) {
            pstm.setInt(1, event.getIdEvent());
            int rowsDeleted = pstm.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Événement supprimé avec succès !");
            } else {
                System.out.println("⚠️ Aucun événement supprimé !");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la suppression de l'événement : " + e.getMessage());
        }
    }

    @Override
    public Event findById(int id) {
        String qry = "SELECT * FROM `event` WHERE `idEvent` = ?";
        try (PreparedStatement pstm = cnx.prepareStatement(qry)) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return new Event(
                        rs.getInt("idEvent"),
                        rs.getInt("prix"),
                        rs.getInt("nbrVisiteurs"),
                        rs.getString("nomEvent"),
                        rs.getString("nomEspace"),
                        rs.getString("details"),
                        rs.getString("date"),
                        rs.getString("image") // Récupération de l'image
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la recherche de l'événement : " + e.getMessage());
        }
        return null;
    }
    @Override
    public void delete(int id) {
        String qry = "DELETE FROM `event` WHERE `idEvent` = ?";
        try (PreparedStatement pstm = cnx.prepareStatement(qry)) {
            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Événement supprimé avec succès !");
            } else {
                System.out.println("⚠️ Aucun événement trouvé avec cet ID !");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la suppression de l'événement : " + e.getMessage());
        }
    }

    public void showEventDetails(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails de l'Événement");
        alert.setHeaderText(event.getNomEvent());
        alert.setContentText("📅 Date : " + event.getDate() +
                "\n💰 Prix : " + event.getPrix() + " DT" +
                "\n👥 Visiteurs : " + event.getNbrVisiteurs() +
                "\n📍 Lieu : " + event.getNomEspace() +
                "\nℹ️ Détails : " + event.getDetails());

        alert.showAndWait();
    }
}
