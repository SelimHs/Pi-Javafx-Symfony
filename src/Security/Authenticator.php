<?php

namespace App\Security;

use App\Entity\User;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Address;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
use Symfony\Component\Security\Core\Authentication\Token\TokenInterface;
use Symfony\Component\Security\Http\Authenticator\AbstractLoginFormAuthenticator;
use Symfony\Component\Security\Http\Authenticator\Passport\Badge\CsrfTokenBadge;
use Symfony\Component\Security\Http\Authenticator\Passport\Badge\RememberMeBadge;
use Symfony\Component\Security\Http\Authenticator\Passport\Badge\UserBadge;
use Symfony\Component\Security\Http\Authenticator\Passport\Credentials\PasswordCredentials;
use Symfony\Component\Security\Http\Authenticator\Passport\Passport;
use Symfony\Component\Security\Http\SecurityRequestAttributes;
use Symfony\Component\Security\Core\Exception\CustomUserMessageAuthenticationException;
use Symfony\Component\Uid\Uuid;
use Symfony\Component\Security\Http\Util\TargetPathTrait;

class Authenticator extends AbstractLoginFormAuthenticator
{
    use TargetPathTrait;

    public const LOGIN_ROUTE = 'app_login';

    private EntityManagerInterface $entityManager;
    private MailerInterface $mailer;
    private UrlGeneratorInterface $urlGenerator;

    public function __construct(
        EntityManagerInterface $entityManager,
        MailerInterface $mailer,
        UrlGeneratorInterface $urlGenerator
    )
    {
        $this->entityManager = $entityManager;
        $this->mailer = $mailer;
        $this->urlGenerator = $urlGenerator;
    }

    public function authenticate(Request $request): Passport
    {
        $email = $request->get('email');
        $request->getSession()->set(SecurityRequestAttributes::LAST_USERNAME, $email);
    
        $user = $this->entityManager->getRepository(User::class)->findOneBy(['email' => $email]);
    
        // ✅ Auth via reconnaissance faciale
        if ($request->attributes->get('_facial_auth') === true) {
            if (!$user) {
                throw new CustomUserMessageAuthenticationException('Utilisateur non trouvé.');
            }
    
            return new Passport(
                new UserBadge($email),
                new PasswordCredentials('dummy'),
                [new RememberMeBadge()]
            );
        }
        // Cas standard avec vérification email
        if ($user) {
            if (!$user->isVerified()) {
                throw new CustomUserMessageAuthenticationException('Votre email n\'est pas encore vérifié. Veuillez vérifier votre boîte mail.');
            }
    
            if (!$user->isAccepted() && in_array('ROLE_ADMIN', $user->getRoles())) {
                throw new CustomUserMessageAuthenticationException('Votre compte admin est en attente de validation.');
            }
        }
    
        return new Passport(
            new UserBadge($email),
            new PasswordCredentials($request->get('password')),
            [
                new CsrfTokenBadge('authenticate', $request->get('_csrf_token')),
                new RememberMeBadge(),
            ]
        );
    }
    

    public function onAuthenticationSuccess(Request $request, TokenInterface $token, string $firewallName): ?Response
    {
        $role = $token->getUser()->getRoles()[0];
        if ($role === 'ROLE_ADMIN') {
            return new RedirectResponse($this->urlGenerator->generate('app_dashboard'));
        }

        return new RedirectResponse($this->urlGenerator->generate('app_home'));
    }

    protected function getLoginUrl(Request $request): string
    {
        return $this->urlGenerator->generate(self::LOGIN_ROUTE);
    }
}
