<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

use App\Repository\RemiseRepository;

#[ORM\Entity(repositoryClass: RemiseRepository::class)]
#[ORM\Table(name: 'remise')]
class Remise
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(type: 'integer', name: 'idRemise')]
    private ?int $idRemise = null;

    #[ORM\Column(type: 'string', name: 'codePromo', nullable: false)]
    #[Assert\NotBlank(message: "Le code promo ne peut pas être vide.")]
    #[Assert\Length(
        min: 3,
        max: 20,
        minMessage: "Le code promo doit contenir au moins {{ limit }} caractères.",
        maxMessage: "Le code promo ne peut pas dépasser {{ limit }} caractères."
    )]
    private ?string $codePromo = null;

    #[ORM\Column(type: 'string', name: 'description', nullable: false)]
    #[Assert\NotBlank(message: "La description ne peut pas être vide.")]
    #[Assert\Length(
        min: 5,
        max: 255,
        minMessage: "La description doit contenir au moins {{ limit }} caractères.",
        maxMessage: "La description ne peut pas dépasser {{ limit }} caractères."
    )]
    private ?string $description = null;

    #[ORM\Column(type: 'decimal', name: 'pourcentageRemise', nullable: false)]
    #[Assert\NotBlank(message: "Le pourcentage de remise ne peut pas être vide.")]
    #[Assert\Range(
        min: 0,
        max: 100,
        notInRangeMessage: "Le pourcentage de remise doit être compris entre {{ min }} et {{ max }}."
    )]
    private ?float $pourcentageRemise = null;

    #[ORM\Column(type: 'date', name: 'dateExpiration', nullable: false)]
    #[Assert\NotBlank(message: "La date d'expiration ne peut pas être vide.")]
    #[Assert\GreaterThanOrEqual("today", message: "La date d'expiration ne peut pas être dans le passé.")]
    private ?\DateTimeInterface $dateExpiration = null;


    #[ORM\ManyToOne(targetEntity: Reservation::class, inversedBy: 'remises')]
    #[ORM\JoinColumn(name: 'idReservation', referencedColumnName: 'idReservation', nullable: true)]
    private ?Reservation $reservation = null;

    public function getIdRemise(): ?int
    {
        return $this->idRemise;
    }

    public function setIdRemise(int $idRemise): self
    {
        $this->idRemise = $idRemise;
        return $this;
    }

    public function getCodePromo(): ?string
    {
        return $this->codePromo;
    }

    public function setCodePromo(string $codePromo): self
    {
        $this->codePromo = $codePromo;
        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;
        return $this;
    }

    public function getPourcentageRemise(): ?float
    {
        return $this->pourcentageRemise;
    }

    public function setPourcentageRemise(float $pourcentageRemise): self
    {
        $this->pourcentageRemise = $pourcentageRemise;
        return $this;
    }

    public function getDateExpiration(): ?\DateTimeInterface
    {
        return $this->dateExpiration;
    }

    public function setDateExpiration(\DateTimeInterface $dateExpiration): self
    {
        $this->dateExpiration = $dateExpiration;
        return $this;
    }


    public function getReservation(): ?Reservation
    {
        return $this->reservation;
    }

    public function setReservation(?Reservation $reservation): self
    {
        $this->reservation = $reservation;
        return $this;
    }
}