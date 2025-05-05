<?php

namespace App\Controller;

use App\Entity\Event;
use App\Form\EventType;
use App\Repository\EventRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

#[Route('/event')]
final class EventController extends AbstractController
{
    #[Route('/countdown', name: 'event_countdown')]
public function countdown(EventRepository $eventRepo): Response
{
    $now = new \DateTime();
    $events = $eventRepo->createQueryBuilder('e')
        ->where('e.date > :now')
        ->orderBy('e.date', 'ASC')
        ->setParameter('now', $now->format('Y-m-d'))
        ->getQuery()
        ->getResult();

    return $this->render('event/countdown.html.twig', [
        'events' => $events,
    ]);
}
    #[Route(name: 'app_event_index', methods: ['GET'])]
    public function index(EventRepository $eventRepository): Response
    {
        return $this->render('event/index.html.twig', [
            'events' => $eventRepository->findAll(),
        ]);
    }
    #[Route('/dashboard', name: 'dashboard_event_index', methods: ['GET'])]
    public function indexDashboard(EventRepository $eventRepository): Response
    {
        return $this->render('event/indexBack.html.twig', [
            'events' => $eventRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_event_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $event = new Event();
        $form = $this->createForm(EventType::class, $event);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            // ✅ Convertir la date en string
            $dateObject = $form->get('date')->getData();
            if ($dateObject instanceof \DateTimeInterface) {
                $event->setDate($dateObject->format('Y-m-d'));
            }
    
            // ✅ Gérer l'image
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $newFilename = uniqid() . '.' . $imageFile->guessExtension();
    
                // Chemin vers le dossier uploads
                $uploadDir = $this->getParameter('uploads_directory');
                $imageFile->move($uploadDir, $newFilename);
    
                // Chemin absolu à sauvegarder dans la BD
                $event->setImage($newFilename);
            }
    
            // ✅ Sauvegarder l'event
            $entityManager->persist($event);
            $entityManager->flush();
    
            return $this->redirectToRoute('app_event_index');
        }
    
        return $this->render('event/new.html.twig', [
            'event' => $event,
            'form' => $form,
        ]);
    }
    #[Route('/newBack', name: 'dashboard_event_new', methods: ['GET', 'POST'])]
    public function newDashboard(Request $request, EntityManagerInterface $entityManager): Response
    {
        $event = new Event();
        $form = $this->createForm(EventType::class, $event);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            // ✅ Convertir la date en string
            $dateObject = $form->get('date')->getData();
            if ($dateObject instanceof \DateTimeInterface) {
                $event->setDate($dateObject->format('Y-m-d'));
            }
    
            // ✅ Gérer l'image
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $newFilename = uniqid() . '.' . $imageFile->guessExtension();
    
                // Chemin vers le dossier uploads
                $uploadDir = $this->getParameter('uploads_directory');
                $imageFile->move($uploadDir, $newFilename);
    
                // Chemin absolu à sauvegarder dans la BD
                $event->setImage($newFilename);
            }
    
            // ✅ Sauvegarder l'event
            $entityManager->persist($event);
            $entityManager->flush();
    
            return $this->redirectToRoute('dashboard_event_index');
        }
    
        return $this->render('event/newBack.html.twig', [
            'event' => $event,
            'form' => $form,
        ]);
    }
    

    #[Route('/{idEvent}', name: 'app_event_show', methods: ['GET'])]
    public function show(Event $event): Response
    {
        return $this->render('event/show.html.twig', [
            'event' => $event,
        ]);
    }
    #[Route('/show/{idEvent}', name: 'dashboard_event_show', methods: ['GET'])]
    public function showDashboard(Event $event): Response
    {
        return $this->render('event/showBack.html.twig', [
            'event' => $event,
        ]);
    }

    #[Route('/{idEvent}/edit', name: 'app_event_edit', methods: ['GET', 'POST'])]
public function edit(Request $request, Event $event, EntityManagerInterface $entityManager): Response
{
    $form = $this->createForm(EventType::class, $event);
    $form->get('date')->setData(\DateTime::createFromFormat('Y-m-d', $event->getDate()));

    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        // ✅ Convertir la date (non mappée) en string
        $dateObject = $form->get('date')->getData();
        if ($dateObject instanceof \DateTimeInterface) {
            $event->setDate($dateObject->format('Y-m-d'));
        }

        // ✅ Gérer l'image si une nouvelle est uploadée
        $imageFile = $form->get('image')->getData();
        if ($imageFile) {
            $newFilename = uniqid() . '.' . $imageFile->guessExtension();
            $uploadDir = $this->getParameter('uploads_directory');
            $imageFile->move($uploadDir, $newFilename);

            $event->setImage($newFilename);
        }

        // ✅ Enregistrer les modifications
        $entityManager->flush();

        return $this->redirectToRoute('app_event_index', [], Response::HTTP_SEE_OTHER);
    }

    return $this->render('event/edit.html.twig', [
        'event' => $event,
        'form' => $form,
    ]);
}
#[Route('/edit/{idEvent}', name: 'dashboard_event_edit', methods: ['GET', 'POST'])]
public function editDasboard(Request $request, Event $event, EntityManagerInterface $entityManager): Response
{
    $form = $this->createForm(EventType::class, $event);
    $form->get('date')->setData(\DateTime::createFromFormat('Y-m-d', $event->getDate()));
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        // ✅ Convertir la date (non mappée) en string
        $dateObject = $form->get('date')->getData();
        if ($dateObject instanceof \DateTimeInterface) {
            $event->setDate($dateObject->format('Y-m-d'));
        }

        // ✅ Gérer l'image si une nouvelle est uploadée
        $imageFile = $form->get('image')->getData();
        if ($imageFile) {
            $newFilename = uniqid() . '.' . $imageFile->guessExtension();
            $uploadDir = $this->getParameter('uploads_directory');
            $imageFile->move($uploadDir, $newFilename);

            $event->setImage($newFilename);
        }

        // ✅ Enregistrer les modifications
        $entityManager->flush();

        return $this->redirectToRoute('dashboard_event_index', [], Response::HTTP_SEE_OTHER);
    }

    return $this->render('event/editBack.html.twig', [
        'event' => $event,
        'form' => $form,
    ]);
}




    #[Route('/delete/{idEvent}', name: 'app_event_delete', methods: ['POST'])]
    public function delete(Request $request, Event $event, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$event->getIdEvent(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($event);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_event_index', [], Response::HTTP_SEE_OTHER);
    }
     #[Route('/{idEvent}/delete', name: 'dashboard_event_delete', methods: ['POST'])]
    public function deleteDashboard(Request $request, Event $event, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$event->getIdEvent(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($event);
            $entityManager->flush();
        }

        return $this->redirectToRoute('dashboard_event_index', [], Response::HTTP_SEE_OTHER);
    }
    
}