
package tn.esprit.services;

        import tn.esprit.interfaces.Iservice;
        import tn.esprit.models.Produit;
        import tn.esprit.utils.myDatabase;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class ServiceProduit implements Iservice<Produit> {

    private Connection cnx;

    public ServiceProduit() {
        cnx = myDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Produit produit) {
        String qry = "INSERT INTO `produit`(`nomProduit`, `prixProduit`, `description`, `categorie`, `quantite`, `idFournisseur`) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, produit.getNomProduit());
            pstm.setInt(2, produit.getPrixProduit());
            pstm.setString(3, produit.getDescription());
            pstm.setString(4, produit.getCategorie());
            pstm.setInt(5, produit.getQuantite());
            pstm.setInt(6, produit.getIdFournisseur()); // Clé étrangère
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> produits = new ArrayList<>();
        String qry = "SELECT * FROM `produit`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Produit produit = new Produit();
                produit.setIdProduit(rs.getInt("idProduit"));
                produit.setNomProduit(rs.getString("nomProduit"));
                produit.setPrixProduit(rs.getInt("prixProduit"));
                produit.setDescription(rs.getString("description"));
                produit.setCategorie(rs.getString("categorie"));
                produit.setQuantite(rs.getInt("quantite"));
                produit.setIdFournisseur(rs.getInt("idFournisseur"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return produits;
    }

    @Override
    public void update(Produit produit) {
        String qry = "UPDATE `produit` SET `nomProduit` = ?, `prixProduit` = ?, `description` = ?, `categorie` = ?, `quantite` = ?, `idFournisseur` = ? WHERE `idProduit` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, produit.getNomProduit());
            pstm.setInt(2, produit.getPrixProduit());
            pstm.setString(3, produit.getDescription());
            pstm.setString(4, produit.getCategorie());
            pstm.setInt(5, produit.getQuantite());
            pstm.setInt(6, produit.getIdFournisseur());
            pstm.setInt(7, produit.getIdProduit());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Produit produit) {
        String qry = "DELETE FROM `produit` WHERE `idProduit` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, produit.getIdProduit());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Produit findById(int id) {
        String qry = "SELECT * FROM `produit` WHERE `idProduit` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Produit produit = new Produit();
                produit.setIdProduit(rs.getInt("idProduit"));
                produit.setNomProduit(rs.getString("nomProduit"));
                produit.setPrixProduit(rs.getInt("prixProduit"));
                produit.setDescription(rs.getString("description"));
                produit.setCategorie(rs.getString("categorie"));
                produit.setQuantite(rs.getInt("quantite"));
                produit.setIdFournisseur(rs.getInt("idFournisseur"));
                return produit;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
