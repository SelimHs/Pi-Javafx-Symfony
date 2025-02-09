package tn.esprit.test;

import tn.esprit.models.Personne;
import tn.esprit.services.ServicePersonne;
import tn.esprit.utils.myDatabase;

public class Main {

    public static void main(String[] args) {
        ServicePersonne sp = new ServicePersonne();
        sp.add(new Personne(180, "Karoui", "yahia"));

        System.out.println(sp.getAll());

    }
}
