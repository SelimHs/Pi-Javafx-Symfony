package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailService {
    private static final String FROM_EMAIL = "lamma.eventgroups@gmail.com"; // Remplacez par votre e-mail
    private static final String EMAIL_PASSWORD = "brfa qcnj knlk ryio"; // Remplacez par le mot de passe d'application

    public String sendConfirmationEmail(String toEmail) {
        // Générer un code de confirmation aléatoire
        Random random = new Random();
        int confirmationCode = 100000 + random.nextInt(900000); // Code à 6 chiffres

        // Configurer les propriétés pour l'envoi d'e-mails
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Serveur SMTP de Gmail
        properties.put("mail.smtp.port", "587"); // Port SMTP pour TLS
        properties.put("mail.smtp.auth", "true"); // Authentification requise
        properties.put("mail.smtp.starttls.enable", "true"); // Activation de TLS
        properties.put("mail.debug", "true"); // Activer le débogage

        // Créer une session pour l'envoi d'e-mails
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, EMAIL_PASSWORD);
            }
        });

        try {
            // Créer un message e-mail
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL)); // Expéditeur
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); // Destinataire
            message.setSubject("Confirmation de votre compte"); // Sujet
            message.setText("Votre code de confirmation est : " + confirmationCode); // Corps du message

            // Envoyer l'e-mail
            Transport.send(message);
            System.out.println("Code de confirmation envoyé à " + toEmail + ": " + confirmationCode);

            return String.valueOf(confirmationCode); // Retourner le code de confirmation
        } catch (MessagingException e) {
            System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
            throw new RuntimeException("Erreur lors de l'envoi de l'e-mail", e);
        }
    }

    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        String code = emailService.sendConfirmationEmail("sandidraslen@gmail.com");
        System.out.println("Code de confirmation : " + code);
    }
}