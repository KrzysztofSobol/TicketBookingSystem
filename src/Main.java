import Flights.Lot;
import Flights.Trasa;
import Resources.Lotnisko;
import Resources.Samolot;
import Resources.SamolotTyp.Typ1;
import Resources.SamolotTyp.Typ2;
import Resources.SamolotTyp.Typ3;

import java.time.LocalTime;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;



public class Main {
    static ArrayList<Lotnisko> lotniska = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lotnisko lotnisko1 = new Lotnisko("Warszawa", "Warszawa", 1, 1);
        Lotnisko lotnisko2 = new Lotnisko("Berlin", "Berlin", 1, 4);
        Lotnisko lotnisko3 = new Lotnisko("Paryz", "Paryz", 1, 20);
        Lotnisko lotnisko4 = new Lotnisko("HongKong", "HongKong", 3, 14);

        lotniska.add(lotnisko1);
        lotniska.add(lotnisko2);
        lotniska.add(lotnisko3);
        lotniska.add(lotnisko4);

        Trasa trasa = new Trasa(lotnisko1, lotnisko2);
        System.out.println("Odległość między lotniskami: " + trasa.getDistance() + "\n Wielki test generacji lotów: \n");

        /// Testy
        Typ1 krotko_dystansowiec = new Typ1("Mini Majk", 1000, 20);
        Typ2 srednio_dystansowiec = new Typ2("Mid John", 5000, 30);
        Typ3 daleko_dystansowiec = new Typ3("Long Ben", 100000, 20);

        lotnisko1.dodajSamolot(krotko_dystansowiec);
        lotnisko1.dodajSamolot(srednio_dystansowiec);
        lotnisko1.dodajSamolot(daleko_dystansowiec);

        generujLot();
        System.out.println("letz do itt");
        wypiszLoty(lotnisko1.getLoty());
    }

    public static void generujLot(){
        DateFormatSymbols symbols = new DateFormatSymbols(new Locale("pl", "PL"));
        String[] daysOfWeek = symbols.getWeekdays();
        Random random = new Random();
        for (Lotnisko lotnisko1 : lotniska) {
            for (int i = 2; i <= 7; i++) {
                for (Lotnisko lotnisko2 : lotniska){
                    if (lotnisko2.equals(lotnisko1)) {
                        continue;
                    }
                    int hour = random.nextInt(24); // Losowa godzina (0-23)
                    int minute = random.nextInt(60); // Losowa minuta (0-59)
                    LocalTime losowaGodzina = LocalTime.of(hour, minute, 00);
                    Samolot samolot = PrzydzielSamolot(lotnisko1, lotnisko2);

                    Lot lot = new Lot(losowaGodzina, daysOfWeek[i], samolot, lotnisko1, lotnisko2);
                    lotnisko1.dodajLot(lot);
                }
            }
        }
    }

    public static Samolot PrzydzielSamolot(Lotnisko lotnisko_p, Lotnisko lotnisko_k) {
        double odleglosc = Odleglosc(lotnisko_p, lotnisko_k);
        for (Samolot samolot : lotnisko_p.getFlota()) {
            if (samolot.getZasieg() >= odleglosc && samolot.getIloscMiejsc() > 0) {
                return samolot;
            }
        }
        return null;
    }


    public static double Odleglosc(Lotnisko lotnisko_p, Lotnisko lotnisko_k){
        double dx = lotnisko_k.getX() - lotnisko_p.getX();
        double dy = lotnisko_k.getY() - lotnisko_p.getY();
        return Math.sqrt(dx*dx + dy*dy)*1000;
    }

    public static void wypiszLoty(ArrayList<Lot> loty) {
        for (int i = 0; i < loty.size(); i++) {
            Lot lot = loty.get(i);

            System.out.println((i + 1) + ". " + lot.getLotnisko_p().getNazwa() + " -> " + lot.getLotnisko_k().getNazwa());
            System.out.println("    Samolot: " + lot.getSamolot().getNazwa());
            System.out.println("    Dnia: " + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
            System.out.println();
        }
    }
}