<?php
namespace App\Service;

use App\Entity\Produit;
use App\Repository\EspaceRepository;
use Symfony\Contracts\HttpClient\HttpClientInterface;

class AiPredictiveService
{
    private HttpClientInterface $client;
    private EspaceRepository $espaceRepository;
    private const API_KEY = 'c5EPrfyjkbxQgLU17d3PmALhiDehJPMDBmvq83Rm';

    public function __construct(HttpClientInterface $client, EspaceRepository $espaceRepository)
    {
        $this->client = $client;
        $this->espaceRepository = $espaceRepository;
    }

    public function generateIdea(array $produits): string
    {
        if (empty($produits)) {
            return "⚠️ Aucun produit sélectionné.";
        }

        $espaces = $this->espaceRepository->findAll();
        $espace = $espaces[array_rand($espaces)];

        $prompt = <<<PROMPT
Vous êtes un expert reconnu en organisation d'événements innovants.

Voici les produits sélectionnés :
PROMPT;

        foreach ($produits as $produit) {
            $prompt .= sprintf(
                "\n- Nom : %s\n  Catégorie : %s\n  Description : %s",
                $produit->getNomProduit(),
                $produit->getCategorie(),
                $produit->getDescription()
            );
        }

        $prompt .= <<<CONTEXT


À partir de ces produits, proposez une **idée d'événement originale**, bien pensée, utile et engageante.

Voici un espace de notre catalogue que vous pouvez recommander pour accueillir cet événement :

- **Nom de l'espace** : **{$espace->getNomEspace()}**
- **Adresse** : **{$espace->getAdresse()}**
- **Type** : **{$espace->getTypeEspace()}**
- **Capacité** : **{$espace->getCapacite()} personnes**

Veuillez intégrer poliment une proposition de lieu, par exemple :
"Je vous recommande vivement d'organiser cet événement à **{$espace->getNomEspace()}**, un espace parfaitement adapté à ce type de manifestation."

Merci de structurer votre réponse comme suit :
- 🎯 **Titre de l’événement**
- 📝 **Concept**
- 👥 **Public cible**
- 🧩 **Utilisation des produits**
- 📍 **Proposition d’espace**

Utilisez un **langage naturel et professionnel** comme si vous étiez un **vrai conseiller événementiel**, et non une IA.
CONTEXT;

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