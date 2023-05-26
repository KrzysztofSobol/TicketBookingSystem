package Reservation;
import Flights.Lot;
import Clients.*;


public class Ticket {
    private double cena;
    private int nr_miejsca;
    private int ID_Klienta;
    private int ID_Lotu;

    public Ticket(int ID_Lotu, int ID_Klienta, int nr_miejsca, int cena){
        this.ID_Lotu=ID_Lotu;
        this.ID_Klienta=ID_Klienta;
        this.nr_miejsca=nr_miejsca;
        this.cena=cena;
    }

    public double getCena() {
        return cena;
    }

    public int getID_Klienta() {
        return ID_Klienta;
    }

    public int getID_Lotu() {
        return ID_Lotu;
    }

    public int getNr_miejsca() {
        return nr_miejsca;
    }

    public void setNr_miejsca(int nr_miejsca) {
        this.nr_miejsca = nr_miejsca;
    }
}
