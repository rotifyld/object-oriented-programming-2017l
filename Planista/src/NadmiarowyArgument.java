package planista;

@SuppressWarnings("serial")
public class NadmiarowyArgument extends Exception {

    public NadmiarowyArgument() {
        super();
    }

    public NadmiarowyArgument(String s) {
        super("\"" + s + "\"");
    }

}
