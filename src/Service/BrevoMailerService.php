<?php
namespace App\Service;

use Symfony\Contracts\HttpClient\HttpClientInterface;

class BrevoMailerService
{
    private HttpClientInterface $client;
    private string $apiKey = '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'; // replace this

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }

    public function sendConfirmation(string $toEmail, string $eventName): void
    {
        $response = $this->client->request('POST', 'https://api.brevo.com/v3/smtp/email', [
            'headers' => [
                'accept' => 'application/json',
                'api-key' => $this->apiKey,
                'content-type' => 'application/json',
            ],
            'json' => [
                'sender' => [
                    'name' => 'Lamma',
                    'email' => 'emailsender.lamma@gmail.com',
                ],
                'to' => [
                    ['email' => $toEmail],
                ],
                'subject' => '🎫 Confirmation de votre billet',
                'htmlContent' => "<p>Merci pour votre réservation à <strong>{$eventName}</strong>.</p>",
            ],
        ]);
    
        // 🚨 Log the response to see what's happening
        $statusCode = $response->getStatusCode();
        $content = $response->getContent(false);
    
        file_put_contents('brevo_mail_log.log', "Status: $statusCode\nResponse:\n$content\n", FILE_APPEND);
    }
}
?>