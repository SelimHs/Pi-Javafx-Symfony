package service;

import model.Users;
import org.mindrot.jbcrypt.BCrypt;
import utils.MyDatabse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersService implements IService<Users> {

    private Connection con;

    public UsersService() {
        con = MyDatabse.getInstance().getCon();
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

    // Vérifier si un email existe déjà dans la base de données
    public boolean isEmailExist(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next(); // Si un utilisateur avec cet email existe, retourne true
        } catch (SQLException e) {
            System.err.println("Error checking email: " + e.getMessage());
        }
        return false;
    }
}
