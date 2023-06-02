package Resources;

import Flights.Lot;

public class Samolot {
    private final String nazwa;
    private final int zasieg, iloscMiejsc;
    private Lot lot;

    public Samolot(String nazwa, int zasieg, int iloscMiejsc){
        this.nazwa = nazwa;
        this.zasieg = zasieg;
        this.iloscMiejsc = iloscMiejsc;
    }
    /* *Zwraca nazwę samolotu*/
    public String getNazwa(){ return nazwa;}

    /* *Zwraca zasięg samolotu*/
    public int getZasieg(){ return zasieg;}

    /* *Zwraca ilosc miejsc samolotu*/
    public int getIloscMiejsc(){ return iloscMiejsc;}

    public Lot getLot() { return lot; }

    public void setLot(Lot lot) { this.lot = lot; }
}

