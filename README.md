# Lamma

Lamma est une application Symfony 6.4.11 pour la gestion des événements, des espaces, des billets et des réservations. Elle offre une interface moderne et responsive et intègre des fonctionnalités telles que le calcul dynamique des prix, la génération de billets PDF, la vérification SMS, des suggestions d'événements par IA, et plus encore.

## Fonctionnalités

- **Gestion des Espaces** : opérations CRUD, recherche, filtres, tri, calendrier de disponibilité
- **Gestion des Événements** : opérations CRUD, pages de liste et détails stylées avec le template Villa Agency
- **Système de Réservation** : calcul de prix dynamique en fonction de la durée et option organisateur
- **Génération de Billets** : billets PDF via l'API PDF.co avec QR codes intégrés pointant vers votre serveur local
- **Codes Promo** : application automatique des codes de réduction
- **Vérification Organisateur** : OTP SMS via l'API Termii lors de l'ajout d'un organisateur
- **Compte à Rebours Temps Réel** : minuteurs pour les événements à venir
- **Chatbot Intégré** : interface conversationnelle pour interroger événements, billets et réservations
- **Suggestions IA** : génération d'idées d'événements à partir de produits sélectionnés et d'espaces disponibles
- **Mode Sombre/Clair** : bascule entre thèmes sombre et clair
- **Stockage Externe** : données de réservation stockées et récupérées via l'API Sheet.best

## Prérequis

- PHP 8.1+
- Composer 2.x
- Symfony CLI (optionnel)
- MySQL/MariaDB 5.7+
- Node.js 16+ et NPM ou Yarn
- wkhtmltopdf (pour KnpSnappy si utilisé)

## Variables d'environnement

Copiez `.env` en `.env.local` et configurez :

```dotenv
DATABASE_URL="mysql://utilisateur:motdepasse@hôte:port/nom_de_la_base?serverVersion=mariadb-10.x"
PDFCO_API_KEY=votre_clef_pdfco
TERMII_API_KEY=votre_clef_termii
SHEETBEST_API_ENDPOINT=https://api.sheetbest.com/sheets/votre_sheet_id
TWILIO_ACCOUNT_SID=votre_twilio_sid
TWILIO_AUTH_TOKEN=votre_twilio_token
TWILIO_PHONE_NUMBER=+1234567890
```

## Installation

```bash
# 1. Cloner le dépôt
git clone https://github.com/votreutilisateur/lamma.git
cd lamma

# 2. Installer les dépendances PHP
composer install

# 3. Configurer les variables d'environnement
cp .env .env.local

# 4. Lancer le serveur Symfony
symfony server:start --no-tls --allow-http --port=8000
```

## Utilisation

- Ouvrez votre navigateur à `http://localhost:8000`
- **Espaces** : `/espaces`
- **Événements** : `/events`
- **Réservations** : suivez le flux de réservation sur les pages d'événements
- **Téléchargement des Billets** : après réservation, téléchargez votre billet PDF à `/billet`
- **Tableau de bord Admin** : `/dashboard` (nécessite ROLE_ADMIN)

## Tests

Exécutez la suite de tests PHPUnit :

```bash
php bin/phpunit
```

## Contribution

Les contributions sont les bienvenues ! Forkez le dépôt, créez une branche pour votre fonctionnalité et soumettez une pull request. Pour les problèmes ou demandes de fonctionnalités, ouvrez une issue sur GitHub.

## Licence

Ce projet est sous licence MIT.

---
_Dernière mise à jour : 6 mai 2025_
