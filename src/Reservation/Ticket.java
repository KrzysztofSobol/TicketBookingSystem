package Reservation;
import Flights.Lot;
import Clients.*;

import java.io.*;


public class Ticket implements Serializable {
    private Lot lot;
    private Klient klient;
    public Ticket(Lot lot, Klient klient) {
        this.lot = lot;
        this.klient = klient;
    }


    public void kupBilet(Lot lot, Klient klient) {
        if (lot.GetDostepneBilety() < 1) {
            System.out.println("Brak dostepnych miejsc na lot");
        } else {
            lot.KupowanieBiletu();
        }
    }

    public void usunBilet(Klient klient, Ticket ticket) {
        ticket.getLot();
        System.out.println("Odwoloano rezerwacje biletu na lot z " + lot.GetLotniskoP().getNazwa() + " do " + lot.GetLotniskoK().getNazwa() + " dnia " + lot.GetDzien() + " o godzinie: " + lot.GetGodzinaOdlotu());
        lot.OdwolywanieBiletu();
        klient.bilety.remove(ticket);
    }

    public Lot getLot() {
        return lot;
    }
    public Klient getKlient() {
        return klient;
    }
}