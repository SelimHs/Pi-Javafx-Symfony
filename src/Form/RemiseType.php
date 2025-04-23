<?php

namespace App\Form;

use App\Entity\Remise;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\NumberType;

class RemiseType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('codePromo')
            ->add('description')
            ->add('pourcentageRemise', NumberType::class, [
                'label' => 'Pourcentage de Remise (%)',
                'scale' => 2,
                'attr' => ['class' => 'form-control', 'step' => '0.01'],
            ])->add('dateExpiration', TextType::class, [
                'label' => 'Date d\'expiration',
                'attr' => ['placeholder' => 'ex: 2025-06-30', 'class' => 'form-control']
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Remise::class,
        ]);
    }
}
