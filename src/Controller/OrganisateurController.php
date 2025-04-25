<?php

namespace App\Controller;

use App\Entity\Espace;
use App\Form\EspaceType;
use App\Repository\EspaceRepository;

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
use Vonage\Client;
use Vonage\SMS\Message\SMS;
use Symfony\Component\Notifier\TexterInterface;
use Symfony\Component\Notifier\Message\SmsMessage;

#[Route('/organisateur')]
final class OrganisateurController extends AbstractController
{
    public function __construct(
        private TexterInterface $texter,
        private Client $vonageClient
    ) {}

    #[Route(name: 'app_organisateur_index', methods: ['GET'])]
    public function index(OrganisateurRepository $organisateurRepository): Response
    {
        return $this->render('organisateur/index.html.twig', [
            'organisateurs' => $organisateurRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_organisateur_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $em, SessionInterface $session): Response
    {
        $organisateur = new Organisateur();
        $form = $this->createForm(OrganisateurType::class, $organisateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $session->set('pending_organisateur', $organisateur);
            return new JsonResponse([
                'status' => 'verification_required',
                'phone' => $organisateur->getTelef()
            ]);
        }

        return $this->render('organisateur/new.html.twig', [
            'form' => $form->createView(),
        ]);
    }

    #[Route('/send-verification', name: 'app_send_verification', methods: ['POST'])]
    public function sendVerification(Request $request, SessionInterface $session): JsonResponse
    {
        $phone = $request->toArray()['phone'] ?? null;
        if (!$phone) {
            return new JsonResponse(['success' => false, 'error' => 'Numéro manquant']);
        }

        $otp = random_int(100000, 999999);
        $session->set('otp_code', $otp);
        $internationalPhone = '+216' . ltrim($phone, '0');

        try {
            $response = $this->vonageClient->sms()->send(
                new SMS($internationalPhone, 'LammaApp', "Votre code Lamma : $otp")
            );

            $message = $response->current();

            if ($message->getStatus() == 0) {
                return new JsonResponse(['success' => true]);
            }
            return new JsonResponse([
                'success' => false,
                'error' => $this->getVonageErrorMessage($message->getStatus())
            ]);
        } catch (\Throwable $e) {
            return $this->sendWithNotifier($internationalPhone, $otp);
        }
    }
    private function getVonageErrorMessage(int $code): string
    {
        return match ($code) {
            1 => 'Trop de requêtes - Veuillez réessayer plus tard',
            2 => 'Numéro de téléphone invalide',
            3 => 'Quota de messages dépassé',
            4 => 'Compte Vonage suspendu',
            5 => 'Erreur d\'authentification',
            6 => 'Paramètres de message invalides',
            7 => 'Numéro blacklisté',
            8 => 'Partenaire non autorisé',
            9 => 'Erreur de traitement',
            default => "Erreur technique (code: $code) - Contactez l'administrateur"
        };
    }
    private function sendWithNotifier(string $phone, string $message): JsonResponse
    {
        try {
            $sms = new SmsMessage($phone, $message);
            $this->texter->send($sms);
            return new JsonResponse(['success' => true]);
        } catch (\Throwable $e) {
            return new JsonResponse([
                'success' => false,
                'error' => 'Échec de l\'envoi SMS: ' . $e->getMessage()
            ]);
        }
    }
    #[Route(name: 'app_espace_index', methods: ['GET'])]
    public function indexx(EspaceRepository $espaceRepository): Response
    {
        return $this->render('espace/index.html.twig', [
            'espaces' => $espaceRepository->findAll(),
        ]);
    }
    #[Route('/verify-code', name: 'app_verify_code', methods: ['POST'])]
    public function verifyCode(Request $request, SessionInterface $session, EntityManagerInterface $em): JsonResponse
    {
        $data = $request->toArray();
        $code = $data['code'] ?? null;
        $storedCode = $session->get('otp_code');

        if ($storedCode && $code === (string)$storedCode) {
            /** @var Organisateur $organisateur */
            $organisateur = $session->get('pending_organisateur');

            if ($organisateur) {
                try {
                    // Gestion spéciale de l'espace sans cascade persist
                    if ($organisateur->getEspace()) {
                        // Si l'espace existe déjà en base (a un ID)
                        if ($organisateur->getEspace()->getIdEspace()) {
                            $espace = $em->find(Espace::class, $organisateur->getEspace()->getIdEspace());
                            if ($espace) {
                                $organisateur->setEspace($espace);
                            } else {
                                throw new \Exception("L'espace associé n'existe pas en base");
                            }
                        }
                        // Si c'est un nouvel espace (n'a pas d'ID)
                        else {
                            $em->persist($organisateur->getEspace());
                            $em->flush(); // Flush pour obtenir l'ID
                        }
                    }

                    $em->persist($organisateur);
                    $em->flush();

                    $session->remove('otp_code');
                    $session->remove('pending_organisateur');

                    return $this->json([
                        'success' => true,
                        'redirect' => $this->generateUrl('app_espace_index')
                    ]);
                } catch (\Exception $e) {
                    return $this->json([
                        'success' => false,
                        'error' => 'Erreur lors de l\'enregistrement: ' . $e->getMessage()
                    ]);
                }
            }
        }

        return $this->json([
            'success' => false,
            'error' => 'Code invalide ou session expirée'
        ]);
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
