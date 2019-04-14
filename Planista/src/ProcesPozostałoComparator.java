package planista;

import java.util.Comparator;

public class ProcesPozostałoComparator implements Comparator<Proces> {

    public int compare(Proces arg0, Proces arg1) {
        double d = arg0.getPozostało() - arg1.getPozostało();

        if (d == 0) {
            d = arg0.getIndeks() - arg1.getIndeks();
        }

        return (d > 0) ? 1 : -1;
    }

}
