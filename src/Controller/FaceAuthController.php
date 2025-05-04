<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\UserRepository;
use Symfony\Component\Security\Http\Authentication\UserAuthenticatorInterface;
use App\Security\Authenticator;

class FaceAuthController extends AbstractController
{
    #[Route('/face-auth', name: 'face_auth', methods: ['POST'])]
    public function faceAuth(
        Request $request,
        UserRepository $userRepository,
        UserAuthenticatorInterface $userAuthenticator,
        Authenticator $authenticator
    ): JsonResponse {
        $uploadedFile = $request->files->get('image');
        if (!$uploadedFile) {
            return new JsonResponse(['success' => false, 'message' => 'Aucune image reçue.'], 400);
        }

        $imagePath = $uploadedFile->getPathname();
        $facesDir = 'C:/wamp64/www/uploads_user_project';

        foreach (scandir($facesDir) as $file) {
            if (in_array($file, ['.', '..'])) continue;

            $filePath = $facesDir . '/' . $file;
            $result = $this->compareFaces($imagePath, $filePath);

            if (isset($result['error'])) {
                return new JsonResponse([
                    'success' => false,
                    'message' => 'Erreur de comparaison avec Face++ : ' . $result['error']
                ]);
            }

            if (!isset($result['confidence'])) {
                return new JsonResponse([
                    'success' => false,
                    'message' => 'Réponse invalide de Face++.'
                ]);
            }

            if ($result['confidence'] > 65) {
                $email = pathinfo($file, PATHINFO_FILENAME);
                $user = $userRepository->findOneBy(['email' => $email]);

                if ($user) {
                    $request->attributes->set('_facial_auth', true);
                    $userAuthenticator->authenticateUser($user, $authenticator, $request);

                    // ✅ Redirection dynamique selon le rôle
                    $redirect = in_array('ROLE_ADMIN', $user->getRoles()) ? '/dashboard' : '/home';

                    return new JsonResponse([
                        'success' => true,
                        'message' => 'Authentification réussie',
                        'redirect' => $redirect
                    ]);
                }

                return new JsonResponse(['success' => false, 'message' => 'Utilisateur introuvable.']);
            }
        }

        return new JsonResponse(['success' => false, 'message' => 'Aucun visage correspondant trouvé.']);
    }

    private function compareFaces(string $image1, string $image2): array
    {
        $apiKey = '-mha-SfYL1fUProDt1gYCmU4XypHSLsX';
        $apiSecret = 'FncbdqNuLQIGzRX84db2tciVBk0UznMT';

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, 'https://api-us.faceplusplus.com/facepp/v3/compare');
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POST, true);

        $postFields = [
            'api_key' => $apiKey,
            'api_secret' => $apiSecret,
            'image_file1' => curl_file_create($image1),
            'image_file2' => curl_file_create($image2),
        ];

        curl_setopt($ch, CURLOPT_POSTFIELDS, $postFields);
        $response = curl_exec($ch);
        $error = curl_error($ch);
        curl_close($ch);

        if ($error) return ['error' => $error];

        $result = json_decode($response, true);

        if (isset($result['error_message'])) {
            return ['error' => $result['error_message']];
        }

        return $result;
    }
}
