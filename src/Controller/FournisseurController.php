<?php

namespace App\Controller;

use App\Service\TwilioService;

use App\Entity\Fournisseur;
use App\Form\FournisseurType;
use App\Repository\FournisseurRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Knp\Snappy\Pdf;
use Twig\Environment;

#[Route('/fournisseur')]
final class FournisseurController extends AbstractController
{
    #[Route(name: 'app_fournisseur_index', methods: ['GET'])]
    public function index(FournisseurRepository $fournisseurRepository): Response
    {
        return $this->render('fournisseur/index.html.twig', [
            'fournisseurs' => $fournisseurRepository->findAll(),
        ]);
    }

    #[Route('/export', name: 'fournisseur_export_pdf', methods: ['GET'])]
public function exportPdf(Pdf $knpSnappyPdf, Environment $twig, FournisseurRepository $repo): Response
{
    $fournisseurs = $repo->findAll();

    $html = $twig->render('fournisseur/pdf.html.twig', [
        'fournisseurs' => $fournisseurs,
    ]);

    $pdfContent = $knpSnappyPdf->getOutputFromHtml($html);

    return new Response(
        $pdfContent,
        200,
        [
            'Content-Type' => 'application/pdf',
            'Content-Disposition' => 'inline; filename="fournisseurs.pdf"'
        ]
    );
}


    #[Route('/new', name: 'app_fournisseur_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager, TwilioService $twilioService): Response
    {
        $fournisseur = new Fournisseur();
        $form = $this->createForm(FournisseurType::class, $fournisseur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('imagePath')->getData();

            if ($file) {
                $fileName = uniqid() . '.' . $file->guessExtension();
                $file->move($this->getParameter('image_directory'), $fileName);
                $fournisseur->setImagePath($fileName);
            }

            $entityManager->persist($fournisseur);
            $entityManager->flush();

            // Envoi du SMS après insertion
            $smsSuccess = $twilioService->sendSms($fournisseur->getTelephone(), $fournisseur->getNomFournisseur());

            if (!$smsSuccess) {
                $this->addFlash('warning', '⚠️ Fournisseur enregistré, mais l’envoi du SMS a échoué.');
            } else {
                $this->addFlash('success', '✅ Fournisseur enregistré et SMS envoyé avec succès.');
            }
            
            return $this->redirectToRoute('app_fournisseur_index');
        }

        return $this->render('fournisseur/new.html.twig', [
            'fournisseur' => $fournisseur,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/{idFournisseur}', name: 'app_fournisseur_show', methods: ['GET'])]
    public function show(Fournisseur $fournisseur): Response
    {
        return $this->render('fournisseur/show.html.twig', [
            'fournisseur' => $fournisseur,
        ]);
    }

    #[Route('/{idFournisseur}/edit', name: 'app_fournisseur_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Fournisseur $fournisseur, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(FournisseurType::class, $fournisseur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('imagePath')->getData();

            if ($file) {
                $fileName = uniqid() . '.' . $file->guessExtension();
                $file->move(
                    $this->getParameter('image_directory'),
                    $fileName
                );
                $fournisseur->setImagePath($fileName);
            }

            $entityManager->flush();

            return $this->redirectToRoute('app_fournisseur_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('fournisseur/edit.html.twig', [
            'fournisseur' => $fournisseur,
            'form' => $form->createView(), // ⚠️ fix here: createView()
        ]);
    }


    #[Route('/{idFournisseur}', name: 'app_fournisseur_delete', methods: ['POST'])]
    public function delete(Request $request, Fournisseur $fournisseur, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete' . $fournisseur->getIdFournisseur(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($fournisseur);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_fournisseur_index', [], Response::HTTP_SEE_OTHER);
    }
}