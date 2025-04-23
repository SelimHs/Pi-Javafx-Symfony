<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;

use App\Repository\ReservationRepository;

#[ORM\Entity(repositoryClass: ReservationRepository::class)]
#[ORM\Table(name: 'reservation')]
class Reservation
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(type: 'integer', name: 'idReservation')]
    private ?int $idReservation = null;

    #[ORM\OneToMany(targetEntity: Remise::class, mappedBy: 'reservation')]
    private Collection $remises;


    #[ORM\Column(type: 'datetime', name: 'dateReservation', nullable: false)]
    #[Assert\NotBlank(message: "La date de réservation ne peut pas être vide.")]
    #[Assert\GreaterThanOrEqual(
        value: "today",
        message: "La date de réservation ne peut pas être dans le passé."
    )]
    private ?\DateTimeInterface $dateReservation = null;

    public function getDateReservation(): ?\DateTimeInterface
    {
        return $this->dateReservation;
    }

    public function setDateReservation(\DateTimeInterface $dateReservation): self
    {
        $this->dateReservation = $dateReservation;
        return $this;
    }
    #[ORM\Column(type: 'string', name: 'statut', nullable: false)]
    #[Assert\NotBlank(message: "Le statut ne peut pas être vide.")]
    #[Assert\Choice(
        choices: ['Confirmée', 'En attente'],
        message: "Le statut doit être 'Confirmée' ou 'En attente'."
    )]
    private ?string $statut = null;

    #[ORM\ManyToOne(targetEntity: User::class, inversedBy: 'reservations')]
    #[ORM\JoinColumn(name: 'idUser', referencedColumnName: 'idUser')]
    #[Assert\NotNull(message: "L'utilisateur ne peut pas être vide.")]
    private ?User $user = null;

    #[ORM\ManyToOne(targetEntity: Event::class, inversedBy: 'reservations')]
    #[ORM\JoinColumn(name: 'idEvent', referencedColumnName: 'idEvent')]
    #[Assert\NotNull(message: "L'événement ne peut pas être vide.")]
    private ?Event $event = null;

    public function __construct()
    {
        $this->remises = new ArrayCollection();
    }

    public function getIdReservation(): ?int
    {
        return $this->idReservation;
    }

    public function setIdReservation(int $idReservation): self
    {
        $this->idReservation = $idReservation;
        return $this;
    }

    public function getStatut(): ?string
    {
        return $this->statut;
    }

    public function setStatut(string $statut): self
    {
        $this->statut = $statut;
        return $this;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;
        return $this;
    }

    public function getEvent(): ?Event
    {
        return $this->event;
    }

    public function setEvent(?Event $event): self
    {
        $this->event = $event;
        return $this;
    }

    /**
     * @return Collection<int, Remise>
     */
    public function getRemises(): Collection
    {
        if (!$this->remises instanceof Collection) {
            $this->remises = new ArrayCollection();
        }
        return $this->remises;
    }

    public function addRemise(Remise $remise): self
    {
        if (!$this->getRemises()->contains($remise)) {
            $this->getRemises()->add($remise);
            $remise->setReservation($this);
        }
        return $this;
    }

    public function removeRemise(Remise $remise): self
    {
        if ($this->getRemises()->removeElement($remise)) {
            if ($remise->getReservation() === $this) {
                $remise->setReservation(null);
            }
        }
        return $this;
    }
}