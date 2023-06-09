package Resources;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Samolot {
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
}

