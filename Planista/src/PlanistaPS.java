package planista;

import java.util.PriorityQueue;
import java.util.Queue;

public class PlanistaPS extends Planista {

    private Queue<Proces> obsługiwane;

    public PlanistaPS(int i) {
        this.obsługiwane = new PriorityQueue<Proces>(i, new ProcesPozostałoComparator());
    }

    protected Proces wczytajWszystkie(Proces p, Zegar z) {
        do {
            obsługiwane.add(p);
            p = z.następny();
        } while (p != null && p.getMomentPojawienia() <= z.getCzas());

        return p;
    }

    protected void zakończProces(Zegar z) {
        Proces p = obsługiwane.remove();
        z.wypiszProces(p);
    }

    @Override
    public void symuluj(Zegar z) {

        Proces p = z.następny();

        while (p != null) {

            if (!obsługiwane.isEmpty()) {

                double d = Math.min(obsługiwane.peek().getPozostało() * obsługiwane.size(),
                        p.getMomentPojawienia() - z.getCzas());
                z.dodajCzas(d);
                for (Proces obsługiwany : obsługiwane) {
                    obsługiwany.obsłuż(d / obsługiwane.size());
                }

                if (obsługiwane.peek().getPozostało() == 0) {
                    do {
                        zakończProces(z);
                    } while (!obsługiwane.isEmpty() && obsługiwane.peek().getPozostało() == 0);
                }

                if (p.getMomentPojawienia() <= z.getCzas()) {
                    p = wczytajWszystkie(p, z);
                }

            } else {

                z.dodajCzas(p.getMomentPojawienia() - z.getCzas());
                p = wczytajWszystkie(p, z);
            }
        }
        while (!obsługiwane.isEmpty()) {

            double d = obsługiwane.peek().getPozostało();
            z.dodajCzas(d * obsługiwane.size());
            for (Proces obsługiwany : obsługiwane) {
                obsługiwany.obsłuż(d);
            }

            zakończProces(z);
        }
    }

    @Override
    public void wypiszStatystyki(String str1, String str2) {
        System.out.println("\nŚredni czas obrotu: " + str1);
        System.out.println("Średni czas oczekiwania: 0.00\n");
    }

    @Override
    public String toString() {
        return "PS";
    }
}
