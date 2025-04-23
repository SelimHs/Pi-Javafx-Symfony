<?php

namespace App\Controller;

use App\Entity\Remise;
use App\Form\RemiseType;
use App\Repository\RemiseRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

#[Route('/remise')]
final class RemiseController extends AbstractController
{
    #[Route(name: 'app_remise_index', methods: ['GET'])]
    public function index(RemiseRepository $remiseRepository): Response
    {
        return $this->render('remise/index.html.twig', [
            'remises' => $remiseRepository->findAll(),
        ]);
    }
    

    #[Route('/new', name: 'app_remise_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $remise = new Remise();
        $form = $this->createForm(RemiseType::class, $remise);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($remise);
            $entityManager->flush();

            return $this->redirectToRoute('app_remise_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('remise/new.html.twig', [
            'remise' => $remise,
            'form' => $form,
        ]);
    }

    #[Route('/{idRemise}', name: 'app_remise_show', methods: ['GET'])]
    public function show(Remise $remise): Response
    {
        return $this->render('remise/show.html.twig', [
            'remise' => $remise,
        ]);
    }

    #[Route('/{idRemise}/edit', name: 'app_remise_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Remise $remise, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(RemiseType::class, $remise);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_remise_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('remise/edit.html.twig', [
            'remise' => $remise,
            'form' => $form,
        ]);
    }

    #[Route('/{idRemise}', name: 'app_remise_delete', methods: ['POST'])]
    public function delete(Request $request, Remise $remise, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$remise->getIdRemise(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($remise);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_remise_index', [], Response::HTTP_SEE_OTHER);
    }
}
