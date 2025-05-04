<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class LegalController extends AbstractController
{
    #[Route('/politique-confidentialite', name: 'politique_confidentialite')]
    public function politique(): Response
    {
        return $this->render('politique_confidentialite.html.twig');
    }
}
