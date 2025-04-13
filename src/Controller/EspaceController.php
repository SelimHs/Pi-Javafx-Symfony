<?php

namespace App\Controller;

use App\Entity\Espace;
use App\Form\EspaceType;
use App\Repository\EspaceRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;

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

    #[Route('/new', name: 'app_espace_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $espace = new Espace();
        $form = $this->createForm(EspaceType::class, $espace);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Gestion de l'image comme dans le code Event
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $newFilename = uniqid() . '.' . $imageFile->guessExtension();

                // Chemin vers le dossier uploads (ici, on utilise le même paramètre que pour event)
                $uploadDir = $this->getParameter('uploads_directory');
                $imageFile->move($uploadDir, $newFilename);

                // Sauvegarder le nom de l'image dans l'entité
                $espace->setImage($newFilename);
            }

            $entityManager->persist($espace);
            $entityManager->flush();

            return $this->redirectToRoute('app_espace_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('espace/new.html.twig', [
            'espace' => $espace,
            'form' => $form,
        ]);
    }


    #[Route('/{idEspace}', name: 'app_espace_show', methods: ['GET'])]
    public function show(Espace $espace, Request $request): Response
    {
        $request->getSession()->set('idEspace', $espace->getIdEspace());

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

            /** @var UploadedFile $imageFile */
            $imageFile = $form->get('image')->getData();

            if ($imageFile) {
                $newFilename = uniqid() . '.' . $imageFile->guessExtension();

                try {
                    $imageFile->move(
                        $this->getParameter('uploads_directory'),
                        $newFilename
                    );
                    $espace->setImage($newFilename);
                } catch (FileException $e) {
                    // Gérer l'erreur ici si besoin
                }
            }

            $entityManager->flush();

            return $this->redirectToRoute('app_espace_show', ['idEspace' => $espace->getIdEspace()]);
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
