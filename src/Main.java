import Flights.Trasa;
import Resources.Lotnisko;

public class Main {
    public static void main(String[] args) {
        System.out.println("Elo\n");
        Lotnisko lotnisko1 = new Lotnisko("a", "aaa", 4.5, 3.2);
        Lotnisko lotnisko2 = new Lotnisko("b", "bbb", 7.5, 1.2);

        Trasa trasa = new Trasa(lotnisko1, lotnisko2);
        System.out.println(trasa.getDistance());
    }
}