import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import input.BrakPliku;
import input.BrakWartosciDlaKlucza;
import input.Dane;
import input.NieJestTekstowy;
import input.NieJestXML;
import input.NiedozwolonaWartosc;
import spoleczenstwo.Spoleczenstwo;

public class Symulacja {

    public static void main(String[] args) {

        Dane d = new Dane();
        Spoleczenstwo s = null;

        try {
            d.wczytajDane();

            s = new Spoleczenstwo(d.seed(), d.liczbaDni(), d.liczbaAgentów(), d.śrZnajomych(),
                    d.prawdTowarzyski());

            try (FileWriter fw = new FileWriter(d.plikZRaportem());
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw)) {

                bw.write(d.wypiszDaneWejściowe() + s.wypiszStanPoczątkowy());

                s.symuluj(pw, d.prawdSpotkania(), d.prawdZarażenia(), d.prawdWyzdrowienia(),
                        d.śmiertelność());

            } catch (IOException e) {
                System.out.println("Niedozwolona wartość \"" + d.plikZRaportem()
                        + "\" dla klucza plikZRaportem");
            }
        } catch (BrakPliku e) {
            System.err.println("Brak pliku " + e.getMessage());
        } catch (NieJestTekstowy e) {
            System.err.println("Plik " + e.getMessage() + " nie jest tekstowy.");
        } catch (NieJestXML e) {
            System.err.println("Plik " + e.getMessage() + " nie jest XML.");
        } catch (BrakWartosciDlaKlucza e) {
            System.out.println("Brak wartości dla klucza: " + e.getMessage());
        } catch (NiedozwolonaWartosc e) {
            System.out.println("Niedozwolona wartość \"" + e.getMessage());
        }
    }

}
