<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250408092933 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE billet (idBillet INT AUTO_INCREMENT NOT NULL, proprietaire VARCHAR(255) NOT NULL, prix INT NOT NULL, dateAchat DATETIME NOT NULL, type VARCHAR(255) NOT NULL, idEvent INT DEFAULT NULL, INDEX IDX_1F034AF62C6A49BA (idEvent), PRIMARY KEY(idBillet)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE espace (id_espace INT AUTO_INCREMENT NOT NULL, nom_espace VARCHAR(255) NOT NULL, adresse VARCHAR(255) NOT NULL, capacite INT NOT NULL, disponibilite VARCHAR(255) NOT NULL, prix DOUBLE PRECISION NOT NULL, type_espace VARCHAR(255) NOT NULL, image VARCHAR(255) NOT NULL, idUser INT DEFAULT NULL, INDEX IDX_6AB096DFE6E88D7 (idUser), PRIMARY KEY(id_espace)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE event (idEvent INT AUTO_INCREMENT NOT NULL, nomEvent VARCHAR(255) NOT NULL, prix INT NOT NULL, details VARCHAR(255) NOT NULL, date VARCHAR(255) NOT NULL, nbrVisiteurs INT NOT NULL, nomEspace VARCHAR(255) NOT NULL, image VARCHAR(255) NOT NULL, PRIMARY KEY(idEvent)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE fournisseur (id_fournisseur INT AUTO_INCREMENT NOT NULL, nom_fournisseur VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, type VARCHAR(255) NOT NULL, telephone VARCHAR(255) NOT NULL, image_path VARCHAR(255) NOT NULL, PRIMARY KEY(id_fournisseur)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE messenger_messages (id INT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(255) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE organisateur (id_org INT AUTO_INCREMENT NOT NULL, nom_org VARCHAR(255) NOT NULL, prenom_org VARCHAR(255) NOT NULL, description_org VARCHAR(255) NOT NULL, telef INT NOT NULL, idEspace INT DEFAULT NULL, INDEX IDX_4BD76D4465BAA1F7 (idEspace), PRIMARY KEY(id_org)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE paiement (id_paiement INT AUTO_INCREMENT NOT NULL, prix_total INT NOT NULL, mode_paiement VARCHAR(255) NOT NULL, idBillet INT DEFAULT NULL, idUser INT DEFAULT NULL, INDEX IDX_B1DC7A1E7C12E26C (idBillet), INDEX IDX_B1DC7A1EFE6E88D7 (idUser), PRIMARY KEY(id_paiement)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE produit (id_produit INT AUTO_INCREMENT NOT NULL, nom_produit VARCHAR(255) NOT NULL, prix_produit INT NOT NULL, description VARCHAR(255) NOT NULL, categorie VARCHAR(255) NOT NULL, quantite INT NOT NULL, image_path VARCHAR(255) NOT NULL, idFournisseur INT DEFAULT NULL, INDEX IDX_29A5EC2776C3354A (idFournisseur), PRIMARY KEY(id_produit)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE remise (id_remise INT AUTO_INCREMENT NOT NULL, code_promo VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, pourcentage_remise NUMERIC(10, 0) NOT NULL, date_expiration VARCHAR(255) NOT NULL, id_reservation INT NOT NULL, PRIMARY KEY(id_remise)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservation (id_reservation INT AUTO_INCREMENT NOT NULL, date_reservation VARCHAR(255) NOT NULL, statut VARCHAR(255) NOT NULL, idUser INT DEFAULT NULL, idEvent INT DEFAULT NULL, idRemise INT DEFAULT NULL, INDEX IDX_42C84955FE6E88D7 (idUser), INDEX IDX_42C849552C6A49BA (idEvent), INDEX IDX_42C84955726B3D5D (idRemise), PRIMARY KEY(id_reservation)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id_user INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, numero_telephone VARCHAR(255) NOT NULL, addresse VARCHAR(255) NOT NULL, type VARCHAR(255) NOT NULL, genre VARCHAR(255) NOT NULL, enregistrer TINYINT(1) DEFAULT NULL, PRIMARY KEY(id_user)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE billet ADD CONSTRAINT FK_1F034AF62C6A49BA FOREIGN KEY (idEvent) REFERENCES event (idEvent)');
        $this->addSql('ALTER TABLE espace ADD CONSTRAINT FK_6AB096DFE6E88D7 FOREIGN KEY (idUser) REFERENCES user (idUser)');
        $this->addSql('ALTER TABLE organisateur ADD CONSTRAINT FK_4BD76D4465BAA1F7 FOREIGN KEY (idEspace) REFERENCES espace (idEspace)');
        $this->addSql('ALTER TABLE paiement ADD CONSTRAINT FK_B1DC7A1E7C12E26C FOREIGN KEY (idBillet) REFERENCES billet (idBillet)');
        $this->addSql('ALTER TABLE paiement ADD CONSTRAINT FK_B1DC7A1EFE6E88D7 FOREIGN KEY (idUser) REFERENCES user (idUser)');
        $this->addSql('ALTER TABLE produit ADD CONSTRAINT FK_29A5EC2776C3354A FOREIGN KEY (idFournisseur) REFERENCES fournisseur (idFournisseur)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955FE6E88D7 FOREIGN KEY (idUser) REFERENCES user (idUser)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C849552C6A49BA FOREIGN KEY (idEvent) REFERENCES event (idEvent)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955726B3D5D FOREIGN KEY (idRemise) REFERENCES remise (idRemise)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE billet DROP FOREIGN KEY FK_1F034AF62C6A49BA');
        $this->addSql('ALTER TABLE espace DROP FOREIGN KEY FK_6AB096DFE6E88D7');
        $this->addSql('ALTER TABLE organisateur DROP FOREIGN KEY FK_4BD76D4465BAA1F7');
        $this->addSql('ALTER TABLE paiement DROP FOREIGN KEY FK_B1DC7A1E7C12E26C');
        $this->addSql('ALTER TABLE paiement DROP FOREIGN KEY FK_B1DC7A1EFE6E88D7');
        $this->addSql('ALTER TABLE produit DROP FOREIGN KEY FK_29A5EC2776C3354A');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955FE6E88D7');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C849552C6A49BA');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955726B3D5D');
        $this->addSql('DROP TABLE billet');
        $this->addSql('DROP TABLE espace');
        $this->addSql('DROP TABLE event');
        $this->addSql('DROP TABLE fournisseur');
        $this->addSql('DROP TABLE messenger_messages');
        $this->addSql('DROP TABLE organisateur');
        $this->addSql('DROP TABLE paiement');
        $this->addSql('DROP TABLE produit');
        $this->addSql('DROP TABLE remise');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE user');
    }
}
