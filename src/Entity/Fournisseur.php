<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;

use App\Repository\FournisseurRepository;

#[ORM\Entity(repositoryClass: FournisseurRepository::class)]
#[ORM\Table(name: 'fournisseur')]
class Fournisseur
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name :'idFournisseur',type: 'integer')]
    private ?int $idFournisseur = null;

    public function getIdFournisseur(): ?int
    {
        return $this->idFournisseur;
    }

    public function setIdFournisseur(int $idFournisseur): self
    {
        $this->idFournisseur = $idFournisseur;
        return $this;
    }

    #[ORM\Column(name: 'nomFournisseur',type: 'string', nullable: false)]
    #[Assert\NotBlank(message: "Le nom du fournisseur ne peut pas être vide.")]
    #[Assert\Length(
        max: 255,
        maxMessage: "Le nom du fournisseur ne peut pas dépasser {{ limit }} caractères."
    )]
    private ?string $nomFournisseur = null;

    public function getNomFournisseur(): ?string
    {
        return $this->nomFournisseur;
    }

    public function setNomFournisseur(string $nomFournisseur): self
    {
        $this->nomFournisseur = $nomFournisseur;
        return $this;
    }

    #[ORM\Column(type: 'string', nullable: false)]
    #[Assert\NotBlank(message: "La description ne peut pas être vide.")]
    #[Assert\Length(
        min: 10,
        max: 1000,
        minMessage: "La description doit contenir au moins {{ limit }} caractères.",
        maxMessage: "La description ne peut pas dépasser {{ limit }} caractères."
    )]

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

    #[ORM\Column(type: 'string', nullable: false)]
    #[Assert\NotBlank(message: "Le type ne peut pas être vide.")]
    #[Assert\Choice(
        choices: ['grossiste', 'fabricant', 'distributeur', 'autre'],
        message: "Le type doit être l'un des suivants : {{ choices }}."
    )]
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
    #[Assert\NotBlank(message: "Le numéro de téléphone ne peut pas être vide.")]
    #[Assert\Regex(
        pattern: "/^[0-9]{8}$/",
        message: "Le numéro de téléphone doit contenir 8*********."
    )]

    private ?string $telephone = null;

    public function getTelephone(): ?string
    {
        return $this->telephone;
    }

    public function setTelephone(string $telephone): self
    {
        $this->telephone = $telephone;
        return $this;
    }

    #[ORM\Column(name: 'imagePath',type: 'string', nullable: false)]
    private ?string $imagePath = null;

    public function getImagePath(): ?string
    {
        return $this->imagePath;
    }

    public function setImagePath(string $imagePath): self
    {
        $this->imagePath = $imagePath;
        return $this;
    }

    #[ORM\OneToMany(targetEntity: Produit::class, mappedBy: 'fournisseur')]
    private Collection $produits;

    public function __construct()
    {
        $this->produits = new ArrayCollection();
    }

    /**
     * @return Collection<int, Produit>
     */
    public function getProduits(): Collection
    {
        if (!$this->produits instanceof Collection) {
            $this->produits = new ArrayCollection();
        }
        return $this->produits;
    }

    public function addProduit(Produit $produit): self
    {
        if (!$this->getProduits()->contains($produit)) {
            $this->getProduits()->add($produit);
        }
        return $this;
    }

    public function removeProduit(Produit $produit): self
    {
        $this->getProduits()->removeElement($produit);
        return $this;
    }

}
