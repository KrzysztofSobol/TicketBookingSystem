package Resources;

public class Lotnisko {
    private final String nazwa, lokalizacja;
    private final double x, y;

    public Lotnisko(String nazwa, String lokalizacja, double x, double y){
        this.nazwa = nazwa;
        this.lokalizacja = lokalizacja;
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
