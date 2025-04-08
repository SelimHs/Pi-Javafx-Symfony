<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;

use App\Repository\EspaceRepository;

#[ORM\Entity(repositoryClass: EspaceRepository::class)]
#[ORM\Table(name: 'espace')]
class Espace
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "idEspace", type: 'integer')]
    private ?int $idEspace = null;

    public function getIdEspace(): ?int
    {
        return $this->idEspace;
    }

    public function setIdEspace(int $idEspace): self
    {
        $this->idEspace = $idEspace;
        return $this;
    }

    #[ORM\Column(name: "nomEspace", type: 'string', nullable: false)]
    #[Assert\NotBlank(message: "Le nom de l'espace est requis.")]
    #[Assert\Length(min: 3, minMessage: "Le nom doit contenir au moins 3 caractères.")]
    private ?string $nomEspace = null;


    public function getNomEspace(): ?string
    {
        return $this->nomEspace;
    }

    public function setNomEspace(string $nomEspace): self
    {
        $this->nomEspace = $nomEspace;
        return $this;
    }

    #[ORM\Column(name: "adresse", type: 'string', nullable: false)]
    #[Assert\NotBlank(message: "L'adresse est requise.")]
    private ?string $adresse = null;

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;
        return $this;
    }

    #[ORM\Column(name: "capacite", type: 'integer', nullable: false)]
    #[Assert\NotBlank(message: "La capacité est requise.")]
    #[Assert\Positive(message: "La capacité doit être un nombre positif.")]
    private ?int $capacite = null;

    public function getCapacite(): ?int
    {
        return $this->capacite;
    }

    public function setCapacite(int $capacite): self
    {
        $this->capacite = $capacite;
        return $this;
    }

    #[ORM\Column(name: "disponibilite", type: 'string', nullable: false)]
    private ?string $disponibilite = null;

    public function getDisponibilite(): ?string
    {
        return $this->disponibilite;
    }

    public function setDisponibilite(string $disponibilite): self
    {
        $this->disponibilite = $disponibilite;
        return $this;
    }

    #[ORM\Column(name: "prix", type: 'float', nullable: false)]
    #[Assert\NotBlank(message: "Le prix est requis.")]
    #[Assert\PositiveOrZero(message: "Le prix ne peut pas être négatif.")]
    private ?float $prix = null;

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;
        return $this;
    }

    #[ORM\ManyToOne(targetEntity: User::class, inversedBy: 'espaces')]
    #[ORM\JoinColumn(name: 'idUser', referencedColumnName: 'idUser')]
    private ?User $user = null;


    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;
        return $this;
    }

    #[ORM\Column(name: "Type_espace", type: 'string', nullable: false)]
    private ?string $Type_espace = null;

    public function getType_espace(): ?string
    {
        return $this->Type_espace;
    }

    public function setType_espace(string $Type_espace): self
    {
        $this->Type_espace = $Type_espace;
        return $this;
    }

    #[ORM\Column(name: "image", type: 'string', nullable: false)]
    private ?string $image = null;

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;
        return $this;
    }

    #[ORM\OneToMany(targetEntity: Organisateur::class, mappedBy: 'espace')]
    private Collection $organisateurs;

    public function __construct()
    {
        $this->organisateurs = new ArrayCollection();
    }

    /**
     * @return Collection<int, Organisateur>
     */
    public function getOrganisateurs(): Collection
    {
        if (!$this->organisateurs instanceof Collection) {
            $this->organisateurs = new ArrayCollection();
        }
        return $this->organisateurs;
    }

    public function addOrganisateur(Organisateur $organisateur): self
    {
        if (!$this->getOrganisateurs()->contains($organisateur)) {
            $this->getOrganisateurs()->add($organisateur);
        }
        return $this;
    }

    public function removeOrganisateur(Organisateur $organisateur): self
    {
        $this->getOrganisateurs()->removeElement($organisateur);
        return $this;
    }

    public function getTypeEspace(): ?string
    {
        return $this->Type_espace;
    }

    public function setTypeEspace(string $Type_espace): static
    {
        $this->Type_espace = $Type_espace;

        return $this;
    }
}   