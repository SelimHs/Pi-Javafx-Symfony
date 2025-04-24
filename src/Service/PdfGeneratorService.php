<?php

namespace App\Service;

use App\Entity\Billet;
use Symfony\Contracts\HttpClient\HttpClientInterface;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

class PdfGeneratorService
{
    private const API_KEY = 'selim.hassani@esprit.tn_7sGsfZw8OXsK0B0ACUDF7S1GqLNzKPf9P8syuyQlnx6dRBGIpFiYSf9HLUhv3fbK';

    private HttpClientInterface $client;
    private UrlGeneratorInterface $router;

    public function __construct(HttpClientInterface $client, UrlGeneratorInterface $router)
    {
        $this->client = $client;
        $this->router = $router;
    }

    public function generateBilletPdf(Billet $billet): ?string
{
    $proprietaire = $billet->getProprietaire();
    $event = $billet->getEvent();

    // Format the event date if it's valid
    $dateObj = \DateTime::createFromFormat('Y-m-d', $event->getDate());
    $formattedDate = $dateObj ? $dateObj->format('Y-m-d') : 'Date inconnue';

    // Generate QR Code with Google Maps location
    $mapsUrl = 'https://www.google.com/maps/search/?api=1&query=' . urlencode($event->getNomEspace());
    $qrCodeUrl = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" . urlencode($mapsUrl);

    // HTML for the PDF
    $html = <<<HTML
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Billet de Réservation</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; padding: 20px; text-align: center; }
        .ticket { background: white; width: 420px; margin: auto; padding: 20px;
                  border-radius: 15px; box-shadow: 0px 5px 20px rgba(0,0,0,0.2);
                  border: 3px solid #2c3e50; position: relative; overflow: hidden; }
        .header { background: #2c3e50; color: white; padding: 12px; text-transform: uppercase;
                  font-size: 20px; font-weight: bold; letter-spacing: 2px; }
        .content { text-align: left; padding: 15px; }
        .content p { font-size: 16px; margin: 8px 0; }
        .content span { font-weight: bold; color: #2c3e50; }
        .divider { border-top: 2px dashed #2c3e50; margin: 15px 0; }
        .qr-section { display: flex; align-items: center; justify-content: center; padding: 10px; }
        .qr-section img { width: 120px; height: 120px; }
        .badge { background: #e74c3c; color: white; padding: 5px 10px; font-size: 14px;
                  border-radius: 5px; text-transform: uppercase; font-weight: bold; }
        .footer { font-size: 12px; color: #666; margin-top: 10px; }
    </style>
</head>
<body>
    <div class="ticket">
        <div class="header">🎫 Billet Électronique</div>
        <div class="content">
            <p><span>Nom :</span> {$proprietaire}</p>
            <p><span>Événement :</span> {$event->getNomEvent()}</p>
            <p><span>Date :</span> {$formattedDate}</p>
            <p><span>Prix :</span> {$billet->getPrix()} DT</p>
            <p class="badge">Valide uniquement pour cet événement</p>
        </div>
        <div class="divider"></div>
        <div class="qr-section">
            <img src="{$qrCodeUrl}" alt="QR Code">
        </div>
        <div class="footer">📍 Scannez ce code si vous êtes perdus !</div>
    </div>
</body>
</html>
HTML;

    // Step 1: Generate PDF with PDF.co
    $response = $this->client->request('POST', 'https://api.pdf.co/v1/pdf/convert/from/html', [
        'headers' => [
            'Content-Type' => 'application/json',
            'x-api-key' => self::API_KEY,
        ],
        'json' => [
            'html' => $html,
            'name' => 'billet_' . $proprietaire . '.pdf',
        ],
    ]);

    // Step 2: Check response and download the PDF
    if ($response->getStatusCode() === 200) {
        $result = $response->toArray(false);
        $remotePdfUrl = $result['url'] ?? null;

        if ($remotePdfUrl) {
            $pdfContent = file_get_contents($remotePdfUrl);
            $fileName = 'billet_' . $billet->getProprietaire() . '_' . time() . '.pdf';
            $localPath = __DIR__ . '/../../public/uploads/billets/' . $fileName;

            file_put_contents($localPath, $pdfContent);
            return realpath($localPath);
        }
    }

    return null;
}

}