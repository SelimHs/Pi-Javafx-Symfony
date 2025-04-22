<?php

namespace App\Controller;

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

#[Route('/espace')]
final class EspaceController extends AbstractController
{
    #[Route(name: 'app_espace_index', methods: ['GET'])]
    public function index(EspaceRepository $espaceRepository): Response
    {
        return $this->render('espace/index.html.twig', [
            'espaces' => $espaceRepository->findAll(),
        ]);
    }

    #[Route('/dashboard', name: 'dashboard_espace_index', methods: ['GET'])]
    public function indexBack(EspaceRepository $espaceRepository): Response
    {
        return $this->render('espace/indexBack.html.twig', [
            'espaces' => $espaceRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_espace_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $espace = new Espace();
        $form = $this->createForm(EspaceType::class, $espace, [
            'is_edit' => false // champ désactivé
        ]);
        $espace = new Espace();
        $espace->setDisponibilite('Disponible'); // ✅ valeur par défaut
        $form = $this->createForm(EspaceType::class, $espace, [
            'is_edit' => false
        ]);


        $form = $this->createForm(EspaceType::class, $espace);
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
        $ip = "192.168.137.174"; // Remplace par l’IP de ton téléphone ou serveur caméra
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
                    // Handle error
                }
            }

            $entityManager->flush();

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
        if ($this->isCsrfTokenValid('delete' . $espace->getIdEspace(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($espace);
            $entityManager->flush();
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
    public function reserverSheetbest(Request $request, HttpClientInterface $client): JsonResponse
    {
        $data = json_decode($request->getContent(), true);

        if (!$data) {
            return new JsonResponse(['success' => false, 'error' => 'Données JSON invalides ou manquantes'], 400);
        }

        try {
            $response = $client->request('POST', 'https://api.sheetbest.com/sheets/4d538bcb-a52a-4dde-84e4-ddb7c9520d8e', [
                'headers' => ['Content-Type' => 'application/json'],
                'json' => $data,
            ]);

            return new JsonResponse([
                'success' => true,
                'message' => 'Réservation envoyée à Sheet.best',
                'result' => $response->getStatusCode()
            ]);
        } catch (\Exception $e) {
            return new JsonResponse([
                'success' => false,
                'error' => $e->getMessage()
            ], 500);
        }
    }

    #[Route('/reservations', name: 'app_reservations_liste', methods: ['GET'])]
    public function listReservationsSheetbest(HttpClientInterface $client): Response
    {
        try {
            $response = $client->request('GET', 'https://api.sheetbest.com/sheets/4d538bcb-a52a-4dde-84e4-ddb7c9520d8e');
            $reservations = $response->toArray();

            return $this->render('espace/reservations.html.twig', [
                'reservations' => $reservations
            ]);
        } catch (\Exception $e) {
            return new Response("Erreur lors du chargement des données : " . $e->getMessage(), 500);
        }
    }

    
}
