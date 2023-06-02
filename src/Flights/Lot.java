package Flights;

import Resources.Lotnisko;
import Resources.Samolot;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lot {
    private LocalTime godzina_odlotu;
    private DayOfWeek dzien;
    private Samolot samolot;
    private Lotnisko lotnisko_p, lotnisko_k;

    public Lot(LocalTime godzina_odlotu, DayOfWeek dzien, Samolot samolot, Lotnisko lotnisko_p, Lotnisko lotnisko_k){
        this.godzina_odlotu = godzina_odlotu;  //will be random
        this.dzien = dzien;                    // pon - niedz
        this.samolot = samolot;                // przydzielony algorytmem
        this.lotnisko_p = lotnisko_p;          // po koleji z pętli
        this.lotnisko_k = lotnisko_k;          // po koleji z pętli
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
}
