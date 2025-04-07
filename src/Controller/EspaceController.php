<?php

namespace App\Controller;
use App\Entity\User;

use App\Entity\Espace;
use App\Form\EspaceType;
use App\Repository\EspaceRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Bridge\Doctrine\Attribute\MapEntity;

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
    
    // src/Controller/HomeController.php
    #[Route('/', name: 'app_home')]
    public function indexx(): Response
    {
        return $this->render('home/index.html.twig');
    }
    #[Route('/new', name: 'app_espace_new', methods: ['GET', 'POST'])]
public function new(Request $request, EntityManagerInterface $entityManager): Response
{
    $espace = new Espace();

    // Valeurs par défaut
    $espace->setDisponibilite("Disponible");
    $user = $entityManager->getRepository(User::class)->find(1);
    $espace->setUser($user);

    $form = $this->createForm(EspaceType::class, $espace);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $entityManager->persist($espace);
        $entityManager->flush();

        return $this->redirectToRoute('app_espace_index');
    }

    return $this->render('espace/new.html.twig', [
        'form' => $form,
    ]);
}

    #[Route('/{idEspace}', name: 'app_espace_show', methods: ['GET'])]
    public function show(
        #[MapEntity(mapping: ['idEspace' => 'idEspace'])] 
        Espace $espace
    ): Response {
        return $this->render('espace/show.html.twig', [
            'espace' => $espace,
        ]);
    }

    #[Route('/{idEspace}/edit', name: 'app_espace_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Espace $espace, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(EspaceType::class, $espace);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_espace_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('espace/edit.html.twig', [
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

        return $this->redirectToRoute('app_espace_index', [], Response::HTTP_SEE_OTHER);
    }
}
