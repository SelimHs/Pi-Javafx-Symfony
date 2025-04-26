<?php

namespace App\Controller;

use App\Entity\User; // ✅ Très important pour vérifier correctement
use App\Repository\EspaceRepository;
use App\Repository\OrganisateurRepository;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

class AjaxDataController extends AbstractController
{
    #[Route('/api/noms', name: 'api_noms', methods: ['GET'])]
    public function noms(EspaceRepository $espaceRepo, OrganisateurRepository $orgaRepo): JsonResponse
    {
        $espaces = $espaceRepo->findAll();
        $organisateurs = $orgaRepo->findAll();

        $user = $this->getUser();
        $idUser = null;
        if ($user instanceof User) {
            $idUser = $user->getId(); // ✅ on récupère l'id
        }

        $data = [
            'espaces' => [],
            'organisateurs' => [],
            'id_user' => $idUser, // ✅ On l'ajoute proprement ici !
        ];

        foreach ($espaces as $e) {
            $data['espaces'][$e->getIdEspace()] = $e->getNomEspace();
        }

        foreach ($organisateurs as $o) {
            $data['organisateurs'][$o->getIdOrg()] = $o->getNomOrg() . ' ' . $o->getPrenomOrg();
        }

        return new JsonResponse($data);
    }
}
