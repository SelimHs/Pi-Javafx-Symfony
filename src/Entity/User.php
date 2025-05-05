<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use App\Repository\UserRepository;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Security\Core\User\PasswordAuthenticatedUserInterface;

#[ORM\Entity(repositoryClass: UserRepository::class)]
#[ORM\Table(name: 'user')]
#[UniqueEntity(fields: ['email'], message: 'There is already an account with this email')]
class User implements UserInterface, PasswordAuthenticatedUserInterface
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "idUser", type: 'integer')]
    private ?int $id = null;

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $nom = null;

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $prenom = null;

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $password = null;

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $email = null;

    #[ORM\Column(name: 'numeroTelephone', type: 'string', nullable: false)]
    private ?string $numeroTelephone = null;

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $addresse = null;

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $type = null; // 👈 Important: type = 'admin' ou 'user'

    #[ORM\Column(type: 'string', nullable: false)]
    private ?string $genre = null;

    #[ORM\Column(type: 'boolean', nullable: true)]
    private ?bool $enregistrer = null;

    #[ORM\OneToMany(targetEntity: Espace::class, mappedBy: 'user')]
    private Collection $espaces;

    #[ORM\Column(name: "is_verified", type: "boolean")]
    private bool $isVerified = false;

    #[ORM\Column(type: 'string', nullable: true)]
    private ?string $verificationToken = null;
    
    #[ORM\Column(type: 'datetime', nullable: true)]
    private ?\DateTimeInterface $tokenExpiresAt = null;
    #[ORM\Column(type: 'string', length: 255, nullable: true)]
    private ?string $profileImage = null;
    #[ORM\Column(type: 'boolean')]
    private $isAccepted = false;
    public function getProfileImage(): ?string
    {
    return $this->profileImage;
    }
    public function setProfileImage(?string $profileImage): self
    {
    $this->profileImage = $profileImage;
    return $this;
    }
    public function __construct()
    {
        $this->espaces = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;
        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;
        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;
        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;
        return $this;
    }

    public function getNumeroTelephone(): ?string
    {
        return $this->numeroTelephone;
    }

    public function setNumeroTelephone(string $numeroTelephone): self
    {
        $this->numeroTelephone = $numeroTelephone;
        return $this;
    }

    public function getAddresse(): ?string
    {
        return $this->addresse;
    }

    public function setAddresse(string $addresse): self
    {
        $this->addresse = $addresse;
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

    public function getGenre(): ?string
    {
        return $this->genre;
    }

    public function setGenre(string $genre): self
    {
        $this->genre = $genre;
        return $this;
    }

    public function isEnregistrer(): ?bool
    {
        return $this->enregistrer;
    }

    public function setEnregistrer(?bool $enregistrer): self
    {
        $this->enregistrer = $enregistrer;
        return $this;
    }

    public function getEspaces(): Collection
    {
        return $this->espaces;
    }

    public function addEspace(Espace $espace): self
    {
        if (!$this->espaces->contains($espace)) {
            $this->espaces->add($espace);
        }
        return $this;
    }

    public function removeEspace(Espace $espace): self
    {
        $this->espaces->removeElement($espace);
        return $this;
    }

    public function eraseCredentials(): void{}

    public function getUserIdentifier(): string
    {
        return $this->email;
    }

    public function getRoles(): array
    {
        if ($this->type === 'admin') {
            return ['ROLE_ADMIN'];
        }
        return ['ROLE_USER'];
    }

    public function isVerified(): bool
    {
        return $this->isVerified;
    }

    public function setIsVerified(bool $isVerified): static
    {
        $this->isVerified = $isVerified;

        return $this;
    }
    public function getVerificationToken(): ?string
   {
    return $this->verificationToken;
   }

public function setVerificationToken(?string $verificationToken): self
   {
    $this->verificationToken = $verificationToken;
    return $this;
  }

public function getTokenExpiresAt(): ?\DateTimeInterface
{
    return $this->tokenExpiresAt;
}

public function setTokenExpiresAt(?\DateTimeInterface $tokenExpiresAt): self
{
    $this->tokenExpiresAt = $tokenExpiresAt;
    return $this;
}

public function isAccepted(): bool
{
    return $this->isAccepted;
}

public function setIsAccepted(bool $isAccepted): self
{
    $this->isAccepted = $isAccepted;
    return $this;
}
}
