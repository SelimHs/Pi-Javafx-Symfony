<?php

namespace App\Form;

use App\Entity\Espace;
use App\Entity\User;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;

class EspaceType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nomEspace')
            ->add('adresse')
            ->add('capacite')
            ->add('disponibilite')
            ->add('prix')
            ->add('Type_espace')
            ->add('image', FileType::class, [
                'mapped' => false, // Important : le fichier n'est pas lié directement à l'entité
                'required' => false,
                'attr' => ['class' => 'form-control'],
            ])
        
            ->add('user', EntityType::class, [
                'class' => User::class,
                'choice_label' => 'id',
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Espace::class,
        ]);
    }
}
