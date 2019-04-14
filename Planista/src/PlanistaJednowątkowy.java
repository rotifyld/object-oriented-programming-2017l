package planista;

import java.util.Queue;

public abstract class PlanistaJednowątkowy extends Planista {

    protected Queue<Proces> oczekujące;
    protected Proces obsługiwany;

    protected boolean obsługaProcesuZakończona() {
        return (obsługiwany.getPozostało() == 0);
    }

    protected void rozpocznijProces() {
        if (!oczekujące.isEmpty()) {
            obsługiwany = oczekujące.remove();
        }
    }

    protected Proces wczytajWszystkie(Proces p, Zegar z) {
        do {
            oczekujące.add(p);
            p = z.następny();
        } while (p != null && p.getMomentPojawienia() <= z.getCzas());

        return p;
    }

    protected void zakończProces(Zegar z) {
        z.wypiszProces(obsługiwany);
        obsługiwany = null;
    }

}
