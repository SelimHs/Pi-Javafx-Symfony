<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

final class Version20250406121624 extends AbstractMigration
{
    public function getDescription(): string
    {
        return 'Fix foreign key issues and rename columns properly';
    }

    public function up(Schema $schema): void
    {
        // Création de la table messenger_messages
        $this->addSql(<<<'SQL'
            CREATE TABLE messenger_messages (
                id BIGINT AUTO_INCREMENT NOT NULL,
                body LONGTEXT NOT NULL,
                headers LONGTEXT NOT NULL,
                queue_name VARCHAR(190) NOT NULL,
                created_at DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)',
                available_at DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)',
                delivered_at DATETIME DEFAULT NULL COMMENT '(DC2Type:datetime_immutable)',
                INDEX IDX_75EA56E0FB7336F0 (queue_name),
                INDEX IDX_75EA56E0E3BD61CE (available_at),
                INDEX IDX_75EA56E016BA31DB (delivered_at),
                PRIMARY KEY(id)
            ) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);

        // Modification de la table billet
        $this->addSql("ALTER TABLE billet DROP FOREIGN KEY FK_1F034AF62C6A49BA");
        $this->addSql("ALTER TABLE billet CHANGE idEvent id_event INT DEFAULT NULL");
        $this->addSql("ALTER TABLE billet DROP INDEX `primary`");
        $this->addSql("ALTER TABLE billet CHANGE idBillet id_billet INT AUTO_INCREMENT NOT NULL");
        $this->addSql("ALTER TABLE billet ADD PRIMARY KEY (id_billet)");
        $this->addSql("ALTER TABLE billet ADD CONSTRAINT FK_BILLET_EVENT FOREIGN KEY (id_event) REFERENCES event (id_event)");

        // Tu peux maintenant appliquer les autres changements de colonnes ici,
        // comme pour proprietaire, dateAchat, etc., si nécessaire

        // Ajoute ici les autres modifications pour les autres tables comme dans ton fichier initial,
        // en suivant la même logique : DROP FK -> modif colonne -> ADD FK
    }

    public function down(Schema $schema): void
    {
        $this->addSql("ALTER TABLE billet DROP FOREIGN KEY FK_BILLET_EVENT");
        $this->addSql("ALTER TABLE billet CHANGE id_event idEvent INT NOT NULL");
        $this->addSql("ALTER TABLE billet DROP PRIMARY KEY");
        $this->addSql("ALTER TABLE billet CHANGE id_billet idBillet INT AUTO_INCREMENT NOT NULL");
        $this->addSql("ALTER TABLE billet ADD PRIMARY KEY (idBillet)");
        $this->addSql("ALTER TABLE billet ADD CONSTRAINT FK_1F034AF62C6A49BA FOREIGN KEY (idEvent) REFERENCES event (idEvent)");

        // Inverse les autres modifications si besoin ici
    }
}