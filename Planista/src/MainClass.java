package planista;

public class MainClass {

    public static void main(String[] args) {

        SystemOperacyjny sys = new SystemOperacyjny();

        Parser parser = null;

        try {
            if (args.length > 1) {
                throw new NadmiarowyArgument(args[1]);
            }

            if (args.length == 1) {
                parser = new Parser(args[0]);
            } else {
                parser = new Parser();
            }

            parser.wczytaj(sys);
            sys.symuluj();

        } catch (NadmiarowyArgument e) {
            System.out.println(
                    "Błąd w argumentach programu: Nadmiarowy argument " + e.getMessage() + ".");
        } catch (NieprawidłowaŚcieżka e) {
            System.out.println(
                    "Błąd w argumentach programu: Podana ścieżka jest nieprawidłowa (Plik nie istnieje).");
        } catch (ZaMałoWierszy e) {
            System.out.println("Błąd w wierszu " + e.getMessage() + ": za mało wierszy.");
        } catch (ZaMałoDanych e) {
            System.out.println("Błąd w wierszu " + e.getMessage() + ": za mało danych.");
        } catch (ZaDużoDanych e) {
            System.out.println("Błąd w wierszu " + e.getMessage() + ": za dużo danych.");
        } catch (NieprawidłowyFormatDanych e) {
            System.out.println("Błąd w wierszu " + e.getMessage() + " nie jest liczbą.");
        } catch (SpozaZakresuDanych e) {
            System.out.println("Błąd w wierszu " + e.getMessage() + ": dane spoza zakresu.");
        } finally {
            if (parser != null)
                parser.zamknijBufor();
        }
    }
}
