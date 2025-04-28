<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use App\Repository\BilletRepository;
use App\Repository\EventRepository;
use App\Repository\EspaceRepository;
use App\Repository\FournisseurRepository;
use Symfony\Contracts\HttpClient\HttpClientInterface;

final class HomeController extends AbstractController
{
    private HttpClientInterface $client;

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }

    #[Route('/home', name: 'app_home')]
    public function index(): Response
    {
        return $this->render('home/index.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
    #[Route('/dashboard', name: 'app_dashboard')]
    public function indexDashboard(
        BilletRepository $billetRepo,
        EventRepository $eventRepo,
        EspaceRepository $espaceRepository,
        FournisseurRepository $fournisseurRepository // Ajouté si tu as les fournisseurs aussi
    ): Response {
        $billetStats = [];
        $revenuStats = [];
        $events = $eventRepo->findAll();
        
        foreach ($events as $event) {
            $count = $billetRepo->count(['event' => $event]);
            $billetStats[] = [
                'event' => $event->getNomEvent(),
                'count' => $count,
            ];
    
            $revenu = 0;
            foreach ($event->getBillets() as $billet) {
                $revenu += $billet->getPrix();
            }
            $revenuStats[] = [
                'event' => $event->getNomEvent(),
                'revenue' => $revenu,
            ];
        }
    
        // Fournisseurs par type (si tu veux continuer aussi)
        $fournisseurStats = [];
        $fournisseurs = $fournisseurRepository->findAll();
        foreach ($fournisseurs as $fournisseur) {
            $type = strtolower($fournisseur->getType());
            if (!isset($fournisseurStats[$type])) {
                $fournisseurStats[$type] = 0;
            }
            $fournisseurStats[$type]++;
        }
        $fournisseurStats = array_map(function ($type, $count) {
            return ['type' => $type, 'count' => $count];
        }, array_keys($fournisseurStats), array_values($fournisseurStats));
    
         // 🛒 🔥 Lire les réservations d'espaces
         $reservationsData = [];
         try {
             $response = $this->client->request('GET', 'https://api.sheetbest.com/sheets/4d538bcb-a52a-4dde-84e4-ddb7c9520d8e');
             $reservationsData = $response->toArray();
         } catch (\Exception $e) {
             // gérer les erreurs éventuelles
         }
 
         $topEspaces = [];
         foreach ($reservationsData as $reservation) {
             $espaceId = $reservation['id_espace'] ?? null;
             if ($espaceId) {
                 if (!isset($topEspaces[$espaceId])) {
                     $topEspaces[$espaceId] = 0;
                 }
                 $topEspaces[$espaceId]++;
             }
         }
 
         // 🔵 Charger les noms des espaces
         $espaceNames = [];
         foreach ($espaceRepository->findAll() as $espace) {
             $espaceNames[$espace->getIdEspace()] = $espace->getNomEspace();
         }
 
         $topEspacesFinal = [];
         foreach ($topEspaces as $id => $count) {
             $nom = $espaceNames[$id] ?? "Espace inconnu ($id)";
             $topEspacesFinal[] = [
                 'espace' => $nom,
                 'count' => $count
             ];
         }
 
         return $this->render('baseBack.html.twig', [
             'billetStats' => $billetStats,
             'espaces' => $espaceRepository->findAll(),
             'fournisseurStats' => $fournisseurStats,
             'revenuStats' => $revenuStats,
             'topEspacesStats' => $topEspacesFinal, // ⬅️ Injecter dans Twig
         ]);
     }
    
}
