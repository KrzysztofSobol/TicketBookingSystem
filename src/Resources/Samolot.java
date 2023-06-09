package Resources;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Resources.SamolotTyp.*;

public class Samolot implements Serializable{
    private final String nazwa;
    private final int zasieg, iloscMiejsc, predkosc;

    public Samolot(String nazwa, int zasieg, int iloscMiejsc, int predkosc) {
        this.nazwa = nazwa;
        this.zasieg = zasieg;
        this.iloscMiejsc = iloscMiejsc;
        this.predkosc = predkosc;
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

    /**
     * Zapisuje listę obiektów samolotów do pliku tekstowego.
     *
     * @param planesList lista obiektów samolotów do zapisania
     * @param fileName   nazwa pliku tekstowego
     */

    public static void writeToFile(List<Samolot> planesList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Samolot plane : planesList) {
                String lineOfData =
                        plane.getClass().toString().substring(plane.getClass().toString().length() - 4) + ";" +
                                plane.getNazwa() + ";" +
                                plane.getZasieg() + ";" +
                                plane.getIloscMiejsc() + ";" +
                                plane.getPredkosc();
                writer.write(lineOfData);
                writer.newLine();
//               System.out.println(lineOfData);
            }

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
    public static List<Samolot> readFromFile(List<Samolot> ListOfPlanes, String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 5) {
                    String className = data[0];
                    String nazwa = data[1];
                    int zasieg = Integer.parseInt(data[2]);
                    int iloscMiejsc = Integer.parseInt(data[3]);
                    int predkosc = Integer.parseInt(data[4]);

                    Samolot samolot;
                    switch (className) {
                        case "Typ1":
                            samolot = new Typ1(nazwa, zasieg, iloscMiejsc, predkosc);
                            break;
                        case "Typ2":
                            samolot = new Typ2(nazwa, zasieg, iloscMiejsc, predkosc);
                            break;
                        case "Typ3":
                            samolot = new Typ3(nazwa, zasieg, iloscMiejsc, predkosc);
                            break;
                        default:
                            samolot = new Samolot(nazwa, zasieg, iloscMiejsc, predkosc);
                            break;
                    }

                    ListOfPlanes.add(samolot);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ListOfPlanes;
    }
}

