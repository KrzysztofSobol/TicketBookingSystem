package Resources;

import Flights.Lot;
import Resources.SamolotTyp.Typ1;
import Resources.SamolotTyp.Typ2;
import Resources.SamolotTyp.Typ3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lotnisko {
    private final String nazwa, lokalizacja;
    private final double x, y;
    ArrayList<Samolot> flota = new ArrayList<>();

    public Lotnisko(String nazwa, String lokalizacja, double x, double y) {
        this.nazwa = nazwa;
        this.lokalizacja = lokalizacja;
        this.x = x;
        this.y = y;
    }

    public void dodajSamolot(Samolot samolot) {
        flota.add(samolot);
    }
    //public void dodajLot(Lot lot){ loty.add(lot) ;}

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getLokalizacja() {
        return lokalizacja;
    }

    public ArrayList<Samolot> getFlota() {
        return flota;
    }

    /**
     * Zapisuje listę obiektów lotnisk do pliku tekstowego.
     *
     * @param airportsList lista obiektów lotnisk do zapisania
     * @param fileName     nazwa pliku tekstowego
     */
    public static void writeToFile(List<Lotnisko> airportsList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Lotnisko airport : airportsList) {
                String lineOfData =
                        airport.getNazwa() + ";" +
                                airport.getLokalizacja() + ";" +
                                airport.getX() + ";" +
                                airport.getY();
                writer.write(lineOfData);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Odczytuje listę obiektów lotnisk z pliku tekstowego i zwraca ją jako wynik.
     *
     * @param airportsList lista, do której będą dodawane odczytane obiekty lotnisk
     * @param fileName     nazwa pliku tekstowego
     * @return lista obiektów lotnisk odczytana z pliku
     */
    public static List<Lotnisko> readFromFile(List<Lotnisko> airportsList, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 4) {
                    String nazwa = data[0];
                    String lokalizacja = data[1];
                    double x = Double.parseDouble(data[2]);
                    double y = Double.parseDouble(data[3]);
                    Lotnisko lotnisko = new Lotnisko(nazwa, lokalizacja, x, y);
                    airportsList.add(lotnisko);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return airportsList;
    }
}