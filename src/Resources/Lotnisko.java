package Resources;

import Flights.Lot;
import Resources.SamolotTyp.Typ1;
import Resources.SamolotTyp.Typ2;
import Resources.SamolotTyp.Typ3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lotnisko implements Serializable{
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
        if (this.nazwa == samolot.getnazwaLotniskaStacjonowanie() && this.lokalizacja == samolot.getLokalizacjaStacjonowania()){
            flota.add(samolot);
        }
        else{
            System.out.println("Błąd odczytu - nieprawidłowo dodawany samolot do lotniska");
        }

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
     * Zapisuje listę obiektów lotnisk do pliku.
     *
     * @param airportsList lista obiektów lotnisk do zapisania
     * @param fileName     nazwa pliku
     */

    public static void writeToFile(List<Lotnisko> airportsList, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(airportsList);
            System.out.println("Zapisano obiekty do pliku: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Odczytuje listę obiektów lotnisk z pliku.
     *
     * @param airportsList lista obiektów lotnisk do zapisania
     * @param fileName nazwa pliku
     */
    public static void readFromFile(List<Lotnisko> airportsList, String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            airportsList = (List<Lotnisko>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}