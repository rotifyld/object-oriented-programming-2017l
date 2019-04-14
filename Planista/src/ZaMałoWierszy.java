package planista;

@SuppressWarnings("serial")
public class ZaMałoWierszy extends Exception {

    public ZaMałoWierszy() {
        super();
    }

    public ZaMałoWierszy(int i) {
        super(Integer.toString(i));
    }

}
