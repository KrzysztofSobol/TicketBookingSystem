import Resources.Lotnisko;
import Resources.Samolot;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LotniskoTest {

    @Test
    public void testWriteToFile() {
        // Przygotowanie danych testowych
        ArrayList<Lotnisko> lotniska = new ArrayList<>();
        Lotnisko lotnisko1 = new Lotnisko("Lotnisko1", "Lokalizacja1", 52.2297, 21.0122);
        Lotnisko lotnisko2 = new Lotnisko("Lotnisko2", "Lokalizacja2", 48.8566, 2.3522);
        lotniska.add(lotnisko1);
        lotniska.add(lotnisko2);
        String fileName = "lotniska_test.txt";

        Lotnisko.writeToFile(lotniska, fileName);

        ArrayList<Lotnisko> odczytaneLotniska = new ArrayList<>();
        Lotnisko.readFromFile(odczytaneLotniska,"lotniska_test.txt");

        assertEquals(lotniska.size(), odczytaneLotniska.size());
        for (int i = 0; i < lotniska.size(); i++) {
            assertEquals(lotniska.get(i).getNazwa(), odczytaneLotniska.get(i).getNazwa());
            assertEquals(lotniska.get(i).getLokalizacja(), odczytaneLotniska.get(i).getLokalizacja());
            assertEquals(lotniska.get(i).getX(), odczytaneLotniska.get(i).getX());
            assertEquals(lotniska.get(i).getY(), odczytaneLotniska.get(i).getY());
            assertEquals(lotniska.get(i).getFlota().size(), odczytaneLotniska.get(i).getFlota().size());
        }
    }

    @Test
    public void testDodajSamolot() {
        // Przygotowanie danych testowych
        Lotnisko lotnisko = new Lotnisko("Lotnisko1", "Lokalizacja1", 52.2297, 21.0122);
        Samolot samolot1 = new Samolot("Boeing 747", 8000, 400, 900, "Lotnisko1", "Lokalizacja1");
        Samolot samolot2 = new Samolot("Airbus A320", 5000, 200, 800, "Lotnisko2", "Lokalizacja2");

        // Wywołanie metody testowanej
        lotnisko.dodajSamolot(samolot1);
        lotnisko.dodajSamolot(samolot2);

        // Sprawdzenie, czy samoloty zostały dodane do floty lotniska
        assertEquals(1, lotnisko.getFlota().size());
        assertEquals(samolot1, lotnisko.getFlota().get(0));
    }
}
