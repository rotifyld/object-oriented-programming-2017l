package planista;

@SuppressWarnings("serial")
public class NieprawidłowyFormatDanych extends Exception {

    public NieprawidłowyFormatDanych() {
        super();
    }

    public NieprawidłowyFormatDanych(int i) {
        super(Integer.toString(i));
    }

    public NieprawidłowyFormatDanych(int i, String str) {
        super(Integer.toString(i) + ": \"" + str + "\"");
    }

}
