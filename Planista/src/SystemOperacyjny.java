package planista;

import java.util.Arrays;

public class SystemOperacyjny {

    private Zegar z;
    private Planista[] planiści;

    public SystemOperacyjny() {
        this.z = new Zegar();
    }

    public void dodajProces(Proces p) {
        z.dodajProces(p);
    }

    public void dodajPlanistów(Planista[] planiści) {
        this.planiści = Arrays.copyOf(planiści, planiści.length);
    }

    public void symuluj() {

        for (Planista p : planiści) {
            System.out.println("Strategia: " + p);

            p.symuluj(z);
            z.wypiszStatystyki(p);
            z.resetuj();
        }
    }
}
