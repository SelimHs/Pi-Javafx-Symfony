package tn.esprit.services;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GeminiService {
    private static final String API_KEY = "AIzaSyCIYfKgYYYMmuUzvKlAKi8byyebAri5cgQ"; // Remplace par ta vraie clé API
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent?key=" + API_KEY;

    private final OkHttpClient client;

    public GeminiService() {
        this.client = new OkHttpClient();
    }

    public String getResponse(String userInput) {
        try {
            // Ajout d'un contexte pour guider le chatbot
            String promptContext = "Tu es un chatbot d'assistance pour une plateforme de gestion d'événements en ligne. " +
                    "Tu aides les utilisateurs à trouver des événements, consulter les espaces disponibles, réserver des billets " +
                    "et obtenir des informations sur les tarifs et les disponibilités. Réponds de manière claire et interactive.";

            String fullPrompt = promptContext + "\n\nUtilisateur : " + userInput;

            // Création du JSON avec la bonne structure
            JSONObject requestBody = new JSONObject();
            JSONArray contentsArray = new JSONArray();
            JSONObject contentObject = new JSONObject();
            contentObject.put("role", "user");
            contentObject.put("parts", new JSONArray().put(new JSONObject().put("text", fullPrompt)));
            contentsArray.put(contentObject);
            requestBody.put("contents", contentsArray);

            // Création de la requête HTTP
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(RequestBody.create(requestBody.toString(), MediaType.get("application/json; charset=utf-8")))
                    .build();

            // Exécution de la requête
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                return "Erreur API " + response.code() + " : " + response.message();
            }

            // Lecture de la réponse
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);

            if (jsonResponse.has("error")) {
                return "Erreur API : " + jsonResponse.getJSONObject("error").toString();
            }

            // Extraction de la réponse du chatbot
            if (jsonResponse.has("candidates")) {
                JSONArray candidates = jsonResponse.getJSONArray("candidates");
                if (candidates.length() > 0) {
                    JSONObject contentObj = candidates.getJSONObject(0).getJSONObject("content");
                    JSONArray partsArray = contentObj.getJSONArray("parts");

                    if (partsArray.length() > 0) {
                        return partsArray.getJSONObject(0).getString("text");
                    }
                }
            }

            return "Erreur : Aucun texte retourné par l'API.";
        } catch (IOException e) {
            return "Erreur de connexion : " + e.getMessage();
        }
    }


}