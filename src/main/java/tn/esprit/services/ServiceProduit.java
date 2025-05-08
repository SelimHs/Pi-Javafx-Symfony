
package tn.esprit.services;

        import tn.esprit.interfaces.Iservice;
        import tn.esprit.models.Produit;
        import tn.esprit.models.fournisseur;
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
        String qry = "INSERT INTO `produit`(`nomProduit`, `prixProduit`, `description`, `categorie`, `quantite`, `idFournisseur`, `imagePath`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, produit.getNomProduit());
            pstm.setInt(2, produit.getPrixProduit());
            pstm.setString(3, produit.getDescription());
            pstm.setString(4, produit.getCategorie().name());
            pstm.setInt(5, produit.getQuantite());
            pstm.setInt(6, produit.getFournisseur().getIdFournisseur());
            pstm.setString(7, produit.getImagePath()); // ✅ Ajout du chemin de l'image
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }



    @Override
    public List<Produit> getAll() {
        List<Produit> produits = new ArrayList<>();
        String qry = "SELECT p.*, f.nomFournisseur, f.description, f.type FROM produit p INNER JOIN fournisseur f ON p.idFournisseur = f.idFournisseur";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                fournisseur fournisseur = new fournisseur(
                        rs.getInt("idFournisseur"),
                        rs.getString("nomFournisseur"),
                        rs.getString("description"),
                        rs.getString("type")
                );

                Produit produit = new Produit(
                        rs.getInt("idProduit"),
                        rs.getString("nomProduit"),
                        rs.getInt("prixProduit"),
                        rs.getString("description"),
                        Produit.CategorieProduit.safeValueOf(rs.getString("categorie")),
                        rs.getInt("quantite"),
                        fournisseur,
                        rs.getString("imagePath") // ✅ Récupération de l'image
                );
                produits.add(produit);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des produits : " + e.getMessage());
        }
        return produits;
    }


    @Override
    public void delete(int id) {

    }
    @Override
    public void update(Produit produit) {
        String qry = "UPDATE `produit` SET `nomProduit` = ?, `prixProduit` = ?, `description` = ?, `categorie` = ?, `quantite` = ?, `idFournisseur` = ?, `imagePath` = ? WHERE `idProduit` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, produit.getNomProduit());
            pstm.setInt(2, produit.getPrixProduit());
            pstm.setString(3, produit.getDescription());
            pstm.setString(4, produit.getCategorie().name());
            pstm.setInt(5, produit.getQuantite());
            pstm.setInt(6, produit.getFournisseur().getIdFournisseur());
            pstm.setString(7, produit.getImagePath()); // ✅ Mettre à jour l'image si modifiée
            pstm.setInt(8, produit.getIdProduit());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du produit : " + e.getMessage());
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
        String qry = "SELECT p.*, f.nomFournisseur, f.description, f.type FROM produit p INNER JOIN fournisseur f ON p.idFournisseur = f.idFournisseur WHERE p.idProduit = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                fournisseur fournisseur = new fournisseur(
                        rs.getInt("idFournisseur"),
                        rs.getString("nomFournisseur"),
                        rs.getString("description"),
                        rs.getString("type")
                );

                return new Produit(
                        rs.getInt("idProduit"),
                        rs.getString("nomProduit"),
                        rs.getInt("prixProduit"),
                        rs.getString("description"),
                        Produit.CategorieProduit.safeValueOf(rs.getString("categorie")),
                        rs.getInt("quantite"),
                        fournisseur,
                        rs.getString("imagePath")
                );

            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du produit : " + e.getMessage());
        }
        return null;
    }

}
