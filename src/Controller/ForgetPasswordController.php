<?php

namespace App\Controller;

use App\Entity\User;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Contracts\HttpClient\HttpClientInterface;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;
use Twilio\Rest\Client;
use Twilio\Http\CurlClient;
use Symfony\Component\Mailer\MailerInterface;

#[Route('/forgot')]
class ForgetPasswordController extends AbstractController
{
    private string $twilioSid = 'AC8b17dc0551acb8be526df486feab53a7'; 
    private string $twilioToken = '5ef2d795c6ee34f15280a41e2a30f408';
    private string $twilioPhone = '+1 978 625 3628';
    private MailerInterface $mailer;
    private HttpClientInterface $httpClient;
    private ParameterBagInterface $params;

    public function __construct(MailerInterface $mailer, HttpClientInterface $httpClient, ParameterBagInterface $params)
    {
        $this->mailer = $mailer;
        $this->httpClient = $httpClient;
        $this->params = $params;
    }

    #[Route('/password', name: 'app_forgot_password')]
    public function request(Request $request, EntityManagerInterface $entityManager): Response
    {
        if ($request->isMethod('POST')) {
            $email = $request->request->get('email');
            $method = $request->request->get('method');
            $user = $entityManager->getRepository(User::class)->findOneBy(['email' => $email]);

            if (!$user) {
                $this->addFlash('error', 'Email address not found.');
                return $this->redirectToRoute('app_forgot_password');
            }

            $code = random_int(100000, 999999);

            if ($method === 'sms') {
                try {
                    $httpClient = new CurlClient([
                        CURLOPT_SSL_VERIFYHOST => false,
                        CURLOPT_SSL_VERIFYPEER => false,
                    ]);

                    $twilio = new Client($this->twilioSid, $this->twilioToken, null, null, $httpClient);
                    $twilio->messages->create(
                        '+21650135096',
                        [
                            'from' => $this->twilioPhone,
                            'body' => "Your reset password code is: $code",
                        ]
                    );
                } catch (\Exception $e) {
                    $this->addFlash('error', 'SMS Error: ' . $e->getMessage());
                    return $this->redirectToRoute('app_forgot_password');
                }
            } elseif ($method === 'email') {
                $emailMessage = (new \Symfony\Bridge\Twig\Mime\TemplatedEmail())
                    ->from('lamma.eventgroups@gmail.com')
                    ->to($user->getEmail())
                    ->subject('Your Reset Password Code')
                    ->htmlTemplate('security/reset_code_email.html.twig')
                    ->context(['code' => $code]);

                $this->mailer->send($emailMessage);
            }

            return $this->render('security/enter_code.html.twig', [
                'real_code' => $code,
                'email' => $email
            ]);
        }

        return $this->render('security/forget_password.html.twig');
    }

    #[Route('/verify-code', name: 'app_verify_code')]
    public function verifyCode(Request $request): Response
    {
        if ($request->isMethod('POST')) {
            $inputCode = $request->request->get('code');
            $realCode = $request->request->get('real_code');
            $email = $request->request->get('email');

            if ($inputCode !== $realCode) {
                $this->addFlash('error', 'Invalid code. Please try again.');
                return $this->render('security/enter_code.html.twig', [
                    'real_code' => $realCode,
                    'email' => $email
                ]);
            }

            return $this->render('security/reset_password.html.twig', [
                'email' => $email,
                'site_key' => $this->params->get('RECAPTCHA_SITE_KEY')
            ]);
        }

        return $this->redirectToRoute('app_forgot_password');
    }

    #[Route('/reset-password', name: 'app_reset_password')]
    public function resetPassword(Request $request, EntityManagerInterface $entityManager, UserPasswordHasherInterface $passwordHasher): Response
    {
        if ($request->isMethod('POST')) {
            $recaptcha = $request->request->get('g-recaptcha-response');

            $secret = $this->params->get('RECAPTCHA_SECRET_KEY');
            $response = $this->httpClient->request('POST', 'https://www.google.com/recaptcha/api/siteverify', [
                'body' => [
                    'secret' => $secret,
                    'response' => $recaptcha
                ]
            ]);
            $data = $response->toArray();

            if (!$data['success']) {
                $this->addFlash('error', 'reCAPTCHA validation failed. Please try again.');
                return $this->redirectToRoute('app_forgot_password');
            }

            $newPassword = $request->request->get('new_password');
            $email = $request->request->get('email');

            $user = $entityManager->getRepository(User::class)->findOneBy(['email' => $email]);

            if (!$user) {
                $this->addFlash('error', 'User not found.');
                return $this->redirectToRoute('app_forgot_password');
            }

            $hashedPassword = $passwordHasher->hashPassword($user, $newPassword);
            $user->setPassword($hashedPassword);
            $entityManager->flush();

            $this->addFlash('success', 'Your password has been updated!');
            return $this->redirectToRoute('app_login');
        }

        return $this->redirectToRoute('app_forgot_password');
    }
}
