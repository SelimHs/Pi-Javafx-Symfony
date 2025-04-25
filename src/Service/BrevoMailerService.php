<?php
namespace App\Service;

use Symfony\Contracts\HttpClient\HttpClientInterface;

use Twig\Environment;

class BrevoMailerService
{
    private HttpClientInterface $client;
    private string $apiKey = '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'; // replace this
    private Environment $twig;

    public function __construct(HttpClientInterface $client,  Environment $twig)
    {
        $this->client = $client;
        $this->twig = $twig;
    }

    public function sendConfirmation(
        string $toEmail,
        string $eventName,
        string $user = 'Cher(e) participant(e)',
        string $location = 'Lieu inconnu',
        string $rawDate = '0000-00-00', // ✅ keep it as string
        ?string $pdfPath = null
    ): void {
        // Convert string date to DateTime only for rendering
        $dateObj = \DateTime::createFromFormat('Y-m-d', $rawDate) ?: new \DateTime();
    
        $html = $this->twig->render('emails/confirmation.html.twig', [
            'user' => $user,
            'event' => $eventName,
            'location' => $location,
            'date' => $dateObj,
        ]);
    
        $jsonBody = [
            'sender' => [
                'name' => 'Lamma',
                'email' => 'emailsender.lamma@gmail.com',
            ],
            'to' => [
                ['email' => $toEmail],
            ],
            'subject' => '🎫 Confirmation de votre billet',
            'htmlContent' => $html,
        ];
    
        if ($pdfPath && file_exists($pdfPath)) {
            $jsonBody['attachment'] = [[
                'content' => base64_encode(file_get_contents($pdfPath)),
                'name' => basename($pdfPath),
            ]];
    
            file_put_contents('brevo_mail_log.log', "✅ PDF attached: " . basename($pdfPath) . "\n", FILE_APPEND);
        } else {
            file_put_contents('brevo_mail_log.log', "❌ PDF missing: $pdfPath\n", FILE_APPEND);
        }
    
        $this->client->request('POST', 'https://api.brevo.com/v3/smtp/email', [
            'headers' => [
                'accept' => 'application/json',
                'api-key' => $this->apiKey,
                'content-type' => 'application/json',
            ],
            'json' => $jsonBody,
        ]);
    }
    
    
}
?>