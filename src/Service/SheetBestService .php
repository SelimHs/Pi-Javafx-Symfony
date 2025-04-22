<?php
// src/Service/SheetBestService.php
namespace App\Service;

use Symfony\Contracts\HttpClient\HttpClientInterface;

class SheetBestService
{
    private string $sheetUrl = 'https://api.sheetbest.com/sheets/4d538bcb-a52a-4dde-84e4-ddb7c9520d8e';

    public function __construct(private readonly HttpClientInterface $client) {}

    public function save(array $data): array
    {
        $response = $this->client->request('POST', $this->sheetUrl, [
            'headers' => ['Content-Type' => 'application/json'],
            'json' => $data
        ]);
        return $response->toArray();
    }

    public function getAll(): array
    {
        $response = $this->client->request('GET', $this->sheetUrl);
        return $response->toArray();
    }
}
