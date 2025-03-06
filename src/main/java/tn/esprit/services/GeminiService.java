package tn.esprit.services;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import tn.esprit.utils.myDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GeminiService {
    private static final String API_KEY = "AIzaSyCIYfKgYYYMmuUzvKlAKi8byyebAri5cgQ"; // Remplace par ta vraie clé API
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent?key=" + API_KEY;

    private final OkHttpClient client;
    private Connection cnx ;

    public GeminiService() {
        this.client = new OkHttpClient();
        cnx = myDatabase.getInstance().getConnection();
    }

    public String getResponse(String userInput) {
        if (userInput.toLowerCase().contains("événements disponibles") || userInput.toLowerCase().contains("quel événement") || userInput.toLowerCase().contains("événement") || userInput.toLowerCase().contains("événements")) {
            return getUpcomingEvents(); // 🔹 Récupération des événements depuis la BDD
        }
        if (userInput.toLowerCase().contains("produits disponibles") || userInput.toLowerCase().contains("quel produit") || userInput.toLowerCase().contains("produit")) {
            return getAvailableProducts(userInput); // 🔹 Vérification des produits
        }

        if (userInput.toLowerCase().contains("espaces disponibles") || userInput.toLowerCase().contains("quel espace") || userInput.toLowerCase().contains("espace") || userInput.toLowerCase().contains("espaces")) {
            return getAvailableSpaces(); // 🔹 Vérification des espaces disponibles
        }

        return askGemini(userInput); // 🔹 Si la question ne concerne pas la BDD, on utilise l'API Gemini
    }

    // 🔹 Méthode pour récupérer les événements disponibles depuis `lammaInteg`
    private String getUpcomingEvents() {
        StringBuilder response = new StringBuilder("🎟️ Voici les événements disponibles :\n");

        try {
            String query = "SELECT nomEvent, prix, date, nomEspace FROM event ";
            PreparedStatement stmt = cnx.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                response.append("🎉 **").append(rs.getString("nomEvent")).append("**\n")
                        .append("📅 Date : ").append(rs.getString("date")).append("\n")
                        .append("📍 Lieu : ").append(rs.getString("nomEspace")).append("\n")
                        .append("💰 Prix : ").append(rs.getInt("prix")).append(" TND\n\n");
            }

            if (response.toString().equals("🎟️ Voici les événements disponibles :\n")) {
                return "Aucun événement n'est prévu pour le moment. Revenez plus tard !";
            }

            return response.toString();
        } catch (Exception e) {
            return "Erreur lors de la récupération des événements : " + e.getMessage();
        }
    }

    // 🔹 Récupération des espaces disponibles
    private String getAvailableSpaces() {
        StringBuilder response = new StringBuilder("📍 Voici les espaces disponibles :\n");

        try {
            String query = "SELECT nomEspace, adresse, capacite, disponibilite FROM espace WHERE disponibilite = 'Disponible'";
            PreparedStatement stmt = cnx.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                response.append("🏢 **").append(rs.getString("nomEspace")).append("**\n")
                        .append("📍 Adresse : ").append(rs.getString("adresse")).append("\n")
                        .append("👥 Capacité : ").append(rs.getInt("capacite")).append(" personnes\n\n");
            }

            if (response.toString().equals("📍 Voici les espaces disponibles :\n")) {
                return "Aucun espace n'est disponible en ce moment.";
            }

            return response.toString();
        } catch (Exception e) {
            return "Erreur lors de la récupération des espaces : " + e.getMessage();
        }
    }

    // 🔹 Méthode pour interagir avec l'API Gemini si nécessaire
    private String askGemini(String userInput) {
        try {
            String promptContext = "Tu es un chatbot d'assistance pour une plateforme de gestion d'événements en ligne. " +
                    "Tu aides les utilisateurs à trouver des événements, consulter les espaces disponibles, réserver des billets " +
                    "et obtenir des informations sur les tarifs et les disponibilités. Réponds de manière claire et interactive.";

            String fullPrompt = promptContext + "\n\nUtilisateur : " + userInput;

            JSONObject requestBody = new JSONObject();
            JSONArray contentsArray = new JSONArray();
            JSONObject contentObject = new JSONObject();
            contentObject.put("role", "user");
            contentObject.put("parts", new JSONArray().put(new JSONObject().put("text", fullPrompt)));
            contentsArray.put(contentObject);
            requestBody.put("contents", contentsArray);

            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(RequestBody.create(requestBody.toString(), MediaType.get("application/json; charset=utf-8")))
                    .build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                return "Erreur API " + response.code() + " : " + response.message();
            }

            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);

            if (jsonResponse.has("error")) {
                return "Erreur API : " + jsonResponse.getJSONObject("error").toString();
            }

            return jsonResponse.optJSONArray("candidates")
                    .optJSONObject(0)
                    .optJSONObject("content")
                    .optJSONArray("parts")
                    .optJSONObject(0)
                    .optString("text", "Erreur : Aucun texte retourné.");
        } catch (IOException e) {
            return "Erreur de connexion : " + e.getMessage();
        }
    }
    // 🔹 Récupération des produits disponibles
    private String getAvailableProducts(String userInput) {
        StringBuilder response = new StringBuilder("🛒 Voici les produits disponibles :\n");

        try {
            String query = "SELECT nomProduit, prixProduit, quantite, description, categorie FROM produit WHERE quantite > 0";
            PreparedStatement stmt = cnx.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                response.append("📦 **").append(rs.getString("nomProduit")).append("**\n")
                        .append("💰 Prix : ").append(rs.getInt("prixProduit")).append(" TND\n")
                        .append("📋 Description : ").append(rs.getString("description")).append("\n")
                        .append("📌 Catégorie : ").append(rs.getString("categorie")).append("\n")
                        .append("📦 Quantité en stock : ").append(rs.getInt("quantite")).append("\n\n");
            }

            if (response.toString().equals("🛒 Voici les produits disponibles :\n")) {
                return "Aucun produit n'est actuellement disponible.";
            }

            return response.toString();
        } catch (Exception e) {
            return "Erreur lors de la récupération des produits : " + e.getMessage();
        }
    }

    // 🔹 Vérification du stock d'un produit spécifique
    private String checkProductStock(String userInput) {
        try {
            String productName = extractProductName(userInput);
            if (productName.isEmpty()) {
                return "Pouvez-vous préciser le produit que vous cherchez ?";
            }

            String query = "SELECT quantite FROM produit WHERE nomProduit LIKE ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, "%" + productName + "%");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("quantite");
                if (stock > 0) {
                    return "✅ **" + productName + "** est disponible avec **" + stock + "** unités en stock.";
                } else {
                    return "❌ Désolé, le produit **" + productName + "** est en rupture de stock.";
                }
            } else {
                return "Je n'ai pas trouvé ce produit. Pouvez-vous vérifier son nom ?";
            }
        } catch (Exception e) {
            return "Erreur lors de la vérification du stock : " + e.getMessage();
        }
    }

    // 🔹 Extraction du nom du produit depuis la question de l'utilisateur
    private String extractProductName(String userInput) {
        String[] keywords = {"produit", "stock", "quantité", "combien"};
        for (String keyword : keywords) {
            if (userInput.toLowerCase().contains(keyword)) {
                return userInput.substring(userInput.toLowerCase().indexOf(keyword) + keyword.length()).trim();
            }
        }
        return "";
    }




}