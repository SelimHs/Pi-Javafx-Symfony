<?php

namespace App\Service;

use App\Entity\Produit;
use Symfony\Contracts\HttpClient\HttpClientInterface;

class OpenAiService
{
    private const API_URL = 'https://api.openai.com/v1/chat/completions';
    private string $apiKey;
    private HttpClientInterface $client;

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
        $this->apiKey = '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'; // Remplace par ta clé OpenAI
    }

    public function generateEventIdea(array $produits): string
    {
        if (empty($produits)) {
            return "⚠️ Aucun produit sélectionné.";
        }

        $prompt = "Voici une liste de produits : ";
        foreach ($produits as $produit) {
            $prompt .= sprintf(
                "Nom: %s, Catégorie: %s, Description: %s | ",
                $produit->getNomProduit(),
                $produit->getCategorie(),
                $produit->getDescription()
            );
        }

        $prompt .= "Propose une idée d'événement original qu'on pourrait organiser autour de ces produits.";

        try {
            $response = $this->client->request('POST', self::API_URL, [
                'headers' => [
                    'Authorization' => 'Bearer ' . $this->apiKey,
                    'Content-Type' => 'application/json',
                ],
                'json' => [
                    'model' => 'gpt-3.5-turbo',
                    'messages' => [
                        ['role' => 'system', 'content' => 'Tu es un expert en organisation d\'événements.'],
                        ['role' => 'user', 'content' => $prompt],
                    ],
                    'temperature' => 0.7,
                ],
            ]);

            $content = $response->getContent(false);
            $data = json_decode($content, true);

            if (json_last_error() !== JSON_ERROR_NONE) {
                return "❌ Réponse OpenAI invalide : " . $content;
            }

            return $data['choices'][0]['message']['content'] ?? "❌ Réponse vide.";
        } catch (\Exception $e) {
            return "❌ Exception OpenAI : " . $e->getMessage();
        }
    }
}
