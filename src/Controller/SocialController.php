<?php

namespace App\Controller;

use App\Entity\User;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use KnpU\OAuth2ClientBundle\Client\ClientRegistry;
use League\OAuth2\Client\Provider\GoogleUser;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Http\Authentication\UserAuthenticatorInterface;
use App\Security\Authenticator;

class SocialController extends AbstractController
{
    #[Route('/connect/google', name: 'connect_google')]
    public function connect(ClientRegistry $clientRegistry): RedirectResponse
    {
        return $clientRegistry->getClient('google')->redirect(
            ['email', 'profile'],
            []
        );
    }

    #[Route('/connect/google/check', name: 'connect_google_check', methods: ['GET', 'POST'])]
    public function connectCheck(
        Request $request,
        ClientRegistry $clientRegistry,
        UserRepository $userRepository,
        EntityManagerInterface $em,
        UserAuthenticatorInterface $userAuthenticator,
        Authenticator $appAuthenticator
    ) {
        $client = $clientRegistry->getClient('google');
    
        /** @var GoogleUser $googleUser */
        $googleUser = $client->fetchUser();
    
        $email = $googleUser->getEmail();
        $nom = $googleUser->getFirstName() ?? 'Google';
        $prenom = $googleUser->getLastName() ?? 'User';
    
        $user = $userRepository->findOneBy(['email' => $email]);
    
        if (!$user) {
            $user = new User();
            $user->setEmail($email);
            $user->setNom($nom);
            $user->setPrenom($prenom);
            $user->setPassword(''); // string vide valide
            $user->setNumeroTelephone('00000000'); // valeur par défaut
            $user->setAddresse('non précisée');    // valeur par défaut
            $user->setType('user');                // ou 'admin' si nécessaire
            $user->setGenre('autre');              // pour éviter le null
            $user->setIsVerified(true);
            $user->setIsAccepted(true);
            $em->persist($user);
            $em->flush();
        }
    
        return $userAuthenticator->authenticateUser($user, $appAuthenticator, $request);
    }
}    