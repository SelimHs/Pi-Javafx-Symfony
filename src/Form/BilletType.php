<?php

namespace App\Form;

use App\Entity\Billet;
use App\Entity\Event;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class BilletType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('proprietaire')
            ->add('prix')
            ->add('type', ChoiceType::class, [
                'choices' => [
                    'Simple' => 'SIMPLE',
                    'Duo' => 'DUO',
                    'VIP' => 'VIP',
                ],
                'placeholder' => 'Choisissez un type de billet',
            ])
            ->add('event', EntityType::class, [
                'class' => Event::class,
                'choice_label' => 'nomEvent',
                'placeholder'=> 'Pour quel évènement?',
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Billet::class,
        ]);
    }
}
