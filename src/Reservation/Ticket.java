package Reservation;
import Flights.Lot;
import Clients.*;


public class Ticket {
    private Lot lot;
    //private int nrBiletu;

    public Ticket(Lot lot){
        this.lot=lot;
    }



    public void kupBilet(Lot lot, Klient klient){
        if(lot.getDostepne_bilety()<1){
            System.out.println("Brak dostepnych miejsc na lot");
        }
        else {
            lot.kupowanieBiletu();
        }
    }

    public void usunBilet( Klient klient, Ticket ticket){
        ticket.getLot();
        System.out.println("Odwoloano rezerwacje biletu na lot z " + lot.getLotnisko_p().getNazwa() + " do " + lot.getLotnisko_k().getNazwa() + " dnia " + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
        lot.odwolywanieBiletu();
        klient.Bilety.remove(ticket);
    }

    public Lot getLot(){
        return lot;
    }

    /*public void wyswietlBilety(){
        System.out.println("Bilety klienta" + imie);
        for()
    }
    */
}
