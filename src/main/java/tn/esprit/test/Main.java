package tn.esprit.test;

import tn.esprit.models.Billet;
import tn.esprit.models.Event;
import tn.esprit.models.LocalServer;
import tn.esprit.services.ServiceBillet;
import tn.esprit.services.ServiceEvent;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        try {
            LocalServer.startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
