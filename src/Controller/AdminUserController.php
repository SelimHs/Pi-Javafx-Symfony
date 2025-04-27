<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\UserType;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;

#[Route('/admin/user')]
class AdminUserController extends AbstractController
{
    #[Route('/', name: 'admin_user_index', methods: ['GET'])]
    public function index(UserRepository $userRepository): Response
    {
        return $this->render('admin_user/index.html.twig', [
            'users' => $userRepository->findAll(),
        ]);
    }


    #[Route('/new', name: 'admin_user_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager, UserPasswordHasherInterface $passwordHasher): Response
    {
        $user = new User();
        $form = $this->createForm(UserType::class, $user);
        $form->remove('password');
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
    
            // 🔥 Donne un mot de passe temporaire
            $plainPassword = '123456';
            $hashedPassword = $passwordHasher->hashPassword($user, $plainPassword);
            $user->setPassword($hashedPassword);
    
            // 🔥 Gestion upload photo
            $uploadedFile = $form['profileImage']->getData();
            if ($uploadedFile) {
                $newFilename = uniqid() . '.' . $uploadedFile->guessExtension();
                $uploadedFile->move(
                    $this->getParameter('uploads_directory'),
                    $newFilename
                );
                $user->setProfileImage($newFilename);
            }
    
            $entityManager->persist($user);
            $entityManager->flush();
            $this->addFlash('success', 'Utilisateur créé avec succès.');
    
            return $this->redirectToRoute('admin_user_index');
        }
    
        return $this->render('admin_user/new.html.twig', [
            'form' => $form->createView(),
        ]);
    }
    

    #[Route('/{id}', name: 'admin_user_show', methods: ['GET'])]
    public function show(User $user): Response
    {
        return $this->render('admin_user/show.html.twig', [
            'user' => $user,
        ]);
    }

    #[Route('/{id}/edit', name: 'admin_user_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(UserType::class, $user);
        $form->remove('password'); // 👈 optional
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();
            $this->addFlash('success', 'Utilisateur modifié avec succès.');

            return $this->redirectToRoute('admin_user_index');
        }

        return $this->render('admin_user/edit.html.twig', [
            'form' => $form->createView(),
            'user' => $user,
        ]);
    }

    #[Route('/{id}', name: 'admin_user_delete', methods: ['POST'])]
    public function delete(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$user->getId(), $request->request->get('_token'))) {
            $entityManager->remove($user);
            $entityManager->flush();
            $this->addFlash('success', 'Utilisateur supprimé avec succès.');
        }

        return $this->redirectToRoute('admin_user_index');
    }
}
