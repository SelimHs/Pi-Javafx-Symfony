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
            'label' => 'Nom complet',
            'attr' => ['class' => 'form-control'],
        ])
        ->add('type', ChoiceType::class, [
            'choices' => [
                'SIMPLE' => 'SIMPLE',
                'DUO' => 'DUO',
                'VIP' => 'VIP',
            ],
            'placeholder' => 'Sélectionner le type de billet',
            'attr' => ['class' => 'form-select'],
        ])
        ->add('event', EntityType::class, [
            'class' => Event::class,
            'disabled' => true,
            'choice_label' => 'nomEvent', // ✅ ceci remplace __toString()
            'attr' => ['class' => 'form-select'],
        ])
        
        
        ->add('codePromo', TextType::class, [
            'mapped' => false,
            'required' => false,
            'label' => 'Code Promo',
            'attr' => ['placeholder' => 'Entrez un code promo', 'class' => 'form-control', 'id' => 'codePromo']
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
