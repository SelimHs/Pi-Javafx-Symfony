package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailService {
    private static final String FROM_EMAIL = "lamma.eventgroups@gmail.com"; // Remplacez par votre e-mail
    private static final String EMAIL_PASSWORD = "brfa qcnj knlk ryio"; // Remplacez par le mot de passe d'application

    private Session createSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, EMAIL_PASSWORD);
            }
        });
    }

    public String sendConfirmationEmail(String toEmail) {
        Random random = new Random();
        int confirmationCode = 100000 + random.nextInt(900000);

        try {
            MimeMessage message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Confirmation de votre compte");
            message.setText("Votre code de confirmation est : " + confirmationCode);

            Transport.send(message);
            System.out.println("Code de confirmation envoyé à " + toEmail + ": " + confirmationCode);

            return String.valueOf(confirmationCode);
        } catch (MessagingException e) {
            System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
            throw new RuntimeException("Erreur lors de l'envoi de l'e-mail", e);
        }
    }

    public boolean sendPasswordResetEmail(String toEmail, String resetToken) {
        // Correction du lien de réinitialisation
        String resetLink = "http://localhost:8080/resetPassword?token=" + resetToken + "&email=" + toEmail;

        try {
            MimeMessage message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Réinitialisation de votre mot de passe");
            message.setText("Pour réinitialiser votre mot de passe, cliquez sur le lien suivant : " + resetLink);

            Transport.send(message);
            System.out.println("E-mail de réinitialisation envoyé à " + toEmail);

            return true;
        } catch (MessagingException e) {
            System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
            throw new RuntimeException("Erreur lors de l'envoi de l'e-mail", e);
        }
    }

    public String generateResetToken() {
        Random random = new Random();
        int token = 100000 + random.nextInt(900000);
        return String.valueOf(token);
    }

    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        String code = emailService.sendConfirmationEmail("sandidraslen1@gmail.com");
        System.out.println("Code de confirmation : " + code);
    }
}