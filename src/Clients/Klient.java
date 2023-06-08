package Clients;
import Flights.Lot;
import Reservation.Ticket;
import Resources.Lotnisko;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.List;

public abstract class   Klient {
    public List<Klient> klienci = new ArrayList<>();
    public List<Ticket> Bilety = new ArrayList<>();

    public void dodajKlienta(Klient klient){
        klienci.add(klient);
    }

    public void usunKlienta(Klient klient){
        klienci.remove(klient);
    }

    public void dodajBilet(Ticket ticket){
        Bilety.add(ticket);
    }

    public List<Ticket> getBilety(){
        return Bilety;
    }


}
