package Clients;

import Reservation.Ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Clients.Types.*;

public abstract class Klient implements Serializable {
    public static List<Klient> klienci = new ArrayList<>();
    public static List<Ticket> Bilety = new ArrayList<>();

    public List<Klient> getListaKlientow() {
        return klienci;
    }

    public static List<Ticket> getBilety() {
        return Bilety;
    }

    public void dodajKlienta() {
        klienci.add(this);
    }

    public void usunKlienta() {
        klienci.remove(this);
    }

    public void dodajBilet(Ticket ticket) {
        Bilety.add(ticket);
    }

    public abstract String getNazwa();

    public abstract String getNazwiskoKrs();

    /**
     * Zapisuje listę obiektów Klientów do pliku.
     *
     * @param klientList lista klientów
     * @param fileName  nazwa pliku
     */
    public static void writeToFileKlient(List<Klient> klientList, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(klientList);
            System.out.println("Zapisano obiekty do pliku: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Odczytuje listę obiektów Klientów z pliku i zapisuje je do listy przekazanej jako parametr.
     *
     * @param klientList lista, do której będą dodawane odczytane obiekty Klientów
     * @param fileName   nazwa pliku
     */
    public static void readFromFileKlient(List<Klient> klientList, String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            klientList.addAll((List<Klient>) inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Zapisuje listę obiektów Biletów do pliku.
     *
     * @param biletyList lista biletów
     * @param fileName  nazwa pliku
     */
    public static void writeToFileBilet(List<Ticket> biletyList, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(biletyList);
            System.out.println("Zapisano obiekty do pliku: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Odczytuje listę obiektów Biletów z pliku i dodaje je do istniejącej listy.
     *
     * @param biletyList lista, do której będą dodawane odczytane obiekty Biletów
     * @param fileName   nazwa pliku
     */
    public static void readFromFileBilet(List<Ticket> biletyList, String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Ticket> odczytaneBiletyList = (List<Ticket>) inputStream.readObject();
            biletyList.addAll(odczytaneBiletyList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}