<?php

namespace App\Form;

use App\Entity\Event;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;


class EventType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nomEvent')
            ->add('prix')
            ->add('details')
            ->add('date', DateType::class, [
                'widget' => 'single_text',
                'mapped' => false, 
                'attr' => [
                    'min' => (new \DateTime())->format('Y-m-d'),
                    'class' => 'form-control'
                ]
            ])
            
            ->add('nbrVisiteurs')
            ->add('nomEspace')
            ->add('image', FileType::class, [
                'label' => 'Upload Image',
                'mapped' => false, // Important: this tells Symfony not to map it directly to the Event entity
                'required' => false,
                'attr' => [
                    'class' => 'form-control'
                ]
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Event::class,
        ]);
    }
}
