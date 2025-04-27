<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\ChangePasswordType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\FormError;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Http\Attribute\IsGranted;

#[Route('/profile/password')]
#[IsGranted('ROLE_USER')]
class PasswordController extends AbstractController
{
    #[Route('/', name: 'app_profile_password')]
    public function changePassword(Request $request, UserPasswordHasherInterface $passwordHasher, EntityManagerInterface $entityManager): Response
    {
        /** @var User $user */
        $user = $this->getUser();

        if (!$user) {
            throw $this->createAccessDeniedException('Vous devez être connecté pour changer votre mot de passe.');
        }
        
        $form = $this->createForm(ChangePasswordType::class);
        $form->handleRequest($request);
        
        if ($form->isSubmitted() && $form->isValid()) {
            $data = $form->getData();
            
            // Vérifier l'ancien mot de passe
            if (!$passwordHasher->isPasswordValid($user, $data['oldPassword'])) {
                $form->addError(new FormError('Ancien mot de passe incorrect.'));
            } else {
                // Modifier le mot de passe
                $hashedPassword = $passwordHasher->hashPassword($user, $data['newPassword']);
                $user->setPassword($hashedPassword);
                
                $entityManager->flush();
                
                $this->addFlash('success', '✅ Mot de passe modifié avec succès.');
                return $this->redirectToRoute('app_profile');
            }
        }

        return $this->render('profile/change_password.html.twig', [
            'form' => $form->createView(),
        ]);
    }
}
