<?php

namespace App\Service;

use Symfony\Contracts\HttpClient\HttpClientInterface;

class CronofyService
{
    private string $clientId = 'MhPDLlsOgUJOBpp4RzSzLIqT2T3qPqBB';
    private string $clientSecret = 'CRN_8b60UnQ0EnDRNnR7vYhOUdR2nFvsr4LgQXcwJc'; // raccourci pour sécurité
    private string $apiUrl = 'https://api.cronofy.com';
    private HttpClientInterface $client;

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }
    public function checkConflicts(\DateTime $start, \DateTime $end): bool
    {
        // ⚠️ Désactivation complète de la détection de conflit
        return false;
    }

    public function getAccessToken(): ?string
    {
        try {
            $response = $this->client->request('POST', $this->apiUrl . '/oauth/token', [
                'headers' => [
                    'Content-Type' => 'application/x-www-form-urlencoded'
                ],
                'body' => [
                    'client_id' => $this->clientId,
                    'client_secret' => $this->clientSecret,
                    'grant_type' => 'client_credentials',
                    'scope' => 'read_events create_event delete_event'
                ]
            ]);

            $data = $response->toArray();

            if (!isset($data['access_token'])) {
                throw new \Exception('No access token in response: ' . json_encode($data));
            }

            return $data['access_token'];
        } catch (\Exception $e) {
            // Log l'erreur complète pour le débogage
            error_log('Cronofy OAuth Error: ' . $e->getMessage());
            return null;
        }
    }



    public function createReservation(array $reservationData): array
    {
        // ✅ Étape 1 : Vérifier l'accès
        $token = $this->getAccessToken();
        if (!$token) {
            return ['error' => 'No access token'];
        }

        // ✅ Étape 2 : Vérifier les champs obligatoires
        if (empty($reservationData['start']) || empty($reservationData['end']) || empty($reservationData['summary'])) {
            return ['error' => 'Champs obligatoires manquants (start, end, summary).'];
        }

        try {
            $startDate = new \DateTime($reservationData['start']);
            $endDate = new \DateTime($reservationData['end']);
            $now = new \DateTime();

            // ✅ Étape 3 : Vérifier que la date de début est dans 7 jours minimum
            $minStartDate = (clone $now)->modify('+7 days');
            if ($startDate < $minStartDate) {
                return ['error' => 'La réservation doit commencer au moins 7 jours après aujourd’hui.'];
            }

            // ✅ Étape 4 : Vérifier que la date de fin est après la date de début
            if ($endDate <= $startDate) {
                return ['error' => 'La date de fin doit être postérieure à la date de début.'];
            }

            // ✅ Étape 5 : Vérification collision dans Cronofy (optionnel mais recommandé)
            $conflictCheck = $this->client->request('GET', $this->apiUrl . '/v1/events', [
                'headers' => [
                    'Authorization' => 'Bearer ' . $token
                ],
                'query' => [
                    'from' => $startDate->format(\DateTime::ATOM),
                    'to'   => $endDate->format(\DateTime::ATOM),
                    'tzid' => $reservationData['tzid'] ?? 'Europe/Paris',
                ]
            ]);

            $existingEvents = $conflictCheck->toArray(false);
            if (!empty($existingEvents['events'])) {
                return ['error' => 'L’espace est déjà réservé pour cette période.'];
            }

            // ✅ Étape 6 : Enregistrement dans Cronofy
            $response = $this->client->request('POST', $this->apiUrl . '/v1/events', [
                'headers' => [
                    'Authorization' => 'Bearer ' . $token
                ],
                'json' => $reservationData
            ]);

            return $response->toArray(false);
        } catch (\Exception $e) {
            return ['error' => 'Erreur lors de la réservation : ' . $e->getMessage()];
        }
    }
    public function getAllReservations(): array
    {
        $token = $this->getAccessToken();
        if (!$token) return [];

        $from = (new \DateTime('-1 year'))->format(\DateTime::ATOM);
        $to = (new \DateTime('+1 year'))->format(\DateTime::ATOM);

        $response = $this->client->request('GET', $this->apiUrl . '/v1/events', [
            'headers' => ['Authorization' => 'Bearer ' . $token],
            'query' => [
                'from' => $from,
                'to' => $to,
                'tzid' => 'Europe/Paris'
            ]
        ]);

        $data = $response->toArray(false);
        return $data['events'] ?? [];
    }
}
