<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\AdminRegistrationType;
use Doctrine\DBAL\Connection;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;

class AdminRegistrationController extends AbstractController
{
    #[Route('/register-admin/{token}', name: 'register_admin')]
    public function registerAdmin(
        string $token, 
        Request $request, 
        EntityManagerInterface $entityManager, 
        Connection $connection, 
        UserPasswordHasherInterface $passwordHasher
    ): Response {
        $invitation = $connection->fetchAssociative('SELECT * FROM invitation_token WHERE token = ?', [$token]);

        if (!$invitation) {
            throw $this->createNotFoundException('Invitation invalide ou expirée.');
        }

        $expiresAt = new \DateTime($invitation['expires_at']);
        if (new \DateTime() > $expiresAt) {
            throw $this->createNotFoundException('Le lien d\'invitation a expiré.');
        }

        $user = new User();
        $user->setType('admin'); // Email sera forcé après la soumission

        $form = $this->createForm(AdminRegistrationType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Remettre l'email car le champ est disabled donc vide après POST
            $user->setEmail($invitation['email']);

            $hashedPassword = $passwordHasher->hashPassword($user, $user->getPassword());
            $user->setPassword($hashedPassword);
            $user->setIsAccepted(true);
            $user->setIsVerified(true);

            $entityManager->persist($user);
            $entityManager->flush();

            // Supprimer le token utilisé
            $connection->delete('invitation_token', ['id' => $invitation['id']]);

            $this->addFlash('success', 'Votre compte Admin a été créé avec succès.');
            return $this->redirectToRoute('app_login');
        }

        return $this->render('admin_registration/register.html.twig', [
            'form' => $form->createView(),
        ]);
    }
}
