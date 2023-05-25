package Resources;

import Flights.Lot;

import java.util.ArrayList;

public class Lotnisko {
    private final String nazwa, lokalizacja;
    private final double x, y;
    ArrayList<Samolot> flota = new ArrayList<>();
    ArrayList<Lot> loty = new ArrayList<>();

    public Lotnisko(String nazwa, String lokalizacja, double x, double y){
        this.nazwa = nazwa;
        this.lokalizacja = lokalizacja;
        this.x = x;
        this.y = y;
    }

    public void dodajSamolot(Samolot samolot){
        flota.add(samolot);
    }
    public void dodajLot(Lot lot){ loty.add(lot) ;}

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getNazwa() { return nazwa; }

    public String getLokalizacja() { return lokalizacja; }

    public ArrayList<Samolot> getFlota(){ return flota; }

    public ArrayList<Lot> getLoty() { return loty; }
}
