<?php

namespace App\Controller;

use App\Entity\User;
use App\Entity\Event;
use App\Entity\Ticket;
use App\Form\UserType;
use App\Repository\UserRepository;
use App\Repository\EventRepository;
use App\Repository\TicketRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Security\Http\Attribute\IsGranted;

#[Route('/admin/user')]
#[IsGranted('ROLE_ADMIN')]
class AdminUserController extends AbstractController
{
    #[Route('/dashboard', name: 'admin_user_dashboard', methods: ['GET'])]
    public function dashboard(
        UserRepository $userRepository,
        EventRepository $eventRepository
    ): Response {
        // Statistiques utilisateurs
        $totalUsers = $userRepository->count([]);
        $hommes = $userRepository->countByGenre('homme');
        $femmes = $userRepository->countByGenre('femme');
        $newUsers = $userRepository->countNewThisMonth();
    
        // Statistiques événements
        $activeEvents = $eventRepository->count(['isActive' => true]);
        $upcomingEvents = $eventRepository->findUpcomingEvents(3);
    
        // Billets & revenus fictifs par défaut (pour compatibilité avec le template)
        $ticketsSold = 0;
        $revenue = 0;
    
        // Statistiques billets simulées
        $billetStats = $eventRepository->getTicketStatsByEvent();
    
        // Utilisateurs récents
        $recentUsers = $userRepository->findBy([], ['createdAt' => 'DESC'], 5);
    
        return $this->render('admin_user/dashboard.html.twig', [
            'total_users' => $totalUsers,
            'hommes' => $hommes,
            'femmes' => $femmes,
            'new_users' => $newUsers,
            'active_events' => $activeEvents,
            'upcoming_events' => $upcomingEvents,
            'tickets_sold' => $ticketsSold,
            'revenue' => $revenue,
            'billetStats' => $billetStats,
            'recent_users' => $recentUsers
        ]);
    }
   
    #[Route('/', name: 'admin_user_index', methods: ['GET'])]
    public function index(UserRepository $userRepository): Response
    {
        $users = $userRepository->findAll();
        $hommes = $userRepository->countByGenre('homme');
        $femmes = $userRepository->countByGenre('femme');
    
        // Create a new user form
        $user = new User();
        $form = $this->createForm(UserType::class, $user, [
            'admin_form' => true,
        ]);
        $form->remove('password');
    
        return $this->render('admin_user/index.html.twig', [
            'users' => $users,
            'hommes' => $hommes,
            'femmes' => $femmes,
            'form' => $form->createView(),
        ]);
    }
    
    

    #[Route('/new', name: 'admin_user_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager, UserPasswordHasherInterface $passwordHasher): Response
    {
        $user = new User();
        $form = $this->createForm(UserType::class, $user, [
            'admin_form' => true,
        ]);
        $form->remove('password');
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Gestion du mot de passe par défaut
            $plainPassword = '123456';
            $hashedPassword = $passwordHasher->hashPassword($user, $plainPassword);
            $user->setPassword($hashedPassword);

            // Gestion des admins en attente
            if ($user->getType() === 'admin') {
                $user->setIsAccepted(false);
            } else {
                $user->setIsAccepted(true);
            }

            // Gestion de l'image de profil
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
        ], ['createdAt' => 'DESC']);

        return $this->render('admin_user/pending_admins.html.twig', [
            'pendingAdmins' => $pendingAdmins,
        ]);
    }

    #[Route('/accept/{id}', name: 'admin_user_accept', methods: ['POST'])]
    public function acceptAdmin(User $user, EntityManagerInterface $entityManager): Response
    {
        if ($user->getType() === 'admin') {
            $user->setIsAccepted(true);
            $entityManager->flush();
            $this->addFlash('success', 'Admin accepté avec succès.');
        } else {
            $this->addFlash('danger', 'Seuls les admins peuvent être acceptés via cette route.');
        }

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
            try {
                $entityManager->remove($user);
                $entityManager->flush();
                $this->addFlash('success', 'Utilisateur supprimé avec succès.');
            } catch (\Exception $e) {
                $this->addFlash('danger', 'Erreur lors de la suppression : ' . $e->getMessage());
            }
        } else {
            $this->addFlash('danger', 'Échec de la suppression : Token CSRF invalide.');
        }

        return $this->redirectToRoute('admin_user_index');
    }
}