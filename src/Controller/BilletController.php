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
use App\Service\PdfGeneratorService;
use App\Service\BilletMailerService;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\Mailer\MailerInterface;
use App\Service\BrevoMailerService;


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
    public function newFront(
        Request $request,
        Event $event,
        EntityManagerInterface $em,
        RemiseRepository $remiseRepo,
        PdfGeneratorService $pdfGenerator,
        BrevoMailerService $brevoMailer
    ): Response {
        $billet = new Billet();
        $reservation = new Reservation();
        $billet->setEvent($event);
    
        $form = $this->createForm(BilletType::class, $billet);
        $form->handleRequest($request);
    
        $prix = $event->getPrix();
        $reservation->setEvent($event);
        $reservation->setDateReservation(new \DateTime());
        $reservation->setStatut('confirmée');
        $reservation->setUser($em->getRepository(\App\Entity\User::class)->find(1));
    
        if ($form->isSubmitted() && $form->isValid()) {
            if ($billet->getType() === 'DUO') {
                $prix += $event->getPrix() * 0.5;
            } elseif ($billet->getType() === 'VIP') {
                $prix = $event->getPrix() * 3;
            }
    
            $codePromo = $form->get('codePromo')->getData();
            if ($codePromo) {
                $remise = $remiseRepo->findOneBy(['codePromo' => $codePromo]);
                if ($remise) {
                    $prix -= $prix * ($remise->getPourcentageRemise() / 100);
                    $reservation->setRemise($remise);
                }
            }
    
            $billet->setPrix((int) $prix);
            $billet->setDateAchat(new \DateTime());
            $billet->setReservation($reservation);
    
            $em->persist($reservation);
            $em->persist($billet);
            $em->flush();
    
            // ✅ Generate local PDF file
            $pdfPath = $pdfGenerator->generateBilletPdf($billet);
    
            // ✅ Send confirmation email with PDF attachment
            $brevoMailer->sendConfirmation(
                'Karouiyahya71@gmail.com',
                $event->getNomEvent(),
                $billet->getProprietaire(),
                $event->getNomEspace(),
                $event->getDate(),
                $pdfPath
            );
    
            // ✅ Redirect always to home page
            return $this->redirectToRoute('app_event_index');
        }
    
        return $this->render('billet/front_reservation.html.twig', [
            'form' => $form,
            'event' => $event,
            'prixFinal' => $prix,
            'promoCodes' => [],
        ]);
    }
    

    #[Route('/test-brevo-mail', name: 'test_brevo_mail')]
    public function testBrevo(BrevoMailerService $mailer): Response
    {
        $mailer->sendConfirmation('dark_soul@hotmail.fr', 'Festival de Musique');
        return new Response('✅ Email envoyé via Brevo !');
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
