package Clients;

import Reservation.Ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Clients.Types.*;

public abstract class Klient implements Serializable {
    public List<Klient> klienci = new ArrayList<>();
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
     * Zapisuje listę obiektów Klientów do pliku tekstowego.
     *
     * @param klientList lista klientów
     * @param fileName  nazwa pliku tekstowego
     */
    public static void writeToFile(List<Klient> klientList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Klient klient : klientList) {
                String lineOfData;
                if (klient instanceof Osoba) {
                    lineOfData = "Osoba;" +
                            ((Osoba) klient).getNazwa() + ";" +
                            ((Osoba) klient).getNazwiskoKrs();
                } else if (klient instanceof Firma) {
                    lineOfData = "Firma;" +
                            ((Firma) klient).getNazwa() + ";" +
                            ((Firma) klient).getNazwiskoKrs();
                } else {
                    lineOfData = "Klient;";
                }

                writer.write(lineOfData);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
     * Odczytuje listę obiektów Klientów z pliku tekstowego i zwraca ją jako wynik.
     *
     * @param fileName nazwa pliku tekstowego
     * @return lista odczytanych obiektów Klientów
     */
    public static List<Klient> readFromFile(String fileName) {
        List<Klient> klientList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length > 0) {
                    String type = data[0];
                    Klient klient = null;
                    switch (type) {
                        case "Osoba":
                            klient = new Osoba(data[1], data[2]);
                            break;
                        case "Firma":
                            klient = new Firma(data[1], data[2]);
                            break;
                    }
                    if (klient != null) {
                        klientList.add(klient);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return klientList;
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
