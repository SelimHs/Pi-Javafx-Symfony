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
use Symfony\Component\HttpFoundation\StreamedResponse;
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
use Symfony\Component\Security\Http\SecurityRequestAttributes;
use App\Service\StripeService;

use Symfony\Component\HttpFoundation\JsonResponse;


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

    #[Route('/{idBillet}', name: 'app_billet_show', methods: ['GET'])]
    public function show(Billet $billet): Response
    {
        return $this->render('billet/show.html.twig', [
            'billet' => $billet,
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



    #[Route('/export/billets', name: 'export_billets_excel')]
    public function exportBillets(BilletRepository $repo): StreamedResponse
    {
        $billets = $repo->findAll();
    
        $spreadsheet = new Spreadsheet();
        $sheet = $spreadsheet->getActiveSheet();
        $sheet->setTitle('Billets');
    
        // Header row values
        $headers = ['Propriétaire', 'Prix (DT)', 'Date d\'achat', 'Type'];
        $sheet->fromArray($headers, null, 'A1');
    
        // 🔹 Header Style
        $headerStyle = [
            'font' => ['bold' => true, 'color' => ['argb' => 'FFFFFFFF'], 'size' => 13],
            'fill' => [
                'fillType' => \PhpOffice\PhpSpreadsheet\Style\Fill::FILL_SOLID,
                'startColor' => ['argb' => 'FF34495E'], // dark blue/gray
            ],
            'alignment' => ['horizontal' => \PhpOffice\PhpSpreadsheet\Style\Alignment::HORIZONTAL_CENTER],
            'borders' => ['allBorders' => ['borderStyle' => \PhpOffice\PhpSpreadsheet\Style\Border::BORDER_MEDIUM]],
        ];
        $sheet->getStyle('A1:D1')->applyFromArray($headerStyle);
    
        // 🔹 Data Rows
        $row = 2;
        foreach ($billets as $billet) {
            $sheet->setCellValue("A{$row}", $billet->getProprietaire());
            $sheet->setCellValue("B{$row}", $billet->getPrix());
            $sheet->setCellValue("C{$row}", $billet->getDateAchat()?->format('Y-m-d H:i') ?? '—');
            $sheet->setCellValue("D{$row}", $billet->getType());
    
            // Alternate row color
            $fillColor = ($row % 2 === 0) ? 'FFF9F9F9' : 'FFFFFFFF';
            $sheet->getStyle("A{$row}:D{$row}")->applyFromArray([
                'fill' => [
                    'fillType' => \PhpOffice\PhpSpreadsheet\Style\Fill::FILL_SOLID,
                    'startColor' => ['argb' => $fillColor],
                ],
                'alignment' => ['horizontal' => \PhpOffice\PhpSpreadsheet\Style\Alignment::HORIZONTAL_CENTER],
                'borders' => ['allBorders' => ['borderStyle' => \PhpOffice\PhpSpreadsheet\Style\Border::BORDER_THIN]],
                'font' => ['size' => 11],
            ]);
    
            $row++;
        }
    
        // 🔹 Auto-size columns
        foreach (range('A', 'D') as $col) {
            $sheet->getColumnDimension($col)->setAutoSize(true);
        }
    
        // 🔹 Freeze top row
        $sheet->freezePane('A2');
    
        // 🔹 Enable filter
        $sheet->setAutoFilter($sheet->calculateWorksheetDimension());
    
        // Output
        $response = new StreamedResponse(function () use ($spreadsheet) {
            $writer = new Xlsx($spreadsheet);
            $writer->save('php://output');
        });
    
        $filename = 'billets_export_' . date('Ymd_His') . '.xlsx';
        $response->headers->set('Content-Type', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
        $response->headers->set('Content-Disposition', "attachment;filename=\"{$filename}\"");
        $response->headers->set('Cache-Control', 'max-age=0');
    
        return $response;
    }
    
    




    #[Route('/reservation/{id}', name: 'app_billet_reservation', methods: ['GET', 'POST'])]
    public function newFront(
        Request $request,
        Event $event,
        EntityManagerInterface $em,
        RemiseRepository $remiseRepo,
        PdfGeneratorService $pdfGenerator,
        BrevoMailerService $brevoMailer,
        StripeService $stripeService
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
        $reservation->setUser($em->getRepository(\App\Entity\User::class)->find(1)); // tu peux changer le user plus tard

        if ($form->isSubmitted() && $form->isValid()) {
            // 🔵 Calculer le prix selon type de billet
            if ($billet->getType() === 'DUO') {
                $prix += $event->getPrix() * 0.5;
            } elseif ($billet->getType() === 'VIP') {
                $prix = $event->getPrix() * 3;
            }

            // 🔵 Appliquer remise si code promo
            $codePromo = $form->get('codePromo')->getData();
            if ($codePromo) {
                $remise = $remiseRepo->findOneBy(['codePromo' => $codePromo]);
                if ($remise) {
                    $prix -= $prix * ($remise->getPourcentageRemise() / 100);
                    $reservation->setRemise($remise);
                }
            }

            // 🔵 Remplir billet
            $billet->setPrix((int)$prix);
            $billet->setDateAchat(new \DateTime());
            $billet->setReservation($reservation);

            // 🔵 Persister en base
            $em->persist($reservation);
            $em->persist($billet);
            $em->flush(); // 🔥 Reservation et Billet créés

            // 🔵 Générer PDF
            $pdfPath = $pdfGenerator->generateBilletPdf($billet);

            // 🔵 Envoyer Email de confirmation
            $email = $this->getUser()?->getUserIdentifier();
            $brevoMailer->sendConfirmation(
                $email,
                $event->getNomEvent(),
                $billet->getProprietaire(),
                $event->getNomEspace(),
                $event->getDate(),
                $pdfPath
            );

            // 🔵 Générer PaymentIntent Stripe
            $paymentIntent = $stripeService->createPaymentIntent($prix, 'usd');
            if (!$paymentIntent) {
                throw new \Exception('Erreur lors de la création du PaymentIntent Stripe.');
            }

            return $this->render('billet/front_reservation.html.twig', [
                'form' => $form, // 🛠 OBLIGATOIRE
                'clientSecret' => $paymentIntent->client_secret,
                'prixFinal' => $prix,
                'event' => $event,
                'promoCodes' => [], // tu peux mettre tes remises
            ]);
            
        }

        // 🔵 Afficher le formulaire de réservation (page initiale)
        return $this->render('billet/front_reservation.html.twig', [
            'form' => $form,
            'event' => $event,
            'prixFinal' => $prix,
            'promoCodes' => [],
        ]);
    }
    #[Route('/create-payment-intent', name: 'app_create_payment_intent', methods: ['POST'])]
public function createPaymentIntent(Request $request, StripeService $stripeService): JsonResponse
{
    $data = json_decode($request->getContent(), true);
    $prix = $data['prix'] ?? 0;

    $paymentIntent = $stripeService->createPaymentIntent($prix, 'usd');

    return new JsonResponse([
        'clientSecret' => $paymentIntent->client_secret,
    ]);
}

    #[Route('/payment-success', name: 'payment_success', methods: ['POST'])]
public function paymentSuccess(
    Request $request,
    EntityManagerInterface $em,
    PdfGeneratorService $pdfGenerator,
    BrevoMailerService $brevoMailer
): JsonResponse {
    $data = json_decode($request->getContent(), true);
    $billetId = $data['billetId'] ?? null;

    if (!$billetId) {
        return new JsonResponse(['status' => 'error', 'message' => 'Aucun billet trouvé'], 400);
    }

    $billet = $em->getRepository(Billet::class)->find($billetId);

    if (!$billet) {
        return new JsonResponse(['status' => 'error', 'message' => 'Billet introuvable'], 404);
    }

    // 🔵 Générer PDF
    $pdfPath = $pdfGenerator->generateBilletPdf($billet);

    // 🔵 Envoyer email
    $email = $this->getUser()?->getUserIdentifier() ?? 'client@example.com'; // Remplace par ton vrai user connecté
    $brevoMailer->sendConfirmation(
        $email,
        $billet->getEvent()->getNomEvent(),
        $billet->getProprietaire(),
        $billet->getEvent()->getNomEspace(),
        $billet->getEvent()->getDate(),
        $pdfPath
    );

    return new JsonResponse(['status' => 'success', 'redirectUrl' => $this->generateUrl('app_event_index')]);
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
