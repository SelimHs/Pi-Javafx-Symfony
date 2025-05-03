<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Form\ReservationType;
use App\Repository\ReservationRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\HttpClient\HttpClient;


#[Route('/reservation')]
final class ReservationController extends AbstractController
{
    #[Route(name: 'app_reservation_index', methods: ['GET'])]
    public function index(ReservationRepository $reservationRepository): Response
    {
        $reservations = $reservationRepository->findAll();

        $totalReservations = count($reservations);
        $totalConfirmees = count(array_filter($reservations, fn($r) => $r->getStatut() === 'confirmée'));
        $totalAnnulee = count(array_filter($reservations, fn($r) => $r->getStatut() === 'annulée'));

        return $this->render('reservation/index.html.twig', [
            'reservations' => $reservations,
            'totalReservations' => $totalReservations,
            'totalConfirmees' => $totalConfirmees,
            'totalAnnulee' => $totalAnnulee,
        ]);
    }
    #[Route('/valider-billets', name: 'app_reservation_valider_billets', methods: ['POST'])]
public function validerBilletsEnAttente(ReservationRepository $reservationRepository, EntityManagerInterface $em): RedirectResponse
{
    $enAttente = $reservationRepository->findBy(['statut' => 'en attente']);

    foreach ($enAttente as $reservation) {
        $reservation->setStatut('confirmée');
    }

    $em->flush();

    $this->addFlash('success', count($enAttente) . ' billet(s) en attente ont été confirmés.');
    return $this->redirectToRoute('app_reservation_index');
}



    #[Route('/new', name: 'app_reservation_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $reservation = new Reservation();
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($reservation);
            $entityManager->flush();

            return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reservation/new.html.twig', [
            'reservation' => $reservation,
            'form' => $form,
        ]);
    }


    #[Route('/export/reservations', name: 'export_reservations_excel')]
    public function exportReservations(ReservationRepository $repo): Response
    {
        $reservations = $repo->findAll();

        // 1. Préparer les données
        $rows = [];
        foreach ($reservations as $reservation) {
            $rows[] = [
                'utilisateur' => $reservation->getUser()?->getNom() ?? '—',
                'date' => $reservation->getDateReservation() instanceof \DateTimeInterface
                    ? $reservation->getDateReservation()->format('Y-m-d H:i')
                    : $reservation->getDateReservation(),
                'statut' => $reservation->getStatut(),
            ];
        }

        $body = [
            'filename' => 'reservations',
            'rows' => $rows,
            'order' => [
                'id' => 0,
                'date' => 1,
                'statut' => 2,
            ],
        ];

        // 2. Appeler l'API
        $client = HttpClient::create();
        $response = $client->request('POST', 'https://json-to-excel.p.rapidapi.com/', [
            'headers' => [
                'Content-Type' => 'application/json',
                'x-rapidapi-host' => 'json-to-excel.p.rapidapi.com',
                'x-rapidapi-key' => '709b7e345fmshd343de8ed8f0f77p14485ajsne28e51f687b5',
            ],
            'body' => json_encode($body),
        ]);

        // 3. Vérification du succès
        if ($response->getStatusCode() !== 200) {
            return new Response("❌ Erreur API (code {$response->getStatusCode()})", 500);
        }

        $result = $response->toArray(false);

        if (!isset($result['url'])) {
            return new Response('❌ Fichier non reçu depuis l’API.', 500);
        }

        // ✅ Redirection directe vers l’URL du fichier généré (téléchargement automatique)
        return new RedirectResponse($result['url']);
    }

    #[Route('/{idReservation}', name: 'app_reservation_show', methods: ['GET'])]
    public function show(Reservation $reservation): Response
    {
        return $this->render('reservation/show.html.twig', [
            'reservation' => $reservation,
        ]);
    }

    #[Route('/{idReservation}/edit', name: 'app_reservation_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Reservation $reservation, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reservation/edit.html.twig', [
            'reservation' => $reservation,
            'form' => $form,
        ]);
    }


    #[Route('/{idReservation}', name: 'app_reservation_delete', methods: ['POST'])]
    public function delete(Request $request, Reservation $reservation, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete' . $reservation->getIdReservation(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($reservation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
    }
}
