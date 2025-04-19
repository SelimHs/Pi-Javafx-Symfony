<?php

namespace App\Form;

use App\Entity\Fournisseur;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\File;

class FournisseurType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nomFournisseur')
            ->add('description')
            ->add('type', ChoiceType::class, [
                'label' => 'Type de Fournisseur',
                'choices' => [
                    'Grossiste' => 'grossiste',
                    'Fabricant' => 'fabricant',
                    'Distributeur' => 'distributeur',
                    'Autre' => 'autre',
                ],
                'placeholder' => 'Sélectionnez un type',
                'expanded' => false, // reste en menu déroulant (true = radio buttons)
                'multiple' => false,
            ])
            ->add('telephone')
            ->add('imagePath', FileType::class, [
                'label' => 'Image du Fournisseur',
                'mapped' => false, // Handle file manually
                'required' => false,
                'constraints' => [
                    new File([
                        'maxSize' => '2M',
                        'mimeTypes' => ['image/jpeg', 'image/png'],
                        'mimeTypesMessage' => 'Veuillez uploader une image valide (JPG, JPEG, PNG).',
                    ])
                ],
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Fournisseur::class,
        ]);
    }
}
