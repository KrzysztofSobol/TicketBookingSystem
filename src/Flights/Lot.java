package Flights;

import Resources.Lotnisko;
import Resources.Samolot;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class Lot implements Serializable{
    private LocalTime godzina_odlotu;
    private DayOfWeek dzien;
    private Samolot samolot;
    private Lotnisko lotnisko_p, lotnisko_k;
    private int dostepne_bilety;

    public Lot(LocalTime godzina_odlotu, DayOfWeek dzien, Samolot samolot, Lotnisko lotnisko_p, Lotnisko lotnisko_k){
        this.godzina_odlotu = godzina_odlotu;  // will be random
        this.dzien = dzien;                    // pon - niedz
        this.samolot = samolot;                // przydzielony algorytmem
        this.lotnisko_p = lotnisko_p;          // po koleji z pętli
        this.lotnisko_k = lotnisko_k;          // po koleji z pętli
        this.dostepne_bilety=new Random().nextInt(samolot.getIloscMiejsc()+1); // randomowo od 0 do ilosci miejsc w samolocie
    }
    public Lotnisko getLotnisko_p() {
        return lotnisko_p;
    }
    public Lotnisko getLotnisko_k() {
        return lotnisko_k;
    }

    public Samolot getSamolot() { return samolot; }

    public DayOfWeek getDzien() { return dzien; }

    public LocalTime getGodzina_odlotu() { return godzina_odlotu; }

    public int getDostepne_bilety() {
        return dostepne_bilety;
    }

    public void kupowanieBiletu(){ //setter
        this.dostepne_bilety-=1;
    }

    public void odwolywanieBiletu(){ //setter
        this.dostepne_bilety+=1;
    }

    /**
     * Zapisuje listę obiektów Lotów do pliku.
     *
     * @param lotyList lista lotów
     * @param fileName nazwa pliku
     */
    public static void writeToFile(List<Lot> lotyList, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(lotyList);
            System.out.println("Zapisano obiekty do pliku: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Odczytuje listę obiektów Lotów z pliku i dodaje je do istniejącej listy.
     *
     * @param lotyList lista, do której będą dodawane odczytane obiekty Lotów
     * @param fileName nazwa pliku
     */
    public static void readFromFile(List<Lot> lotyList, String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Lot> odczytaneLotyList = (List<Lot>) inputStream.readObject();
            lotyList.addAll(odczytaneLotyList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return lotnisko_p + " -> " + lotnisko_k;
    }
}
