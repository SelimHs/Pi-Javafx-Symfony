package tn.esprit.test;

import tn.esprit.models.Billet;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceBillet;
import tn.esprit.services.ServiceEvent;


import java.time.LocalDateTime;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Event event = new Event();
        ServiceEvent ev = new ServiceEvent();
        ServiceBillet billet = new ServiceBillet();

        ev.add(new Event(100,100,"t'hour","Boumestir","wechCousin","24/12/2020"));
        billet.add(new Billet(100, event, "user2", Billet.TypeBillet.SIMPLE));
        billet.add(new Billet(500, event, "MOHSEN", Billet.TypeBillet.VIP));

        System.out.println(ev.getAll());
        System.out.println(billet.getAll());

        //System.out.println(ev.findById(1));
        //System.out.println(billet.findById(1));

    }
}
