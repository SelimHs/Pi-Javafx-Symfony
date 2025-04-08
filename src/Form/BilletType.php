<?php

namespace App\Form;

use App\Entity\Billet;
use App\Entity\Event;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;

class BilletType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('proprietaire', TextType::class, [
            'attr' => ['class' => 'form-control'],
            'empty_data' => '',
        ])
        
        
        ->add('prix', IntegerType::class, [
            'attr' => ['class' => 'form-control'],
            'empty_data' => '0',
            'required' => true,
        ])
        
        
        ->add('type', ChoiceType::class, [
            'choices' => [
                'Simple' => 'SIMPLE',
                'Duo' => 'DUO',
                'VIP' => 'VIP',
            ],
            'placeholder' => 'Choisissez un type de billet',
            'attr' => ['class' => 'form-control'],
            'required' => true,
            'empty_data' => '', // important : envoie une string vide (pas null)
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
