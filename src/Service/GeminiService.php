<?php
// src/Service/GeminiService.php
namespace App\Service;

use Doctrine\DBAL\Connection;
use Symfony\Contracts\HttpClient\HttpClientInterface;

class GeminiService
{
    private string $apiKey = 'AIzaSyCIYfKgYYYMmuUzvKlAKi8byyebAri5cgQ';
    private string $apiUrl = 'https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent?key=';

    public function __construct(private Connection $conn, private HttpClientInterface $httpClient) {}

    public function getResponse(string $prompt): string
{
    $userInput = strtolower($prompt);

    if ($this->containsAny($userInput, ['événement', 'événements', 'event', 'disponible', 'quels événements', 'liste des événements'])) {
        return $this->getEvents();
    }

    if ($this->containsAny($userInput, ['espace', 'espaces', 'lieux', 'salles', 'disponibles', 'réserver un espace'])) {
        return $this->getEspaces();
    }

    if ($this->containsAny($userInput, ['produit', 'produits', 'catalogue', 'stock', 'articles', 'objets'])) {
        return $this->getProduits();
    }

    return $this->askGemini($prompt);
}

private function containsAny(string $text, array $keywords): bool
{
    foreach ($keywords as $word) {
        if (str_contains($text, $word)) {
            return true;
        }
    }
    return false;
}

    private function getEvents(): string
    {
        $res = $this->conn->executeQuery('SELECT nomEvent, prix, date, nomEspace FROM event')->fetchAllAssociative();
        if (!$res) return 'Aucun événement trouvé.';
        $txt = "🎟️ Événements disponibles :\n";
        foreach ($res as $e) {
            $txt .= "🎉 {$e['nomEvent']} | 📅 {$e['date']} | 📍 {$e['nomEspace']} | 💰 {$e['prix']} TND\n";
        }
        return $txt;
    }

    private function getEspaces(): string
    {
        $res = $this->conn->executeQuery("SELECT nomEspace, adresse, capacite FROM espace WHERE disponibilite = 'Disponible'")->fetchAllAssociative();
        if (!$res) return 'Aucun espace disponible.';
        $txt = "📍 Espaces disponibles :\n";
        foreach ($res as $e) {
            $txt .= "🏢 {$e['nomEspace']} | 📍 {$e['adresse']} | 👥 Capacité : {$e['capacite']} pers.\n";
        }
        return $txt;
    }

    private function getProduits(): string
    {
        $res = $this->conn->executeQuery("SELECT nomProduit, prixProduit, quantite FROM produit WHERE quantite > 0")->fetchAllAssociative();
        if (!$res) return 'Aucun produit en stock.';
        $txt = "🛒 Produits disponibles :\n";
        foreach ($res as $p) {
            $txt .= "📦 {$p['nomProduit']} | 💰 {$p['prixProduit']} TND | 📦 Stock : {$p['quantite']}\n";
        }
        return $txt;
    }

    private function askGemini(string $prompt): string
    {
        $fullPrompt = "Tu es un assistant pour une plateforme de gestion d'événements. Réponds clairement.\n\nUtilisateur : $prompt";
        $json = [
            "contents" => [[
                "role" => "user",
                "parts" => [["text" => $fullPrompt]]
            ]]
        ];

        $response = $this->httpClient->request('POST', $this->apiUrl . $this->apiKey, [
            'json' => $json
        ]);

        $data = $response->toArray(false);
        return $data['candidates'][0]['content']['parts'][0]['text'] ?? '🤖 Je n’ai pas compris. Réessaie.';
    }
}
?>