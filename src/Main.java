import Flights.Trasa;
import Resources.Lotnisko;

public class Main {
    public static void main(String[] args) {
        Lotnisko lotnisko1 = new Lotnisko("a", "aaa", 1, 1);
        Lotnisko lotnisko2 = new Lotnisko("b", "bbb", 1, 4);

        Trasa trasa = new Trasa(lotnisko1, lotnisko2);
        System.out.println("Odległość między lotniskami: " + trasa.getDistance());
    }
}