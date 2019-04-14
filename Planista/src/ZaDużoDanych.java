package planista;

@SuppressWarnings("serial")
public class ZaDużoDanych extends Exception {

    public ZaDużoDanych() {
        super();
    }

    public ZaDużoDanych(int i) {
        super(Integer.toString(i));
    }

}
