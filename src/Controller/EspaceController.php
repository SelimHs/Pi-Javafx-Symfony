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

use Geocoder\Query\GeocodeQuery;
use Http\Adapter\Guzzle7\Client as GuzzleAdapter;
use Geocoder\Provider\Nominatim\Nominatim;
use Geocoder\StatefulGeocoder;


use App\Service\CronofyService;

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


    private HttpClientInterface $client;

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }

    #[Route('/api/espace/{idEspace}/reserver', name: 'api_espace_reserver', methods: ['POST'])]
    public function reserverEspace(Request $request, Espace $espace, CronofyService $cronofy): JsonResponse
    {
        $nom = $request->request->get('nom_complet');
        $startDate = new \DateTime($request->request->get('start_date'));
        $endDate = new \DateTime($request->request->get('end_date'));
        $organisateurId = $request->request->get('id_organisateur');

        // Validation nom
        if (empty($nom)) {
            return new JsonResponse(['error' => '❌ Le nom complet est requis.'], 400);
        }

        // Validation date min 7 jours
        $minDate = new \DateTime('+7 days');
        if ($startDate < $minDate) {
            return new JsonResponse(['error' => '❌ La réservation doit commencer au moins 7 jours après aujourd’hui.'], 400);
        }

        // Validation date fin > date début
        if ($endDate <= $startDate) {
            return new JsonResponse(['error' => '❌ La date de fin doit être après la date de début.'], 400);
        }

        // Vérifier conflit sur Cronofy
        if ($cronofy->checkConflicts($startDate, $endDate, $espace->getNomEspace())) {
            return new JsonResponse(['error' => '❌ L’espace est déjà réservé pour cette période.'], 409);
        }

        // Création de l’événement
        $event = [
            'event_id' => uniqid(),
            'calendar_id' => 'primary',
            'summary' => "Réservation de {$espace->getNomEspace()} par $nom",
            'description' => "Client: $nom" . ($organisateurId ? "\nOrganisateur ID: $organisateurId" : ''),
            'start' => $startDate->format(\DateTime::ATOM),
            'end' => $endDate->format(\DateTime::ATOM),
            'tzid' => 'Europe/Paris',
        ];

        $result = $cronofy->createReservation($event);
        return new JsonResponse($result);
    }
    private function fallbackReservation(Espace $espace, string $nom, \DateTime $start, \DateTime $end, ?string $organisateurId): JsonResponse
{
    // Implémentez ici la logique pour enregistrer en base de données
    // Par exemple :
    try {
        // Créez une entité Reservation et persistez-la
        return new JsonResponse([
            'success' => true,
            'message' => 'Réservation enregistrée localement (fallback)',
            'reservation' => [
                'espace' => $espace->getNomEspace(),
                'client' => $nom,
                'start' => $start->format('Y-m-d'),
                'end' => $end->format('Y-m-d'),
                'reference' => 'LOCAL-' . uniqid()
            ]
        ]);
    } catch (\Exception $e) {
        return new JsonResponse([
            'error' => 'Échec de la réservation locale: ' . $e->getMessage()
        ], 500);
    }
}
    #[Route('/reservations/all', name: 'app_cronofy_reservations', methods: ['GET'])]
    public function afficherReservations(CronofyService $cronofy): Response
    {
        $reservations = $cronofy->getAllReservations();

        return $this->render('espace/reservations.html.twig', [
            'reservations' => $reservations,
        ]);
    }
}
