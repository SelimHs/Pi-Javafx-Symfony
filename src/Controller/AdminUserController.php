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
use Symfony\Component\Security\Http\Attribute\IsGranted;

#[Route('/admin/user')]
#[IsGranted('ROLE_ADMIN')] // 🚀 Seuls les admins peuvent accéder
class AdminUserController extends AbstractController
{
    #[Route('/', name: 'admin_user_index', methods: ['GET'])]
    public function index(UserRepository $userRepository): Response
    {
        $users = $userRepository->findAll();
        $hommes = 0;
        $femmes = 0;

        foreach ($users as $user) {
            if (strtolower($user->getGenre()) === 'homme') {
                $hommes++;
            } elseif (strtolower($user->getGenre()) === 'femme') {
                $femmes++;
            }
        }

        // 🔥 ici on change vers show.html.twig
        return $this->render('admin_user/show.html.twig', [
            'users' => $users,
            'hommes' => $hommes,
            'femmes' => $femmes,
        ]);
    }

    #[Route('/new', name: 'admin_user_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager, UserPasswordHasherInterface $passwordHasher): Response
    {
        $user = new User();
        $form = $this->createForm(UserType::class, $user, [
            'admin_form' => true,
        ]);
        $form->remove('password'); // mot de passe par défaut
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $plainPassword = '123456';
            $hashedPassword = $passwordHasher->hashPassword($user, $plainPassword);
            $user->setPassword($hashedPassword);

            if ($user->getType() === 'admin') {
                $user->setIsAccepted(false);
            } else {
                $user->setIsAccepted(true);
            }

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

    #[Route('/pending', name: 'admin_user_pending', methods: ['GET'])]
    public function pendingAdmins(UserRepository $userRepository): Response
    {
        $pendingAdmins = $userRepository->findBy([
            'isAccepted' => false,
            'type' => 'admin',
        ]);

        return $this->render('admin_user/pending_admins.html.twig', [
            'pendingAdmins' => $pendingAdmins,
        ]);
    }

    #[Route('/accept/{id}', name: 'admin_user_accept', methods: ['POST'])]
    public function acceptAdmin(User $user, EntityManagerInterface $entityManager): Response
    {
        $user->setIsAccepted(true);
        $entityManager->flush();

        $this->addFlash('success', 'Admin accepté avec succès.');
        return $this->redirectToRoute('admin_user_pending');
    }
    #[Route('/edit/{id}', name: 'admin_user_edit', methods: ['GET', 'POST'])]
public function edit(Request $request, User $user, EntityManagerInterface $entityManager): Response
{
    $form = $this->createForm(UserType::class, $user, [
        'admin_form' => true,
    ]);
    $form->remove('password');
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $uploadedFile = $form['profileImage']->getData();
        if ($uploadedFile) {
            $newFilename = uniqid() . '.' . $uploadedFile->guessExtension();
            $uploadedFile->move(
                $this->getParameter('uploads_directory'),
                $newFilename
            );
            $user->setProfileImage($newFilename);
        }

        $entityManager->flush();

        $this->addFlash('success', 'Utilisateur modifié avec succès.');
        return $this->redirectToRoute('admin_user_index');
    }

    return $this->render('admin_user/edit.html.twig', [
        'form' => $form->createView(),
        'user' => $user,
    ]);
}
#[Route('/delete/{id}', name: 'admin_user_delete', methods: ['POST'])]
public function delete(Request $request, User $user, EntityManagerInterface $entityManager): Response
{
    if ($this->isCsrfTokenValid('delete' . $user->getId(), $request->request->get('_token'))) {
        $entityManager->remove($user);
        $entityManager->flush();

        $this->addFlash('success', 'Utilisateur supprimé avec succès.');
    } else {
        $this->addFlash('danger', 'Échec de la suppression : Token CSRF invalide.');
    }

    return $this->redirectToRoute('admin_user_index');
}

    
}
