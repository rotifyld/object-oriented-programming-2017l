package spoleczenstwo;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public abstract class Agent {

    private final int indeks;
    protected Stan stan;
    private List<Agent> znajomi;
    private Queue<Spotkanie> spotkania;

    public Agent(int indeks) {
        znajomi = new ArrayList<>();
        spotkania = new PriorityQueue<>(new SpotkaniaComparator());
        this.indeks = indeks;
        this.stan = Stan.Zdrowy;
    }

    public boolean dodajZnajomego(Agent a) {
        if (znajomi.contains(a)) {
            return false;
        }

        for (Agent znajomy : znajomi) {
            znajomy.dodajZnajomegoZnajomego(a);
            a.dodajZnajomegoZnajomego(znajomy);
        }
        znajomi.add(a);
        return true;
    }

    public void usuńZnajomego(Agent a) {
        znajomi.remove(a);
        for (Agent znajomy : znajomi) {
            znajomy.usuńZnajomegoZnajomego(a);
            a.usuńZnajomegoZnajomego(znajomy);
        }
    }

    protected void dodajZnajomegoZnajomego(Agent a) {
    }

    protected void usuńZnajomegoZnajomego(Agent a) {
    }

    public void zaraź() {
        stan = Stan.Chory;
    }

    public void zgiń() {
        stan = Stan.Martwy;
        for (Agent a : znajomi) {
            a.usuńZnajomego(this);
        }
    }

    public Stan początekDnia(Random generator, Kalendarz k, double śmiertelność,
            double prawdWyzdrowienia, double prawdSpotkania) {

        if (stan == Stan.Chory) {
            if (generator.nextDouble() < śmiertelność) {
                stan = Stan.Martwy;
                return stan;
            }
            if (generator.nextDouble() < prawdWyzdrowienia) {
                stan = Stan.Uodporniony;
                return stan;
            }
        }

        if (!znajomi.isEmpty() && !k.ostatniDzień()) {
            int wylosowanychSpotkań = 0;
            while (generator.nextDouble() < modyfikatorPrawdSpotkania() * prawdSpotkania) {
                ++wylosowanychSpotkań;
            }
            spotkania.addAll(losujSpotkania(generator, wylosowanychSpotkań, k));
        }

        return null;
    }

    public int spotykajSię(Random generator, int dzisiaj, double prawdZarażenia) {
        int zarażonych = 0;
        while (!spotkania.isEmpty() && spotkania.peek().dzień() == dzisiaj) {

            Spotkanie s = spotkania.remove();
            if (s.przeprowadź(generator, prawdZarażenia, this)) {
                ++zarażonych;
            }
        }
        return zarażonych;
    }

    protected List<Spotkanie> losujSpotkania(Random generator, int spotkań, Kalendarz k) {
        List<Spotkanie> spotkania = new ArrayList<Spotkanie>(spotkań);
        for (int i = 0; i < spotkań; ++i) {
            spotkania.add(new Spotkanie(znajomi.get(generator.nextInt(znajomi.size())),
                    k.losujDzień(generator)));
        }
        return spotkania;
    }

    protected double modyfikatorPrawdSpotkania() {
        return 1;
    }

    public int indeks() {
        return indeks;
    }

    public Stan stan() {
        return stan;
    }

    public String wypiszSzczegóły() {
        String s = Integer.toString(indeks);

        if (stan == Stan.Chory) {
            s += "*";
        }
        return s;
    }

    public String wypiszZnajomych() {
        StringBuilder s = new StringBuilder();
        s.append(indeks);
        for (Agent a : znajomi) {
            s.append(" " + a.indeks());
        }
        return s.toString();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(indeks);
        if (stan == Stan.Chory) {
            s.append("*");
        } else if (stan == Stan.Martwy) {
            s.append("X");
        } else if (stan == Stan.Uodporniony) {
            s.append("#");
        }
        for (Agent a : znajomi) {
            s.append(" " + a.indeks());
        }
        return s.toString();
    }
}
