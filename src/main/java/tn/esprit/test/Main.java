package tn.esprit.test;

import tn.esprit.models.Paiement;
import tn.esprit.models.Personne;
import tn.esprit.models.Remise;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServicePaiement;
import tn.esprit.services.ServicePersonne;
import tn.esprit.services.ServiceRemise;
import tn.esprit.services.ServiceReservation;

public class Main {

    public static void main(String[] args) {

        ServiceReservation sr = new ServiceReservation();
        ServiceRemise remise = new ServiceRemise();
        remise.getAll();
        System.out.println("tester");

        System.out.println(sr.getAll());
        System.out.println("tester");

        System.out.println(remise.getAll());
    }
}
