<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;

use App\Repository\OrganisateurRepository;

#[ORM\Entity(repositoryClass: OrganisateurRepository::class)]
#[ORM\Table(name: 'organisateur')]
class Organisateur
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name:'id_org',type: 'integer')]
    private ?int $id_org = null;

    public function getId_org(): ?int
    {
        return $this->id_org;
    }

    public function setId_org(int $id_org): self
    {
        $this->id_org = $id_org;
        return $this;
    }

    #[ORM\Column(name:'nom_org',type: 'string', nullable: false)]
    private ?string $nom_org = null;

    public function getNom_org(): ?string
    {
        return $this->nom_org;
    }

    public function setNom_org(string $nom_org): self
    {
        $this->nom_org = $nom_org;
        return $this;
    }

    #[ORM\Column(name:'prenom_org',type: 'string', nullable: false)]
    private ?string $prenom_org = null;

    public function getPrenom_org(): ?string
    {
        return $this->prenom_org;
    }

    public function setPrenom_org(string $prenom_org): self
    {
        $this->prenom_org = $prenom_org;
        return $this;
    }

    #[ORM\Column(name:'description_org',type: 'string', nullable: false)]
    private ?string $description_org = null;

    public function getDescription_org(): ?string
    {
        return $this->description_org;
    }

    public function setDescription_org(string $description_org): self
    {
        $this->description_org = $description_org;
        return $this;
    }

    #[ORM\ManyToOne(targetEntity: Espace::class, inversedBy: 'organisateurs')]
    #[ORM\JoinColumn(name: 'idEspace', referencedColumnName: 'idEspace')]
    private ?Espace $espace = null;

    public function getEspace(): ?Espace
    {
        return $this->espace;
    }

    public function setEspace(?Espace $espace): self
    {
        $this->espace = $espace;
        return $this;
    }

    #[ORM\Column(type: 'integer', nullable: false)]
    private ?int $telef = null;

    public function getTelef(): ?int
    {
        return $this->telef;
    }

    public function setTelef(int $telef): self
    {
        $this->telef = $telef;
        return $this;
    }

    public function getIdOrg(): ?int
    {
        return $this->id_org;
    }

    public function getNomOrg(): ?string
    {
        return $this->nom_org;
    }

    public function setNomOrg(string $nom_org): static
    {
        $this->nom_org = $nom_org;

        return $this;
    }

    public function getPrenomOrg(): ?string
    {
        return $this->prenom_org;
    }

    public function setPrenomOrg(string $prenom_org): static
    {
        $this->prenom_org = $prenom_org;

        return $this;
    }

    public function getDescriptionOrg(): ?string
    {
        return $this->description_org;
    }

    public function setDescriptionOrg(string $description_org): static
    {
        $this->description_org = $description_org;

        return $this;
    }

}
