<?php

namespace App\Controller;

use App\Entity\User;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Twilio\Rest\Client;
use Twilio\Http\CurlClient;

#[Route('/forgot')]
class ForgetPasswordController extends AbstractController
{
    private string $twilioSid = 'AC8b17dc0551acb8be526df486feab53a7'; 
    private string $twilioToken = '5ef2d795c6ee34f15280a41e2a30f408';
    private string $twilioPhone = '+1 978 625 3628'; // ✅ Your Twilio official number

    #[Route('/password', name: 'app_forgot_password')]
    public function request(Request $request, EntityManagerInterface $entityManager): Response
    {
        if ($request->isMethod('POST')) {
            $email = $request->request->get('email');
            $user = $entityManager->getRepository(User::class)->findOneBy(['email' => $email]);

            if (!$user) {
                $this->addFlash('error', 'Email address not found.');
                return $this->redirectToRoute('app_forgot_password');
            }

            $code = random_int(100000, 999999);

            try {
                $httpClient = new CurlClient([
                    CURLOPT_SSL_VERIFYHOST => false,
                    CURLOPT_SSL_VERIFYPEER => false,
                ]);

                $twilio = new Client(
                    $this->twilioSid,
                    $this->twilioToken,
                    null,
                    null,
                    $httpClient
                );

                $twilio->messages->create(
                    '+21650135096', // ✅ Send TO your verified phone number
                    [
                        'from' => $this->twilioPhone, // ✅ FROM Twilio number
                        'body' => "Your reset password code is: $code",
                    ]
                );

                return $this->render('security/enter_code.html.twig', [
                    'real_code' => $code,
                    'email' => $email
                ]);
            } catch (\Exception $e) {
                $this->addFlash('error', 'SMS Error: ' . $e->getMessage());
                return $this->redirectToRoute('app_forgot_password');
            }
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
                'email' => $email
            ]);
        }

        return $this->redirectToRoute('app_forgot_password');
    }

    #[Route('/reset-password', name: 'app_reset_password')]
    public function resetPassword(
        Request $request,
        EntityManagerInterface $entityManager,
        UserPasswordHasherInterface $passwordHasher
    ): Response {
        if ($request->isMethod('POST')) {
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

            $this->addFlash('success', 'Your password has been updated! You can now login.');
            return $this->redirectToRoute('app_login');
        }

        return $this->redirectToRoute('app_forgot_password');
    }
}
