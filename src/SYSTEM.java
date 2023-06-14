import Clients.Klient;
import Clients.Types.Firma;
import Clients.Types.Osoba;
import Flights.Lot;
import Reservation.*;

import Resources.Lotnisko;
import Resources.Samolot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.text.DateFormatSymbols;
import java.util.*;




public class SYSTEM {
    private ArrayList<Lotnisko> lotniska = new ArrayList<>();
    private ArrayList<Lot> loty = new ArrayList<>();

    public void generujLot() {
        DateFormatSymbols dni = new DateFormatSymbols(new Locale("en", "US"));
        String[] daysOfWeek = dni.getWeekdays();
        for (Lotnisko lotnisko_p : lotniska) {
            for (Lotnisko lotnisko_k : lotniska) {
                if (lotnisko_k.equals(lotnisko_p)) { continue; }
                for (int i = 2; i <= 7; i++) {
                    // lot pierwszy
                    double odleglosc = Odleglosc(lotnisko_p, lotnisko_k);
                    LocalTime godzinaOdlotu = RandomHour();
                    DayOfWeek dzienOdlotu = DayOfWeek.valueOf(daysOfWeek[i].toUpperCase(Locale.ENGLISH));

                    Samolot samolot = PrzydzielSamolot(lotnisko_p, godzinaOdlotu, dzienOdlotu, odleglosc);
                    if (samolot == null) {
                        continue;
                    }
                    Lot lot_out = new Lot(godzinaOdlotu, dzienOdlotu, samolot, lotnisko_p, lotnisko_k);

                    // lot powrotny
                    LocalTime godzinaPrzylotu = GodzinaPrzylotu(godzinaOdlotu, odleglosc, samolot);
                    if (godzinaPrzylotu.isAfter(LocalTime.MIDNIGHT) && godzinaPrzylotu.isBefore(godzinaOdlotu)) {
                        dzienOdlotu = dzienOdlotu.plus(1);
                    }
                    Lot lot_in = new Lot(godzinaPrzylotu, dzienOdlotu, samolot, lotnisko_k, lotnisko_p);

                    loty.add(lot_out);
                    loty.add(lot_in);
                }
            }
        }
    }


    public Samolot PrzydzielSamolot(Lotnisko lotnisko_p, LocalTime godzina, DayOfWeek dzien, double odleglosc) {
        for (Samolot samolot : lotnisko_p.getFlota()) {
            if (samolot.getZasieg() >= odleglosc && samolot.getIloscMiejsc() > 0) {
                if(!Overlap(samolot, lotnisko_p, godzina, dzien, odleglosc)){
                    return samolot;
                }
            }
        }
        return null;
    }


