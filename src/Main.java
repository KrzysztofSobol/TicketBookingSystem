import Clients.Klient;
import Clients.Types.Osoba;
import Flights.Lot;
import Reservation.*;
import Resources.Lotnisko;
import Resources.Samolot;
import Resources.SamolotTyp.Typ1;
import Resources.SamolotTyp.Typ2;
import Resources.SamolotTyp.Typ3;

import javax.swing.text.html.HTMLEditorKit;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.text.DateFormatSymbols;



public class Main {
    static ArrayList<Lotnisko> lotniska = new ArrayList<>();
    static ArrayList<Lot> loty = new ArrayList<>();
    public static void main(String[] args) {
        Lotnisko lotnisko1 = new Lotnisko("Warszawa", "Warszawa", 1, 1);
        Lotnisko lotnisko2 = new Lotnisko("Berlin", "Berlin", 1, 1);
        Lotnisko lotnisko3 = new Lotnisko("Paryz", "Paryz", 1, 1);
        Lotnisko lotnisko4 = new Lotnisko("HongKong", "HongKong", 1, 1);

        lotniska.add(lotnisko1);
        lotniska.add(lotnisko2);
        lotniska.add(lotnisko3);
        lotniska.add(lotnisko4);

        /// Testy
        Typ1 krotko_dystansowiec = new Typ1("Mini Majk", 1500, 20);
        Typ2 srednio_dystansowiec = new Typ2("Mid John", 5000, 30);
        Typ3 daleko_dystansowiec = new Typ3("Long Ben", 100000, 20);

        Typ1 krotko_dystansowiec2 = new Typ1("Mini Majk2", 1500, 20);
        Typ2 srednio_dystansowiec2 = new Typ2("Mid John2", 5000, 30);
        Typ3 daleko_dystansowiec2 = new Typ3("Long Ben2", 100000, 20);

        Typ1 krotko_dystansowiec3 = new Typ1("Mini Majk3", 1500, 20);
        Typ2 srednio_dystansowiec3 = new Typ2("Mid John3", 5000, 30);
        Typ3 daleko_dystansowiec3 = new Typ3("Long Ben3", 100000, 20);

        Typ1 krotko_dystansowiec4 = new Typ1("Mini Majk4", 1500, 20);
        Typ2 srednio_dystansowiec4 = new Typ2("Mid John4", 5000, 30);
        Typ3 daleko_dystansowiec4 = new Typ3("Long Ben4", 100000, 20);




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

        System.out.println("Loty z Berlina do WWA \n");
        generujLot();
        wypiszLoty();
        Osoba osoba = new Osoba("Jan","Kowalski");
        rezerwacjaBiletu(osoba,loty.get(2));
        wyswietlanieBiletu(osoba);
        odwolywanieBiletu(osoba,osoba.Bilety.get(0));
        wyswietlanieBiletu(osoba);

    }

    public static void generujLot() {
        DateFormatSymbols dni = new DateFormatSymbols(new Locale("en", "US"));
        String[] daysOfWeek = dni.getWeekdays();
        Random random = new Random();
        for (Lotnisko lotnisko_p : lotniska) {
            for (int i = 2; i <= 7; i++) {
                for (Lotnisko lotnisko_k : lotniska) {
                    if (lotnisko_k.equals(lotnisko_p)) {
                        continue;
                    }
                    int hour = random.nextInt(24);
                    int minute = random.nextInt(60);
                    LocalTime losowaGodzina = LocalTime.of(hour, minute);
                    DayOfWeek dzien_odlotu = DayOfWeek.valueOf(daysOfWeek[i].toUpperCase(Locale.ENGLISH));
                    DayOfWeek nowy_dzien_odlotu = dzien_odlotu;
                    Samolot samolot = PrzydzielSamolot(lotnisko_p, lotnisko_k, losowaGodzina, dzien_odlotu);
                    if (samolot == null) {
                        continue;
                    }

                    Lot lot = new Lot(losowaGodzina, dzien_odlotu, samolot, lotnisko_p, lotnisko_k);
                    LocalTime losowaGodzina2 = losowaGodzina.plusHours(3);
                    if (losowaGodzina2.isAfter(LocalTime.MIDNIGHT) && losowaGodzina2.isBefore(losowaGodzina)) {
                        nowy_dzien_odlotu = dzien_odlotu.plus(1);
                    }

                    Lot lot2 = new Lot(losowaGodzina2, nowy_dzien_odlotu, samolot, lotnisko_k, lotnisko_p);
                    loty.add(lot);
                    loty.add(lot2);
                }
            }
        }
    }


    public static Samolot PrzydzielSamolot(Lotnisko lotnisko_p, Lotnisko lotnisko_k, LocalTime godzina, DayOfWeek dzien) {
        double odleglosc = Odleglosc(lotnisko_p, lotnisko_k);
        for (Samolot samolot : lotnisko_p.getFlota()) {
            if (samolot.getZasieg() >= odleglosc && samolot.getIloscMiejsc() > 0) {
                if(!Overlap(samolot, lotnisko_p, godzina, dzien)){
                    return samolot;
                }
            }
        }
        return null;
    }


    public static Boolean Overlap(Samolot samolot, Lotnisko lotnisko_p, LocalTime godzina, DayOfWeek dzien) {
        if (!loty.isEmpty()) {
            for (int i = loty.size() - 1; i >= 0; i--) {
                Lot lot = loty.get(i);
                if (lot.getSamolot() == samolot && lot.getLotnisko_p() == lotnisko_p) {
                    if(!godzina.isBefore(lot.getGodzina_odlotu()) && !godzina.plusHours(3).isBefore(lot.getGodzina_odlotu()) && dzien.equals(lot.getDzien()) || !godzina.isAfter(lot.getGodzina_odlotu().plusHours(3)) && dzien.equals(lot.getDzien())){
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

    public static void wypiszLoty() {
        for (int i = 0; i < loty.size(); i++) {
            Lot lot = loty.get(i);
            //if(lot.getLotnisko_p().getNazwa().equals("Berlin")&&lot.getLotnisko_k().getNazwa().equals("Warszawa")){
                System.out.println((i + 1) + ". " + lot.getLotnisko_p().getNazwa() + " -> " + lot.getLotnisko_k().getNazwa());
                System.out.println("    Samolot: " + lot.getSamolot().getNazwa());
                System.out.println("    Dnia: " + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
                System.out.println();
           // }
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
        int i=0;
        if(klient instanceof Osoba) {
            for (Ticket ticket : BiletyKlienta) {
                //System.out.println("Bilety klienta: ") + klient;
                System.out.println((i + 1) + ". " + ticket.getLot().getLotnisko_p().getNazwa() + " -> " + ticket.getLot().getLotnisko_k().getNazwa());
                System.out.println("    Dnia: " + ticket.getLot().getDzien() + " o godzinie: " + ticket.getLot().getGodzina_odlotu());
                System.out.println();
            }
            if (BiletyKlienta.isEmpty()) {
                System.out.println("Klient nie posiada biletow");
                System.out.println();
            }
        }
        else

    }

}

