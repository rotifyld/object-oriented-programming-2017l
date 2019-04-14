package spoleczenstwo;

import java.util.Random;

public class Spotkanie {

    private final Agent a;
    private final int dzień;

    public Spotkanie(Agent a, int d) {
        this.a = a;
        this.dzień = d;
    }

    public Agent agent() {
        return a;
    }

    public int dzień() {
        return dzień;
    }

    @Override
    public String toString() {
        return "<" + dzień + ": " + a.indeks() + ">";
    }

    public boolean przeprowadź(Random generator, double prawdZarażenia, Agent b) {
        if (a.stan() == Stan.Zdrowy && b.stan() == Stan.Chory) {
            if (generator.nextDouble() < prawdZarażenia) {
                a.zaraź();
                return true;
            }
        } else if (a.stan() == Stan.Zdrowy && b.stan() == Stan.Chory) {
            if (generator.nextDouble() < prawdZarażenia) {
                b.zaraź();
                return true;
            }

        }
        return false;
    }

}
