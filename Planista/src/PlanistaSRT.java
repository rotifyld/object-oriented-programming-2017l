package planista;

import java.util.PriorityQueue;

public class PlanistaSRT extends PlanistaJednowątkowy {

    public PlanistaSRT(int i) {
        this.oczekujące = new PriorityQueue<Proces>(i, new ProcesPozostałoComparator());
    }

    public void symuluj(Zegar z) {

        Proces p = z.następny();

        while (p != null) {

            if (obsługiwany != null) {

                double d = Math.min(obsługiwany.getPozostało(),
                        p.getMomentPojawienia() - z.getCzas());
                z.dodajCzas(d);
                obsługiwany.obsłuż(d);

                if (p.getMomentPojawienia() <= z.getCzas()) {
                    p = wczytajWszystkie(p, z);
                    if (oczekujące.peek().getZapotrzebowanie() < obsługiwany.getPozostało()) {
                        oczekujące.add(obsługiwany);
                        obsługiwany = oczekujące.remove();
                    }
                }

                if (obsługaProcesuZakończona()) {
                    zakończProces(z);
                    rozpocznijProces();
                }

            } else {

                z.dodajCzas(p.getMomentPojawienia() - z.getCzas());
                p = wczytajWszystkie(p, z);
                rozpocznijProces();
            }
        }
        while (obsługiwany != null) {

            double d = obsługiwany.getPozostało();
            z.dodajCzas(d);
            obsługiwany.obsłuż(d);

            zakończProces(z);
            rozpocznijProces();
        }
    }

    @Override
    public String toString() {
        return "SRT";
    }

}
