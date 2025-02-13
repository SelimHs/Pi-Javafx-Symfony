package tn.esprit.test;

import tn.esprit.models.Espace;
import tn.esprit.models.Organisateur;
import tn.esprit.services.ServiceEspace;
import tn.esprit.services.ServiceOrganisateur;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ServiceEspace serviceEspace = new ServiceEspace();
        ServiceOrganisateur serviceOrganisateur = new ServiceOrganisateur();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Ajouter un espace");
            System.out.println("2. Afficher tous les espaces");
            System.out.println("3. Modifier un espace");
            System.out.println("4. Supprimer un espace");
            System.out.println("5. Ajouter un organisateur");
            System.out.println("6. Afficher tous les organisateurs");
            System.out.println("7. Modifier un organisateur");
            System.out.println("8. Supprimer un organisateur");
            System.out.println("9. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choix) {
                case 1:
                    ajouterEspace(serviceEspace, scanner);
                    break;
                case 2:
                    afficherEspaces(serviceEspace);
                    break;
                case 3:
                    modifierEspace(serviceEspace, scanner);
                    break;
                case 4:
                    supprimerEspace(serviceEspace, scanner);
                    break;
                case 5:
                    ajouterOrganisateur(serviceOrganisateur, serviceEspace, scanner);
                    break;
                case 6:
                    afficherOrganisateurs(serviceOrganisateur);
                    break;
                case 7:
                    modifierOrganisateur(serviceOrganisateur, scanner);
                    break;
                case 8:
                    supprimerOrganisateur(serviceOrganisateur, scanner);
                    break;
                case 9:
                    System.out.println("Programme terminé.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    // 🚀 CRUD sur ESPACE

    private static void ajouterEspace(ServiceEspace serviceEspace, Scanner scanner) {
        System.out.print("Nom de l'espace : ");
        String nom = scanner.nextLine();
        System.out.print("Adresse : ");
        String adresse = scanner.nextLine();
        System.out.print("Capacité : ");
        int capacite = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Disponibilité (Disponible/Indisponible) : ");
        String disponibilite = scanner.nextLine();
        System.out.print("Prix : ");
        float prix = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("ID de l'utilisateur : ");
        int idUser = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Type d'espace : ");
        String typeEspace = scanner.nextLine();

        Espace espace = new Espace(0, nom, adresse, capacite, disponibilite, prix, idUser, typeEspace);
        serviceEspace.add(espace);
        System.out.println("✅ Espace ajouté avec succès !");
    }

    private static void afficherEspaces(ServiceEspace serviceEspace) {
        System.out.println("\nListe des espaces :");
        for (Espace e : serviceEspace.getAll()) {
            System.out.println(e);
        }
    }

    private static void modifierEspace(ServiceEspace serviceEspace, Scanner scanner) {
        System.out.print("Entrez l'ID de l'espace à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Espace> optionalEspace = serviceEspace.findById(id);
        if (optionalEspace.isPresent()) {
            Espace espace = optionalEspace.get();

            System.out.print("Nouveau nom (" + espace.getNomEspace() + ") : ");
            String nom = scanner.nextLine();
            if (!nom.isEmpty()) espace.setNomEspace(nom);

            System.out.print("Nouvelle adresse (" + espace.getAdresse() + ") : ");
            String adresse = scanner.nextLine();
            if (!adresse.isEmpty()) espace.setAdresse(adresse);

            System.out.print("Nouvelle capacité (" + espace.getCapacite() + ") : ");
            int capacite = scanner.nextInt();
            if (capacite > 0) espace.setCapacite(capacite);
            scanner.nextLine();

            System.out.print("Nouvelle disponibilité (" + espace.getDisponibilite() + ") : ");
            String disponibilite = scanner.nextLine();
            if (!disponibilite.isEmpty()) espace.setDisponibilite(disponibilite);

            System.out.print("Nouveau prix (" + espace.getPrix() + ") : ");
            float prix = scanner.nextFloat();
            if (prix > 0) espace.setPrix(prix);

            System.out.print("Nouveau type d'espace (" + espace.getTypeEspace() + ") : ");
            scanner.nextLine();
            String typeEspace = scanner.nextLine();
            if (!typeEspace.isEmpty()) espace.setTypeEspace(typeEspace);

            serviceEspace.update(espace);
            System.out.println("✅ Espace modifié avec succès !");
        } else {
            System.out.println("❌ Aucun espace trouvé avec cet ID.");
        }
    }

    private static void supprimerEspace(ServiceEspace serviceEspace, Scanner scanner) {
        System.out.print("Entrez l'ID de l'espace à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        serviceEspace.delete(id);
        System.out.println("🗑️ Espace supprimé avec succès !");
    }

    // 🚀 CRUD sur ORGANISATEUR

    private static void ajouterOrganisateur(ServiceOrganisateur serviceOrganisateur, ServiceEspace serviceEspace, Scanner scanner) {
        System.out.print("Nom de l'organisateur : ");
        String nomOrg = scanner.nextLine();
        System.out.print("Prénom de l'organisateur : ");
        String prenomOrg = scanner.nextLine();
        System.out.print("Description : ");
        String descriptionOrg = scanner.nextLine();
        System.out.print("ID de l'espace associé : ");
        int idEspace = scanner.nextInt();
        scanner.nextLine();

        Optional<Espace> espace = serviceEspace.findById(idEspace);
        if (espace.isPresent()) {
            Organisateur organisateur = new Organisateur(0, nomOrg, prenomOrg, descriptionOrg, idEspace);
            serviceOrganisateur.add(organisateur);
            System.out.println("✅ Organisateur ajouté avec succès !");
        } else {
            System.out.println("❌ Erreur : L'espace associé n'existe pas !");
        }
    }

    private static void afficherOrganisateurs(ServiceOrganisateur serviceOrganisateur) {
        System.out.println("\nListe des organisateurs :");
        for (Organisateur o : serviceOrganisateur.getAll()) {
            System.out.println(o);
        }
    }

    private static void modifierOrganisateur(ServiceOrganisateur serviceOrganisateur, Scanner scanner) {
        System.out.print("Entrez l'ID de l'organisateur à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Organisateur> optionalOrganisateur = serviceOrganisateur.findById(id);
        if (optionalOrganisateur.isPresent()) {
            Organisateur organisateur = optionalOrganisateur.get();

            System.out.print("Nouveau nom (" + organisateur.getNomOrg() + ") : ");
            String nom = scanner.nextLine();
            if (!nom.isEmpty()) organisateur.setNomOrg(nom);

            System.out.print("Nouveau prénom (" + organisateur.getPrenomOrg() + ") : ");
            String prenom = scanner.nextLine();
            if (!prenom.isEmpty()) organisateur.setPrenomOrg(prenom);

            System.out.print("Nouvelle description (" + organisateur.getDescriptionOrg() + ") : ");
            String description = scanner.nextLine();
            if (!description.isEmpty()) organisateur.setDescriptionOrg(description);

            serviceOrganisateur.update(organisateur);
            System.out.println("✅ Organisateur modifié avec succès !");
        } else {
            System.out.println("❌ Aucun organisateur trouvé avec cet ID.");
        }
    }

    private static void supprimerOrganisateur(ServiceOrganisateur serviceOrganisateur, Scanner scanner) {
        System.out.print("Entrez l'ID de l'organisateur à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        serviceOrganisateur.delete(id);
        System.out.println("🗑️ Organisateur supprimé avec succès !");
    }
}
