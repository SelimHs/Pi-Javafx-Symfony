<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use App\Repository\BilletRepository;
use App\Repository\EventRepository;

final class HomeController extends AbstractController
{
    #[Route('/home', name: 'app_home')]
    public function index(): Response
    {
        return $this->render('home/index.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
    #[Route('/dashboard', name: 'app_dashboard')]
    public function indexDashboard(BilletRepository $billetRepo, EventRepository $eventRepo): Response
    {
        $billetStats = [];

    $events = $eventRepo->findAll();
    foreach ($events as $event) {
        $count = $billetRepo->count(['event' => $event]);
        $billetStats[] = [
            'event' => $event->getNomEvent(),
            'count' => $count,
        ];
    }

    return $this->render('baseBack.html.twig', [
        'billetStats' => $billetStats,
        // other data...
    ]);
    }
}
