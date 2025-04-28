<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Response;

class ProfileControllerAdmin extends AbstractController
{
    #[Route('/profile', name: 'profile')]
    public function profile(): Response
    {
        $user = $this->getUser();

        if (!$user) {
            throw $this->createAccessDeniedException('Vous devez être connecté pour voir votre profil.');
        }

        return $this->render('profile/index.html.twig', [
            'user' => $user,
        ]);
    }
}
