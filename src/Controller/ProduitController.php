<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Form\ProduitType;
use App\Repository\ProduitRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use App\Service\OpenAiService;


use Symfony\Component\HttpFoundation\JsonResponse;

#[Route('/produit')]
final class ProduitController extends AbstractController
{
    #[Route(name: 'app_produit_index', methods: ['GET'])]
    public function index(ProduitRepository $produitRepository): Response
    {
        return $this->render('produit/index.html.twig', [
            'produits' => $produitRepository->findAll(),
        ]);
    }

    #[Route('/back', name: 'app_produit_indexback', methods: ['GET'])]
    public function indexback(ProduitRepository $produitRepository): Response
    {
        return $this->render('produit/indexBack.html.twig', [
            'produits' => $produitRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'dashboard_produit_index', methods: ['GET', 'POST'])]
    #[Route('/dashboard/new', name: 'dashboard_produit_new', methods: ['GET', 'POST'])]
    public function newProduit(Request $request, EntityManagerInterface $entityManager): Response
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('imagePath')->getData();

            if ($file) {
                $fileName = uniqid() . '.' . $file->guessExtension();
                $file->move(
                    $this->getParameter('uploads_directory'),
                    $fileName
                );
                $produit->setImagePath($fileName);
            }

            $entityManager->persist($produit);
            $entityManager->flush();

            return $this->redirectToRoute('app_produit_index');
        }

        $template = $request->attributes->get('_route') === 'dashboard_produit_new'
            ? 'produit/newBack.html.twig'
            : 'produit/new.html.twig';

        return $this->render($template, [
            'produit' => $produit,
            'form' => $form,
        ]);
    }

    #[Route('/{idProduit}', name: 'app_produit_show', methods: ['GET'])]
    public function show(Produit $produit): Response
    {
        return $this->render('produit/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    #[Route('/dashboard/{idProduit}', name: 'dashboard_produit_show', methods: ['GET'])]
    public function showBack(Produit $produit): Response
    {
        return $this->render('produit/showBack.html.twig', [
            'produit' => $produit,
        ]);
    }

    #[Route('/{idProduit}/edit', name: 'app_produit_edit', methods: ['GET', 'POST'])]
    #[Route('/dashboard/{idProduit}/edit', name: 'dashboard_produit_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Produit $produit, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('imagePath')->getData();

            if ($file) {
                $fileName = uniqid() . '.' . $file->guessExtension();
                $file->move(
                    $this->getParameter('uploads_directory'),
                    $fileName
                );
                $produit->setImagePath($fileName);
            }

            $entityManager->flush();

            $redirectRoute = $request->attributes->get('_route') === 'dashboard_produit_edit'
                ? 'dashboard_produit_index'
                : 'app_produit_index';

            return $this->redirectToRoute($redirectRoute);
        }

        $template = $request->attributes->get('_route') === 'dashboard_produit_edit'
            ? 'produit/editBack.html.twig'
            : 'produit/edit.html.twig';

        return $this->render($template, [
            'produit' => $produit,
            'form' => $form,
        ]);
    }

    #[Route('/{idProduit}', name: 'app_produit_delete', methods: ['POST'])]
    #[Route('/dashboard/{idProduit}', name: 'dashboard_produit_delete', methods: ['POST'])]
    public function delete(Request $request, Produit $produit, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete' . $produit->getIdProduit(), $request->request->get('_token'))) {
            $entityManager->remove($produit);
            $entityManager->flush();
        }

        $redirectRoute = $request->attributes->get('_route') === 'dashboard_produit_delete'
            ? 'dashboard_produit_index'
            : 'app_produit_index';

        return $this->redirectToRoute($redirectRoute);
    }
    #[Route('/generate-event-idea', name: 'produit_generate_event_idea', methods: ['GET'])]
    public function generateEventIdea(
        Request $request,
        ProduitRepository $produitRepository,
        OpenAiService $openAiService
    ): JsonResponse {
        try {
            $idsParam = $request->query->get('ids', '');
            $ids = array_filter(explode(',', $idsParam));

            if (empty($ids)) {
                return new JsonResponse(['idea' => "❗ Aucun ID de produit fourni."], 400);
            }

            // 🔍 Récupération des produits
            $produits = $produitRepository->findBy(['idProduit' => $ids]);

            if (empty($produits)) {
                return new JsonResponse(['idea' => "❗ Aucun produit trouvé."], 404);
            }

            // 🧠 Générer l'idée via OpenAI
            $idea = $openAiService->generateEventIdea($produits);

            // ✅ Sécurité : forcer string
            if (!is_string($idea)) {
                $idea = json_encode($idea);
            }

            return new JsonResponse(['idea' => $idea]);
        } catch (\Throwable $e) {
            // 🛡️ Sécurité totale : toujours retourner un JSON
            return new JsonResponse([
                'idea' => "❌ Erreur technique : " . $e->getMessage()
            ], 500);
        }
    }
}
