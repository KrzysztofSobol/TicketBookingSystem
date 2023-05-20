package Clients;

import java.util.ArrayList;
import java.util.List;

public abstract class Klient {
    public List<Klient> klienci = new ArrayList<>();

    public void dodajKlienta(Klient klient){
        klienci.add(klient);
    }

    public void usunKlienta(Klient klient){
        klienci.remove(klient);
    }
}
