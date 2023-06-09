package Resources;

import java.util.Comparator;

public class ZasiegComparator implements Comparator<Samolot> {
    public int compare(Samolot samolot1, Samolot samolot2) {
        return samolot1.getZasieg() - samolot2.getZasieg();
    }
}
