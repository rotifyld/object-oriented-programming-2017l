package input;

import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;

public class Dane {

    private long seed;
    private int liczbaAgentów;
    private int śrZnajomych;
    private double prawdTowarzyski;
    private double prawdSpotkania;
    private double prawdZarażenia;
    private double prawdWyzdrowienia;
    private double śmiertelność;
    private int liczbaDni;
    private String plikZRaportem;

    public void wczytajDane() throws BrakPliku, NieJestTekstowy, BrakWartosciDlaKlucza,
            NiedozwolonaWartosc, NieJestXML {

        Properties defaultProp = new Properties();
        try (FileInputStream stream = new FileInputStream("db394094/default.properties");
                Reader reader = Channels.newReader(stream.getChannel(),
                        StandardCharsets.UTF_8.name())) {
            defaultProp.load(reader);
        } catch (MalformedInputException e) {
            throw new NieJestTekstowy("default.properties");
        } catch (IOException e) {
            throw new BrakPliku("default.properties");
        }

        Properties prop = new Properties(defaultProp);
        try {
            FileInputStream stream = new FileInputStream("db394094/simulation-config.xml");
            prop.loadFromXML(stream);

        } catch (InvalidPropertiesFormatException e) {
            throw new NieJestXML("simulation-config.xml");
        } catch (IOException e) {
            throw new BrakPliku("simulation-config.xml");
        }

        this.prawdTowarzyski = wczytajDouble("prawdTowarzyski", prop);
        this.prawdSpotkania = wczytajDouble("prawdSpotkania", prop);
        if (prawdSpotkania == 1.0) {
            throw new NiedozwolonaWartosc("1.0\" dla klucza prawdSpotkania");
        }
        this.prawdZarażenia = wczytajDouble("prawdZarażenia", prop);
        this.prawdWyzdrowienia = wczytajDouble("prawdWyzdrowienia", prop);
        this.śmiertelność = wczytajDouble("śmiertelność", prop);
        this.liczbaAgentów = wczytajInteger("liczbaAgentów", prop, 1, 1000000);
        this.śrZnajomych = wczytajInteger("śrZnajomych", prop, 1, liczbaAgentów - 1);
        this.liczbaDni = wczytajInteger("liczbaDni", prop, 1, 1000);
        this.seed = wczytajLong("seed", prop);

        if (prop.getProperty("plikZRaportem") == null) {
            throw new BrakWartosciDlaKlucza("plikZRaportem");
        }

        this.plikZRaportem = prop.getProperty("plikZRaportem");
    }

    private int wczytajInteger(String s, Properties prop, int min, int max)
            throws BrakWartosciDlaKlucza, NiedozwolonaWartosc {
        if (prop.getProperty(s) == null) {
            throw new BrakWartosciDlaKlucza(s);
        }

        int i;

        try {
            i = Integer.parseInt(prop.getProperty(s));
        } catch (NumberFormatException e) {
            throw new NiedozwolonaWartosc(prop.getProperty(s) + "\" dla klucza " + s);
        }

        if (i < min || i > max) {
            throw new NiedozwolonaWartosc(Integer.toString(i) + "\" dla klucza " + s);
        }
        return i;
    }

    private double wczytajDouble(String s, Properties prop)
            throws BrakWartosciDlaKlucza, NiedozwolonaWartosc {

        if (prop.getProperty(s) == null) {
            throw new BrakWartosciDlaKlucza(s);
        }

        Double d;

        try {
            d = Double.parseDouble(prop.getProperty(s));
        } catch (NumberFormatException e) {
            throw new NiedozwolonaWartosc(prop.getProperty(s) + "\" dla klucza " + s);
        }

        if (d < 0 || d > 1) {
            throw new NiedozwolonaWartosc(d.toString() + "\" dla klucza " + s);
        }
        return d;
    }

    private long wczytajLong(String s, Properties prop)
            throws BrakWartosciDlaKlucza, NiedozwolonaWartosc {
        if (prop.getProperty(s) == null) {
            throw new BrakWartosciDlaKlucza(s);
        }

        long l;

        try {
            l = Long.parseLong(prop.getProperty(s));
        } catch (NumberFormatException e) {
            throw new NiedozwolonaWartosc(prop.getProperty(s) + "\" dla klucza " + s);
        }

        return l;
    }

    public long seed() {
        return seed;
    }

    public int liczbaAgentów() {
        return liczbaAgentów;
    }

    public int śrZnajomych() {
        return śrZnajomych;
    }

    public double prawdTowarzyski() {
        return prawdTowarzyski;
    }

    public double prawdSpotkania() {
        return prawdSpotkania;
    }

    public double prawdZarażenia() {
        return prawdZarażenia;
    }

    public double prawdWyzdrowienia() {
        return prawdWyzdrowienia;
    }

    public double śmiertelność() {
        return śmiertelność;
    }

    public int liczbaDni() {
        return liczbaDni;
    }

    public String plikZRaportem() {
        return plikZRaportem;
    }

    public String wypiszDaneWejściowe() {
        StringBuilder s = new StringBuilder();

        s.append("# twoje wyniki powinny zawierać te komentarze\n");
        s.append("seed=" + seed + "\n");
        s.append("liczbaAgentów=" + liczbaAgentów + "\n");
        s.append("prawdTowarzyski=" + prawdTowarzyski + "\n");
        s.append("prawdSpotkania=" + prawdSpotkania + "\n");
        s.append("prawdZarażenia=" + prawdZarażenia + "\n");
        s.append("prawdWyzdrowienia=" + prawdWyzdrowienia + "\n");
        s.append("śmiertelność=" + śmiertelność + "\n");
        s.append("liczbaDni=" + liczbaDni + "\n");
        s.append("śrZnajomych=" + śrZnajomych + "\n\n");

        return s.toString();
    }
}
