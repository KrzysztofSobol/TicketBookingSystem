package Clients;
import Flights.Lot;
import Resources.Lotnisko;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.List;

public abstract class Klient {
    public List<Klient> klienci = new ArrayList<>();
    public List<Lot> Bilety = new ArrayList<>();

    public void dodajKlienta(Klient klient){
        klienci.add(klient);
    }

    public void usunKlienta(Klient klient){
        klienci.remove(klient);
    }

    public void kupBilet(Lot lot){
        if(lot.getDostepne_bilety()<1){
            System.out.println("Brak dostepnych meijsc na lot");
        }
        else {
            lot.kupowanieBiletu(lot.getDostepne_bilety());
            Bilety.add(lot);
            System.out.println("Kupiono bilet o numerze" + Bilety.indexOf(lot) +" na lot z lotniska " + lot.getLotnisko_p() +" do" + lot.getLotnisko_k() + " dnia" + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
        }
    }

    public void odwolajBilet(Lot lot){
        lot.odwolywanieBiletu(lot.getDostepne_bilety());
        System.out.println("Odwoloano rezerwacje biletu o numerze" + Bilety.indexOf(lot));
        Bilety.remove(lot);
    }
}