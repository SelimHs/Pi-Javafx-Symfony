<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;

use App\Repository\RemiseRepository;

#[ORM\Entity(repositoryClass: RemiseRepository::class)]
#[ORM\Table(name: 'remise')]
class Remise
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(type: 'integer')]
    private ?int $idRemise = null;

    public function getIdRemise(): ?int
    {
        return $this->idRemise;
    }

    public function setIdRemise(int $idRemise): self
    {
        $this->idRemise = $idRemise;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $codePromo = null;

    public function getCodePromo(): ?string
    {
        return $this->codePromo;
    }

    public function setCodePromo(string $codePromo): self
    {
        $this->codePromo = $codePromo;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $description = null;

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;
        return $this;
    }

    #[ORM\Column(type: 'decimal', nullable: false)]
    private ?float $pourcentageRemise = null;

    public function getPourcentageRemise(): ?float
    {
        return $this->pourcentageRemise;
    }

    public function setPourcentageRemise(float $pourcentageRemise): self
    {
        $this->pourcentageRemise = $pourcentageRemise;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $dateExpiration = null;

    public function getDateExpiration(): ?string
    {
        return $this->dateExpiration;
    }

    public function setDateExpiration(string $dateExpiration): self
    {
        $this->dateExpiration = $dateExpiration;
        return $this;
    }

    #[ORM\Column(type: 'integer', nullable: false)]
    private ?int $idReservation = null;

    public function getIdReservation(): ?int
    {
        return $this->idReservation;
    }

    public function setIdReservation(int $idReservation): self
    {
        $this->idReservation = $idReservation;
        return $this;
    }

}
