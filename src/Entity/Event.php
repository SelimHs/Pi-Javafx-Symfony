<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;

use App\Repository\EventRepository;

#[ORM\Entity(repositoryClass: EventRepository::class)]
#[ORM\Table(name: 'event')]
class Event
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: 'idEvent', type: 'integer')]
    private ?int $idEvent = null;


    public function getIdEvent(): ?int
    {
        return $this->idEvent;
    }

    public function setIdEvent(int $idEvent): self
    {
        $this->idEvent = $idEvent;
        return $this;
    }

    #[ORM\Column(name: 'nomEvent', type: 'string', nullable: false)]
    private ?string $nomEvent = null;

    public function getNomEvent(): ?string
    {
        return $this->nomEvent;
    }

    public function setNomEvent(string $nomEvent): self
    {
        $this->nomEvent = $nomEvent;
        return $this;
    }

    #[ORM\Column(name: 'prix', type: 'integer', nullable: false)]
    private ?int $prix = null;

    public function getPrix(): ?int
    {
        return $this->prix;
    }

    public function setPrix(int $prix): self
    {
        $this->prix = $prix;
        return $this;
    }

    #[ORM\Column(name: 'details', type: 'string', nullable: false)]
    private ?string $details = null;

    public function getDetails(): ?string
    {
        return $this->details;
    }

    public function setDetails(string $details): self
    {
        $this->details = $details;
        return $this;
    }

    #[ORM\Column(name: 'date', type: 'string', nullable: false)]
    private ?string $date = null;

    public function getDate(): ?string
    {
        return $this->date;
    }

    public function setDate(string $date): self
    {
        $this->date = $date;
        return $this;
    }

    #[ORM\Column(name: 'nbrVisiteurs', type: 'integer', nullable: false)]
    private ?int $nbrVisiteurs = null;

    public function getNbrVisiteurs(): ?int
    {
        return $this->nbrVisiteurs;
    }

    public function setNbrVisiteurs(int $nbrVisiteurs): self
    {
        $this->nbrVisiteurs = $nbrVisiteurs;
        return $this;
    }

    #[ORM\Column(name: 'nomEspace', type: 'string', nullable: false)]
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

    #[ORM\Column(name: 'image', type: 'string', nullable: false)]
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

    #[ORM\OneToMany(targetEntity: Billet::class, mappedBy: 'event')]
    private Collection $billets;

    /**
     * @return Collection<int, Billet>
     */
    public function getBillets(): Collection
    {
        if (!$this->billets instanceof Collection) {
            $this->billets = new ArrayCollection();
        }
        return $this->billets;
    }

    public function addBillet(Billet $billet): self
    {
        if (!$this->getBillets()->contains($billet)) {
            $this->getBillets()->add($billet);
        }
        return $this;
    }

    public function removeBillet(Billet $billet): self
    {
        $this->getBillets()->removeElement($billet);
        return $this;
    }

    #[ORM\OneToMany(targetEntity: Reservation::class, mappedBy: 'event')]
    private Collection $reservations;

    public function __construct()
    {
        $this->billets = new ArrayCollection();
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
