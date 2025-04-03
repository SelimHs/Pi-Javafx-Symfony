<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

final class Version20250403113843 extends AbstractMigration
{
    public function getDescription(): string
    {
        return 'Fix foreign key constraint errors and apply column renaming with correct ordering';
    }

    public function up(Schema $schema): void
    {
        // Create Messenger table
        $this->addSql('CREATE TABLE messenger_messages (
            id BIGINT AUTO_INCREMENT NOT NULL,
            body LONGTEXT NOT NULL,
            headers LONGTEXT NOT NULL,
            queue_name VARCHAR(190) NOT NULL,
            created_at DATETIME NOT NULL COMMENT \'(DC2Type:datetime_immutable)\',
            available_at DATETIME NOT NULL COMMENT \'(DC2Type:datetime_immutable)\',
            delivered_at DATETIME DEFAULT NULL COMMENT \'(DC2Type:datetime_immutable)\',
            INDEX IDX_75EA56E0FB7336F0 (queue_name),
            INDEX IDX_75EA56E0E3BD61CE (available_at),
            INDEX IDX_75EA56E016BA31DB (delivered_at),
            PRIMARY KEY(id)
        ) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');

        // Drop foreign key first (important)
        $this->addSql('ALTER TABLE billet DROP FOREIGN KEY FK_1F034AF62C6A49BA');

        // Modify and rename columns in billet
        $this->addSql('ALTER TABLE billet DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE billet 
            CHANGE proprietaire proprietaire VARCHAR(255) NOT NULL,
            CHANGE idEvent idEvent INT DEFAULT NULL,
            CHANGE idBillet id_billet INT AUTO_INCREMENT NOT NULL,
            CHANGE dateAchat date_achat DATETIME NOT NULL
        ');
        $this->addSql('ALTER TABLE billet ADD PRIMARY KEY (id_billet)');
        $this->addSql('ALTER TABLE billet RENAME INDEX idevent TO IDX_1F034AF62C6A49BA');
        $this->addSql('ALTER TABLE billet ADD CONSTRAINT FK_1F034AF62C6A49BA FOREIGN KEY (idEvent) REFERENCES event (id_event)');

        // Continue with similar pattern for other tables...

        // espace
        $this->addSql('ALTER TABLE espace DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE espace 
            CHANGE disponibilite disponibilite VARCHAR(255) NOT NULL,
            CHANGE idUser idUser INT DEFAULT NULL,
            CHANGE Type_espace type_espace VARCHAR(255) NOT NULL,
            CHANGE image image VARCHAR(255) NOT NULL,
            CHANGE idEspace id_espace INT AUTO_INCREMENT NOT NULL,
            DROP nomEspace,
            ADD nom_espace VARCHAR(255) NOT NULL
        ');
        $this->addSql('ALTER TABLE espace ADD PRIMARY KEY (id_espace)');
        $this->addSql('ALTER TABLE espace RENAME INDEX iduser TO IDX_6AB096DFE6E88D7');

        // event
        $this->addSql('ALTER TABLE event DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE event 
            CHANGE idEvent id_event INT AUTO_INCREMENT NOT NULL,
            CHANGE date date VARCHAR(255) NOT NULL,
            CHANGE image image VARCHAR(255) NOT NULL,
            CHANGE nbrVisiteurs nbr_visiteurs INT NOT NULL,
            DROP nomEvent,
            DROP nomEspace,
            ADD nom_event VARCHAR(255) NOT NULL,
            ADD nom_espace VARCHAR(255) NOT NULL
        ');
        $this->addSql('ALTER TABLE event ADD PRIMARY KEY (id_event)');

        // fournisseur
        $this->addSql('ALTER TABLE fournisseur DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE fournisseur 
            CHANGE idFournisseur id_fournisseur INT AUTO_INCREMENT NOT NULL,
            CHANGE type type VARCHAR(255) NOT NULL,
            CHANGE telephone telephone VARCHAR(255) NOT NULL,
            DROP nomFournisseur,
            DROP imagePath,
            ADD nom_fournisseur VARCHAR(255) NOT NULL,
            ADD image_path VARCHAR(255) NOT NULL
        ');
        $this->addSql('ALTER TABLE fournisseur ADD PRIMARY KEY (id_fournisseur)');

        // organisateur
        $this->addSql('ALTER TABLE organisateur DROP FOREIGN KEY fk_org');
        $this->addSql('ALTER TABLE organisateur 
            CHANGE description_org description_org VARCHAR(255) NOT NULL,
            CHANGE idEspace idEspace INT DEFAULT NULL
        ');
        $this->addSql('ALTER TABLE organisateur ADD CONSTRAINT FK_4BD76D4465BAA1F7 FOREIGN KEY (idEspace) REFERENCES espace (id_espace)');
        $this->addSql('ALTER TABLE organisateur RENAME INDEX fk_org TO IDX_4BD76D4465BAA1F7');

        // paiement
        $this->addSql('ALTER TABLE paiement DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE paiement 
            CHANGE idBillet idBillet INT DEFAULT NULL,
            CHANGE idUser idUser INT DEFAULT NULL,
            CHANGE idPaiement id_paiement INT AUTO_INCREMENT NOT NULL,
            CHANGE prixTotal prix_total INT NOT NULL,
            CHANGE modePaiement mode_paiement VARCHAR(255) NOT NULL
        ');
        $this->addSql('ALTER TABLE paiement ADD PRIMARY KEY (id_paiement)');
        $this->addSql('ALTER TABLE paiement RENAME INDEX idbillet TO IDX_B1DC7A1E7C12E26C');
        $this->addSql('ALTER TABLE paiement RENAME INDEX idpuser TO IDX_B1DC7A1EFE6E88D7');

        // produit
        $this->addSql('ALTER TABLE produit DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE produit 
            CHANGE idProduit id_produit INT AUTO_INCREMENT NOT NULL,
            CHANGE prixProduit prix_produit INT NOT NULL,
            CHANGE idFournisseur idFournisseur INT DEFAULT NULL,
            CHANGE categorie categorie VARCHAR(255) NOT NULL,
            DROP nomProduit,
            DROP imagePath,
            ADD nom_produit VARCHAR(255) NOT NULL,
            ADD image_path VARCHAR(255) NOT NULL
        ');
        $this->addSql('ALTER TABLE produit ADD PRIMARY KEY (id_produit)');
        $this->addSql('ALTER TABLE produit RENAME INDEX idfournisseur TO IDX_29A5EC2776C3354A');

        // remise
        $this->addSql('ALTER TABLE remise DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE remise 
            CHANGE idRemise id_remise INT AUTO_INCREMENT NOT NULL,
            CHANGE codePromo code_promo VARCHAR(255) NOT NULL,
            CHANGE idReservation id_reservation INT NOT NULL,
            DROP pourcentageRemise,
            DROP dateExpiration,
            ADD pourcentage_remise NUMERIC(10, 0) NOT NULL,
            ADD date_expiration VARCHAR(255) NOT NULL
        ');
        $this->addSql('ALTER TABLE remise ADD PRIMARY KEY (id_remise)');

        // reservation
        $this->addSql('ALTER TABLE reservation DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE reservation 
            CHANGE idReservation id_reservation INT AUTO_INCREMENT NOT NULL,
            CHANGE statut statut VARCHAR(255) NOT NULL,
            CHANGE idUser idUser INT DEFAULT NULL,
            CHANGE idEvent idEvent INT DEFAULT NULL,
            DROP dateReservation,
            ADD date_reservation VARCHAR(255) NOT NULL
        ');
        $this->addSql('ALTER TABLE reservation ADD PRIMARY KEY (id_reservation)');
        $this->addSql('ALTER TABLE reservation RENAME INDEX idruser TO IDX_42C84955FE6E88D7');
        $this->addSql('ALTER TABLE reservation RENAME INDEX idrevent TO IDX_42C849552C6A49BA');

        // user
        $this->addSql('ALTER TABLE user DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE user 
            CHANGE idUser id_user INT AUTO_INCREMENT NOT NULL,
            CHANGE nom nom VARCHAR(255) NOT NULL,
            CHANGE prenom prenom VARCHAR(255) NOT NULL,
            CHANGE email email VARCHAR(255) NOT NULL,
            CHANGE addresse addresse VARCHAR(255) NOT NULL,
            CHANGE enregistrer enregistrer TINYINT(1) DEFAULT NULL,
            DROP numeroTelephone,
            ADD numero_telephone VARCHAR(255) NOT NULL
        ');
        $this->addSql('ALTER TABLE user ADD PRIMARY KEY (id_user)');
    }

    public function down(Schema $schema): void
    {
        // You can keep or modify the auto-generated down() if needed
        // Consider reversing the `up()` changes properly
    }
}
