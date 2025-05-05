<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\RegistrationFormType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mime\Address;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Contracts\Translation\TranslatorInterface;
use Symfony\Component\Mailer\MailerInterface; 
use Symfony\Component\Uid\Uuid;

class RegistrationController extends AbstractController
{
    #[Route('/register', name: 'app_register')]
    public function register(Request $request, UserPasswordHasherInterface $userPasswordHasher, EntityManagerInterface $entityManager, MailerInterface $mailer): Response
    {
        $user = new User();
        $form = $this->createForm(RegistrationFormType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $plainPassword = $form->get('plainPassword')->getData();
            $user->setPassword($userPasswordHasher->hashPassword($user, $plainPassword));
            $user->setType('user');
            $verificationToken = bin2hex(random_bytes(32));
            $user->setVerificationToken($verificationToken);

            $entityManager->persist($user);
            $entityManager->flush();
            $email = (new TemplatedEmail())
                ->from(new Address('lamma.eventgroups@gmail.com', 'Lamma'))
                ->to($user->getEmail())
                ->subject('Please Confirm your Email')
                ->htmlTemplate('registration/confirmation_email.html.twig')
                ->context([
                    'token' => $verificationToken,
                ]);

            $mailer->send($email);
            $this->addFlash('success', 'Registration successful! Please check your email to confirm your account.');
            return $this->redirectToRoute('app_login');
        }

        return $this->render('registration/register.html.twig', [
            'registrationForm' => $form,
        ]);
    }

    #[Route('/verify/email', name: 'app_verify_email')]
    public function verifyUserEmail(Request $request, EntityManagerInterface $entityManager): Response
    {
        $token = $request->query->get('token');

        if (!$token) {
            $this->addFlash('verify_email_error', 'No token provided.');
            return $this->redirectToRoute('app_login');
        }

        $user = $entityManager->getRepository(User::class)->findOneBy(['verificationToken' => $token]);

        if (!$user) {
            $this->addFlash('verify_email_error', 'Invalid or expired verification link.');
            return $this->redirectToRoute('app_login');
        }
        $user->setIsVerified(true);
        $user->setVerificationToken(null);
        $entityManager->flush();

        $this->addFlash('success', 'Your email address has been successfully verified. You can now login.');

        return $this->redirectToRoute('app_login');
    }

    #[Route('/admin/verify-by-email', name: 'admin_verify_by_email', methods: ['POST'])]
    public function verifyByEmail(Request $request, EntityManagerInterface $entityManager): Response
    {
        $email = $request->request->get('email');
        $user = $entityManager->getRepository(User::class)->findOneBy(['email' => $email]);
        if (!$user) {
            $this->addFlash('verify_email_error', 'No user found with that email.');
            return $this->redirectToRoute('app_login');
        }
        $user->setIsVerified(true);
        $entityManager->flush();
        $this->addFlash('success', 'User has been verified by email.');
        return $this->redirectToRoute('app_login');
    }
}
