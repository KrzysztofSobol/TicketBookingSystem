package Clients.Types;

import Clients.Klient;

public class Osoba extends Klient {
    private final String imie, nazwisko;

    public Osoba(String Imie, String Nazwisko){
        this.imie = Imie;
        this.nazwisko = Nazwisko;
    }

    public String getNazwa(){
        return imie;
    }

    public String getNazwiskoKrs(){
        return nazwisko;
    }

}
