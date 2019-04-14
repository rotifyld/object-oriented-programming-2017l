package planista;

public abstract class Planista {

    public abstract void symuluj(Zegar z);

    public void wypiszStatystyki(String str1, String str2) {
        System.out.println("\nŚredni czas obrotu: " + str1);
        System.out.println("Średni czas oczekiwania: " + str2 + "\n");
    }

}
