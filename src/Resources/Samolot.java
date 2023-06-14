package Resources;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Samolot implements Serializable {
    private final String nazwa;
    private final String nazwaLotniskaStacjonowanie;
    private final String lokalizacjaStacjonowania;
    private final int zasieg, iloscMiejsc, predkosc;

    public Samolot(String nazwa, int zasieg, int iloscMiejsc, int predkosc, String nazwaLotniskaStacjonowanie, String lokalizacjaStacjonowania) {
        this.nazwa = nazwa;
        this.zasieg = zasieg;
        this.iloscMiejsc = iloscMiejsc;
        this.predkosc = predkosc;
        this.nazwaLotniskaStacjonowanie = nazwaLotniskaStacjonowanie;
        this.lokalizacjaStacjonowania = lokalizacjaStacjonowania;
    }

    /* *Zwraca nazwę samolotu*/
    public String getNazwa() {
        return nazwa;
    }

    /* *Zwraca zasięg samolotu*/
    public int getZasieg() {
        return zasieg;
    }

    /* *Zwraca ilosc miejsc samolotu*/
    public int getIloscMiejsc() {
        return iloscMiejsc;
    }

    public int getPredkosc() {
        return predkosc;
    }

    /* *Zwraca nazwaLotniskaStacjonowanie*/
    public String getnazwaLotniskaStacjonowanie() {
        return nazwaLotniskaStacjonowanie;
    }

    /* *Zwraca LokalizacjaStacjonowania*/
    public String getLokalizacjaStacjonowania() {
        return lokalizacjaStacjonowania;
    }

    /**
     * Zapisuje listę obiektów samolotów do pliku tekstowego.
     *
     * @param planesList lista obiektów samolotów do zapisania
     * @param fileName   nazwa pliku tekstowego
     */

    public static void writeToFile(List<Samolot> planesList, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(planesList);
            System.out.println("Zapisano obiekty do pliku: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Nadpisuje liste obiektami - samolotami na podstawie danych odczytanych z pliku.
     *
     * @param ListOfPlanes lista na samoloty
     * @param fileName     nazwa pliku tekstowego
     * @return lista obiektów samolotów
     */

    public static void readFromFile (ArrayList <Samolot> ListOfPlanes, String fileName){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            ListOfPlanes.clear();
            ListOfPlanes.addAll((ArrayList<Samolot>) inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile(ArrayList<Samolot> ListOfPlanes, String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            ListOfPlanes.clear();
            ListOfPlanes.addAll((ArrayList<Samolot>) inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

