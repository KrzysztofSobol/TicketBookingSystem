import Flights.Lot;
import Resources.Lotnisko;
import Resources.Samolot;
import Resources.SamolotTyp.Typ1;
import Resources.SamolotTyp.Typ2;
import Resources.SamolotTyp.Typ3;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.text.DateFormatSymbols;



public class Main {
    static ArrayList<Lotnisko> lotniska = new ArrayList<>();
    static ArrayList<Lot> loty = new ArrayList<>();
    public static void main(String[] args) {
        Lotnisko lotnisko1 = new Lotnisko("Warszawa", "Warszawa", 1, 1);
        Lotnisko lotnisko2 = new Lotnisko("Berlin", "Berlin", 1, 20);
        Lotnisko lotnisko3 = new Lotnisko("Paryz", "Paryz", 1, 100);
        Lotnisko lotnisko4 = new Lotnisko("HongKong", "HongKong", 1, 1000);

        lotniska.add(lotnisko1);
        lotniska.add(lotnisko2);
        lotniska.add(lotnisko3);
        lotniska.add(lotnisko4);

        /// Testy
        Typ1 krotko_dystansowiec = new Typ1("Mini Majk", 1000, 20);
        Typ2 srednio_dystansowiec = new Typ2("Mid John", 5000, 30);
        Typ3 daleko_dystansowiec = new Typ3("Long Ben", 100000, 20);

        Typ1 krotko_dystansowiec2 = new Typ1("Mini Majk2", 1000, 20);
        Typ2 srednio_dystansowiec2 = new Typ2("Mid John2", 5000, 30);
        Typ3 daleko_dystansowiec2 = new Typ3("Long Ben2", 100000, 20);

        Typ1 krotko_dystansowiec3 = new Typ1("Mini Majk3", 1000, 20);
        Typ2 srednio_dystansowiec3 = new Typ2("Mid John3", 5000, 30);
        Typ3 daleko_dystansowiec3 = new Typ3("Long Ben3", 100000, 20);

        Typ1 krotko_dystansowiec4 = new Typ1("Mini Majk4", 1000, 20);
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
    }

    public static void generujLot() {
        DateFormatSymbols symbols = new DateFormatSymbols(new Locale("en", "US"));
        String[] daysOfWeek = symbols.getWeekdays();
        Random random = new Random();
        for (int j = 0; j < lotniska.size(); j++) {
            for (int i = 2; i <= 7; i++) {
                Lotnisko lotnisko_p = lotniska.get(j);
                for (int k = 0; k < lotniska.size(); k++) {
                    Lotnisko lotnisko_k = lotniska.get(k);
                    if (lotnisko_k.equals(lotnisko_p)) {
                        continue;
                    }
                    int hour = random.nextInt(24);
                    int minute = random.nextInt(60);
                    LocalTime losowaGodzina = LocalTime.of(hour, minute);
                    DayOfWeek dzien_odlotu = DayOfWeek.valueOf(daysOfWeek[i].toUpperCase(Locale.ENGLISH));
                    DayOfWeek nowy_dzien_odlotu = dzien_odlotu;
                    Samolot samolot = PrzydzielSamolot(lotnisko_p, lotnisko_k, losowaGodzina);
                    if(samolot==null){
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


    public static Samolot PrzydzielSamolot(Lotnisko lotnisko_p, Lotnisko lotnisko_k, LocalTime Godzina) {
        double odleglosc = Odleglosc(lotnisko_p, lotnisko_k);
        for (Samolot samolot : lotnisko_p.getFlota()) {
            if (samolot.getZasieg() >= odleglosc && samolot.getIloscMiejsc() > 0) {
                Lot lot = ZnajdzOstatniLot(samolot, lotnisko_p, lotnisko_k);
                if (lot != null && (lot.getGodzina_odlotu().plusHours(3)).isBefore(Godzina)) {
                    System.out.println("zdążył wrócić");
                    return samolot;
                }
                else if (lot != null && (lot.getGodzina_odlotu().plusHours(3)).isAfter(Godzina)) {
                    System.out.println("nałożył sie");
                    return null;
                }
                else{
                    System.out.println("nie było takiego lotu");
                    return samolot;
                }
            }
        }
        return null;
    }


    public static Lot ZnajdzOstatniLot(Samolot samolot, Lotnisko lotnisko_p, Lotnisko lotnisko_k) {
        if (!loty.isEmpty()) {
            for (int i = loty.size() - 1; i >= 0; i--) {
                Lot lot = loty.get(i);
                if (lot.getSamolot() == samolot && lot.getLotnisko_p() == lotnisko_p && lot.getLotnisko_k() == lotnisko_k) {
                    return lot;
                }
            }
        }
        return null;
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
            //}
        }
    }
}