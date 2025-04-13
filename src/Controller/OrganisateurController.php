<?php

namespace App\Controller;

use App\Entity\Organisateur;
use App\Form\OrganisateurType;
use App\Repository\OrganisateurRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

#[Route('/organisateur')]
final class OrganisateurController extends AbstractController
{
    #[Route(name: 'app_organisateur_index', methods: ['GET'])]
    public function index(OrganisateurRepository $organisateurRepository): Response
    {
        return $this->render('organisateur/index.html.twig', [
            'organisateurs' => $organisateurRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_organisateur_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $organisateur = new Organisateur();
        $form = $this->createForm(OrganisateurType::class, $organisateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($organisateur);
            $entityManager->flush();

            // 🔁 Rediriger vers la page de détails de l'espace
            $espaceId = $organisateur->getEspace()?->getIdEspace(); // méthode safe pour éviter une erreur si null

            if ($espaceId) {
                return $this->redirectToRoute('app_espace_show', ['idEspace' => $espaceId]);
            }

            // Si pas d'espace, revenir à la liste générale
            return $this->redirectToRoute('app_organisateur_index');
        }

        return $this->render('organisateur/new.html.twig', [
            'organisateur' => $organisateur,
            'form' => $form,
        ]);
    }


    #[Route('/{id_org}', name: 'app_organisateur_show', methods: ['GET'])]
    public function show(Organisateur $organisateur): Response
    {
        return $this->render('organisateur/show.html.twig', [
            'organisateur' => $organisateur,
        ]);
    }

    #[Route('/{id_org}/edit', name: 'app_organisateur_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Organisateur $organisateur, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(OrganisateurType::class, $organisateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_organisateur_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('organisateur/edit.html.twig', [
            'organisateur' => $organisateur,
            'form' => $form,
        ]);
    }

    #[Route('/{id_org}', name: 'app_organisateur_delete', methods: ['POST'])]
    public function delete(Request $request, Organisateur $organisateur, EntityManagerInterface $entityManager): Response
    {
        // Récupérer l'ID de l'espace AVANT de supprimer l'organisateur
        $idEspace = $organisateur->getEspace()?->getIdEspace();

        if ($this->isCsrfTokenValid('delete' . $organisateur->getId_org(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($organisateur);
            $entityManager->flush();
        }

        // Redirection vers la page de détail de l'espace (si espace lié)
        if ($idEspace) {
            return $this->redirectToRoute('app_espace_show', ['idEspace' => $idEspace]);
        }

        // Fallback si pas d'espace (très peu probable)
        return $this->redirectToRoute('app_organisateur_index', [], Response::HTTP_SEE_OTHER);
    }
}
