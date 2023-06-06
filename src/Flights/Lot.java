package Flights;

import Resources.Lotnisko;
import Resources.Samolot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Random;

public class Lot {
    private LocalTime godzina_odlotu;
    private DayOfWeek dzien;
    private Samolot samolot;
    private Lotnisko lotnisko_p, lotnisko_k;
    private int dostepne_bilety;

    public Lot(LocalTime godzina_odlotu, DayOfWeek dzien, Samolot samolot, Lotnisko lotnisko_p, Lotnisko lotnisko_k){
        this.godzina_odlotu = godzina_odlotu;  // will be random
        this.dzien = dzien;                    // pon - niedz
        this.samolot = samolot;                // przydzielony algorytmem
        this.lotnisko_p = lotnisko_p;          // po koleji z pętli
        this.lotnisko_k = lotnisko_k;          // po koleji z pętli
        this.dostepne_bilety=new Random().nextInt(samolot.getIloscMiejsc()+1); // randomowo od 0 do ilosci miejsc w samolocie
    }
    public Lotnisko getLotnisko_p() {
        return lotnisko_p;
    }
    public Lotnisko getLotnisko_k() {
        return lotnisko_k;
    }

    public Samolot getSamolot() { return samolot; }

    public DayOfWeek getDzien() { return dzien; }

    public LocalTime getGodzina_odlotu() { return godzina_odlotu; }

    public int getDostepne_bilety() {
        return dostepne_bilety;
    }

    public void kupowanieBiletu(int dostepne_bilety){ //setter
        this.dostepne_bilety=dostepne_bilety-1;
    }

    public void odwolywanieBiletu(int dostepne_bilety){ //setter
        this.dostepne_bilety=dostepne_bilety+1;
    }

}

