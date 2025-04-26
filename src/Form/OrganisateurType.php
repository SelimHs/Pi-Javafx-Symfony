<?php

namespace App\Form;

use App\Entity\Espace;
use App\Entity\Organisateur;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;

class OrganisateurType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
{
    $builder
        ->add('nom_org', TextType::class, [
            'attr' => [
                'class' => 'form-control',
                'required' => true,
                'minlength' => 3
            ],
            'label' => 'Nom org'
        ])
        ->add('prenom_org', TextType::class, [
            'attr' => [
                'class' => 'form-control',
                'required' => true,
                'minlength' => 3
            ],
            'label' => 'Prenom org'
        ])
        ->add('description_org', TextType::class, [
            'attr' => [
                'class' => 'form-control',
                'required' => true,
                'minlength' => 5
            ],
            'label' => 'Description org'
        ])
        ->add('telef', TextType::class, [
            'attr' => [
                'class' => 'form-control',
                'required' => true,
                'pattern' => '[0-9]{8}',
                'placeholder' => 'Ex: 25123456'
            ],
            'label' => 'Telef'
        ])
        ->add('espace', EntityType::class, [
            'class' => \App\Entity\Espace::class,
            'choice_label' => 'nomEspace',
            'placeholder' => 'Sélectionnez un espace',
            'attr' => [
                'class' => 'form-control',
                'required' => true
            ],
            'label' => 'Espace'
        ]);
}
}
