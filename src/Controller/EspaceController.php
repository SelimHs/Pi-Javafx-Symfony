<?php

namespace App\Controller;

use App\Entity\User; // 🔥 Très important : importer ta vraie entité User

use App\Entity\Espace;
use App\Form\EspaceType;
use App\Repository\EspaceRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Contracts\HttpClient\HttpClientInterface;

use App\Service\JsonBinService;
use App\Service\SheetBestService;

use App\Entity\Organisateur;
use App\Repository\OrganisateurRepository;



#[Route('/espace')]
final class EspaceController extends AbstractController
{
    private $client;

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }

    #[Route(name: 'app_espace_index', methods: ['GET'])]
    public function index(EspaceRepository $espaceRepository): Response
    {
        $espaces = $espaceRepository->findAll();
        $user = $this->getUser();

        $idUser = null;
        if ($user instanceof User) { // ✅ Vérifie vraiment que c'est TON User
            $idUser = $user->getId();
        }

        return $this->render('espace/index.html.twig', [
            'espaces' => $espaces,
            'idUser' => $idUser, // 👈 injecte id user dans twig
        ]);
    }


    #[Route('/dashboard', name: 'dashboard_espace_index', methods: ['GET'])]
    public function indexBack(EspaceRepository $espaceRepository, OrganisateurRepository $organisateurRepo): Response
    {
        $espaces = $espaceRepository->findAll();

        // 🔵 Statistiques :
        $totalEspaces = count($espaces);
        $espacesAvecOrganisateur = 0;
        $espacesSansOrganisateur = 0;

        foreach ($espaces as $espace) {
            $organisateurs = $organisateurRepo->findBy(['espace' => $espace]);
            if (count($organisateurs) > 0) {
                $espacesAvecOrganisateur++;
            } else {
                $espacesSansOrganisateur++;
            }
        }

        return $this->render('espace/indexBack.html.twig', [
            'espaces' => $espaces,
            'totalEspaces' => $totalEspaces,
            'espacesAvecOrganisateur' => $espacesAvecOrganisateur,
            'espacesSansOrganisateur' => $espacesSansOrganisateur,
        ]);
    }



    #[Route('/new', name: 'app_espace_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        // Vérifier si l'utilisateur est connecté
        $user = $this->getUser();
        if (!$user) {
            $this->addFlash('error', 'Vous devez être connecté pour créer un espace.');
            return $this->redirectToRoute('app_login');
        }

        $espace = new Espace();
        $espace->setDisponibilite('Disponible'); // Valeur par défaut

        // Associer l'utilisateur à l'espace
        $espace->setUser($user);

        $form = $this->createForm(EspaceType::class, $espace, [
            'is_edit' => false
        ]);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $newFilename = uniqid() . '.' . $imageFile->guessExtension();
                $uploadDir = $this->getParameter('uploads_directory');
                $imageFile->move($uploadDir, $newFilename);
                $espace->setImage($newFilename);
            }

            $entityManager->persist($espace);
            $entityManager->flush();

            $this->addFlash('success', 'Espace créé avec succès !');
            return $this->redirectToRoute('app_espace_index');
        }

        return $this->render('espace/new.html.twig', [
            'espace' => $espace,
            'form' => $form,
        ]);
    }

    #[Route('/newBack', name: 'dashboard_espace_new', methods: ['GET', 'POST'])]
    public function newDashboard(Request $request, EntityManagerInterface $entityManager): Response
    {
        $espace = new Espace();
        $espace->setDisponibilite('Disponible'); // Valeur par défaut

        // Récupérer l'utilisateur connecté
        $user = $this->getUser();
        if (!$user) {
            $this->addFlash('error', 'Vous devez être connecté pour créer un espace.');
            return $this->redirectToRoute('app_login');
        }

        // Associer l'utilisateur à l'espace
        $espace->setUser($user);

        $form = $this->createForm(EspaceType::class, $espace, [
            'is_edit' => false
        ]);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $newFilename = uniqid() . '.' . $imageFile->guessExtension();
                $imageFile->move($this->getParameter('uploads_directory'), $newFilename);
                $espace->setImage($newFilename);
            }

            $entityManager->persist($espace);
            $entityManager->flush();

            $this->addFlash('success', 'Espace créé avec succès !');
            return $this->redirectToRoute('dashboard_espace_index');
        }

        return $this->render('espace/newBack.html.twig', [
            'espace' => $espace,
            'form' => $form,
        ]);
    }



    #[Route('/{idEspace}', name: 'app_espace_show', methods: ['GET'])]
    public function show(Espace $espace, Request $request): Response
    {
        // 🔒 Stocker l'ID dans la session (utile pour suivre la navigation)
        $request->getSession()->set('idEspace', $espace->getIdEspace());

        // 🎥 Génération du lien de live stream basé sur la capacité
        $ip = "192.168.137.174"; // Remplace par l'IP de ton téléphone ou serveur caméra
        $port = $espace->getCapacite();
        $liveURL = "http://$ip:$port/jsfs.html";

        // Exemple de coordonnées (à remplacer plus tard par geocoding si tu veux)
        $latitude = 36.8065; // Tunis
        $longitude = 10.1815;

        return $this->render('espace/show.html.twig', [
            'espace' => $espace,
            'liveURL' => 'http://192.168.137.174:' . $espace->getCapacite() . '/jsfs.html',
            'latitude' => $latitude,
            'longitude' => $longitude,
        ]);
    }
    #[Route('/show/{idEspace}', name: 'dashboard_espace_show', methods: ['GET'])]
    public function showDashboard(Espace $espace): Response
    {
        return $this->render('espace/showBack.html.twig', [
            'espace' => $espace,
        ]);
    }

    #[Route('/{idEspace}/edit', name: 'app_espace_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Espace $espace, EntityManagerInterface $entityManager): Response
    {
        // Vérifier si l'utilisateur est connecté
        $user = $this->getUser();
        if (!$user) {
            $this->addFlash('error', 'Vous devez être connecté pour modifier un espace.');
            return $this->redirectToRoute('app_login');
        }

        // Vérifier si l'utilisateur est le propriétaire de l'espace
        if ($espace->getUser() !== $user) {
            $this->addFlash('error', 'Vous n\'êtes pas autorisé à modifier cet espace.');
            return $this->redirectToRoute('app_espace_index');
        }

        $form = $this->createForm(EspaceType::class, $espace, [
            'is_edit' => true
        ]);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $newFilename = uniqid() . '.' . $imageFile->guessExtension();
                try {
                    $imageFile->move($this->getParameter('uploads_directory'), $newFilename);
                    $espace->setImage($newFilename);
                } catch (FileException $e) {
                    $this->addFlash('error', 'Erreur lors du téléchargement de l\'image.');
                }
            }

            $entityManager->flush();
            $this->addFlash('success', 'Espace modifié avec succès !');
            return $this->redirectToRoute('app_espace_show', ['idEspace' => $espace->getIdEspace()]);
        }

        return $this->render('espace/edit.html.twig', [
            'espace' => $espace,
            'form' => $form,
        ]);
    }


    #[Route('/edit/{idEspace}', name: 'dashboard_espace_edit', methods: ['GET', 'POST'])]
    public function editDashboard(Request $request, Espace $espace, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(EspaceType::class, $espace, [
            'is_edit' => true // champ activé
        ]);
        $form->handleRequest($request); // ✅ maintenant c'est sur le bon form

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $newFilename = uniqid() . '.' . $imageFile->guessExtension();
                try {
                    $imageFile->move($this->getParameter('uploads_directory'), $newFilename);
                    $espace->setImage($newFilename);
                } catch (FileException $e) {
                    // Gérer les erreurs si nécessaire
                }
            }

            $entityManager->flush();

            // ✅ Redirection vers la page de détail après modification
            return $this->redirectToRoute('dashboard_espace_show', [
                'idEspace' => $espace->getIdEspace()
            ]);
        }

        return $this->render('espace/editBack.html.twig', [
            'espace' => $espace,
            'form' => $form,
        ]);
    }


    #[Route('/{idEspace}', name: 'app_espace_delete', methods: ['POST'])]
    public function delete(Request $request, Espace $espace, EntityManagerInterface $entityManager): Response
    {
        // Vérifier si l'utilisateur est connecté
        $user = $this->getUser();
        if (!$user) {
            $this->addFlash('error', 'Vous devez être connecté pour supprimer un espace.');
            return $this->redirectToRoute('app_login');
        }

        // Vérifier si l'utilisateur est le propriétaire de l'espace
        if ($espace->getUser() !== $user) {
            $this->addFlash('error', 'Vous n\'êtes pas autorisé à supprimer cet espace.');
            return $this->redirectToRoute('app_espace_index');
        }

        if ($this->isCsrfTokenValid('delete' . $espace->getIdEspace(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($espace);
            $entityManager->flush();
            $this->addFlash('success', 'Espace supprimé avec succès !');
        }

        return $this->redirectToRoute('app_espace_index');
    }

    #[Route('/delete/{idEspace}', name: 'dashboard_espace_delete', methods: ['POST'])]
    public function deleteDashboard(Request $request, Espace $espace, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete' . $espace->getIdEspace(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($espace);
            $entityManager->flush();
        }

        return $this->redirectToRoute('dashboard_espace_index');
    }

    #[Route('/api/reserver', name: 'api_reserver_sheetbest', methods: ['POST'])]
    public function reserverViaSheet(Request $request, HttpClientInterface $client): JsonResponse
    {
        $json = $request->getContent();
        $data = json_decode($json, true);

        if (!$data || !isset($data['prix'])) {
            return new JsonResponse(['success' => false, 'error' => 'Données incomplètes ou champ prix manquant.'], 400);
        }

        $url = 'https://api.sheetbest.com/sheets/4d538bcb-a52a-4dde-84e4-ddb7c9520d8e';

        try {
            $response = $client->request('GET', $url);
            $reservations = $response->toArray();

            foreach ($reservations as $res) {
                if (
                    $res['id_espace'] == $data['id_espace'] &&
                    (
                        ($data['date_debut'] >= $res['date_debut'] && $data['date_debut'] < $res['date_fin']) ||
                        ($data['date_fin'] > $res['date_debut'] && $data['date_fin'] <= $res['date_fin']) ||
                        ($data['date_debut'] <= $res['date_debut'] && $data['date_fin'] >= $res['date_fin'])
                    )
                ) {
                    return new JsonResponse([
                        'success' => false,
                        'error' => 'Conflit : cet espace est déjà réservé sur cette période.'
                    ], 409);
                }
            }

            $user = $this->getUser();
            if ($user instanceof User) { // ✅ Vérifie que c'est ton entité User
                $data['id_user'] = $user->getId();
            } else {
                $data['id_user'] = ''; // ou -1 ou "Anonyme"
            }

            $client->request('POST', $url, [
                'json' => $data
            ]);

            return new JsonResponse([
                'success' => true,
                'message' => 'Réservation enregistrée avec succès.',
                'prix' => $data['prix']
            ]);
        } catch (\Exception $e) {
            return new JsonResponse([
                'success' => false,
                'error' => 'Erreur API',
                'message' => $e->getMessage()
            ], 500);
        }
    }

    #[Route('/reservations', name: 'app_reservations_liste', methods: ['GET'])]
    public function listReservations(SheetBestService $sheetBest): JsonResponse
    {
        try {
            $reservations = $sheetBest->getAll();
            return new JsonResponse($reservations);
        } catch (\Exception $e) {
            return new JsonResponse([
                'error' => 'Erreur lors de la récupération des réservations.',
                'message' => $e->getMessage()
            ], 500);
        }
    }
}