package planista;

@SuppressWarnings("serial")
public class ZaMałoDanych extends Exception {

    public ZaMałoDanych() {
        super();
    }

    public ZaMałoDanych(int i) {
        super(Integer.toString(i));
    }

}
