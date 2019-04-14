package planista;

@SuppressWarnings("serial")
public class SpozaZakresuDanych extends Exception {

    public SpozaZakresuDanych() {
        super();
    }

    public SpozaZakresuDanych(int i) {
        super(Integer.toString(i));
    }

}
