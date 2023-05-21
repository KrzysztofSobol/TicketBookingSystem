package Resources;

public class Samolot {
    private final String nazwa;
    private final int zasieg, iloscMiejsc;

    public Samolot(String nazwa, int zasieg, int iloscMiejsc){
        this.nazwa = nazwa;
        this.zasieg = zasieg;
        this.iloscMiejsc = iloscMiejsc;
    }
    /* *Zwraca nazwę samolotu*/
    public String getNazwa(){ return nazwa;}

    /* *Zwraca zasięg samolotu*/
    public int getZasieg(){ return zasieg;}

    /* *Zwraca ilosc miejsc samolotu*/
    public int getIloscMiejsc(){ return iloscMiejsc;}

}

