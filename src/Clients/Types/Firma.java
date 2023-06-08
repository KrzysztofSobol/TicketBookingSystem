package Clients.Types;

import Clients.Klient;

public class Firma extends Klient {
    private final String nazwa, numer_KRS;

    public Firma(String Nazwa, String Numer_KRS) {
        this.nazwa = Nazwa;
        this.numer_KRS = Numer_KRS;
    }
}
