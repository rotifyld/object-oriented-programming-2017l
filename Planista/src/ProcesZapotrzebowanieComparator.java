package planista;

import java.util.Comparator;

public class ProcesZapotrzebowanieComparator implements Comparator<Proces> {

    public int compare(Proces arg0, Proces arg1) {
        int i = arg0.getZapotrzebowanie() - arg1.getZapotrzebowanie();

        if (i == 0) {
            i = arg0.getIndeks() - arg1.getIndeks();
        }

        return i;
    }

}
