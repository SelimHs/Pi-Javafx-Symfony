<?php

namespace App\Form;

use App\Entity\Espace;
use App\Entity\User;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class EspaceType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $isEdit = $options['is_edit']; // on récupère l’option

        $builder
            ->add('nomEspace')
            ->add('adresse')
            ->add('capacite')
            ->add('disponibilite', ChoiceType::class, [
                'choices' => [
                    'Disponible' => 'Disponible',
                    'Indisponible' => 'Indisponible',
                ],
                'attr' => ['class' => 'form-control'],
                'data' => 'Disponible', // valeur par défaut
                'disabled' => !$isEdit, // désactivé en mode création
            ])          
            ->add('prix')
            ->add('Type_espace')
            ->add('image', FileType::class, [
                'mapped' => false,
                'required' => false,
                'attr' => ['class' => 'form-control']
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
        'is_edit' => false // false par défaut
    ]);
}

}
