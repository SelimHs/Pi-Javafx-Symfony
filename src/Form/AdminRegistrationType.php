<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\OptionsResolver\OptionsResolver;

class AdminRegistrationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom', TextType::class, [
                'constraints' => [
                    new NotBlank(['message' => 'Veuillez entrer votre nom.']),
                ],
            ])
            ->add('prenom', TextType::class, [
                'constraints' => [
                    new NotBlank(['message' => 'Veuillez entrer votre prénom.']),
                ],
            ])
            ->add('password', PasswordType::class, [
                'constraints' => [
                    new NotBlank(['message' => 'Veuillez entrer un mot de passe.']),
                ],
            ])
            ->add('numeroTelephone', TextType::class, [
                'constraints' => [
                    new NotBlank(['message' => 'Veuillez entrer votre numéro de téléphone.']),
                ],
            ])
            ->add('addresse', TextType::class, [
                'constraints' => [
                    new NotBlank(['message' => 'Veuillez entrer votre adresse.']),
                ],
            ])
            ->add('genre', ChoiceType::class, [
                'label' => 'Genre',
                'choices' => [
                    'Homme' => 'homme',
                    'Femme' => 'femme',
                ],
                'placeholder' => 'Sélectionner un genre',
                'constraints' => [
                    new NotBlank(['message' => 'Veuillez choisir votre genre.']),
                ],
            ])
            ->add('email', EmailType::class, [
                'disabled' => true,
                'label' => 'Email (non modifiable)',
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}
