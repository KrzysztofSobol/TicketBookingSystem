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
            //klient.Bilety.add(ticket); TO CHYBA DO MAINA IDK
            //System.out.println("Kupiono bilet na lot z lotniska " + lot.getLotnisko_p() +" do " + lot.getLotnisko_k() + " dnia " + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
        }
    }

    public void usunBilet( Klient klient, Ticket ticket){
        ticket.getLot();
        lot.odwolywanieBiletu();
        System.out.println("Odwoloano rezerwacje biletu na lot z " + lot.getLotnisko_p().getNazwa() + " do " + lot.getLotnisko_k().getNazwa() + " dnia " + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
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
