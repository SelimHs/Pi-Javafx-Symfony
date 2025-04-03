<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;

use App\Repository\PaiementRepository;

#[ORM\Entity(repositoryClass: PaiementRepository::class)]
#[ORM\Table(name: 'paiement')]
class Paiement
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(type: 'integer')]
    private ?int $idPaiement = null;

    public function getIdPaiement(): ?int
    {
        return $this->idPaiement;
    }

    public function setIdPaiement(int $idPaiement): self
    {
        $this->idPaiement = $idPaiement;
        return $this;
    }

    #[ORM\Column(type: 'integer', nullable: false)]
    private ?int $prixTotal = null;

    public function getPrixTotal(): ?int
    {
        return $this->prixTotal;
    }

    public function setPrixTotal(int $prixTotal): self
    {
        $this->prixTotal = $prixTotal;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $modePaiement = null;

    public function getModePaiement(): ?string
    {
        return $this->modePaiement;
    }

    public function setModePaiement(string $modePaiement): self
    {
        $this->modePaiement = $modePaiement;
        return $this;
    }

    #[ORM\ManyToOne(targetEntity: Billet::class, inversedBy: 'paiements')]
    #[ORM\JoinColumn(name: 'idBillet', referencedColumnName: 'idBillet')]
    private ?Billet $billet = null;

    public function getBillet(): ?Billet
    {
        return $this->billet;
    }

    public function setBillet(?Billet $billet): self
    {
        $this->billet = $billet;
        return $this;
    }

    #[ORM\ManyToOne(targetEntity: User::class, inversedBy: 'paiements')]
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

}
