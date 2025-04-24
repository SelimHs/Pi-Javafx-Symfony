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

    public function sendConfirmation(string $to, string $eventName): void
    {
        try {
        $email = (new Email())
            ->from('emailsender.lamma@gmail.com')
            ->to($to)
            ->subject('🎫 Confirmation de votre billet')
            ->html("
                <h2>Merci pour votre réservation !</h2>
                <p>Votre billet pour <strong>{$eventName}</strong> a bien été confirmé.</p>
                <p>À bientôt !</p>
            ");

        $this->mailer->send($email);
    } catch (\Exception $e) {
        // Save the error to a log file
        file_put_contents('mailer_error.log', $e->getMessage());
    }
    }
}
?>