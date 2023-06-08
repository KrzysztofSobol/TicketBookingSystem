package Clients.Types;

import Clients.Klient;

public class Osoba extends Klient {
    private final String imie, nazwisko;
    public Osoba(String Imie, String Nazwisko){
        this.imie = Imie;
        this.nazwisko = Nazwisko;
    }

   /* public String getImie(){
        return imie;
    }
    */
    public String getNazwa(){
        return imie;
    }

    public String getNazwiskoKrs(){
        return nazwisko;
    }

}
