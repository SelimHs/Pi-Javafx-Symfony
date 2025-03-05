package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import tn.esprit.models.Produit;

public class AI_Predictive {
    private static final String API_KEY = "c5EPrfyjkbxQgLU17d3PmALhiDehJPMDBmvq83Rm"; // Mets ici ta clé API Cohere

    public static String generateEventIdea(List<Produit> produits) {
        try {
            // 🔹 Vérifier que la liste des produits n'est pas vide
            if (produits == null || produits.isEmpty()) {
                return "⚠️ Aucune donnée de produit sélectionnée.";
            }

            // 🔹 Construire le prompt avec les détails des produits
            StringBuilder productList = new StringBuilder();
            for (Produit produit : produits) {
                productList.append("Nom: ").append(produit.getNomProduit())
                        .append(", Catégorie: ").append(produit.getCategorie())
                        .append(", Description: ").append(produit.getDescription())
                        .append(", Prix: ").append(produit.getPrixProduit()).append(" DT")
                        .append(", Quantité: ").append(produit.getQuantite())
                        .append(", Fournisseur: ").append(produit.getFournisseur().getNomFournisseur())
                        .append(" | ");
            }

            // Vérifier que le prompt contient bien du texte
            if (productList.length() == 0) {
                return "⚠️ Impossible de générer un événement car les produits sont vides.";
            }

            String prompt = "Je suis un expert en organisation d'événements. En fonction de ces produits : "
                    + productList.toString()
                    + " suggérez un événement que je pourrais organiser. Fournissez une réponse courte et pratique.";


            // 🔹 Utilisation de l'API Chat Cohere
            URL url = new URL("https://api.cohere.ai/v1/chat");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 🔹 Création du JSON pour Cohere Chat API
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("model", "command-r-plus"); // Modèle recommandé
            requestBody.addProperty("message", prompt);
            requestBody.addProperty("temperature", 0.7); // Niveau de créativité

            // 🔹 Envoyer la requête
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 🔹 Vérifier la réponse HTTP
            if (conn.getResponseCode() != 200) {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
                StringBuilder errorResponse = new StringBuilder();
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    errorResponse.append(errorLine.trim());
                }
                System.out.println("❌ Erreur API Cohere: " + errorResponse.toString());
                return "❌ Erreur API Cohere : " + errorResponse.toString();
            }

            // 🔹 Lire la réponse
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // 🔹 Extraire le texte de la réponse (Correction ici)
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            return jsonResponse.get("text").getAsString(); // ✅ CORRECTION

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Erreur lors de la génération de l'événement.";
        }
    }
}
