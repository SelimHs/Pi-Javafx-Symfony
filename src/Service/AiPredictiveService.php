<?php
// src/Service/OpenAiService.php
// src/Service/AiPredictiveService.php
namespace App\Service;

use App\Entity\Produit;
use Symfony\Contracts\HttpClient\HttpClientInterface;

class AiPredictiveService
{
    private const API_KEY = 'c5EPrfyjkbxQgLU17d3PmALhiDehJPMDBmvq83Rm';
    private HttpClientInterface $client;

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }

    public function generateIdea(array $produits): string
    {
        if (empty($produits)) {
            return "⚠️ Aucun produit sélectionné.";
        }

        $prompt = "Je suis un expert en organisation d'événements. En te basant sur ces produits : ";
        foreach ($produits as $produit) {
            $prompt .= sprintf(
                "Nom: %s, Catégorie: %s, Description: %s, Prix: %d DT, Quantité: %d, Fournisseur: %s | ",
                $produit->getNomProduit(),
                $produit->getCategorie(),
                $produit->getDescription(),
                $produit->getPrixProduit(),
                $produit->getQuantite(),
                $produit->getFournisseur()->getNomFournisseur()
            );
        }
        $prompt .= "Suggère-moi un événement que je pourrais organiser.";

        try {
            $response = $this->client->request('POST', 'https://api.cohere.ai/v1/chat', [
                'headers' => [
                    'Authorization' => 'Bearer ' . self::API_KEY,
                    'Content-Type' => 'application/json',
                ],
                'json' => [
                    'model' => 'command-r-plus',
                    'message' => $prompt,
                    'temperature' => 0.7,
                ],
            ]);

            $data = $response->toArray();
            return $data['text'] ?? "❌ Réponse inattendue de l'API.";
        } catch (\Exception $e) {
            return "❌ Exception : " . $e->getMessage();
        }
    }
}
