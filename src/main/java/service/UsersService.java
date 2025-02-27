package service;

import model.Users;
import utils.MyDatabse;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UsersService implements IService<Users> {

    private Connection con;
    private static final String SECRET_KEY = "MySuperSecretKey"; // 16 caractères

    public UsersService() {
        con = MyDatabse.getInstance().getCon();
    }


    // Retourne la connexion à la base de données
    public Connection getConnection() {
        return this.con;
    }
    public static String encrypt(String strToEncrypt) throws Exception {
        byte[] key = SECRET_KEY.getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(strToEncrypt.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
    public static String decrypt(String strToDecrypt) throws Exception {
        byte[] key = SECRET_KEY.getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
        return new String(decrypted);
    }

    @Override
    public void add(Users user) {
        try {
            String encryptedPassword = encrypt(user.getPassword());
            String sql = "INSERT INTO user (nom, prenom, password, email, numeroTelephone, addresse, type, genre) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getNom());
                preparedStatement.setString(2, user.getPrenom());
                preparedStatement.setString(3, encryptedPassword);
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getNumeroTelephone());
                preparedStatement.setString(6, user.getAdresse());
                preparedStatement.setString(7, user.getType());
                preparedStatement.setString(8, user.getGenre());
                preparedStatement.executeUpdate();
                System.out.println("User added successfully!");
            }
        } catch (Exception e) {
            System.err.println("Error encrypting password: " + e.getMessage());
        }
    }
    // Mettre à jour un utilisateur dans la base de données
    @Override
    public void update(Users user) {
        try {
            String encryptedPassword = encrypt(user.getPassword());
            String sql = "UPDATE user SET nom = ?, prenom = ?, password = ?, email = ?, numeroTelephone = ?, addresse = ?, type = ?, genre = ? WHERE IdUser = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getNom());
                preparedStatement.setString(2, user.getPrenom());
                preparedStatement.setString(3, encryptedPassword);
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getNumeroTelephone());
                preparedStatement.setString(6, user.getAdresse());
                preparedStatement.setString(7, user.getType());
                preparedStatement.setString(8, user.getGenre());
                preparedStatement.setInt(9, user.getIdUser());
                preparedStatement.executeUpdate();
                System.out.println("User updated successfully!");
            }
        } catch (Exception e) {
            System.err.println("Error updating password: " + e.getMessage());
        }
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
        List<Users> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
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


    public boolean authenticate(String email, String password) {
        String sql = "SELECT password FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String decryptedPassword = decrypt(storedPassword);
                return decryptedPassword.equals(password);
            }
        } catch (Exception e) {
            System.err.println("Error authenticating user: " + e.getMessage());
        }
        return false;
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

    public boolean updatePassword(String email, String newPassword) {
        String query = "UPDATE user SET password = ? WHERE email = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            String hashedPassword = encrypt(newPassword);
            pst.setString(1, hashedPassword);
            pst.setString(2, email);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Password updated successfully for email: " + email);
                return true;
            } else {
                System.out.println("No rows updated in the database.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("SQL error while updating password: " + e.getMessage());
            e.printStackTrace();
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
    public List<String> getSavedEmails() {
        List<String> savedEmails = new ArrayList<>();
        String query = "SELECT email FROM user WHERE enregistrer = TRUE"; // Filtrer par `enregistrer = true`
        try (PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                savedEmails.add(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des emails enregistrés : " + e.getMessage());
        }
        return savedEmails;
    }
    public String getPasswordByEmail(String email) {
        String passwordHash = null;
        String query = "SELECT password FROM user WHERE email = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                passwordHash = rs.getString("password");
                passwordHash = decrypt(passwordHash);
            }
        } catch (Exception e) {
            System.err.println("Error fetching password: " + e.getMessage());
        }
        return passwordHash;
    }
}