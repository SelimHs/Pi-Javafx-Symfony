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

    #[Route('/api/reserver', name: 'api_reserver_sheet', methods: ['POST'])]
    public function reserverSheet(Request $request, HttpClientInterface $client): JsonResponse
    {
        $data = json_decode($request->getContent(), true);

        // 🔍 Sauvegarde les données reçues pour debug
        if (!is_dir('var/log')) {
            mkdir('var/log', 0777, true);
        }
        file_put_contents('var/log/reservation_debug.json', json_encode($data, JSON_PRETTY_PRINT));

        // 🔎 Liste des champs obligatoires
        $requiredFields = ['nom_complet', 'date_debut', 'date_fin', 'id_espace'];
        $missing = [];

        foreach ($requiredFields as $field) {
            if (empty($data[$field])) {
                $missing[] = $field;
            }
        }

        // 🔥 Retour si au moins un champ est manquant
        if (!empty($missing)) {
            return new JsonResponse([
                'error' => 'Champs obligatoires manquants.',
                'champs_manquants' => $missing,
                'données_reçues' => $data
            ], 400);
        }

        // ✅ URL vers ton Google Sheet
        $url = 'https://api.sheetbest.com/sheets/4d538bcb-a52a-4dde-84e4-ddb7c9520d8e';

        try {
            $client->request('POST', $url, [
                'json' => $data
            ]);

            return new JsonResponse([
                'success' => true,
                'message' => 'Réservation enregistrée dans le Google Sheet',
                'envoyé' => $data
            ]);
        } catch (\Exception $e) {
            return new JsonResponse([
                'error' => $e->getMessage()
            ], 500);
        }
    }
}
