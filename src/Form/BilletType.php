<?php

namespace App\Form;

use App\Entity\Billet;
use App\Entity\Event;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class BilletType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('proprietaire')
            ->add('prix')
            ->add('dateAchat', null, [
                'widget' => 'single_text',
            ])
            ->add('type')
            ->add('event', EntityType::class, [
                'class' => Event::class,
                'choice_label' => 'idEvent',
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
