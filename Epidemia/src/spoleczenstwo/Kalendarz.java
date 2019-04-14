package spoleczenstwo;

import java.util.Random;

public class Kalendarz {

    private final int ilośćDni;
    private int dzisiaj;

    public Kalendarz(int i) {
        this.ilośćDni = i;
        this.dzisiaj = 1;
    }

    public int dzisiaj() {
        return dzisiaj;
    }

    public int losujDzień(Random generator) {
        return dzisiaj + generator.nextInt(ilośćDni - dzisiaj) + 1;
    }

    public boolean kolejnyDzień() {
        return (++dzisiaj <= ilośćDni);
    }

    public boolean ostatniDzień() {
        return dzisiaj == ilośćDni;
    }

}
