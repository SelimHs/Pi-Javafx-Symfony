<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\ProduitRepository;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: ProduitRepository::class)]
#[ORM\Table(name: 'produit')]
class Produit
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(type: 'integer', name: 'idProduit')]
    private ?int $idProduit = null;

    #[ORM\Column(type: 'string', name: 'nomProduit', nullable: false)]
    #[Assert\NotBlank(message: "Le nom du produit est requis.")]
    #[Assert\Length(
        min: 3,
        max: 50,
        minMessage: "Le nom doit contenir au moins {{ limit }} caractères.",
        maxMessage: "Le nom ne peut pas dépasser {{ limit }} caractères."
    )]
    private ?string $nomProduit = null;

    #[ORM\Column(type: 'integer', name: 'prixProduit', nullable: false)]
    #[Assert\NotNull(message: "Le prix est obligatoire.")]
    #[Assert\Positive(message: "Le prix doit être un nombre positif.")]
    private ?int $prixProduit = null;

    #[ORM\Column(type: 'string', name: 'description', nullable: false)]
    #[Assert\NotBlank(message: "La description est obligatoire.")]
    #[Assert\Length(
        min: 10,
        max: 500,
        minMessage: "La description doit contenir au moins {{ limit }} caractères.",
        maxMessage: "La description ne peut pas dépasser {{ limit }} caractères."
    )]
    private ?string $description = null;

    #[ORM\Column(type: 'string', name: 'categorie', nullable: false)]
    #[Assert\NotBlank(message: "La catégorie est obligatoire.")]
    #[Assert\Length(
        max: 30,
        maxMessage: "La catégorie ne peut pas dépasser {{ limit }} caractères."
    )]
    private ?string $categorie = null;

    #[ORM\Column(type: 'integer', name: 'quantite', nullable: false)]
    #[Assert\NotNull(message: "La quantité est obligatoire.")]
    #[Assert\PositiveOrZero(message: "La quantité doit être positive ou égale à zéro.")]
    private ?int $quantite = null;

    #[ORM\ManyToOne(targetEntity: Fournisseur::class, inversedBy: 'produits')]
    #[ORM\JoinColumn(name: 'idFournisseur', referencedColumnName: 'idFournisseur')]
    #[Assert\NotNull(message: "Le fournisseur est requis.")]
    private ?Fournisseur $fournisseur = null;

    #[ORM\Column(type: 'string', name: 'imagePath', nullable: true)] // Made nullable to match form behavior
    private ?string $imagePath = null;

    // Getters & Setters

    public function getIdProduit(): ?int { return $this->idProduit; }
    public function setIdProduit(int $idProduit): self { $this->idProduit = $idProduit; return $this; }

    public function getNomProduit(): ?string { return $this->nomProduit; }
    public function setNomProduit(?string $nomProduit): self { $this->nomProduit = $nomProduit; return $this; }

    public function getPrixProduit(): ?int { return $this->prixProduit; }
    public function setPrixProduit(int $prixProduit): self { $this->prixProduit = $prixProduit; return $this; }

    public function getDescription(): ?string { return $this->description; }
    public function setDescription(?string $description): self { $this->description = $description; return $this; }

    public function getCategorie(): ?string { return $this->categorie; }
    public function setCategorie(?string $categorie): self { $this->categorie = $categorie; return $this; }

    public function getQuantite(): ?int { return $this->quantite; }
    public function setQuantite(int $quantite): self { $this->quantite = $quantite; return $this; }

    public function getFournisseur(): ?Fournisseur { return $this->fournisseur; }
    public function setFournisseur(?Fournisseur $fournisseur): self { $this->fournisseur = $fournisseur; return $this; }

    public function getImagePath(): ?string { return $this->imagePath; }
    public function setImagePath(?string $imagePath): self { $this->imagePath = $imagePath; return $this; }
}