<?php
namespace App\Controller;

use App\Service\GeminiService;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class ChatbotController extends AbstractController
{
    #[Route('/api/chat', name: 'chatbot_ask', methods: ['POST'])]
    public function ask(Request $request, GeminiService $gemini): JsonResponse
    {
        $data = json_decode($request->getContent(), true);
        $msg = $data['message'] ?? '';
        if (!$msg) return new JsonResponse(['error' => 'Message vide'], 400);

        $response = $gemini->getResponse($msg);
        return new JsonResponse(['reply' => $response]);
    }
}
?>