    public Boolean Overlap(Samolot samolot, Lotnisko lotnisko_p, LocalTime godzina, DayOfWeek dzien, double odleglosc) {
        if (!loty.isEmpty()) {
            for (int i = loty.size() - 1; i >= 0; i--) {
                Lot lot = loty.get(i);
                if (lot.getSamolot() == samolot && lot.getLotnisko_p() == lotnisko_p) {
                    if(!godzina.isBefore(lot.getGodzina_odlotu()) && GodzinaPrzylotu(godzina, odleglosc, samolot).isBefore(lot.getGodzina_odlotu()) && dzien.equals(lot.getDzien()) || !godzina.isAfter(lot.getGodzina_odlotu().plusHours(3)) && dzien.equals(lot.getDzien())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public double Odleglosc(Lotnisko lotnisko_p, Lotnisko lotnisko_k){
        double dx = lotnisko_k.getX() - lotnisko_p.getX();
        double dy = lotnisko_k.getY() - lotnisko_p.getY();
        return Math.sqrt(dx*dx + dy*dy)*1000;
    }

    public int TravelTimeHours(double odleglosc, int speed){
        double hours = odleglosc/(double)speed;
        return (int)Math.floor(hours);
    }

    public int TravelTimeMinutes(double odleglosc, int speed){
        double minutes = odleglosc/(double)speed;
        minutes = (minutes - (int)minutes)*100;
        return (int) minutes;
    }

    public LocalTime RandomHour(){
        Random random = new Random();
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        return LocalTime.of(hour, minute);
    }

    public LocalTime GodzinaPrzylotu(LocalTime godzinaPrzylotu, double odleglosc, Samolot samolot){
        godzinaPrzylotu = godzinaPrzylotu.plusHours(TravelTimeHours(odleglosc, samolot.getPredkosc()));
        godzinaPrzylotu = godzinaPrzylotu.plusMinutes(TravelTimeMinutes(odleglosc, samolot.getPredkosc()));
        return godzinaPrzylotu;
    }

    public void wypiszLoty() {
        for (int i = 0; i < loty.size(); i++) {
            Lot lot = loty.get(i);
            //if(lot.getLotnisko_p().getNazwa().equals("Berlin")&&lot.getLotnisko_k().getNazwa().equals("Warszawa")){
                System.out.println((i + 1) + ". " + lot.getLotnisko_p().getNazwa() + " -> " + lot.getLotnisko_k().getNazwa());
                System.out.println("    Samolot: " + lot.getSamolot().getNazwa());
                System.out.println("    Dnia: " + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
                System.out.println();
            //}
        }
    }
     public void rezerwacjaBiletu(Klient klient, Lot lot){
        Ticket ticket = new Ticket(lot);
        ticket.kupBilet(lot,klient);
        System.out.println("Kupiono bilet na lot z lotniska " + lot.getLotnisko_p().getNazwa() +" do " + lot.getLotnisko_k().getNazwa() + " dnia " + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
        klient.dodajBilet(ticket);
        System.out.println();
    }

    public void odwolywanieBiletu(Klient klient, Ticket ticket){
        ticket.usunBilet(klient,ticket);
    }

    public void wyswietlanieBiletu(Klient klient){
        List<Ticket>BiletyKlienta = klient.getBilety();
        //int i=0;
        if(klient instanceof Osoba) {
            for(int i=0; i<BiletyKlienta.size();i++){
                Ticket ticket=BiletyKlienta.get(i);
                System.out.println("Bilety klienta: " + klient.getNazwa() + " " + klient.getNazwiskoKrs());
                System.out.println((i + 1) + ". " + ticket.getLot().getLotnisko_p().getNazwa() + " -> " + ticket.getLot().getLotnisko_k().getNazwa());
                System.out.println("    Dnia: " + ticket.getLot().getDzien() + " o godzinie: " + ticket.getLot().getGodzina_odlotu());
                System.out.println();
            }
            if (BiletyKlienta.isEmpty()) {
                System.out.println("Klient nie posiada biletow");
                System.out.println();
            }
        }
        else{
            if(klient instanceof Firma){
                for(int i=0; i<BiletyKlienta.size();i++){
                    Ticket ticket=BiletyKlienta.get(i);
                    System.out.println("Bilety firmy: " + klient.getNazwa() + " " + klient.getNazwiskoKrs());
                    System.out.println((i + 1) + ". " + ticket.getLot().getLotnisko_p().getNazwa() + " -> " + ticket.getLot().getLotnisko_k().getNazwa());
                    System.out.println("    Dnia: " + ticket.getLot().getDzien() + " o godzinie: " + ticket.getLot().getGodzina_odlotu());
                    System.out.println();
                }
                if (BiletyKlienta.isEmpty()) {
                    System.out.println("Firma nie posiada biletow");
                    System.out.println();
                }
            }
        }
    }

    public void StworzOsobe(String Imie, String Nazwisko){
        Osoba osoba = new Osoba(Imie,Nazwisko);
        osoba.dodajKlienta();
    }

    public void StworzFirme(String Nazwa, String KRS) {
        Firma firma = new Firma(Nazwa, KRS);
        firma.dodajKlienta();
    }
}

//        lotnisko1.getFlota().sort(new ZasiegComparator());
//       lotnisko2.getFlota().sort(new ZasiegComparator());
//        lotnisko3.getFlota().sort(new ZasiegComparator());
//        lotnisko4.getFlota().sort(new ZasiegComparator());
