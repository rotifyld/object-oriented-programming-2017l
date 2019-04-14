package planista;

import java.util.Scanner;

import java.io.File;

public class Parser {

    private Scanner bufor;

    public Parser(String str) throws NieprawidłowaŚcieżka {

        try {
            File plik = new File(str);
            this.bufor = new Scanner(plik);
        } catch (Exception e) {
            throw new NieprawidłowaŚcieżka(str);
        }
    }

    public Parser() {
        this.bufor = new Scanner(System.in);
    }

    private static int stringWInt(String str, int wiersz) throws NieprawidłowyFormatDanych {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new NieprawidłowyFormatDanych(wiersz, str);
        }
    }

    private static int wczytajLiczbę(int wiersz, Scanner bufor) throws ZaDużoDanych, ZaMałoDanych,
            NieprawidłowyFormatDanych, SpozaZakresuDanych, ZaMałoWierszy {

        if (!bufor.hasNextLine()) {
            throw new ZaMałoWierszy(wiersz);
        }

        String strWiersz = bufor.nextLine();
        String[] strArrWiersz = strWiersz.split(" ");
        if (strArrWiersz.length > 1) {
            throw new ZaDużoDanych(wiersz);
        } else if (strArrWiersz.length == 1 && strArrWiersz[0].equals("")) {
            throw new ZaMałoDanych(wiersz);
        }

        return stringWInt(strArrWiersz[0], wiersz);
    }

    public void wczytaj(SystemOperacyjny sys) throws ZaMałoWierszy, NieprawidłowyFormatDanych,
            ZaMałoDanych, ZaDużoDanych, SpozaZakresuDanych {

        int wiersz = 1;
        String srtWiersz = null;
        String[] strArrWiersz = null;

        // Wczytywanie procesów

        int procesy = wczytajLiczbę(wiersz, bufor);
        if (procesy <= 0) {
            throw new SpozaZakresuDanych(wiersz);
        }

        ++wiersz;
        for (int i = 0; i < procesy; ++i, ++wiersz) {
            if (!bufor.hasNextLine()) {
                throw new ZaMałoWierszy(wiersz);
            }
            srtWiersz = bufor.nextLine();
            strArrWiersz = srtWiersz.split(" ");

            if (strArrWiersz.length > 2) {
                throw new ZaDużoDanych(wiersz);
            } else if (strArrWiersz.length < 2) {
                throw new ZaMałoDanych(wiersz);
            }

            int momentPojawienia = stringWInt(strArrWiersz[0], wiersz);
            int zapotrzebowanie = stringWInt(strArrWiersz[1], wiersz);
            if (momentPojawienia < 0 || zapotrzebowanie <= 0) {
                throw new SpozaZakresuDanych(wiersz);
            }

            sys.dodajProces(new Proces(i + 1, momentPojawienia, zapotrzebowanie));
        }

        // Wczytanie planistów

        int planiściRR = wczytajLiczbę(wiersz, bufor);
        if (planiściRR <= 0) {
            throw new SpozaZakresuDanych(wiersz);
        }

        ++wiersz;

        Planista[] planiści = new Planista[4 + planiściRR];
        planiści[0] = new PlanistaFCFS();
        planiści[1] = new PlanistaSJF(procesy);
        planiści[2] = new PlanistaSRT(procesy);
        planiści[3] = new PlanistaPS(procesy);

        if (!bufor.hasNextLine()) {
            throw new ZaMałoWierszy(wiersz);
        }

        srtWiersz = bufor.nextLine();
        strArrWiersz = srtWiersz.split(" ");

        if (strArrWiersz.length > planiściRR) {
            throw new ZaDużoDanych(wiersz);
        } else if (strArrWiersz.length < planiściRR
                || planiściRR == 1 && strArrWiersz[0].equals("")) {
            throw new ZaMałoDanych(wiersz);
        }

        for (int i = 0; i < planiściRR; ++i) {

            int q = stringWInt(strArrWiersz[i], wiersz);
            planiści[4 + i] = new PlanistaRR(q);
        }

        sys.dodajPlanistów(planiści);

        zamknijBufor();
    }

    public void zamknijBufor() {
        bufor.close();
    }

}
