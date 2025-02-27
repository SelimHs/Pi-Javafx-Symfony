package tn.esprit.services;

import model.Users;
import org.mindrot.jbcrypt.BCrypt;
import tn.esprit.utils.myDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersService implements service.IService<Users> {

    private Connection con;

    public UsersService() {
        con = myDatabase.getInstance().getConnection();
    }
    // Retourne la connexion à la base de données
    public Connection getConnection() {
        return this.con;
    }

    // Ajouter un utilisateur à la base de données
    @Override
    public void add(Users user) {
        // Hash du mot de passe avant de l'ajouter à la base
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        // Requête SQL pour ajouter l'utilisateur
        String sql = "INSERT INTO user (nom, prenom, password, email, numeroTelephone, addresse, type, genre) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getNumeroTelephone());
            preparedStatement.setString(6, user.getAdresse());
            preparedStatement.setString(7, user.getType());
            preparedStatement.setString(8, user.getGenre());
            preparedStatement.executeUpdate();
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    // Mettre à jour un utilisateur dans la base de données
    @Override
    public void update(Users user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        String sql = "UPDATE user SET nom = ?, prenom = ?, password = ?, email = ?, numeroTelephone = ?, addresse = ?, type = ?, genre = ? WHERE IdUser = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getNumeroTelephone());
            preparedStatement.setString(6, user.getAdresse());
            preparedStatement.setString(7, user.getType());
            preparedStatement.setString(8, user.getGenre());
            preparedStatement.setInt(9, user.getIdUser());
            preparedStatement.executeUpdate();
            System.out.println("User updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
    }

    // Vérifier si le mot de passe saisi correspond au mot de passe haché
    public boolean checkPassword(String enteredPassword, String storedPasswordHash) {
        return BCrypt.checkpw(enteredPassword, storedPasswordHash);
    }

    // Supprimer un utilisateur de la base de données
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM user WHERE idUser  = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }

    // Afficher tous les utilisateurs de la base de données
    @Override
    public List<Users> display() {
        String query = "SELECT * FROM user";
        List<Users> users = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                Users user = new Users();
                user.setIdUser(rs.getInt("idUser"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setNumeroTelephone(rs.getString("numeroTelephone"));
                user.setAdresse(rs.getString("addresse"));
                user.setType(rs.getString("type"));
                user.setGenre(rs.getString("genre"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
        }

        return users;
    }

    // Authentifier un utilisateur en comparant le mot de passe
    public boolean authenticate(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String storedPasswordHash = rs.getString("password");

                // Vérification du mot de passe
                boolean isPasswordCorrect = checkPassword(password, storedPasswordHash);

                return isPasswordCorrect;
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating user: " + e.getMessage());
        }
        return false; // L'email n'existe pas ou le mot de passe est incorrect
    }

    // Vérifier si un email existe dans la base de données
    public boolean isEmailExist(String email) {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Nombre d'utilisateurs trouvés avec cet email : " + count);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.err.println("Erreur SQL lors de la vérification de l'email : " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    // Mettre à jour le mot de passe d'un utilisateur
    public boolean updatePassword(String email, String newPassword) {
        if (!isEmailExist(email)) {
            System.out.println("Email non trouvé dans la base de données.");
            return false;
        }

        String query = "UPDATE user SET password = ? WHERE email = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt()); // Hash du mot de passe
            pst.setString(1, hashedPassword);
            pst.setString(2, email);

            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Mot de passe mis à jour avec succès pour l'email : " + email);
                return true;
            } else {
                System.out.println("Aucune ligne mise à jour dans la base de données.");
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("Erreur SQL lors de la mise à jour du mot de passe : " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    // Récupérer tous les emails avec enregistrer = TRUE
    public List<String> getAllEmails() {
        List<String> emails = new ArrayList<>();
        String query = "SELECT email FROM user WHERE enregistrer = TRUE"; // Requête SQL pour récupérer les emails avec enregistrer = TRUE
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                String email = rs.getString("email");
                emails.add(email); // Ajouter l'email à la liste
            }
        } catch (SQLException e) {
            System.err.println("Error fetching emails: " + e.getMessage());
        }
        return emails; // Retourner la liste des emails
    }

    // Récupérer le mot de passe associé à un email spécifique
    public String getPasswordByEmail(String email) {
        String passwordHash = null;
        String query = "SELECT password FROM user WHERE email = ?"; // Requête SQL pour récupérer le mot de passe haché
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, email); // Définir l'email comme paramètre
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                passwordHash = rs.getString("password"); // Récupérer le mot de passe haché
            }
        } catch (SQLException e) {
            System.err.println("Error fetching password: " + e.getMessage());
        }
        return passwordHash; // Retourner le mot de passe haché (ou null si l'email n'existe pas)
    }
}