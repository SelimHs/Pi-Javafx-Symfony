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
        BilletMailerService $mailer
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
            // 💰 Adjust price based on type
            if ($billet->getType() === 'DUO') {
                $prix += $event->getPrix() * 0.5;
            } elseif ($billet->getType() === 'VIP') {
                $prix = $event->getPrix() * 3;
            }
    
            // 🔖 Apply promo
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
    
            // 📄 Generate PDF
            $pdfUrl = $pdfGenerator->generateBilletPdf($billet);
    
            // ✅ Optional: Log for debugging
            file_put_contents('var/log/billet_debug.log', "PDF Generated: $pdfUrl\n", FILE_APPEND);
    
            // 📧 Send email
            try {
                $mailer->sendBilletEmail(
                    'dark_soul@hotmail.fr',
                    $billet->getProprietaire() ?? 'Client'
                );
                file_put_contents('var/log/billet_debug.log', "Mail sent successfully.\n", FILE_APPEND);
            } catch (\Exception $e) {
                file_put_contents('var/log/billet_debug.log', "Mail error: " . $e->getMessage() . "\n", FILE_APPEND);
            }
    
            // 🔁 Use RedirectResponse for external URLs
            return new RedirectResponse($pdfUrl);
        }
    
        return $this->render('billet/front_reservation.html.twig', [
            'form' => $form,
            'event' => $event,
            'prixFinal' => $prix,
            'promoCodes' => [],
        ]);
    }




    #[Route('/test-mail', name: 'test_mail')]
    public function testMail(BilletMailerService $mailer): Response
    {
        $mailer->sendBilletEmail('dark_soul@hotmail.fr', 'TestUser');
        return new Response('Email sent');
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
