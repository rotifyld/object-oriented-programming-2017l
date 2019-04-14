package planista;

import java.util.LinkedList;

public class PlanistaFCFS extends PlanistaJednowątkowy {

    public PlanistaFCFS() {
        this.oczekujące = new LinkedList<Proces>();
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
        return "FCFS";
    }

}
