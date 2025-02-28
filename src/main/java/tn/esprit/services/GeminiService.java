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
            // Création du JSON avec le bon format
            JSONObject requestBody = new JSONObject();
            JSONArray contentsArray = new JSONArray();
            JSONObject contentObject = new JSONObject();
            contentObject.put("role", "user");
            contentObject.put("parts", new JSONArray().put(new JSONObject().put("text", userInput)));
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

            // Debug : Affiche la réponse complète de l'API dans la console
            System.out.println("Réponse API : " + jsonResponse.toString(2));

            // Vérification des erreurs dans la réponse JSON
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