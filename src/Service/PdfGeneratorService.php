<?php

namespace App\Service;

use App\Entity\Billet;
use Symfony\Contracts\HttpClient\HttpClientInterface;

class PdfGeneratorService
{
    private const API_KEY = 'selim.hassani@esprit.tn_7sGsfZw8OXsK0B0ACUDF7S1GqLNzKPf9P8syuyQlnx6dRBGIpFiYSf9HLUhv3fbK';
    
    private HttpClientInterface $client;

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }

    public function generateBilletPdf(Billet $billet): ?string
    {
        $proprietaire = $billet->getProprietaire();
        $event = $billet->getEvent()->getNomEvent();
        $prix = $billet->getPrix();
        $type = $billet->getType();

        // Basic QR code that encodes only the owner's name
        $qrData = urlencode($proprietaire);
        $qrCodeUrl = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data={$qrData}";

        // HTML template
        $html = "
        <html>
        <head><style>
            body { font-family: Arial, sans-serif; padding: 20px; }
            .ticket { border: 2px solid #333; padding: 20px; border-radius: 10px; width: 400px; margin: auto; }
            .header { font-size: 20px; font-weight: bold; margin-bottom: 15px; }
            .row { margin-bottom: 10px; }
            .qr { text-align: center; margin-top: 20px; }
        </style></head>
        <body>
            <div class='ticket'>
                <div class='header'>🎫 Billet de Réservation</div>
                <div class='row'><strong>Nom:</strong> {$proprietaire}</div>
                <div class='row'><strong>Événement:</strong> {$event}</div>
                <div class='row'><strong>Prix:</strong> {$prix} DT</div>
                <div class='row'><strong>Type:</strong> {$type}</div>
                <div class='qr'><img src='{$qrCodeUrl}' alt='QR Code'></div>
            </div>
        </body>
        </html>";

        // Call PDF.co API
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

        if ($response->getStatusCode() === 200) {
            $result = $response->toArray(false);
            return $result['url'] ?? null;
        }

        return null;
    }
}
