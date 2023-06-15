import Resources.Samolot;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SamolotTest {

    @Test
    public void testWriteToFile() {
        List<Samolot> samoloty = new ArrayList<>();
        samoloty.add(new Samolot("Boeing 747", 8000, 400, 900, "Lotnisko1", "Lokalizacja1"));
        samoloty.add(new Samolot("Airbus A320", 5000, 200, 800, "Lotnisko2", "Lokalizacja2"));
        String fileName = "samoloty_test.txt";

        Samolot.writeToFile(samoloty, fileName);

        ArrayList<Samolot> odczytaneSamoloty = new ArrayList<>();
        Samolot.readFromFile(odczytaneSamoloty, fileName);

        assertEquals(samoloty.size(), odczytaneSamoloty.size());
        for (int i = 0; i < samoloty.size(); i++) {
            assertEquals(samoloty.get(i).getNazwa(), odczytaneSamoloty.get(i).getNazwa());
            assertEquals(samoloty.get(i).getZasieg(), odczytaneSamoloty.get(i).getZasieg());
            assertEquals(samoloty.get(i).getIloscMiejsc(), odczytaneSamoloty.get(i).getIloscMiejsc());
            assertEquals(samoloty.get(i).getPredkosc(), odczytaneSamoloty.get(i).getPredkosc());
            assertEquals(samoloty.get(i).getnazwaLotniskaStacjonowanie(), odczytaneSamoloty.get(i).getnazwaLotniskaStacjonowanie());
            assertEquals(samoloty.get(i).getLokalizacjaStacjonowania(), odczytaneSamoloty.get(i).getLokalizacjaStacjonowania());
        }
    }

    @Test
    public void testReadFromFile() {
        List<Samolot> samoloty = new ArrayList<>();
        samoloty.add(new Samolot("Boeing 747", 8000, 400, 900, "Lotnisko1", "Lokalizacja1"));
        samoloty.add(new Samolot("Airbus A320", 5000, 200, 800, "Lotnisko2", "Lokalizacja2"));
        String fileName = "samoloty_test.txt";

        Samolot.writeToFile(samoloty, fileName);

        ArrayList<Samolot> odczytaneSamoloty = new ArrayList<>();
        Samolot.readFromFile(odczytaneSamoloty, fileName);

        assertEquals(samoloty.size(), odczytaneSamoloty.size());
        for (int i = 0; i < samoloty.size(); i++) {
            assertEquals(samoloty.get(i).getNazwa(), odczytaneSamoloty.get(i).getNazwa());
            assertEquals(samoloty.get(i).getZasieg(), odczytaneSamoloty.get(i).getZasieg());
            assertEquals(samoloty.get(i).getIloscMiejsc(), odczytaneSamoloty.get(i).getIloscMiejsc());
            assertEquals(samoloty.get(i).getPredkosc(), odczytaneSamoloty.get(i).getPredkosc());
            assertEquals(samoloty.get(i).getnazwaLotniskaStacjonowanie(), odczytaneSamoloty.get(i).getnazwaLotniskaStacjonowanie());
            assertEquals(samoloty.get(i).getLokalizacjaStacjonowania(), odczytaneSamoloty.get(i).getLokalizacjaStacjonowania());
        }
    }
}
