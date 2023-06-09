import Clients.Klient;
import Clients.Types.Firma;
import Clients.Types.Osoba;
import Flights.Lot;
import Reservation.*;
import Resources.Lotnisko;
import Resources.Samolot;
import Resources.SamolotTyp.Typ1;
import Resources.SamolotTyp.Typ2;
import Resources.SamolotTyp.Typ3;
import Resources.ZasiegComparator;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.text.DateFormatSymbols;




public class Main {
    static ArrayList<Lotnisko> lotniska = new ArrayList<>();
    static ArrayList<Lot> loty = new ArrayList<>();
    public static void main(String[] args) {
        Lotnisko lotnisko1 = new Lotnisko("Warszawa", "Warszawa", 1, 87);
        Lotnisko lotnisko2 = new Lotnisko("Berlin", "Berlin", 1, 64);
        Lotnisko lotnisko3 = new Lotnisko("Paryz", "Paryz", 1, 22);
        Lotnisko lotnisko4 = new Lotnisko("HongKong", "HongKong", 1, 44);

        lotniska.add(lotnisko1);
        lotniska.add(lotnisko2);
        lotniska.add(lotnisko3);
        lotniska.add(lotnisko4);
        
        Typ1 krotko_dystansowiec = new Typ1("Mini Majk", 1500, 40, 20);
        Typ2 srednio_dystansowiec = new Typ2("Mid John", 5000, 30, 30);
        Typ3 daleko_dystansowiec = new Typ3("Long Ben", 100000, 20, 70);

        Typ1 krotko_dystansowiec2 = new Typ1("Mini Majk2", 1500, 20, 20);
        Typ2 srednio_dystansowiec2 = new Typ2("Mid John2", 5000, 30, 35);
        Typ3 daleko_dystansowiec2 = new Typ3("Long Ben2", 100000, 20, 80);

        Typ1 krotko_dystansowiec3 = new Typ1("Mini Majk3", 1500, 20, 20);
        Typ2 srednio_dystansowiec3 = new Typ2("Mid John3", 5000, 30, 40);
        Typ3 daleko_dystansowiec3 = new Typ3("Long Ben3", 100000, 20, 90);

        Typ1 krotko_dystansowiec4 = new Typ1("Mini Majk4", 1500, 20, 23);
        Typ2 srednio_dystansowiec4 = new Typ2("Mid John4", 5000, 30, 42);
        Typ3 daleko_dystansowiec4 = new Typ3("Long Ben4", 100000, 20, 65);

        lotnisko1.dodajSamolot(krotko_dystansowiec);
        lotnisko1.dodajSamolot(srednio_dystansowiec);
        lotnisko1.dodajSamolot(daleko_dystansowiec);

        lotnisko2.dodajSamolot(krotko_dystansowiec2);
        lotnisko2.dodajSamolot(srednio_dystansowiec2);
        lotnisko2.dodajSamolot(daleko_dystansowiec2);

        lotnisko3.dodajSamolot(krotko_dystansowiec3);
        lotnisko3.dodajSamolot(srednio_dystansowiec3);
        lotnisko3.dodajSamolot(daleko_dystansowiec3);

        lotnisko4.dodajSamolot(krotko_dystansowiec4);
        lotnisko4.dodajSamolot(srednio_dystansowiec4);
        lotnisko4.dodajSamolot(daleko_dystansowiec4);

        Collections.sort(lotnisko1.getFlota(), new ZasiegComparator());
        Collections.sort(lotnisko2.getFlota(), new ZasiegComparator());
        Collections.sort(lotnisko3.getFlota(), new ZasiegComparator());
        Collections.sort(lotnisko4.getFlota(), new ZasiegComparator());

        generujLot();
        wypiszLoty();

        Osoba osoba = new Osoba("Jan","Kowalski");
        rezerwacjaBiletu(osoba,loty.get(0));
        rezerwacjaBiletu(osoba,loty.get(1));
        wyswietlanieBiletu(osoba);
        odwolywanieBiletu(osoba,osoba.Bilety.get(0));
        wyswietlanieBiletu(osoba);
    }

    public static void generujLot() {
        DateFormatSymbols dni = new DateFormatSymbols(new Locale("en", "US"));
        String[] daysOfWeek = dni.getWeekdays();
        for (Lotnisko lotnisko_p : lotniska) {
            for (int i = 2; i <= 7; i++) {
                for (Lotnisko lotnisko_k : lotniska) {
                    if (lotnisko_k.equals(lotnisko_p)) {
                        continue;
                    }

                    double odleglosc = Odleglosc(lotnisko_p, lotnisko_k);
                    LocalTime godzinaOdlotu = RandomHour();
                    DayOfWeek dzienOdlotu = DayOfWeek.valueOf(daysOfWeek[i].toUpperCase(Locale.ENGLISH));

                    Samolot samolot = PrzydzielSamolot(lotnisko_p, godzinaOdlotu, dzienOdlotu, odleglosc);
                    if (samolot == null) {
                        continue;
                    }
                    Lot lot_out = new Lot(godzinaOdlotu, dzienOdlotu, samolot, lotnisko_p, lotnisko_k);

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


    public static Samolot PrzydzielSamolot(Lotnisko lotnisko_p, LocalTime godzina, DayOfWeek dzien, double odleglosc) {
        for (Samolot samolot : lotnisko_p.getFlota()) {
            if (samolot.getZasieg() >= odleglosc && samolot.getIloscMiejsc() > 0) {
                if(!Overlap(samolot, lotnisko_p, godzina, dzien, odleglosc)){
                    return samolot;
                }
            }
        }
        return null;
    }


    public static Boolean Overlap(Samolot samolot, Lotnisko lotnisko_p, LocalTime godzina, DayOfWeek dzien, double odleglosc) {
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


    public static double Odleglosc(Lotnisko lotnisko_p, Lotnisko lotnisko_k){
        double dx = lotnisko_k.getX() - lotnisko_p.getX();
        double dy = lotnisko_k.getY() - lotnisko_p.getY();
        return Math.sqrt(dx*dx + dy*dy)*1000;
    }

    public static int TravelTimeHours(double odleglosc, int speed){
        double hours = odleglosc/(double)speed;
        return (int)Math.floor(hours);
    }

    public static int TravelTimeMinutes(double odleglosc, int speed){
        double minutes = odleglosc/(double)speed;
        minutes = (minutes - (int)minutes)*100;
        return (int) minutes;
    }

    public static LocalTime RandomHour(){
        Random random = new Random();
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        return LocalTime.of(hour, minute);
    }

    public static LocalTime GodzinaPrzylotu(LocalTime godzinaOdlotu, double odleglosc, Samolot samolot){
        godzinaOdlotu = godzinaOdlotu.plusHours(TravelTimeHours(odleglosc, samolot.getPredkosc()));
        godzinaOdlotu = godzinaOdlotu.plusMinutes(TravelTimeMinutes(odleglosc, samolot.getPredkosc()));
        return godzinaOdlotu;
    }

    public static void wypiszLoty() {
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
     public static void rezerwacjaBiletu(Klient klient, Lot lot){
        Ticket ticket = new Ticket(lot);
        ticket.kupBilet(lot,klient);
        System.out.println("Kupiono bilet na lot z lotniska " + lot.getLotnisko_p().getNazwa() +" do " + lot.getLotnisko_k().getNazwa() + " dnia " + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
        klient.dodajBilet(ticket);
        System.out.println();
    }

    public static void odwolywanieBiletu(Klient klient, Ticket ticket){
        ticket.usunBilet(klient,ticket);
    }

    public static void wyswietlanieBiletu(Klient klient){

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
}
