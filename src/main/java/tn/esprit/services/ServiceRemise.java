package tn.esprit.services;
import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Remise;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRemise implements Iservice<Remise>{
    private Connection cnx;

    public ServiceRemise() {
        cnx = myDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Remise remise) {
        String qry = "INSERT INTO `remise`(`codePromo`, `description`, `pourcentageRemise`, `dateExpiration`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, remise.getCodePromo());
            pstm.setString(2, remise.getDescription());
            pstm.setDouble(3, remise.getPourcentageRemise());
            pstm.setString(4,remise.getDateExpiration());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Remise> getAll() {
        List<Remise> remises = new ArrayList<>();
        String qry = "SELECT * FROM `remise`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Remise remise = new Remise();
                remise.setIdRemise(rs.getInt("idRemise"));
                remise.setCodePromo(rs.getString("codePromo"));
                remise.setDescription(rs.getString("description"));
                remise.setPourcentageRemise(rs.getDouble("pourcentageRemise"));
                remise.setDateExpiration(rs.getString("dateExpiration"));
                remises.add(remise);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return remises;
    }

    @Override
    public void update(Remise remise) {
        String qry = "UPDATE `remise` SET `codePromo` = ?, `description` = ?, `pourcentageRemise` = ?, `dateExpiration` = ? WHERE `idRemise` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, remise.getCodePromo());
            pstm.setString(2, remise.getDescription());
            pstm.setDouble(3, remise.getPourcentageRemise());
            pstm.setString(4,remise.getDateExpiration());
            pstm.setInt(5, remise.getIdRemise());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Remise remise) {
        String qry = "DELETE FROM `remise` WHERE `idRemise` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, remise.getIdRemise());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Remise findById(int id) {
        String qry = "SELECT * FROM `remise` WHERE `idRemise` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Remise remise = new Remise();
                remise.setIdRemise(rs.getInt("idRemise"));
                remise.setCodePromo(rs.getString("codePromo"));
                remise.setDescription(rs.getString("description"));
                remise.setPourcentageRemise(rs.getDouble("pourcentageRemise"));
                remise.setDateExpiration(rs.getString("dateExpiration"));
                return remise;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

