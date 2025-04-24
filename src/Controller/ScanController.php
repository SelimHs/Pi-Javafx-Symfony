<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;

class ScanController extends AbstractController
{
    #[Route('/scan/data', name: 'scan_receive', methods: ['GET'])]
    public function receiveData(Request $request): Response
    {
        $doubleEncodedData = $request->query->get('data');
        if (!$doubleEncodedData) {
            return new Response('No data received.', 400);
        }

        // Decode twice
        $decodedJson = urldecode(urldecode($doubleEncodedData));

        // Save to file
        $filePath = $this->getParameter('kernel.project_dir') . '/public/event_data.json';
        file_put_contents($filePath, $decodedJson);

        return $this->redirectToRoute('scan_view');
    }

    #[Route('/scan/view', name: 'scan_view', methods: ['GET'])]
    public function viewEvent(): Response
    {
        return $this->render('scan/view.html.twig');
    }

    #[Route('/scan/data.json', name: 'scan_data_json', methods: ['GET'])]
    public function getEventData(): JsonResponse
    {
        $filePath = $this->getParameter('kernel.project_dir') . '/public/event_data.json';

        if (!file_exists($filePath)) {
            return new JsonResponse(['error' => 'Event data not found.'], 404);
        }

        $jsonContent = file_get_contents($filePath);
        return JsonResponse::fromJsonString($jsonContent);
    }
}
