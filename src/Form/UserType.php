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
    private bool $isAdminForm;

    public function __construct(bool $isAdminForm = false)
    {
        $this->isAdminForm = $isAdminForm;
    }

    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom')
            ->add('prenom')
            ->add('password', PasswordType::class)
            ->add('email')
            ->add('numeroTelephone')
            ->add('addresse')
            ->add('genre', ChoiceType::class, [
                'choices' => [
                    'Homme' => 'homme',
                    'Femme' => 'femme',
                ],
                'placeholder' => 'Sélectionner un genre',
                'required' => false,
                'empty_data' => 'homme',
                'label' => 'Genre',
            ])
            ->add('profileImage', FileType::class, [
                'label' => 'Photo de profil',
                'mapped' => false,
                'required' => false,
            ]);

        // 🔥 Ajouter le champ 'type' uniquement si c'est l'admin qui utilise le formulaire
        if ($options['admin_form']) {
            $builder->add('type', ChoiceType::class, [
                'choices' => [
                    'Admin' => 'admin',
                    'User' => 'user',
                ],
                'expanded' => true,
                'multiple' => false,
                'label' => 'Type d\'utilisateur',
            ]);
        }
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => User::class,
            'admin_form' => false, // Par défaut, pas d'admin_form
        ]);
    }
}
