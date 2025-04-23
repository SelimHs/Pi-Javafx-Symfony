<?php

namespace App\Form;

use App\Entity\Event;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;


class EventType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
       
       
        ->add('nomEvent', TextType::class, [
            'attr' => ['class' => 'form-control'],
            'empty_data' => '',
        ])
       
       
        ->add('prix', IntegerType::class, [
            'attr' => ['class' => 'form-control'],
            'empty_data' => '0',
        ])
        
        
        ->add('details', TextType::class, [
            'attr' => ['class' => 'form-control'],
            'empty_data' => '',
        ])
        
        
        ->add('date', DateType::class, [
            'widget' => 'single_text',
            'mapped' => false, 
            'attr' => [
                'min' => (new \DateTime())->format('Y-m-d'),
                'class' => 'form-control'
            ]
        ])
        
        
        ->add('nbrVisiteurs', IntegerType::class, [
            'attr' => ['class' => 'form-control'],
            'empty_data' => '0',
        ])
        
        
        ->add('nomEspace', TextType::class, [
            'attr' => ['class' => 'form-control'],
            'empty_data' => '',
        ])
        
        
        ->add('image', FileType::class, [
            'label' => 'Upload Image',
            'mapped' => false,
            'constraints' => [
                
                new Assert\File([
                    'maxSize' => '3M',
                    'mimeTypes' => [
                        'image/jpeg',
                        'image/png',
                        'image/webp',
                    ],
                    'mimeTypesMessage' => 'Veuillez uploader une image valide (JPG, PNG, WEBP)',
                ])
            ],
            'attr' => ['class' => 'form-control']
        ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Event::class,
        ]);
    }
}
