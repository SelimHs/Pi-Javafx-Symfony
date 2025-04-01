-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 01 avr. 2025 à 10:18
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
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(48, 'yahya', 525, '2025-03-03 22:05:49', 'DUO', 24),
(49, 'yahya', 180, '2025-03-05 20:57:16', 'DUO', 31),
(50, 'kopaska', 300, '2025-03-05 21:01:44', 'VIP', 27),
(51, 'moudir', 450, '2025-03-05 21:29:47', 'VIP', 21),
(52, 'slayem', 1350, '2025-03-05 21:47:58', 'VIP', 28),
(53, 'yahya', 150, '2025-03-05 23:21:27', 'DUO', 23),
(54, 'yahya', 150, '2025-03-05 23:22:34', 'DUO', 23),
(55, 'yahya', 750, '2025-03-05 23:30:59', 'DUO', 25),
(56, 'yahya', 525, '2025-03-05 23:32:43', 'DUO', 24),
(57, 'zakaria', 450, '2025-03-05 23:39:32', 'VIP', 21),
(58, 'yahya', 225, '2025-03-05 23:47:04', 'DUO', 23),
(59, 'kopaska', 1500, '2025-03-05 23:49:01', 'VIP', 25),
(60, 'monji', 150, '2025-03-05 23:56:52', 'DUO', 27),
(61, 'ahelm', 675, '2025-03-06 00:12:14', 'DUO', 28),
(62, 'jakob', 750, '2025-03-06 00:14:34', 'DUO', 25),
(63, 'yaha', 225, '2025-03-06 00:23:58', 'DUO', 21),
(64, 'yahya', 225, '2025-03-06 00:25:31', 'DUO', 21),
(65, 'yahya', 150, '2025-03-06 11:54:53', 'SIMPLE', 21),
(66, 'yahya', 180, '2025-03-06 12:23:33', 'DUO', 21),
(67, 'eya', 144, '2025-03-06 12:34:44', 'DUO', 21),
(68, 'yahya', 288, '2025-03-06 12:40:03', 'VIP', 21),
(69, 'feryal', 96, '2025-03-06 12:44:16', 'DUO', 27),
(70, 'feryal', 96, '2025-03-06 12:44:30', 'DUO', 27),
(71, 'azertyui', 180, '2025-03-06 12:53:47', 'DUO', 21),
(72, 'eya', 480, '2025-03-06 12:58:26', 'DUO', 25),
(73, 'kopaska', 192, '2025-03-06 13:01:25', 'DUO', 26),
(74, 'eya', 960, '2025-03-06 13:02:09', 'VIP', 25),
(75, 'azertyu', 225, '2025-03-06 14:12:31', 'DUO', 21),
(76, 'yahya', 360, '2025-03-06 14:46:47', 'VIP', 21),
(77, 'kammoun', 150, '2025-03-06 15:02:51', 'SIMPLE', 30),
(78, 'ines', 180, '2025-03-07 08:41:19', 'DUO', 21),
(79, 'eya', 432, '2025-03-07 09:05:04', 'VIP', 30),
(80, 'mr hosni', 360, '2025-03-07 09:26:42', 'VIP', 21),
(81, 'marwen', 1200, '2025-03-07 09:52:30', 'VIP', 25);

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
  `image` varchar(550) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEspace`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `espace`
--

INSERT INTO `espace` (`idEspace`, `nomEspace`, `adresse`, `capacite`, `disponibilite`, `prix`, `idUser`, `Type_espace`, `image`) VALUES
(23, 'dar jabbes', 'jabbes', 7411, 'Disponible', 8520, 1, 'azerty', ''),
(24, 'medina', 'rejiche', 7410, 'Disponible', 9630, 1, 'type', ''),
(27, 'napoli', 'pizza napoli rejiche', 2000, 'Disponible', 20, 1, 'makla', ''),
(28, 'esprit', 'ariana', 7654, 'Disponible', 8200, 1, 'azerty', ''),
(36, 'espace vert', 'ariana', 3300, 'Disponible', 2100, 7, 'nature', ''),
(37, 'alhambra', 'sidi bou said', 4450, 'Disponible', 3500, 7, 'andalou', ''),
(38, 'honeymoon', 'rejiche', 5200, 'Disponible', 4250, 1, 'tout option', ''),
(40, 'yahya', 'fseg mahdia', 7410, 'Disponible', 8520, 1, 'azertyu', 'C:\\wamp64\\www\\img\\1742374468796_Blank diagram (1).png'),
(41, '9sayer', 'rejiche ', 8520, 'Disponible', 5236, 1, 'azertyuhh', 'C:/wamp64/www/img/22c6f7ba-eb10-4539-a485-ab732656d873_01_LVN_34-Front.jpg');

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
  `image` varchar(550) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEvent`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`idEvent`, `nomEvent`, `prix`, `details`, `date`, `nbrVisiteurs`, `nomEspace`, `image`) VALUES
