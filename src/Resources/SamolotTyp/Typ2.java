package Resources.SamolotTyp;
import java.io.Serializable;
import Resources.Samolot;

public class Typ2 extends Samolot implements Serializable {
    public Typ2(String nazwa, int zasieg, int iloscMiejsc, int predkosc) {
        super(nazwa, zasieg, iloscMiejsc, predkosc);
    }
}
