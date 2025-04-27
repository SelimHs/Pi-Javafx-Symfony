<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;

class UserType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom')
            ->add('prenom')
            ->add('password', PasswordType::class)
            ->add('email')
            ->add('numeroTelephone')
            ->add('addresse')
            ->add('type', ChoiceType::class, [
                'choices' => [
                    'Admin' => 'admin',
                    'User' => 'user',
                ],
                'expanded' => true, // Radio button
                'multiple' => false,
                'label' => 'Type d\'utilisateur',
            ])
            ->add('genre', ChoiceType::class, [
                'choices' => [
                    'Homme' => 'homme',
                    'Femme' => 'femme',
                ],
                'placeholder' => 'Sélectionner un genre', 
                'required' => false, // ✅ il faut laisser false
                'empty_data' => 'homme', // ✅ par défaut remplir "homme" si vide
                'label' => 'Genre',
            ])
            ->add('profileImage', FileType::class, [
                'label' => 'Photo de profil',
                'mapped' => false,
                'required' => false,
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}
