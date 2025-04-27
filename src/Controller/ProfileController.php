<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\UserType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Security\Http\Attribute\IsGranted;

#[Route('/profile')]
#[IsGranted('ROLE_USER')]
class ProfileController extends AbstractController
{
    #[Route('/', name: 'app_profile')]
    public function view(): Response
    {
        $user = $this->getUser();

        return $this->render('profile/view.html.twig', [
            'user' => $user,
        ]);
    }

    #[Route('/edit', name: 'app_profile_edit')]
    public function edit(Request $request, EntityManagerInterface $entityManager): Response
    {
        /** @var User $user */
        $user = $this->getUser();
    
        $form = $this->createForm(UserType::class, $user);
        $form->remove('password');
        $form->remove('type');
        $form->remove('isVerified');
        $form->remove('verificationToken');
        $form->remove('tokenExpiresAt');
    
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
    
            // Gestion de l'image uploadée
            $uploadedFile = $request->files->get('user')['profileImage'] ?? null;
            if ($uploadedFile) {
                $newFilename = uniqid().'.'.$uploadedFile->guessExtension();
                $uploadedFile->move($this->getParameter('avatars_directory'), $newFilename);
                $user->setProfileImage($newFilename); // juste le nom du fichier
            }
    
            $entityManager->flush();
            $this->addFlash('success', 'Profil mis à jour avec succès !');
    
            return $this->redirectToRoute('app_profile');
        }
    
        return $this->render('profile/edit.html.twig', [
            'form' => $form->createView(),
        ]);
    }
    
}
