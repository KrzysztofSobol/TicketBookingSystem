package Clients;

import Reservation.Ticket;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Clients.Types.*;

public abstract class Klient {
    public static List<Klient> klienci = new ArrayList<>();
    public static List<Ticket> Bilety = new ArrayList<>();

    public static List<Klient> getListaKlientow() {
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
     * @param  klientList lista klientów
     * @param  fileName  nazwa pliku tekstowego
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
     * Odczytuje listę obiektów lotnisk z pliku tekstowego i zwraca ją jako wynik.
     *
     * @param fileName     nazwa pliku tekstowego
     */
    public static void readFromFile(String fileName) {
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
                    Klient.klienci.add(klient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
