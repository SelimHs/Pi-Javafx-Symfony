<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250407174547 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE billet ADD id_billet INT AUTO_INCREMENT NOT NULL, DROP idBillet, CHANGE proprietaire proprietaire VARCHAR(255) NOT NULL, CHANGE idEvent idEvent INT DEFAULT NULL, CHANGE dateAchat date_achat DATETIME NOT NULL, ADD PRIMARY KEY (id_billet)');
        $this->addSql('ALTER TABLE billet RENAME INDEX idevent TO IDX_1F034AF62C6A49BA');
        $this->addSql('ALTER TABLE espace MODIFY idEspace INT NOT NULL');
        $this->addSql('DROP INDEX `primary` ON espace');
        $this->addSql('ALTER TABLE espace ADD nom_espace VARCHAR(255) NOT NULL, DROP nomEspace, CHANGE disponibilite disponibilite VARCHAR(255) NOT NULL, CHANGE idUser idUser INT DEFAULT NULL, CHANGE Type_espace type_espace VARCHAR(255) NOT NULL, CHANGE image image VARCHAR(255) NOT NULL, CHANGE idEspace id_espace INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE espace ADD PRIMARY KEY (id_espace)');
        $this->addSql('ALTER TABLE espace RENAME INDEX iduser TO IDX_6AB096DFE6E88D7');
        $this->addSql('ALTER TABLE event MODIFY idEvent INT NOT NULL');
        $this->addSql('DROP INDEX `primary` ON event');
        $this->addSql('ALTER TABLE event ADD nom_event VARCHAR(255) NOT NULL, ADD nom_espace VARCHAR(255) NOT NULL, DROP nomEvent, DROP nomEspace, CHANGE date date VARCHAR(255) NOT NULL, CHANGE image image VARCHAR(255) NOT NULL, CHANGE idEvent id_event INT AUTO_INCREMENT NOT NULL, CHANGE nbrVisiteurs nbr_visiteurs INT NOT NULL');
        $this->addSql('ALTER TABLE event ADD PRIMARY KEY (id_event)');
        $this->addSql('ALTER TABLE fournisseur MODIFY idFournisseur INT NOT NULL');
        $this->addSql('DROP INDEX `primary` ON fournisseur');
        $this->addSql('ALTER TABLE fournisseur ADD nom_fournisseur VARCHAR(255) NOT NULL, ADD image_path VARCHAR(255) NOT NULL, DROP nomFournisseur, DROP imagePath, CHANGE type type VARCHAR(255) NOT NULL, CHANGE telephone telephone VARCHAR(255) NOT NULL, CHANGE idFournisseur id_fournisseur INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE fournisseur ADD PRIMARY KEY (id_fournisseur)');
        $this->addSql('ALTER TABLE organisateur DROP FOREIGN KEY fk_org');
        $this->addSql('ALTER TABLE organisateur CHANGE description_org description_org VARCHAR(255) NOT NULL, CHANGE idEspace idEspace INT DEFAULT NULL');
        $this->addSql('ALTER TABLE organisateur ADD CONSTRAINT FK_4BD76D4465BAA1F7 FOREIGN KEY (idEspace) REFERENCES espace (idEspace)');
        $this->addSql('ALTER TABLE organisateur RENAME INDEX fk_org TO IDX_4BD76D4465BAA1F7');
        $this->addSql('ALTER TABLE paiement MODIFY idPaiement INT NOT NULL');
        $this->addSql('DROP INDEX `primary` ON paiement');
        $this->addSql('ALTER TABLE paiement CHANGE idBillet idBillet INT DEFAULT NULL, CHANGE idUser idUser INT DEFAULT NULL, CHANGE idPaiement id_paiement INT AUTO_INCREMENT NOT NULL, CHANGE prixTotal prix_total INT NOT NULL, CHANGE modePaiement mode_paiement VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE paiement ADD CONSTRAINT FK_B1DC7A1E7C12E26C FOREIGN KEY (idBillet) REFERENCES billet (idBillet)');
        $this->addSql('ALTER TABLE paiement ADD PRIMARY KEY (id_paiement)');
        $this->addSql('ALTER TABLE paiement RENAME INDEX idbillet TO IDX_B1DC7A1E7C12E26C');
        $this->addSql('ALTER TABLE paiement RENAME INDEX idpuser TO IDX_B1DC7A1EFE6E88D7');
        $this->addSql('ALTER TABLE produit MODIFY idProduit INT NOT NULL');
        $this->addSql('DROP INDEX `primary` ON produit');
        $this->addSql('ALTER TABLE produit ADD nom_produit VARCHAR(255) NOT NULL, ADD image_path VARCHAR(255) NOT NULL, DROP nomProduit, DROP imagePath, CHANGE categorie categorie VARCHAR(255) NOT NULL, CHANGE idFournisseur idFournisseur INT DEFAULT NULL, CHANGE idProduit id_produit INT AUTO_INCREMENT NOT NULL, CHANGE prixProduit prix_produit INT NOT NULL');
        $this->addSql('ALTER TABLE produit ADD PRIMARY KEY (id_produit)');
        $this->addSql('ALTER TABLE produit RENAME INDEX idfournisseur TO IDX_29A5EC2776C3354A');
        $this->addSql('ALTER TABLE remise MODIFY idRemise INT NOT NULL');
        $this->addSql('DROP INDEX fk_remise_reservation ON remise');
        $this->addSql('DROP INDEX `primary` ON remise');
        $this->addSql('ALTER TABLE remise ADD pourcentage_remise NUMERIC(10, 0) NOT NULL, ADD date_expiration VARCHAR(255) NOT NULL, DROP pourcentageRemise, DROP dateExpiration, CHANGE idRemise id_remise INT AUTO_INCREMENT NOT NULL, CHANGE codePromo code_promo VARCHAR(255) NOT NULL, CHANGE idReservation id_reservation INT NOT NULL');
        $this->addSql('ALTER TABLE remise ADD PRIMARY KEY (id_remise)');
        $this->addSql('ALTER TABLE reservation MODIFY idReservation INT NOT NULL');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY fk_reservation_remise');
        $this->addSql('DROP INDEX `primary` ON reservation');
        $this->addSql('ALTER TABLE reservation ADD date_reservation VARCHAR(255) NOT NULL, DROP dateReservation, CHANGE statut statut VARCHAR(255) NOT NULL, CHANGE idUser idUser INT DEFAULT NULL, CHANGE idEvent idEvent INT DEFAULT NULL, CHANGE idReservation id_reservation INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955726B3D5D FOREIGN KEY (idRemise) REFERENCES remise (idRemise)');
        $this->addSql('ALTER TABLE reservation ADD PRIMARY KEY (id_reservation)');
        $this->addSql('ALTER TABLE reservation RENAME INDEX idruser TO IDX_42C84955FE6E88D7');
        $this->addSql('ALTER TABLE reservation RENAME INDEX idrevent TO IDX_42C849552C6A49BA');
        $this->addSql('ALTER TABLE reservation RENAME INDEX fk_reservation_remise TO IDX_42C84955726B3D5D');
        $this->addSql('ALTER TABLE user MODIFY idUser INT NOT NULL');
        $this->addSql('DROP INDEX `primary` ON user');
        $this->addSql('ALTER TABLE user ADD numero_telephone VARCHAR(255) NOT NULL, DROP numeroTelephone, CHANGE nom nom VARCHAR(255) NOT NULL, CHANGE prenom prenom VARCHAR(255) NOT NULL, CHANGE email email VARCHAR(255) NOT NULL, CHANGE addresse addresse VARCHAR(255) NOT NULL, CHANGE enregistrer enregistrer TINYINT(1) DEFAULT NULL, CHANGE idUser id_user INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE user ADD PRIMARY KEY (id_user)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE event MODIFY id_event INT NOT NULL');
        $this->addSql('DROP INDEX `PRIMARY` ON event');
        $this->addSql('ALTER TABLE event ADD nomEvent VARCHAR(100) NOT NULL, ADD nomEspace VARCHAR(100) NOT NULL, DROP nom_event, DROP nom_espace, CHANGE date date VARCHAR(100) NOT NULL, CHANGE image image VARCHAR(550) NOT NULL, CHANGE id_event idEvent INT AUTO_INCREMENT NOT NULL, CHANGE nbr_visiteurs nbrVisiteurs INT NOT NULL');
        $this->addSql('ALTER TABLE event ADD PRIMARY KEY (idEvent)');
        $this->addSql('ALTER TABLE billet MODIFY id_billet INT NOT NULL');
        $this->addSql('DROP INDEX `primary` ON billet');
        $this->addSql('ALTER TABLE billet ADD idBillet INT NOT NULL, DROP id_billet, CHANGE proprietaire proprietaire VARCHAR(150) NOT NULL, CHANGE idEvent idEvent INT NOT NULL, CHANGE date_achat dateAchat DATETIME NOT NULL');
        $this->addSql('ALTER TABLE billet RENAME INDEX idx_1f034af62c6a49ba TO idEvent');
        $this->addSql('ALTER TABLE paiement MODIFY id_paiement INT NOT NULL');
        $this->addSql('ALTER TABLE paiement DROP FOREIGN KEY FK_B1DC7A1E7C12E26C');
        $this->addSql('DROP INDEX `PRIMARY` ON paiement');
        $this->addSql('ALTER TABLE paiement CHANGE idBillet idBillet INT NOT NULL, CHANGE idUser idUser INT NOT NULL, CHANGE id_paiement idPaiement INT AUTO_INCREMENT NOT NULL, CHANGE prix_total prixTotal INT NOT NULL, CHANGE mode_paiement modePaiement VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE paiement ADD PRIMARY KEY (idPaiement)');
        $this->addSql('ALTER TABLE paiement RENAME INDEX idx_b1dc7a1e7c12e26c TO idBillet');
        $this->addSql('ALTER TABLE paiement RENAME INDEX idx_b1dc7a1efe6e88d7 TO idPUser');
        $this->addSql('ALTER TABLE fournisseur MODIFY id_fournisseur INT NOT NULL');
        $this->addSql('DROP INDEX `PRIMARY` ON fournisseur');
        $this->addSql('ALTER TABLE fournisseur ADD nomFournisseur VARCHAR(100) NOT NULL, ADD imagePath VARCHAR(550) NOT NULL, DROP nom_fournisseur, DROP image_path, CHANGE type type VARCHAR(100) NOT NULL, CHANGE telephone telephone VARCHAR(25) NOT NULL, CHANGE id_fournisseur idFournisseur INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE fournisseur ADD PRIMARY KEY (idFournisseur)');
        $this->addSql('ALTER TABLE organisateur DROP FOREIGN KEY FK_4BD76D4465BAA1F7');
        $this->addSql('ALTER TABLE organisateur CHANGE description_org description_org VARCHAR(540) NOT NULL, CHANGE idEspace idEspace INT NOT NULL');
        $this->addSql('ALTER TABLE organisateur ADD CONSTRAINT fk_org FOREIGN KEY (idEspace) REFERENCES espace (idEspace) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE organisateur RENAME INDEX idx_4bd76d4465baa1f7 TO fk_org');
        $this->addSql('ALTER TABLE espace MODIFY id_espace INT NOT NULL');
        $this->addSql('DROP INDEX `PRIMARY` ON espace');
        $this->addSql('ALTER TABLE espace ADD nomEspace VARCHAR(100) NOT NULL, DROP nom_espace, CHANGE disponibilite disponibilite VARCHAR(200) NOT NULL, CHANGE type_espace Type_espace VARCHAR(250) NOT NULL, CHANGE image image VARCHAR(550) NOT NULL, CHANGE idUser idUser INT NOT NULL, CHANGE id_espace idEspace INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE espace ADD PRIMARY KEY (idEspace)');
        $this->addSql('ALTER TABLE espace RENAME INDEX idx_6ab096dfe6e88d7 TO idUser');
        $this->addSql('ALTER TABLE produit MODIFY id_produit INT NOT NULL');
        $this->addSql('DROP INDEX `PRIMARY` ON produit');
        $this->addSql('ALTER TABLE produit ADD nomProduit VARCHAR(100) NOT NULL, ADD imagePath VARCHAR(550) NOT NULL, DROP nom_produit, DROP image_path, CHANGE categorie categorie VARCHAR(100) NOT NULL, CHANGE idFournisseur idFournisseur INT NOT NULL, CHANGE id_produit idProduit INT AUTO_INCREMENT NOT NULL, CHANGE prix_produit prixProduit INT NOT NULL');
        $this->addSql('ALTER TABLE produit ADD PRIMARY KEY (idProduit)');
        $this->addSql('ALTER TABLE produit RENAME INDEX idx_29a5ec2776c3354a TO idFournisseur');
        $this->addSql('ALTER TABLE reservation MODIFY id_reservation INT NOT NULL');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955726B3D5D');
        $this->addSql('DROP INDEX `PRIMARY` ON reservation');
        $this->addSql('ALTER TABLE reservation ADD dateReservation VARCHAR(100) NOT NULL, DROP date_reservation, CHANGE statut statut VARCHAR(100) NOT NULL, CHANGE idUser idUser INT NOT NULL, CHANGE idEvent idEvent INT NOT NULL, CHANGE id_reservation idReservation INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT fk_reservation_remise FOREIGN KEY (idRemise) REFERENCES remise (idRemise) ON UPDATE NO ACTION ON DELETE SET NULL');
        $this->addSql('ALTER TABLE reservation ADD PRIMARY KEY (idReservation)');
        $this->addSql('ALTER TABLE reservation RENAME INDEX idx_42c84955fe6e88d7 TO idRUser');
        $this->addSql('ALTER TABLE reservation RENAME INDEX idx_42c849552c6a49ba TO idREvent');
        $this->addSql('ALTER TABLE reservation RENAME INDEX idx_42c84955726b3d5d TO fk_reservation_remise');
        $this->addSql('ALTER TABLE remise MODIFY id_remise INT NOT NULL');
        $this->addSql('DROP INDEX `PRIMARY` ON remise');
        $this->addSql('ALTER TABLE remise ADD codePromo VARCHAR(255) NOT NULL, ADD pourcentageRemise DOUBLE PRECISION NOT NULL, ADD dateExpiration VARCHAR(100) NOT NULL, DROP code_promo, DROP pourcentage_remise, DROP date_expiration, CHANGE id_remise idRemise INT AUTO_INCREMENT NOT NULL, CHANGE id_reservation idReservation INT NOT NULL');
        $this->addSql('CREATE INDEX fk_remise_reservation ON remise (idReservation)');
        $this->addSql('ALTER TABLE remise ADD PRIMARY KEY (idRemise)');
        $this->addSql('ALTER TABLE user MODIFY id_user INT NOT NULL');
        $this->addSql('DROP INDEX `PRIMARY` ON user');
        $this->addSql('ALTER TABLE user ADD numeroTelephone VARCHAR(250) NOT NULL, DROP numero_telephone, CHANGE nom nom VARCHAR(158) NOT NULL, CHANGE prenom prenom VARCHAR(100) NOT NULL, CHANGE email email VARCHAR(100) NOT NULL, CHANGE addresse addresse VARCHAR(100) NOT NULL, CHANGE enregistrer enregistrer TINYINT(1) DEFAULT 0, CHANGE id_user idUser INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE user ADD PRIMARY KEY (idUser)');
    }
}