(20, 'Showcasesss', 600, 'Concert multigroupeytzaQSD', '22/02/2025', 200, 'La scEAIne', ''),
(21, 'Bellydancing', 150, 'Recommended for (single) men', '22/02/2025', 200, 'Al Aariqa', ''),
(23, 'Forum Options', 150, 'Esprit', '27/02/2025', 300, 'Esprit', ''),
(24, 'Jazz Night', 350, 'Live jazz music performance', '10/03/2025', 150, 'Blue Note', ''),
(25, 'Tech Summit', 500, 'Annual technology conference', '15/03/2025', 500, 'Innovation Hub', ''),
(26, 'Food Festival', 200, 'Tasting of international dishes', '20/03/2025', 700, 'Gourmet Plaza', ''),
(27, 'Art Exhibition', 100, 'Showcasing local and international artists', '25/03/2025', 250, 'Gallery One', ''),
(28, 'Gaming Tournament', 450, 'Esports competition with top players', '30/03/2025', 400, 'Gaming Arena', ''),
(29, 'Startup Pitch', 300, 'Entrepreneurs presenting innovative ideas', '05/04/2025', 350, 'Business Center', ''),
(30, 'Theater Play', 180, 'Drama play featuring famous actors', '10/04/2025', 200, 'Grand Theatre', ''),
(31, 'Yoga Retreat', 120, 'A full-day relaxation and wellness retreat', '15/04/2025', 100, 'Nature Escape', ''),
(33, 'aertyu', 5155, 'zec', '2025-03-23', 1000, 'sazc', 'images/1742378592398_Hunting_License_Rectangle_UML.png'),
(34, 'event special', 123, 'sx', '2025-04-06', 410, 'zsx', 'C:\\wamp64\\www\\img\\e4a96ab1-b647-4131-b077-4bb87eb8e9ca_ea-fifa-22-cover-kylian-mbappe_1qeaco87s803l13iu0tnr84jhq.jpg');

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
  `imagePath` varchar(550) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idFournisseur`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`idFournisseur`, `nomFournisseur`, `description`, `type`, `telephone`, `imagePath`) VALUES
(15, '9array', 'zcze', 'czedc', '53960240', 'C:\\wamp64\\www\\img\\ad2ccb42-d64b-4a6e-8f96-4cdcaa07339a_1-Brabus-930.jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `organisateur`
--

INSERT INTO `organisateur` (`id_org`, `nom_org`, `prenom_org`, `description_org`, `idEspace`, `telef`) VALUES
(18, 'khaled', 'kh', 'azerty', 23, 85207410),
(23, 'ahmed', 'ahmed', 'azertyuioqsdfghjkl', 24, 74108520),
(24, 'azerty', 'azertyu', 'azerty', 24, 85209630),
(26, 'monji', 'sd', 'azerty', 23, 74108520),
(27, 'yassin', 'mahmoud', 'trés actif', 37, 53960240),
(28, 'abdelkader', 'fkih hassen', 'A9wa prof physique', 23, 53960240),
(29, 'karim', 'karoui', 'azerty', 23, 53960240);

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
  `imagePath` varchar(550) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idProduit`),
  KEY `idFournisseur` (`idFournisseur`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`idProduit`, `nomProduit`, `prixProduit`, `description`, `categorie`, `quantite`, `idFournisseur`, `imagePath`) VALUES
(42, 'Makarouna', 8520, 'zdevcf', 'VETEMENTS', 520, 15, 'C:\\wamp64\\www\\img\\22642c8d-8cc2-4d2a-8cea-7687d26dae10_téléchargé.jpg'),
(43, 'jellbana', 520, 'zdad', 'VETEMENTS', 96, 15, 'C:\\wamp64\\www\\img\\2d6a62d6-c3b4-4785-9d2d-a5cf3b63ef6e_taxi_collectif_no_bg-removebg-preview.png');

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
  `idReservation` int NOT NULL,
  PRIMARY KEY (`idRemise`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `remise`
--

INSERT INTO `remise` (`idRemise`, `codePromo`, `description`, `pourcentageRemise`, `dateExpiration`, `idReservation`) VALUES
(3, 'azertyu', 'ytcy', 20, '2025-02-27', 0),
(2, '7dcdv', 'remise pr', 96, '2025-03-09', 0),
(4, 'kopaska', 'm3allem', 20, '2025-03-22', 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`idReservation`, `dateReservation`, `statut`, `idUser`, `idEvent`) VALUES
(4, '2025-02-28', 'Confirmée', 1, 20),
(5, '2025-02-26', 'En attente', 556, 21),
(6, '2025-02-25', 'Confirmée', 1, 20),
(10, '2025-03-06T12:34:49.917466400', 'Confirmé', 1, 21),
(11, '2025-03-06T12:40:07.667345', 'Confirmé', 1, 21),
(12, '2025-03-06T12:44:21.003296400', 'Confirmé', 1, 27),
(13, '2025-03-06T12:44:34.228014700', 'Confirmé', 1, 27),
(14, '2025-03-06T12:53:51.661579400', 'Confirmé', 1, 21),
(15, '2025-03-06T12:58:30.918849200', 'Confirmé', 1, 25),
(16, '2025-03-06T13:01:30.447345700', 'Confirmé', 1, 26),
(17, '2025-03-06T13:02:13.757866900', 'Confirmé', 1, 25),
(18, '2025-03-06T14:12:36.012794600', 'Confirmé', 1, 21),
(19, '2025-03-06 14:46:52', 'Confirmé', 1, 21),
(20, '2025-03-07 08:41:23', 'Confirmé', 1, 21),
(21, '2025-03-07 09:05:09', 'Confirmé', 1, 30),
(22, '2025-03-07 09:26:47', 'Confirmé', 1, 21),
(23, '2025-03-07 09:52:35', 'Confirmé', 1, 25);

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
