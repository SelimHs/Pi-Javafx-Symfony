package tn.esprit.test;

import tn.esprit.models.Espace;
import tn.esprit.services.ServiceEspace;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ServiceEspace serviceEspace = new ServiceEspace();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Ajouter un espace");
            System.out.println("2. Afficher tous les espaces");
            System.out.println("3. Rechercher un espace par ID");
            System.out.println("4. Modifier un espace");
            System.out.println("5. Supprimer un espace");
            System.out.println("6. Quitter");
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
                    rechercherEspace(serviceEspace, scanner);
                    break;
                case 4:
                    modifierEspace(serviceEspace, scanner);
                    break;
                case 5:
                    supprimerEspace(serviceEspace, scanner);
                    break;
                case 6:
                    System.out.println("Programme terminé.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    // Fonction pour ajouter un espace
    private static void ajouterEspace(ServiceEspace serviceEspace, Scanner scanner) {
        System.out.print("Nom de l'espace : ");
        String nom = scanner.nextLine();
        System.out.print("Adresse : ");
        String adresse = scanner.nextLine();
        System.out.print("Capacité : ");
        int capacite = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne
        System.out.print("Disponibilité (Disponible/Indisponible) : ");
        String disponibilite = scanner.nextLine();
        System.out.print("Prix : ");
        float prix = scanner.nextFloat();
        System.out.print("ID de l'utilisateur : ");
        int idUser = scanner.nextInt();

        Espace espace = new Espace(0, nom, adresse, capacite, disponibilite, prix, idUser);
        serviceEspace.add(espace);
        System.out.println("✅ Espace ajouté avec succès !");
    }

    // Fonction pour afficher tous les espaces
    private static void afficherEspaces(ServiceEspace serviceEspace) {
        System.out.println("\nListe des espaces :");
        for (Espace e : serviceEspace.getAll()) {
            System.out.println(e);
        }
    }

    // Fonction pour rechercher un espace par ID
    private static void rechercherEspace(ServiceEspace serviceEspace, Scanner scanner) {
        System.out.print("Entrez l'ID de l'espace à rechercher : ");
        int id = scanner.nextInt();
        Optional<Espace> espace = serviceEspace.findById(id);
        if (espace.isPresent()) {
            System.out.println("🔍 Espace trouvé : " + espace.get());
        } else {
            System.out.println("❌ Aucun espace trouvé avec cet ID.");
        }
    }

    // Fonction pour modifier un espace
    private static void modifierEspace(ServiceEspace serviceEspace, Scanner scanner) {
        System.out.print("Entrez l'ID de l'espace à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne

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
            scanner.nextLine(); // Consommer le retour à la ligne

            System.out.print("Nouvelle disponibilité (" + espace.getDisponibilite() + ") : ");
            String disponibilite = scanner.nextLine();
            if (!disponibilite.isEmpty()) espace.setDisponibilite(disponibilite);

            System.out.print("Nouveau prix (" + espace.getPrix() + ") : ");
            float prix = scanner.nextFloat();
            if (prix > 0) espace.setPrix(prix);

            System.out.print("Nouvel ID utilisateur (" + espace.getIdUser() + ") : ");
            int idUser = scanner.nextInt();
            if (idUser > 0) espace.setIdUser(idUser);

            serviceEspace.update(espace);
            System.out.println("✅ Espace modifié avec succès !");
        } else {
            System.out.println("❌ Aucun espace trouvé avec cet ID.");
        }
    }

    // Fonction pour supprimer un espace
    private static void supprimerEspace(ServiceEspace serviceEspace, Scanner scanner) {
        System.out.print("Entrez l'ID de l'espace à supprimer : ");
        int id = scanner.nextInt();

        Optional<Espace> optionalEspace = serviceEspace.findById(id);
        if (optionalEspace.isPresent()) {
            serviceEspace.delete(id);
            System.out.println("🗑️ Espace supprimé avec succès !");
        } else {
            System.out.println("❌ Aucun espace trouvé avec cet ID.");
        }
    }
}
