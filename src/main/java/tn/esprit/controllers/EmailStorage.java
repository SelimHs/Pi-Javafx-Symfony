package tn.esprit.controllers;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.ArrayList;
import java.util.List;

public class EmailStorage {
    private static final String EMAILS_KEY = "previousEmails";
    private static final int MAX_EMAILS = 5; // Nombre maximum d'e-mails à stocker

    private Preferences prefs;

    public EmailStorage() {
        prefs = Preferences.userNodeForPackage(tn.esprit.controllers.Login.class);
    }

    // Ajouter un nouvel e-mail à la liste
    public void addEmail(String email) {
        String emails = prefs.get(EMAILS_KEY, "");
        List<String> emailList = new ArrayList<>(List.of(emails.split(",")));

        // Éviter les doublons
        if (!emailList.contains(email)) {
            emailList.add(email);

            // Limiter la taille de la liste
            if (emailList.size() > MAX_EMAILS) {
                emailList.remove(0);
            }

            // Sauvegarder la liste mise à jour
            prefs.put(EMAILS_KEY, String.join(",", emailList));
            try {
                prefs.flush();
            } catch (BackingStoreException e) {
                e.printStackTrace();
            }
        }
    }

    // Récupérer la liste des e-mails
    public List<String> getEmails() {
        String emails = prefs.get(EMAILS_KEY, "");
        return new ArrayList<>(List.of(emails.split(",")));
    }
}