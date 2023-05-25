import Flights.Lot;
import Resources.Lotnisko;
import Resources.Samolot;
import Resources.SamolotTyp.Typ1;
import Resources.SamolotTyp.Typ2;
import Resources.SamolotTyp.Typ3;

import java.time.LocalTime;
import java.util.*;
import java.text.DateFormatSymbols;



public class Main {
    static ArrayList<Lotnisko> lotniska = new ArrayList<>();
    static ArrayList<Lot> loty = new ArrayList<>();
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

        //System.out.println("Odległość między lotniskami: " + trasa.getDistance() + "\n Wielki test generacji lotów: \n");

        /// Testy
        Typ1 krotko_dystansowiec = new Typ1("Mini Majk", 1000, 20);
        Typ2 srednio_dystansowiec = new Typ2("Mid John", 5000, 30);
        Typ3 daleko_dystansowiec = new Typ3("Long Ben", 100000, 20);

        Typ1 krotko_dystansowiec2 = new Typ1("Mini Majk2", 1000, 20);
        Typ2 srednio_dystansowiec2 = new Typ2("Mid John2", 5000, 30);
        Typ3 daleko_dystansowiec2 = new Typ3("Long Ben2", 100000, 20);

        lotnisko1.dodajSamolot(krotko_dystansowiec);
        lotnisko1.dodajSamolot(srednio_dystansowiec);
        lotnisko1.dodajSamolot(daleko_dystansowiec);

        lotnisko2.dodajSamolot(krotko_dystansowiec2);
        lotnisko2.dodajSamolot(srednio_dystansowiec2);
        lotnisko2.dodajSamolot(daleko_dystansowiec2);

        generujLot();

        System.out.println("Loty z Berlina do WWA \n");

        wypiszLoty();
    }

    public static void generujLot(){
        DateFormatSymbols symbols = new DateFormatSymbols(new Locale("pl", "PL"));
        String[] daysOfWeek = symbols.getWeekdays();
        Random random = new Random();
        for (Lotnisko lotnisko_p : lotniska) {
            for (int i = 2; i <= 7; i++) {
                for (Lotnisko lotnisko_k : lotniska){
                    if (lotnisko_k.equals(lotnisko_p)) {
                        continue;
                    }
                    int hour = random.nextInt(24); // Losowa godzina (0-23)
                    int minute = random.nextInt(60); // Losowa minuta (0-59)
                    LocalTime losowaGodzina = LocalTime.of(hour, minute, 00);
                    Samolot samolot = PrzydzielSamolot(lotnisko_p, lotnisko_k);

                    Lot lot = new Lot(losowaGodzina, daysOfWeek[i], samolot, lotnisko_p, lotnisko_k);
                    LocalTime losowaGodzina2 = losowaGodzina.plusHours(1);
                    Lot lot2 = new Lot(losowaGodzina2, daysOfWeek[i], samolot, lotnisko_k, lotnisko_p);
                    loty.add(lot);
                    loty.add(lot2);
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

    public static void wypiszLoty() {
        for (int i = 0; i < loty.size(); i++) {
            Lot lot = loty.get(i);
            if(lot.getLotnisko_p().getNazwa().equals("Berlin")&&lot.getLotnisko_k().getNazwa().equals("Warszawa")){
                System.out.println((i + 1) + ". " + lot.getLotnisko_p().getNazwa() + " -> " + lot.getLotnisko_k().getNazwa());
                System.out.println("    Samolot: " + lot.getSamolot().getNazwa());
                System.out.println("    Dnia: " + lot.getDzien() + " o godzinie: " + lot.getGodzina_odlotu());
                System.out.println();
            }
        }
    }
}