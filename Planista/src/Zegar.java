package planista;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Zegar {

    List<Proces> procesy;
    int indeks;
    double czas;
    double sumaCzasOczekiwania;
    double sumaCzasObrotu;

    public Zegar() {
        this.procesy = new ArrayList<Proces>();
        this.indeks = 0;
        this.czas = 0;
        this.sumaCzasObrotu = 0;
        this.sumaCzasOczekiwania = 0;
    }

    public void dodajProces(Proces p) {
        procesy.add(p);
    }

    public Proces następny() {
        if (indeks == procesy.size()) {
            return null;
        } else {
            return procesy.get(indeks++);
        }
    }

    public void wypiszProces(Proces p) {
        sumaCzasObrotu += czas - p.getMomentPojawienia();
        sumaCzasOczekiwania += czas - p.getMomentPojawienia() - p.getZapotrzebowanie();
        System.out.print(p.wypisz() + String.format(Locale.ROOT, "%.2f", czas) + "]");
    }

    public void wypiszStatystyki(Planista p) {
        p.wypiszStatystyki(String.format(Locale.ROOT, "%.2f", (sumaCzasObrotu / procesy.size())),
                String.format(Locale.ROOT, "%.2f", (sumaCzasOczekiwania / procesy.size())));
    }

    public double getCzas() {
        return czas;
    }

    public void dodajCzas(double d) {
        this.czas += d;
    }

    public double setCzas(double czas) {
        double d = czas - this.czas;
        this.czas = czas;
        return d;
    }

    public void resetuj() {
        this.indeks = 0;
        this.czas = 0;
        this.sumaCzasObrotu = 0;
        this.sumaCzasOczekiwania = 0;
        for (Proces p : procesy) {
            p.resetuj();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Proces p : procesy) {
            str.append(p.toString());
        }
        return str.toString();
    }
}
