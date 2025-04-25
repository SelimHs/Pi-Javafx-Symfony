<?php

namespace App\Controller;

use App\Entity\Organisateur;
use App\Form\OrganisateurType;
use App\Repository\OrganisateurRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use App\Service\MessageBirdVerifyService;
use App\Service\MessageBirdService;
use Symfony\Contracts\HttpClient\HttpClientInterface;

use MessageBird\Client;
use MessageBird\Objects\Verify;
#[Route('/organisateur')]
final class OrganisateurController extends AbstractController
{
    private string $termiiApiKey = 'TLKUesZWcUwRXgHgzORLRAJlsdROGflsyDoExNDJnOVxYlAKhluwtSOSCFZxNA';
    private HttpClientInterface $client;


    #[Route(name: 'app_organisateur_index', methods: ['GET'])]
    public function index(OrganisateurRepository $organisateurRepository): Response
    {
        return $this->render('organisateur/index.html.twig', [
            'organisateurs' => $organisateurRepository->findAll(),
        ]);
    }



    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }
   
    #[Route('/new', name: 'app_organisateur_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $em): Response
    {
        $organisateur = new Organisateur();
        $form = $this->createForm(OrganisateurType::class, $organisateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $request->getSession()->set('pending_organisateur', $organisateur);
            return new JsonResponse(['status' => 'verification_required']);
        }

        return $this->render('organisateur/new.html.twig', [
            'form' => $form->createView(),
        ]);
    }

    #[Route('/send-verification', name: 'app_send_verification', methods: ['POST'])]
    public function sendVerification(Request $request, SessionInterface $session): JsonResponse
    {
        $data = json_decode($request->getContent(), true);
        $phone = $data['phone'] ?? null;

        if (!$phone) {
            return $this->json(['success' => false, 'error' => 'Numéro manquant']);
        }

        try {
            $response = $this->client->request('POST', 'https://api.ng.termii.com/api/sms/otp/send', [
                'json' => [
                    'api_key' => $this->termiiApiKey,
                    'message_type' => 'NUMERIC',
                    'to' => '+216' . ltrim($phone, '0'),
                    'from' => 'LammaApp',
                    'channel' => 'generic',
                    'pin_attempts' => 5,
                    'pin_time_to_live' => 300,
                    'pin_length' => 6,
                    'pin_placeholder' => '<123456>',
                    'message_text' => 'Votre code de vérification est <123456>',
                ]
            ]);

            $responseData = $response->toArray();
            $session->set('termii_pin_id', $responseData['pin_id'] ?? null);

            return $this->json(['success' => true]);
        } catch (\Exception $e) {
            return $this->json(['success' => false, 'error' => 'Erreur : ' . $e->getMessage()]);
        }
    }

    #[Route('/verify-code', name: 'app_verify_code', methods: ['POST'])]
    public function verifyCode(Request $request, SessionInterface $session, EntityManagerInterface $em): JsonResponse
    {
        $data = json_decode($request->getContent(), true);
        $code = $data['code'] ?? null;
        $pinId = $session->get('termii_pin_id');

        if (!$code || !$pinId) {
            return $this->json(['success' => false, 'error' => 'Code ou session manquante']);
        }

        try {
            $response = $this->client->request('POST', 'https://api.ng.termii.com/api/sms/otp/verify', [
                'json' => [
                    'api_key' => $this->termiiApiKey,
                    'pin_id' => $pinId,
                    'pin' => $code
                ]
            ]);

            $result = $response->toArray();

            if ($result['verified'] ?? false) {
                $organisateur = $session->get('pending_organisateur');
                if ($organisateur) {
                    $em->persist($organisateur);
                    $em->flush();
                }

                $session->remove('pending_organisateur');
                $session->remove('termii_pin_id');

                return $this->json(['success' => true]);
            } else {
                return $this->json(['success' => false, 'error' => 'Code invalide']);
            }

        } catch (\Exception $e) {
            return $this->json(['success' => false, 'error' => 'Erreur de vérification']);
        }
    }


    
    // Route ajout général depuis la liste des organisateurs
    #[Route('/newBack', name: 'dashboard_organisateur_new_general', methods: ['GET', 'POST'])]
    public function newBackGeneral(Request $request, EntityManagerInterface $entityManager): Response
    {
        $organisateur = new Organisateur();

        $form = $this->createForm(OrganisateurType::class, $organisateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($organisateur);
            $entityManager->flush();

            return $this->redirectToRoute('app_organisateur_index');
        }

        return $this->render('organisateur/newBackGeneral.html.twig', [
            'organisateur' => $organisateur,
            'form' => $form,
        ]);
    }


    #[Route('/{id_org}', name: 'app_organisateur_show', methods: ['GET'])]
    public function show(Organisateur $organisateur): Response
    {
        return $this->render('organisateur/show.html.twig', [
            'organisateur' => $organisateur,
        ]);
    }

    #[Route('/{id_org}/edit', name: 'app_organisateur_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Organisateur $organisateur, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(OrganisateurType::class, $organisateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_organisateur_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('organisateur/edit.html.twig', [
            'organisateur' => $organisateur,
            'form' => $form,
        ]);
    }

    #[Route('/{id_org}', name: 'app_organisateur_delete', methods: ['POST'])]
    public function delete(Request $request, Organisateur $organisateur, EntityManagerInterface $entityManager): Response
    {
        // Récupérer l'ID de l'espace AVANT de supprimer l'organisateur
        $idEspace = $organisateur->getEspace()?->getIdEspace();

        if ($this->isCsrfTokenValid('delete' . $organisateur->getId_org(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($organisateur);
            $entityManager->flush();
        }

        // Redirection vers la page de détail de l'espace (si espace lié)
        if ($idEspace) {
            return $this->redirectToRoute('app_espace_show', ['idEspace' => $idEspace]);
        }

        // Fallback si pas d'espace (très peu probable)
        return $this->redirectToRoute('app_organisateur_index', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/deleteBack/{id_org}', name: 'dashboard_organisateur_delete', methods: ['POST'])]
    public function deleteBack(Request $request, Organisateur $organisateur, EntityManagerInterface $entityManager): Response
    {
        $idEspace = $organisateur->getEspace()?->getIdEspace();
        $from = $request->request->get('_from'); // 🔽 on lit la valeur du champ caché

        if ($this->isCsrfTokenValid('delete' . $organisateur->getIdOrg(), $request->getPayload()->getString('_token'))) {
            $entityManager->remove($organisateur);
            $entityManager->flush();
        }

        // ✅ Redirection conditionnelle
        if ($from === 'liste') {
            return $this->redirectToRoute('app_organisateur_index');
        } elseif ($idEspace) {
            return $this->redirectToRoute('dashboard_espace_show', ['idEspace' => $idEspace]);
        }

        return $this->redirectToRoute('app_organisateur_index');
    }





    #[Route('/editBack/{id_org}', name: 'dashboard_organisateur_edit', methods: ['GET', 'POST'])]
    public function editBack(Request $request, Organisateur $organisateur, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(OrganisateurType::class, $organisateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            $espaceId = $organisateur->getEspace()?->getIdEspace();

            if ($espaceId) {
                return $this->redirectToRoute('dashboard_espace_show', ['idEspace' => $espaceId]);
            }

            return $this->redirectToRoute('dashboard_espace_index');
        }

        return $this->render('organisateur/editBack.html.twig', [
            'organisateur' => $organisateur,
            'form' => $form,
        ]);
    }

    
}
