package Flights;

import Resources.Lotnisko;

public class Trasa {
    private final Lotnisko lotnisko_p, lotnisko_k;
    private final double distance;

    public Trasa(Lotnisko lotnisko_p, Lotnisko lotnisko_k){
        this.lotnisko_p = lotnisko_p;
        this.lotnisko_k = lotnisko_k;
        this.distance = obliczOdleglosc(lotnisko_p.getX(), lotnisko_p.getY(),lotnisko_k.getX(), lotnisko_k.getY()) * 1000;
    }

    public double obliczOdleglosc(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        return Math.sqrt(dx*dx + dy*dy);
    }

    public double getDistance() {
        return distance;
    }
}
