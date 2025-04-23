<?php
namespace App\Service;

use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;

class BilletMailerService
{
    private MailerInterface $mailer;

    public function __construct(MailerInterface $mailer)
    {
        $this->mailer = $mailer;
    }

    public function sendBilletEmail(string $toEmail, string $proprietaire): void
    {
        $email = (new Email())
            ->from('emailsender.lamma@gmail.com')
            ->to($toEmail)
            ->subject("Votre billet pour $proprietaire")
            ->text("Bonjour $proprietaire,\n\nMerci pour votre réservation. Ceci est un test d'envoi sans pièce jointe.");

        $this->mailer->send($email);
    }
}
?>