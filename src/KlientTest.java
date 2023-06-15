import Clients.Klient;
import Clients.Types.Firma;
import Clients.Types.Osoba;
import Reservation.Ticket;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KlientTest {

    @Test
    public void testWriteAndReadFromFileKlient() {
        // Przygotowanie danych testowych
        ArrayList<Klient> klienci = new ArrayList<>();
        Firma klient1 = new Firma("polibuda","3333333");
        Osoba klient2 = new Osoba("Janek", "Kowalski");
        klienci.add(klient1);
        klienci.add(klient2);
        String fileName = "klienci_test.txt";

        // Wywołanie metody testowanej
        Klient.writeToFileKlient(klienci, fileName);

        // Sprawdzenie, czy plik został poprawnie zapisany
        List<Klient> odczytaniKlienci = new ArrayList<>();
        Klient.readFromFileKlient(odczytaniKlienci, fileName);

        assertEquals(klienci.size(), odczytaniKlienci.size());
        for (int i = 0; i < klienci.size(); i++) {
            assertEquals(klienci.get(i).getNazwa(), odczytaniKlienci.get(i).getNazwa());
            assertEquals(klienci.get(i).getNazwiskoKrs(), odczytaniKlienci.get(i).getNazwiskoKrs());
        }
    }
}

