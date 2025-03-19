package tn.esprit.services;

import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Espace;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.util.*;

public class ServiceEspace implements Iservice<Optional<Espace>> {

    private Connection cnx;

    public ServiceEspace() {
        cnx = myDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Optional<Espace> espace) {
        String qry = "INSERT INTO `espace`(`nomEspace`, `adresse`, `capacite`, `disponibilite`, `prix`, `idUser`, `Type_espace`, `image`) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, espace.get().getNomEspace());
            pstm.setString(2, espace.get().getAdresse());
            pstm.setInt(3, espace.get().getCapacite());
            pstm.setString(4, espace.get().getDisponibilite());
            pstm.setFloat(5, espace.get().getPrix());
            pstm.setInt(6, espace.get().getIdUser());
            pstm.setString(7, espace.get().getTypeEspace());
            pstm.setString(8, espace.get().getImage());
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
                        rs.getString("Type_espace"),
                        rs.getString("image")
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
        if (espace.isEmpty()) {
            System.out.println("Erreur : Aucun espace fourni pour la mise à jour !");
            return;
        }

        String qry = "UPDATE `espace` SET `nomEspace` = ?, `adresse` = ?, `capacite` = ?, `disponibilite` = ?, `prix` = ?, `idUser` = ?, `Type_espace` = ?, `image` = ? WHERE `idEspace` = ?";

        try (PreparedStatement pstm = cnx.prepareStatement(qry)) {
            pstm.setString(1, espace.get().getNomEspace());
            pstm.setString(2, espace.get().getAdresse());
            pstm.setInt(3, espace.get().getCapacite());
            pstm.setString(4, espace.get().getDisponibilite());
            pstm.setFloat(5, espace.get().getPrix());
            pstm.setInt(6, espace.get().getIdUser());
            pstm.setString(7, espace.get().getTypeEspace());

            // 🔥 Vérification et mise à jour de l'image
            String currentImage = getImagePathFromDB(espace.get().getIdEspace()); // Récupère l'image actuelle
            if (espace.get().getImage() != null && !espace.get().getImage().isEmpty()) {
                pstm.setString(8, espace.get().getImage()); // Mettre la nouvelle image si elle existe
            } else {
                pstm.setString(8, currentImage); // Garde l'image actuelle si aucune nouvelle image n'est sélectionnée
            }

            pstm.setInt(9, espace.get().getIdEspace());

            int rowsUpdated = pstm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Espace mis à jour avec succès !");
            } else {
                System.out.println("❌ Aucun espace mis à jour !");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    /**
     * 🔍 Récupère le chemin actuel de l'image pour un espace donné
     */
    private String getImagePathFromDB(int idEspace) {
        String imagePath = null;
        String qry = "SELECT image FROM `espace` WHERE `idEspace` = ?";

        try (PreparedStatement pstm = cnx.prepareStatement(qry)) {
            pstm.setInt(1, idEspace);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                imagePath = rs.getString("image");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'image : " + e.getMessage());
        }

        return imagePath;
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
                        rs.getString("Type_espace") ,
                        rs.getString("image")
// Ajout de Type_espace dans findById
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
    public Map<String, Integer> getNombreEspacesParAdresse() {
        Map<String, Integer> stats = new HashMap<>();
        String query = "SELECT adresse, COUNT(*) as nombre FROM espace GROUP BY adresse";

        try {
            PreparedStatement stmt = cnx.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(); {
        }
            while (rs.next()) {
                stats.put(rs.getString("adresse"), rs.getInt("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }

}
