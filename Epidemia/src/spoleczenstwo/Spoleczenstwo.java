package spoleczenstwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.io.PrintWriter;

public class Spoleczenstwo {

    private final Random generator;
    private final Kalendarz k;
    private List<Agent> agenci;
    private int zdrowi;
    private int chorzy;
    private int uodp;

    public Spoleczenstwo(long seed, int liczbaDni, int liczbaAgentów, int śrZnajomych,
            double prawdTowarzyski) {
        this.generator = new Random(seed);
        this.k = new Kalendarz(liczbaDni);
        this.agenci = new ArrayList<Agent>(liczbaAgentów);
        this.losujGraf(liczbaAgentów, śrZnajomych, prawdTowarzyski);
        this.zdrowi = liczbaAgentów - 1;
        this.chorzy = 1;
        this.uodp = 0;
    }

    private Agent losujAgenta(double prawdTowarzyski, int i) {
        if (generator.nextDouble() < prawdTowarzyski) {
            return new AgentTowarzyski(generator, i + 1);
        } else {
            return new AgentZwykly(i + 1);
        }
    }

    private void losujGraf(int liczbaAgentów, int śrZnajomych, double prawdTowarzyski) {
        for (int i = 0; i < liczbaAgentów; ++i) {
            agenci.add(losujAgenta(prawdTowarzyski, i));
        }

        // Krawędzie są reprezentowane przez liczbę zapisaną w systemie o podstawie
        // liczbaAgentów, a zatem dla liczby x, krawędź ta łączy Agentów o indeksach
        // (x % liczbaAgentów + 1) i (x / liczbaAgentów + 1)
        List<Integer> krawędzie = new ArrayList<>(); // liczbaAgentów * (liczbaAgentów - 1) / 2);
        for (int i = 1; i < liczbaAgentów; ++i) {
            for (int j = 0; j < i; ++j) {
                krawędzie.add(i * liczbaAgentów + j);
            }
        }
        for (int i = 0; i < (śrZnajomych * agenci.size()) / 2; ++i) {
            int krawędź = krawędzie.remove(generator.nextInt(krawędzie.size()));
            agenci.get(krawędź % liczbaAgentów).dodajZnajomego(agenci.get(krawędź / liczbaAgentów));
            agenci.get(krawędź / liczbaAgentów).dodajZnajomego(agenci.get(krawędź % liczbaAgentów));
        }

        agenci.get(generator.nextInt(agenci.size())).zaraź();
    }

    public void symuluj(PrintWriter pw, double prawdSpotkania, double prawdZarażenia,
            double prawdWyzdrowienia, double śmiertelność) {

        do {
            // Uzdrowienie/Śmierć i planowanie spotkań
            List<Agent> kostnica = new ArrayList<>();
            for (Agent a : agenci) {
                Stan zmiana = a.początekDnia(generator, k, śmiertelność, prawdWyzdrowienia,
                        prawdSpotkania);
                if (zmiana != null) {
                    --chorzy;
                    if (zmiana == Stan.Uodporniony) {
                        ++uodp;
                    } else { // zmiana == Stan.Martwy
                        a.zgiń();
                        kostnica.add(a);
                    }
                }
            }
            agenci.removeAll(kostnica);

            // Wykonywanie zaplanowanych spotkań na dany dzień
            for (Agent a : agenci) {
                int zarażonych = a.spotykajSię(generator, k.dzisiaj(), prawdZarażenia);
                chorzy += zarażonych;
                zdrowi -= zarażonych;
            }

            pw.println(wypiszLiczność());
        } while (k.kolejnyDzień());
    }

    public String wypiszStanPoczątkowy() {
        StringBuilder s = new StringBuilder();
        s.append("# agenci jako: id typ lub id* typ dla chorego\n");
        for (Agent a : agenci) {
            s.append(a.wypiszSzczegóły() + "\n");
        }
        s.append("\n# graf\n");
        for (Agent a : agenci) {
            s.append(a.wypiszZnajomych() + "\n");
        }
        s.append("\n# liczność w kolejnych dniach\n");
        return s.toString();
    }

    public String wypiszLiczność() {
        return (zdrowi + " " + chorzy + " " + uodp);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Agent a : agenci) {
            s.append(a.toString() + "\n");
        }
        return s.toString();
    }

}
