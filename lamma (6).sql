-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 05 mars 2025 à 11:11
-- Version du serveur : 8.3.0
-- Version de PHP : 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `lamma`
--

-- --------------------------------------------------------

--
-- Structure de la table `billet`
--

DROP TABLE IF EXISTS `billet`;
CREATE TABLE IF NOT EXISTS `billet` (
  `idBillet` int NOT NULL AUTO_INCREMENT,
  `proprietaire` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `prix` int NOT NULL,
  `dateAchat` datetime NOT NULL,
  `type` enum('SIMPLE','DUO','VIP','') COLLATE utf8mb4_general_ci NOT NULL,
  `idEvent` int NOT NULL,
  PRIMARY KEY (`idBillet`),
  KEY `idEvent` (`idEvent`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `billet`
--

INSERT INTO `billet` (`idBillet`, `proprietaire`, `prix`, `dateAchat`, `type`, `idEvent`) VALUES
(19, 'Mourad', 500, '2025-02-21 09:44:03', 'SIMPLE', 20),
(20, 'Belhssan', 1200, '2025-02-21 09:44:13', 'VIP', 21),
(21, 'Yahya', 300, '2025-02-26 15:35:08', 'VIP', 23),
(26, 'zzz', 150, '2025-02-27 15:26:05', 'SIMPLE', 21),
(27, 'yahya', 600, '2025-02-27 15:31:19', 'DUO', 20),
(28, 'pppp', 150, '2025-02-27 15:32:59', 'DUO', 21),
(29, 'yahya', 150, '2025-02-27 15:39:42', 'DUO', 21),
(30, 'monji', 150, '2025-02-27 15:41:25', 'DUO', 23),
(31, 'azert', 150, '2025-02-27 16:54:39', 'SIMPLE', 21),
(32, 'azerty', 150, '2025-02-27 18:58:17', 'DUO', 21),
(33, 'kopa', 150, '2025-02-27 23:06:32', 'SIMPLE', 21),
(34, 'ahmed', 150, '2025-02-27 23:10:39', 'SIMPLE', 21),
(35, 'aqws', 150, '2025-02-27 23:17:26', 'SIMPLE', 21),
(36, 'hu', 150, '2025-02-27 23:20:08', 'SIMPLE', 21),
(37, 'a', 150, '2025-02-27 23:25:27', 'SIMPLE', 21),
(38, 'aa', 150, '2025-02-27 23:34:15', 'SIMPLE', 21),
(39, 'az', 150, '2025-02-27 23:40:41', 'DUO', 21),
(40, 'a', 150, '2025-02-27 23:42:43', 'SIMPLE', 21),
(41, 'a', 150, '2025-02-27 23:43:53', 'DUO', 21),
(42, 'ahmed', 150, '2025-02-28 01:29:16', 'DUO', 21),
(43, 'mouhamed', 150, '2025-02-28 01:37:38', 'DUO', 21),
(44, 'skander', 150, '2025-02-28 01:40:45', 'VIP', 21),
(45, 'Mr Hosni', 150, '2025-02-28 09:25:22', 'SIMPLE', 21),
(46, 'MOUHAMED', 150, '2025-02-28 09:28:09', 'SIMPLE', 23),
(47, 'imed', 450, '2025-03-01 12:01:03', 'VIP', 21),
(48, 'yahya', 525, '2025-03-03 22:05:49', 'DUO', 24);

-- --------------------------------------------------------

--
-- Structure de la table `espace`
--

DROP TABLE IF EXISTS `espace`;
CREATE TABLE IF NOT EXISTS `espace` (
  `idEspace` int NOT NULL AUTO_INCREMENT,
  `nomEspace` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `capacite` int NOT NULL,
  `disponibilite` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `prix` float NOT NULL,
  `idUser` int NOT NULL,
  `Type_espace` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEspace`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `espace`
--

INSERT INTO `espace` (`idEspace`, `nomEspace`, `adresse`, `capacite`, `disponibilite`, `prix`, `idUser`, `Type_espace`) VALUES
(23, 'dar jabbes', 'jabbes', 7411, 'Disponible', 8520, 1, 'azerty'),
(24, 'medina', 'rejiche', 7410, 'Disponible', 9630, 1, 'type'),
(25, 'opera', 'ksour essef opera', 55, 'Disponible', 55, 1, 'familiale'),
(27, 'napoli', 'pizza napoli rejiche', 2000, 'Disponible', 20, 1, 'makla'),
(28, 'esprit', 'ariana', 7654, 'Disponible', 8200, 1, 'azerty'),
(36, 'espace vert', 'ariana', 3300, 'Disponible', 2100, 7, 'nature'),
(37, 'alhambra', 'sidi bou said', 4450, 'Disponible', 3500, 7, 'andalou'),
(38, 'honeymoon', 'rejiche', 5200, 'Disponible', 4250, 1, 'tout option');

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `idEvent` int NOT NULL AUTO_INCREMENT,
  `nomEvent` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `prix` int NOT NULL,
  `details` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `date` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `nbrVisiteurs` int NOT NULL,
  `nomEspace` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEvent`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`idEvent`, `nomEvent`, `prix`, `details`, `date`, `nbrVisiteurs`, `nomEspace`) VALUES
(20, 'Showcasesss', 600, 'Concert multigroupeytzaQSD', '22/02/2025', 200, 'La scEAIne'),
(21, 'Bellydancing', 150, 'Recommended for (single) men', '22/02/2025', 200, 'Al Aariqa'),
(23, 'Forum Options', 150, 'Esprit', '27/02/2025', 300, 'Esprit'),
(24, 'Jazz Night', 350, 'Live jazz music performance', '10/03/2025', 150, 'Blue Note'),
(25, 'Tech Summit', 500, 'Annual technology conference', '15/03/2025', 500, 'Innovation Hub'),
(26, 'Food Festival', 200, 'Tasting of international dishes', '20/03/2025', 700, 'Gourmet Plaza'),
(27, 'Art Exhibition', 100, 'Showcasing local and international artists', '25/03/2025', 250, 'Gallery One'),
(28, 'Gaming Tournament', 450, 'Esports competition with top players', '30/03/2025', 400, 'Gaming Arena'),
(29, 'Startup Pitch', 300, 'Entrepreneurs presenting innovative ideas', '05/04/2025', 350, 'Business Center'),
(30, 'Theater Play', 180, 'Drama play featuring famous actors', '10/04/2025', 200, 'Grand Theatre'),
(31, 'Yoga Retreat', 120, 'A full-day relaxation and wellness retreat', '15/04/2025', 100, 'Nature Escape');

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
CREATE TABLE IF NOT EXISTS `fournisseur` (
  `idFournisseur` int NOT NULL AUTO_INCREMENT,
  `nomFournisseur` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `telephone` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idFournisseur`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`idFournisseur`, `nomFournisseur`, `description`, `type`, `telephone`) VALUES
(7, 'yahya', 'karoui', 'ytreza', '53960240'),
(8, 'kopa', 'kopa', 'kopa', '93830333'),
(9, 'monji', 'azerty', 'azerty', '53960240'),
(11, 'yahya', 'azerty', 'azertyu', '+21693830333'),
(12, 'kopaska', 'azert', 'azerty', '+21653960240'),
(13, 'ines', 'ines', 'ines', '+21628606048');

-- --------------------------------------------------------

--
-- Structure de la table `organisateur`
--

DROP TABLE IF EXISTS `organisateur`;
CREATE TABLE IF NOT EXISTS `organisateur` (
  `id_org` int NOT NULL AUTO_INCREMENT,
  `nom_org` varchar(255) NOT NULL,
  `prenom_org` varchar(255) NOT NULL,
  `description_org` varchar(540) NOT NULL,
  `idEspace` int NOT NULL,
  `telef` int NOT NULL,
  PRIMARY KEY (`id_org`),
  KEY `fk_org` (`idEspace`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `organisateur`
--

INSERT INTO `organisateur` (`id_org`, `nom_org`, `prenom_org`, `description_org`, `idEspace`, `telef`) VALUES
(18, 'khaled', 'kh', 'azerty', 23, 85207410),
(23, 'ahmed', 'ahmed', 'azertyuioqsdfghjkl', 24, 74108520),
(24, 'azerty', 'azertyu', 'azerty', 24, 85209630),
(25, 'yahya', 'karoui', 'azerty', 25, 53960240),
(26, 'monji', 'sd', 'azerty', 23, 74108520),
(27, 'yassin', 'mahmoud', 'trés actif', 37, 53960240),
(28, 'abdelkader', 'fkih hassen', 'A9wa prof physique', 23, 53960240);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
CREATE TABLE IF NOT EXISTS `paiement` (
  `idPaiement` int NOT NULL AUTO_INCREMENT,
  `prixTotal` int NOT NULL,
  `modePaiement` enum('ESPECE','CHEQUE','CARTEBANCAIRE','VIREMENT') COLLATE utf8mb4_general_ci NOT NULL,
  `idBillet` int NOT NULL,
  `idUser` int NOT NULL,
  PRIMARY KEY (`idPaiement`),
  KEY `idBillet` (`idBillet`),
  KEY `idPUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `idProduit` int NOT NULL AUTO_INCREMENT,
  `nomProduit` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `prixProduit` int NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `categorie` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `quantite` int NOT NULL,
  `idFournisseur` int NOT NULL,
  PRIMARY KEY (`idProduit`),
  KEY `idFournisseur` (`idFournisseur`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`idProduit`, `nomProduit`, `prixProduit`, `description`, `categorie`, `quantite`, `idFournisseur`) VALUES
(3, 'jben', 10, 'bnin', 'ALIMENTAIRE', 410, 9),
(4, 'gatou', 20, 'bnin', 'ALIMENTAIRE', 5120, 7),
(5, 'as', 410, 'azerty', 'VETEMENTS', 1, 7),
(6, 'Casque Bluetooth', 50, 'Casque sans fil haute qualité', 'ELECTRONIQUE', 100, 7),
(7, 'Bâtons lumineux', 7, 'Bâton LED multicolore', 'ELECTRONIQUE', 300, 8),
(8, 'Chargeur portable', 40, 'Power bank 10000mAh', 'ELECTRONIQUE', 150, 9),
(9, 'Casquette événementielle', 15, 'Casquette avec logo', 'VETEMENTS', 200, 7),
(10, 'T-shirt officiel', 25, 'T-shirt floqué événementiel', 'VETEMENTS', 12, 11),
(11, 'Bracelet VIP', 2, 'Bracelet en tissu', 'VETEMENTS', 600, 12),
(12, 'Boisson gazeuse', 5, 'Canette 330ml', 'ALIMENTAIRE', 500, 8),
(13, 'Sandwich mixte', 12, 'Pain, fromage, charcuterie', 'ALIMENTAIRE', 250, 9),
(14, 'Popcorn', 6, 'Sachet moyen', 'ALIMENTAIRE', 400, 13),
(15, 'Peluche mascotte', 30, 'Mascotte officielle', 'MEUBLES', 80, 9),
(16, 'Banderole publicitaire', 35, 'Banderole personnalisée', 'MEUBLES', 100, 12),
(17, 'Ballon de football', 60, 'Ballon officiel FIFA', 'SPORT', 50, 13),
(18, 'Autocollants événementiels', 2, 'Set de 10 stickers', 'SPORT', 500, 11),
(19, 'Écouteurs sans fil', 35, 'Écouteurs Bluetooth rechargeables', 'ELECTRONIQUE', 120, 7),
(20, 'Enceinte portable', 55, 'Mini enceinte Bluetooth', 'ELECTRONIQUE', 90, 8),
(21, 'Ventilateur USB', 15, 'Mini ventilateur pour ordinateur', 'ELECTRONIQUE', 200, 9),
(22, 'Lampe LED rechargeable', 20, 'Lampe de poche LED avec USB', 'ELECTRONIQUE', 150, 13),
(23, 'Hoodie événementiel', 45, 'Sweat-shirt à capuche floqué', 'VETEMENTS', 80, 9),
(24, 'Chaussettes à logo', 8, 'Paire de chaussettes avec branding', 'VETEMENTS', 120, 11),
(25, 'Écharpe personnalisée', 18, 'Écharpe chaude avec logo', 'VETEMENTS', 90, 12),
(26, 'Bandeau sportif', 5, 'Bandeau en tissu absorbant', 'VETEMENTS', 300, 13),
(27, 'Café à emporter', 4, 'Tasse de café chaud', 'ALIMENTAIRE', 350, 7),
(28, 'Burger classique', 15, 'Pain, steak, fromage, sauce', 'ALIMENTAIRE', 200, 8),
(29, 'Salade fraîche', 10, 'Salade mixte avec légumes frais', 'ALIMENTAIRE', 180, 9),
(30, 'Gaufres sucrées', 12, 'Gaufres au chocolat ou caramel', 'ALIMENTAIRE', 220, 11),
(31, 'Smoothie aux fruits', 8, 'Boisson naturelle et rafraîchissante', 'ALIMENTAIRE', 180, 12),
(32, 'Chaise pliante', 25, 'Chaise portable pour événements', 'MEUBLES', 60, 9),
(33, 'Table en bois', 50, 'Table pliante pour stands', 'MEUBLES', 40, 11),
(34, 'Tapis rouge', 70, 'Tapis pour VIP', 'MEUBLES', 20, 12),
(35, 'Parasol événementiel', 85, 'Parasol grand format', 'MEUBLES', 35, 13),
(36, 'Maillot sportif', 40, 'Maillot officiel floqué', 'SPORT', 100, 7),
(37, 'Gourde isotherme', 15, 'Bouteille en inox réutilisable', 'SPORT', 250, 8),
(38, 'Bande de résistance', 10, 'Bande élastique pour fitness', 'SPORT', 200, 9),
(39, 'Serviette microfibre', 12, 'Serviette de sport séchage rapide', 'SPORT', 180, 12),
(40, 'Sifflet professionnel', 7, 'Sifflet en métal pour coachs', 'SPORT', 300, 13);

-- --------------------------------------------------------

--
-- Structure de la table `remise`
--

DROP TABLE IF EXISTS `remise`;
CREATE TABLE IF NOT EXISTS `remise` (
  `idRemise` int NOT NULL AUTO_INCREMENT,
  `codePromo` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `pourcentageRemise` double NOT NULL,
  `dateExpiration` varchar(100) NOT NULL,
  PRIMARY KEY (`idRemise`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `remise`
--

INSERT INTO `remise` (`idRemise`, `codePromo`, `description`, `pourcentageRemise`, `dateExpiration`) VALUES
(3, 'azertyu', 'ytcy', 20, '2025-02-27'),
(2, '7dcdv', 'remise pr', 96, '2025-03-09');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `idReservation` int NOT NULL AUTO_INCREMENT,
  `dateReservation` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `statut` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `idUser` int NOT NULL,
  `idEvent` int NOT NULL,
  PRIMARY KEY (`idReservation`),
  KEY `idRUser` (`idUser`),
  KEY `idREvent` (`idEvent`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`idReservation`, `dateReservation`, `statut`, `idUser`, `idEvent`) VALUES
(4, '2025-02-28', 'Confirmée', 1, 20),
(5, '2025-02-26', 'En attente', 1, 21),
(6, '2025-02-25', 'Confirmée', 1, 20);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(158) COLLATE utf8mb4_general_ci NOT NULL,
  `prenom` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `numeroTelephone` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `addresse` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `type` enum('admin','client','','') COLLATE utf8mb4_general_ci NOT NULL,
  `genre` enum('homme','femme','','') COLLATE utf8mb4_general_ci NOT NULL,
  `enregistrer` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=558 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idUser`, `nom`, `prenom`, `password`, `email`, `numeroTelephone`, `addresse`, `type`, `genre`, `enregistrer`) VALUES
(1, 'karoui', 'yahya', '$2a$10$4WBZeja1EjJlVFSUlvVxhO34IfUfP4OOGTWWpXntoxaztM1BA0WE2', 'yahia.karoui@esprit.tn', '53960240', '1 rue elons', 'client', 'homme', 0),
(4, 'a', 'a', '$2a$10$bN3ql9XmR8GqLjr.qhMEPeE2NLEgDqzhzMC6yNd3TktTzlOUHgz0u', 'a', '74120963', 'a', 'client', 'homme', 0),
(7, 'raslen', 'sandid', 'azerty', 'rasle@esprit.com', '74108520', 'fdg', 'admin', 'homme', 0),
(10, 'karoui', 'yahya', '$2a$10$2uxHqCxthncX5ZkoU4ILJOQcG/7AGF2rQD0WHsVGZU9WP5ofb4/QO', 'karouiyahya71@gmail.com', '53960240', 'rejiche mahdia 1rue il ons', 'client', 'homme', 0),
(11, 'monji', 'mahboul', '$2a$10$DLKfphYMU1VSvLKz9aZ2h.69GQYq3yaYgFE30hzXVnwj2QLGkSPCC', 'narouiyahya@gmail.com', '93830333', 'azertytreez', 'client', 'homme', 0),
(555, 'b', 'b', 'b', 'b', 'b', 'b', '', '', 0),
(556, 'ahmed', 'karoui', '$2a$10$C5oEYSee958qyw6XExfiNO.ffF5z.rA/VgOirbk578atxmKZtxzlm', 'yahya@gmail.com', '74108520', 'azertyu', 'admin', 'homme', 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `billet`
--
ALTER TABLE `billet`
  ADD CONSTRAINT `idEvent` FOREIGN KEY (`idEvent`) REFERENCES `event` (`idEvent`);

--
-- Contraintes pour la table `espace`
--
ALTER TABLE `espace`
  ADD CONSTRAINT `idUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `organisateur`
--
ALTER TABLE `organisateur`
  ADD CONSTRAINT `fk_org` FOREIGN KEY (`idEspace`) REFERENCES `espace` (`idEspace`) ON DELETE CASCADE;

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `idBillet` FOREIGN KEY (`idBillet`) REFERENCES `billet` (`idBillet`),
  ADD CONSTRAINT `idPUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `idFournisseur` FOREIGN KEY (`idFournisseur`) REFERENCES `fournisseur` (`idFournisseur`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `idREvent` FOREIGN KEY (`idEvent`) REFERENCES `event` (`idEvent`),
  ADD CONSTRAINT `idRUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
