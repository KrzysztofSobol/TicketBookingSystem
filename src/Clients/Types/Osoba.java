package Clients.Types;

import Clients.Klient;
import Reservation.Ticket;
import Flights.Lot;

import java.util.ArrayList;
import java.util.List;

public class Osoba extends Klient {
    public List<Ticket> Bilety = new ArrayList<>();
    private final String imie, nazwisko;
    public Osoba(String Imie, String Nazwisko){
        this.imie = Imie;
        this.nazwisko = Nazwisko;
    }
}
