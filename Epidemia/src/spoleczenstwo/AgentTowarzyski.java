package spoleczenstwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgentTowarzyski extends Agent {

    private List<Agent> znajomiZnajomych;

    public AgentTowarzyski(Random generator, int i) {
        super(i);
        znajomiZnajomych = new ArrayList<>();
    }

    @Override
    public boolean dodajZnajomego(Agent a) {
        if (!super.dodajZnajomego(a)) {
            return false;
        } else {
            if (!znajomiZnajomych.contains(a)) {
                znajomiZnajomych.add(a);
            }
            return true;
        }
    }

    @Override
    protected void dodajZnajomegoZnajomego(Agent a) {
        if (!znajomiZnajomych.contains(a))
            znajomiZnajomych.add(a);
    }

    @Override
    protected void usuńZnajomegoZnajomego(Agent a) {
        if (znajomiZnajomych.contains(a))
            znajomiZnajomych.remove(a);
    }

    @Override
    protected List<Spotkanie> losujSpotkania(Random generator, int spotkań, Kalendarz k) {
        if (stan == Stan.Chory) {
            return super.losujSpotkania(generator, spotkań, k);
        }

        List<Spotkanie> spotkania = new ArrayList<Spotkanie>(spotkań);
        for (int i = 0; i < spotkań; ++i) {
            spotkania.add(
                    new Spotkanie(znajomiZnajomych.get(generator.nextInt(znajomiZnajomych.size())),
                            k.losujDzień(generator)));
        }
        return spotkania;
    }

    @Override
    public String wypiszSzczegóły() {
        return super.wypiszSzczegóły() + " towarzyski";
    }

}
