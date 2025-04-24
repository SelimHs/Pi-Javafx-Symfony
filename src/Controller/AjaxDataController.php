<?php


// src/Controller/AjaxDataController.php
namespace App\Controller;

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

        $data = [
            'espaces' => [],
            'organisateurs' => [],
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
