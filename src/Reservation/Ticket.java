package Reservation;
import Flights.Lot;
import Clients.*;


public class Ticket {
    private Lot lot;
    private int nrBiletu;

    public Ticket(int nrBiletu, Lot lot){
        this.nrBiletu=nrBiletu;
        this.lot=lot;
    }



    public void kupBilet(Lot lot, Klient klient){
        if(lot.getDostepne_bilety()<1){
            System.out.println("Brak dostepnych meijsc na lot");
        }
        else {
            lot.kupowanieBiletu();
            //klient.Bilety.add(ticket); TO CHYBA DO MAINA IDK
            System.out.println("Kupiono bilet o numerze" + klient.Bilety.indexOf(lot) +" na lot z lotniska " + lot.getLotnisko_p() +" do" + lot.getLotnisko_k() + " dnia" + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
        }
    }

    public void usunBilet(Ticket ticket, Klient klient, Lot lot){
        lot.odwolywanieBiletu();
        System.out.println("Odwoloano rezerwacje biletu o numerze" +klient.Bilety.indexOf(lot));
        klient.Bilety.remove(lot);
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
