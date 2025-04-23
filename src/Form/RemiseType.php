<?php

namespace App\Form;

use App\Entity\Remise;
use App\Entity\Reservation;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use App\Form\DataTransformer\PercentToFloatTransformer;

class RemiseType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('codePromo')
            ->add('description')

            // ✅ Champ pourcentage avec TextType (accepte "%")
            ->add('pourcentageRemise', TextType::class, [
                'label' => 'Pourcentage de Remise (%)',
                'required' => true,
                'attr' => [
                    'placeholder' => 'Ex: 25%'
                ]
            ])

            // ✅ Date d'expiration par défaut +7 jours
            ->add('dateExpiration', DateType::class, [
                'widget' => 'single_text',
                'html5' => true,
                   'data' => (new \DateTime())->modify('+7 days'),
                'attr' => [
                    'min' => (new \DateTime())->format('Y-m-d')
                ]
            ])


            ->add('reservation', EntityType::class, [
                'class' => Reservation::class,
                'choice_label' => function (Reservation $reservation) {
                    return sprintf(
                        'Réservation #%d - %s (%s)',
                        $reservation->getIdReservation(),
                        $reservation->getDateReservation()->format('Y-m-d H:i'),
                        $reservation->getStatut()
                    );
                },
                'placeholder' => 'Sélectionnez une réservation (optionnel)',
                'required' => false,
            ]);

        // ✅ Appliquer le transformer au champ pourcentage
        $builder->get('pourcentageRemise')
            ->addModelTransformer(new PercentToFloatTransformer());
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Remise::class,
        ]);
    }
}
