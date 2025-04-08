<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;

use App\Repository\UserRepository;

#[ORM\Entity(repositoryClass: UserRepository::class)]
#[ORM\Table(name: 'user')]
class User
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "idUser",type: 'integer')] // Retirez name: "idUser" si possible
    private ?int $id = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function setId(int $id): self
    {
        $this->id = $id;
        return $this;
    }



    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $nom = null;

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $prenom = null;

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $password = null;

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $email = null;

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;
        return $this;
    }

    #[ORM\Column(name: 'numeroTelephone', type: 'string', nullable: false)]
    private ?string $numeroTelephone = null;

    public function getNumeroTelephone(): ?string
    {
        return $this->numeroTelephone;
    }

    public function setNumeroTelephone(string $numeroTelephone): self
    {
        $this->numeroTelephone = $numeroTelephone;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $addresse = null;

    public function getAddresse(): ?string
    {
        return $this->addresse;
    }

    public function setAddresse(string $addresse): self
    {
        $this->addresse = $addresse;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $type = null;

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $genre = null;

    public function getGenre(): ?string
    {
        return $this->genre;
    }

    public function setGenre(string $genre): self
    {
        $this->genre = $genre;
        return $this;
    }

    #[ORM\Column(type: 'boolean', nullable: true)]
    private ?bool $enregistrer = null;

    public function isEnregistrer(): ?bool
    {
        return $this->enregistrer;
    }

    public function setEnregistrer(?bool $enregistrer): self
    {
        $this->enregistrer = $enregistrer;
        return $this;
    }

    #[ORM\OneToMany(targetEntity: Espace::class, mappedBy: 'user')]
    private Collection $espaces;

    /**
     * @return Collection<int, Espace>
     */
    public function getEspaces(): Collection
    {
        if (!$this->espaces instanceof Collection) {
            $this->espaces = new ArrayCollection();
        }
        return $this->espaces;
    }

    public function addEspace(Espace $espace): self
    {
        if (!$this->getEspaces()->contains($espace)) {
            $this->getEspaces()->add($espace);
        }
        return $this;
    }

    public function removeEspace(Espace $espace): self
    {
        $this->getEspaces()->removeElement($espace);
        return $this;
    }

    #[ORM\OneToMany(targetEntity: Paiement::class, mappedBy: 'user')]
    private Collection $paiements;

    /**
     * @return Collection<int, Paiement>
     */
    public function getPaiements(): Collection
    {
        if (!$this->paiements instanceof Collection) {
            $this->paiements = new ArrayCollection();
        }
        return $this->paiements;
    }

    public function addPaiement(Paiement $paiement): self
    {
        if (!$this->getPaiements()->contains($paiement)) {
            $this->getPaiements()->add($paiement);
        }
        return $this;
    }

    public function removePaiement(Paiement $paiement): self
    {
        $this->getPaiements()->removeElement($paiement);
        return $this;
    }

    #[ORM\OneToMany(targetEntity: Reservation::class, mappedBy: 'user')]
    private Collection $reservations;

    public function __construct()
    {
        $this->espaces = new ArrayCollection();
        $this->paiements = new ArrayCollection();
        $this->reservations = new ArrayCollection();
    }

    /**
     * @return Collection<int, Reservation>
     */
    public function getReservations(): Collection
    {
        if (!$this->reservations instanceof Collection) {
            $this->reservations = new ArrayCollection();
        }
        return $this->reservations;
    }

    public function addReservation(Reservation $reservation): self
    {
        if (!$this->getReservations()->contains($reservation)) {
            $this->getReservations()->add($reservation);
        }
        return $this;
    }

    public function removeReservation(Reservation $reservation): self
    {
        $this->getReservations()->removeElement($reservation);
        return $this;
    }
}