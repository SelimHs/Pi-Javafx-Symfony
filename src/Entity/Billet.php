<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;

use App\Repository\BilletRepository;

#[ORM\Entity(repositoryClass: BilletRepository::class)]
#[ORM\Table(name: 'billet')]
class Billet
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: 'idBillet',type: 'integer')]
    private ?int $idBillet = null;

    public function getIdBillet(): ?int
    {
        return $this->idBillet;
    }

    public function setIdBillet(int $idBillet): self
    {
        $this->idBillet = $idBillet;
        return $this;
    }

    #[ORM\Column(name: 'proprietaire',type: 'string', nullable: false)]
    #[Assert\NotBlank(message: 'Le nom du propriétaire est obligatoire.')]
    #[Assert\Length(min: 3, max: 50, minMessage: 'Le nom du propriétaire doit contenir au moins {{ limit }} caractères.')]
    private ?string $proprietaire = null;

    public function getProprietaire(): ?string
    {
        return $this->proprietaire;
    }

    public function setProprietaire(string $proprietaire): self
    {
        $this->proprietaire = $proprietaire;
        return $this;
    }

    #[ORM\Column(name: 'prix',type: 'integer', nullable: false)]
    #[Assert\NotBlank(message: 'Le prix est obligatoire.')]
    #[Assert\Positive(message: 'Le prix doit être un entier positif.')]
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

    #[ORM\Column(name: 'dateAchat',type: 'datetime', nullable: false)]
    private ?\DateTimeInterface $dateAchat = null;

    public function getDateAchat(): ?\DateTimeInterface
    {
        return $this->dateAchat;
    }

    public function setDateAchat(\DateTimeInterface $dateAchat): self
    {
        $this->dateAchat = $dateAchat;
        return $this;
    }

    #[ORM\Column(name: 'type', type: 'string', nullable: false)]
    #[Assert\NotBlank(message: 'Le type de billet est obligatoire.')]
    #[Assert\Choice(choices: ['SIMPLE', 'DUO', 'VIP'], message: 'Choisissez un type valide (SIMPLE, DUO, VIP).')]
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

    #[ORM\ManyToOne(targetEntity: Event::class, inversedBy: 'billets')]
    #[ORM\JoinColumn(name: 'idEvent', referencedColumnName: 'idEvent')]
    #[Assert\NotNull(message: 'Veuillez sélectionner un événement.')]
    private ?Event $event = null;

    public function getEvent(): ?Event
    {
        return $this->event;
    }

    public function setEvent(?Event $event): self
    {
        $this->event = $event;
        return $this;
    }

    #[ORM\OneToMany(targetEntity: Paiement::class, mappedBy: 'billet')]
    private Collection $paiements;

    public function __construct()
    {
        $this->paiements = new ArrayCollection();
    }

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

}
