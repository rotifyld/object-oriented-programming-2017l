package planista;

import java.util.PriorityQueue;

public class PlanistaSJF extends PlanistaJednowątkowy {

    public PlanistaSJF(int i) {
        this.oczekujące = new PriorityQueue<Proces>(i, new ProcesZapotrzebowanieComparator());
    }

    @Override
    public void symuluj(Zegar z) {

        Proces p = z.następny();

        while (p != null) {

            if (obsługiwany != null) {

                double d = obsługiwany.getPozostało();
                z.dodajCzas(d);
                obsługiwany.obsłuż(d);

                if (p.getMomentPojawienia() <= z.getCzas()) {
                    p = wczytajWszystkie(p, z);
                }

                zakończProces(z);
                rozpocznijProces();

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
        return "SJF";
    }

}
