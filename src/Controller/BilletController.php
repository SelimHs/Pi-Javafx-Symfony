<?php

namespace App\Controller;


use App\Entity\Event;
use App\Entity\Remise;
use App\Entity\Billet;
use App\Entity\Reservation;
use App\Form\BilletType;
use App\Repository\BilletRepository;
use App\Repository\RemiseRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

#[Route('/billet')]
final class BilletController extends AbstractController
{
    #[Route(name: 'app_billet_index', methods: ['GET'])]
    public function index(BilletRepository $billetRepository): Response
    {
        $billets = $billetRepository->findAll();

        $totalBillets = count($billets);
        $totalVip = count(array_filter($billets, fn($b) => $b->getType() === 'VIP'));
        $totalDuo = count(array_filter($billets, fn($b) => $b->getType() === 'DUO'));

        return $this->render('billet/index.html.twig', [
            'billets' => $billets,
            'totalBillets' => $totalBillets,
            'totalVip' => $totalVip,
            'totalDuo' => $totalDuo,
        ]);
    }

    // Création de billet depuis le back
    #[Route('/new', name: 'app_billet_new', methods: ['GET', 'POST'])]
    public function newBack(Request $request, EntityManagerInterface $entityManager): Response
    {
        $billet = new Billet();
        $form = $this->createForm(BilletType::class, $billet);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $billet->setDateAchat(new \DateTime());
            $entityManager->persist($billet);
            $entityManager->flush();

            return $this->redirectToRoute('app_billet_index');
        }

        return $this->render('billet/new.html.twig', [
            'billet' => $billet,
            'form' => $form,
        ]);
    }

    #[Route('/reservation/{id}', name: 'app_billet_reservation', methods: ['GET', 'POST'])]
public function newFront(Request $request, Event $event, EntityManagerInterface $em, RemiseRepository $remiseRepo): Response
{
    $billet = new Billet();
    $reservation = new Reservation();

    $form = $this->createForm(BilletType::class, $billet);
    $form->handleRequest($request);

    $eventPrix = $event->getPrix();
    $prix = $eventPrix;

    $reservation->setEvent($event);
    $reservation->setDateReservation(new \DateTime());
    $reservation->setStatut('confirmée');

    if ($form->isSubmitted() && $form->isValid()) {
        // 🔥 Récupérer type pour ajuster prix selon billet
        $type = $billet->getType();
        if ($type === 'DUO') {
            $prix += $eventPrix * 0.5;
        } elseif ($type === 'VIP') {
            $prix = $eventPrix * 3;
        }

        // ✅ Code promo
        $codePromo = $form->get('codePromo')->getData();
        if ($codePromo) {
            $remise = $remiseRepo->findOneBy(['code' => $codePromo]);
            if ($remise) {
                $pourcentage = $remise->getValeur(); // ex: 10 = 10%
                $reduction = $prix * ($pourcentage / 100);
                $prix -= $reduction;

                $reservation->setRemise($remise);
            }
        }

        // ✅ Finaliser
        $billet->setDateAchat(new \DateTime());
        $billet->setEvent($event);
        $billet->setPrix((int) $prix);
        $billet->setReservation($reservation); // 🧠 Lier réservation au billet

        $em->persist($reservation);
        $em->persist($billet);
        $em->flush();

        return $this->redirectToRoute('app_event_index'); // 🔁 retour à liste des événements
    }

    return $this->render('billet/front_reservation.html.twig', [
        'form' => $form,
        'event' => $event,
        'prixFinal' => $prix
    ]);
}



    #[Route('/{idBillet}', name: 'app_billet_show', methods: ['GET'])]
    public function show(Billet $billet): Response
    {
        return $this->render('billet/show.html.twig', [
            'billet' => $billet,
        ]);
    }

    #[Route('/{idBillet}/edit', name: 'app_billet_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Billet $billet, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(BilletType::class, $billet);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_billet_index');
        }

        return $this->render('billet/edit.html.twig', [
            'billet' => $billet,
            'form' => $form,
        ]);
    }

    #[Route('/{idBillet}', name: 'app_billet_delete', methods: ['POST'])]
    public function delete(Request $request, Billet $billet, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete' . $billet->getIdBillet(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($billet);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_billet_index');
    }
}
