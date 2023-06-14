package Resources.SamolotTyp;
import java.io.Serializable;
import Resources.Samolot;

public class Typ3 extends Samolot implements Serializable{
    public Typ3(String nazwa, int zasieg, int iloscMiejsc, int predkosc, String nazwaLotniskaStacjonowanie, String lokalizacjaStacjonowania) {
        super(nazwa, zasieg, iloscMiejsc, predkosc, nazwaLotniskaStacjonowanie, lokalizacjaStacjonowania);
    }
}
