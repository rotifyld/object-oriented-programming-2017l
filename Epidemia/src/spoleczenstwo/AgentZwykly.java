package spoleczenstwo;

public class AgentZwykly extends Agent {

    public AgentZwykly(int i) {
        super(i);
    }

    @Override
    protected double modyfikatorPrawdSpotkania() {
        return (stan == Stan.Chory) ? 0.5 : 1;
    }

    public String wypiszSzczegóły() {
        return super.wypiszSzczegóły() + " zwykły";
    }

}
