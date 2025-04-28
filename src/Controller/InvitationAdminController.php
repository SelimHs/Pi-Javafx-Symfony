<?php

namespace App\Controller;

use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mime\Address;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Uid\Uuid;
use Symfony\Component\Security\Http\Attribute\IsGranted;

#[Route('/admin')]
class InvitationAdminController extends AbstractController
{
    #[Route('/send-invitation', name: 'send_invitation_admin', methods: ['POST'])]
    #[IsGranted('ROLE_ADMIN')]
    public function sendInvitation(Request $request, EntityManagerInterface $entityManager, MailerInterface $mailer): Response
    {
        $email = $request->request->get('email');

        if (!$email) {
            $this->addFlash('error', 'Email invalide.');
            return $this->redirectToRoute('admin_dashboard');
        }

        // Générer un token d'invitation
        $token = Uuid::v4()->toRfc4122();
        $expiresAt = (new \DateTime())->modify('+24 hours');

        // Insérer dans la base invitation_token
        $conn = $entityManager->getConnection();
        $conn->insert('invitation_token', [
            'email' => $email,
            'token' => $token,
            'expires_at' => $expiresAt->format('Y-m-d H:i:s'),
        ]);

        // Envoyer email
        $emailMessage = (new TemplatedEmail())
            ->from(new Address('lamma.eventgroups@gmail.com', 'Lamma Admin'))
            ->to($email)
            ->subject('Invitation à devenir Admin')
            ->htmlTemplate('emails/invite_admin.html.twig')
            ->context([
                'token' => $token,
            ]);

        $mailer->send($emailMessage);

        $this->addFlash('success', 'Invitation envoyée avec succès à ' . $email);
        return $this->redirectToRoute('app_home');
    }
}
