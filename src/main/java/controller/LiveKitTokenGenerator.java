package controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

public class LiveKitTokenGenerator {

    private static final String API_KEY = "APIKYNck8oEp8Gh"; // 🔑 Remplace avec ta vraie API Key
    private static final long TOKEN_EXPIRATION = 3600 * 1000; // Expiration : 1 heure

    /**
     * 🔑 Génère un token JWT sécurisé basé sur l'adresse de l'espace.
     */
    public static String generateToken(String address) {
        try {
            if (address == null || address.isEmpty()) {
                throw new IllegalArgumentException("⚠️ L'adresse de l'espace ne peut pas être vide !");
            }

            // 🔐 Transformer l'adresse en clé secrète SHA-256 (au format Base64)
            String secretKey = hashAddressToSecretKey(address);

            // 🔑 Convertir en SecretKey pour le JWT
            byte[] decodedKey = Base64.getDecoder().decode(secretKey);
            SecretKey key = Keys.hmacShaKeyFor(decodedKey);

            // 🔥 Génération du Token JWT
            return Jwts.builder()
                    .setIssuer(API_KEY)
                    .setSubject(address) // 📍 L'adresse devient l'identifiant unique du Token
                    .claim("video", true) // Autorisation vidéo
                    .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION)) // Expiration
                    .signWith(key, SignatureAlgorithm.HS256) // Signature JWT sécurisée
                    .compact();
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la génération du token : " + e.getMessage());
            return null;
        }
    }

    /**
     * 🛠 Convertit une adresse en clé secrète sécurisée (SHA-256, Base64).
     */
    private static String hashAddressToSecretKey(String address) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(address.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash); // Encode en Base64 pour être compatible avec JWT
    }
}
