package spoleczenstwo;

import java.util.Comparator;

public class SpotkaniaComparator implements Comparator<Spotkanie> {

    @Override
    public int compare(Spotkanie x, Spotkanie y) {
        return x.dzień() - y.dzień();
    }

}
