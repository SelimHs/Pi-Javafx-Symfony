package controllers;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encryptor {

    // Clé secrète (16 caractères pour AES 128 bits)
    private static final String SECRET_KEY = "PIDEVENCRYPTION";
    private static final String ALGORITHM = "AES";

    // Méthode pour crypter le texte
    public static String encrypt(String text) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Méthode pour décrypter le texte
    public static String decrypt(String encryptedText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decrypted);
    }

    public static void main(String[] args) {
        try {
            String password = "mypassword";
            System.out.println("Mot de passe original: " + password);

            String encrypted = encrypt(password);
            System.out.println("Mot de passe crypté: " + encrypted);

            String decrypted = decrypt(encrypted);
            System.out.println("Mot de passe décrypté: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
