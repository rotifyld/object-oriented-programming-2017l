package planista;

import java.util.LinkedList;

public class PlanistaRR extends PlanistaJednowątkowy {

    private int q;

    public PlanistaRR(int q) {
        this.oczekujące = new LinkedList<Proces>();
        this.q = q;
    }

    @Override
    public void symuluj(Zegar z) {

        Proces p = z.następny();

        while (p != null) {

            if (obsługiwany != null) {

                double d = Math.min(obsługiwany.getPozostało(), q);
                z.dodajCzas(d);
                obsługiwany.obsłuż(d);

                while (p != null && p.getMomentPojawienia() < z.getCzas()) {
                    oczekujące.add(p);
                    p = z.następny();
                }

                if (obsługaProcesuZakończona()) {
                    zakończProces(z);
                } else {
                    oczekujące.add(obsługiwany);
                }

                rozpocznijProces();

                while (p != null && p.getMomentPojawienia() == z.getCzas()) {
                    oczekujące.add(p);
                    p = z.następny();
                }

            } else {

                z.dodajCzas(p.getMomentPojawienia() - z.getCzas());
                p = wczytajWszystkie(p, z);
                rozpocznijProces();
            }
        }
        while (obsługiwany != null) {

            double d = Math.min(obsługiwany.getPozostało(), q);
            z.dodajCzas(d);
            obsługiwany.obsłuż(d);

            if (obsługaProcesuZakończona()) {
                zakończProces(z);
            } else {
                oczekujące.add(obsługiwany);
            }

            rozpocznijProces();
        }
    }

    @Override
    public String toString() {
        return "RR-" + q;
    }

}
