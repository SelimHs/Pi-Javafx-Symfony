<?php

namespace App\Service;

use Twilio\Rest\Client;
use Twilio\Exceptions\TwilioException;

class TwilioService
{
    private const ACCOUNT_SID = 'AC805dd6d0c1103969ade5ad32ff09b34a';
    private const AUTH_TOKEN = 'a229d0549855331bcd1182b2cfcc76f0';
    private const TWILIO_PHONE_NUMBER = '+17622525081';

    public function sendSms(string $to, string $fournisseurName): bool
    {
        if (empty($to) || empty($fournisseurName)) {
            dump("Numéro ou nom du fournisseur manquant.");
            return false;
        }

        $client = new Client(self::ACCOUNT_SID, self::AUTH_TOKEN);

        $messageBody = "Cher fournisseur $fournisseurName,\n\n" .
                       "Nous sommes ravis de vous informer que vous avez été ajouté avec succès à notre système en tant que partenaire privilégié.\n\n" .
                       "Bienvenue dans notre réseau de fournisseurs de confiance !";

        try {
            $formattedNumber = $this->formatPhoneNumber($to);

            dump("📲 Envoi SMS à : $formattedNumber"); // Affiche dans le terminal
            $client->messages->create($formattedNumber, [
                'from' => self::TWILIO_PHONE_NUMBER,
                'body' => $messageBody,
            ]);

            dump("✅ SMS envoyé avec succès.");
            return true;
        } catch (TwilioException $e) {
            error_log('Erreur Twilio : ' . $e->getMessage());      // Log dans var/log
            dump('❌ Erreur Twilio : ' . $e->getMessage());         // Console/DebugBar
            return false;
        }
    }

    private function formatPhoneNumber(string $number): string
    {
        $cleaned = preg_replace('/[^0-9]/', '', $number);

        // Si commence par 216 → déjà indicatif tunisien
        if (str_starts_with($cleaned, '216')) {
            return '+' . $cleaned;
        }

        // Si 8 chiffres → ajoute +216
        if (strlen($cleaned) === 8) {
            $firstDigit = substr($cleaned, 0, 1);
            if (in_array($firstDigit, ['2', '5', '7', '9'])) {
                return '+216' . $cleaned;
            }
        }

        // Sinon → ajoute juste le +
        return '+' . $cleaned;
    }
}
