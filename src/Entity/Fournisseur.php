<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use App\Repository\FournisseurRepository;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: FournisseurRepository::class)]
#[ORM\Table(name: 'fournisseur')]
class Fournisseur
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "idFournisseur", type: "integer")]
    private ?int $idFournisseur = null;

    #[ORM\Column(name: "nomFournisseur", type: "string", length: 255, nullable: false)]
    #[Assert\NotBlank(message: "Le nom du fournisseur ne peut pas être vide.")]
    #[Assert\Length(
        max: 255,
        maxMessage: "Le nom du fournisseur ne peut pas dépasser {{ limit }} caractères."
    )]
    private ?string $nomFournisseur = null;

    #[ORM\Column(name: "description", type: "string", length: 1000, nullable: false)]
    #[Assert\NotBlank(message: "La description ne peut pas être vide.")]
    #[Assert\Length(
        min: 10,
        max: 1000,
        minMessage: "La description doit contenir au moins {{ limit }} caractères.",
        maxMessage: "La description ne peut pas dépasser {{ limit }} caractères."
    )]
    private ?string $description = null;

    #[ORM\Column(name: "type", type: "string", length: 255, nullable: false)]
    #[Assert\NotBlank(message: "Le type ne peut pas être vide.")]
    #[Assert\Choice(
        choices: ['grossiste', 'fabricant', 'distributeur', 'autre'],
        message: "Le type doit être l'un des suivants : {{ choices }}."
    )]
    private ?string $type = null;

    #[ORM\Column(name: "telephone", type: "string", length: 20, nullable: false)]
    #[Assert\NotBlank(message: "Le numéro de téléphone ne peut pas être vide.")]
    #[Assert\Regex(
        pattern: "/^[0-9]{8}$/",
        message: "Le numéro de téléphone doit contenir 8*********."
    )]
    private ?string $telephone = null;

    #[ORM\Column(name: "imagePath", type: "string", length: 255, nullable: true)]
    private ?string $imagePath = null;

    public function getImagePath(): ?string
    {
        return $this->imagePath;
    }

    public function setImagePath(?string $imagePath): self
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

    public function getIdFournisseur(): ?int
    {
        return $this->idFournisseur;
    }

    public function setIdFournisseur(int $idFournisseur): self
    {
        $this->idFournisseur = $idFournisseur;
        return $this;
    }

    public function getNomFournisseur(): ?string
    {
        return $this->nomFournisseur;
    }

    public function setNomFournisseur(string $nomFournisseur): self
    {
        $this->nomFournisseur = $nomFournisseur;
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

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;
        return $this;
    }

    public function getTelephone(): ?string
    {
        return $this->telephone;
    }

    public function setTelephone(string $telephone): self
    {
        $this->telephone = $telephone;
        return $this;
    }



    /**
     * @return Collection<int, Produit>
     */
    public function getProduits(): Collection
    {
        return $this->produits;
    }

    public function addProduit(Produit $produit): self
    {
        if (!$this->produits->contains($produit)) {
            $this->produits->add($produit);
            $produit->setFournisseur($this); // utile pour la relation inverse
        }
        return $this;
    }

    public function removeProduit(Produit $produit): self
    {
        if ($this->produits->removeElement($produit)) {
            if ($produit->getFournisseur() === $this) {
                $produit->setFournisseur(null);
            }
        }
        return $this;
    }
}
