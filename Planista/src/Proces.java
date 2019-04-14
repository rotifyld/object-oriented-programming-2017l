package planista;

public class Proces {

    private int indeks;
    private int momentPojawienia;
    private double pozostało;
    private int zapotrzebowanie;

    public Proces(int indeks, int momentPojawienia, int zapotrzebowanie) {
        this.indeks = indeks;
        this.momentPojawienia = momentPojawienia;
        this.pozostało = zapotrzebowanie;
        this.zapotrzebowanie = zapotrzebowanie;
    }

    public int getIndeks() {
        return indeks;
    }

    public int getMomentPojawienia() {
        return momentPojawienia;
    }

    public double getPozostało() {
        return pozostało;
    }

    public int getZapotrzebowanie() {
        return zapotrzebowanie;
    }

    public void obsłuż(double d) {
        pozostało -= d;
    }

    public void resetuj() {
        this.pozostało = zapotrzebowanie;
    }

    public String wypisz() {
        return "[" + indeks + " " + momentPojawienia + " ";
    }

}
