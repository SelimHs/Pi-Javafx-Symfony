package tn.esprit.services;

import tn.esprit.interfaces.Iservice;
import tn.esprit.models.Personne;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePersonne implements Iservice<Personne> {
    private Connection cnx ;

    public ServicePersonne(){
        cnx = myDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Personne personne) {
        //create Qry SQL
        //execute Qry
        String qry ="INSERT INTO `personne`(`age`, `nom`, `prenom` ) VALUES (?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1,personne.getAge());
            pstm.setString(2,personne.getNom());
            pstm.setString(3, personne.getPrenom());



            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public List<Personne> getAll() {
        //create Qry sql
        //execution
        //Mapping data


        List<Personne> personnes = new ArrayList<>();
        String qry ="SELECT * FROM `personne`";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setAge(rs.getInt("age"));

                personnes.add(p);
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return personnes;
    }

    @Override
    public void update(Personne personne) {
        // Create SQL query to update a record
        String qry = "UPDATE `personne` SET `nom` = ?, `prenom` = ?, `age` = ? WHERE `id` = ?";

        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, personne.getNom());
            pstm.setString(2, personne.getPrenom());
            pstm.setInt(3, personne.getAge());
            pstm.setInt(4, personne.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Personne P) {
        // SQL Query
        String qry = "DELETE FROM `personne` WHERE `id` = ?";

        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, P.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Personne findById(int id) {
        // Create SQL query to select a record by id
        String qry = "SELECT * FROM `personne` WHERE `id` = ?";

        List<Personne> personnes = new ArrayList<>();
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) { // If a record is found
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString("prenom"));
                p.setAge(rs.getInt(4));

                personnes.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null; // Return null if no record is found or if an error occurs
    }

}
