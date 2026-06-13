package skrzyzowania;

import pojazdy.Pojazd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Skrzyzowanie {
    private int pojemnosc;
    private int wspolrzednaX;  //atrybuty klasy
    private int wspolrzednaY;
    private Map<Kierunki, List<Pojazd>> kolejkiKierunkowe ;
    public int getWspolrzednaX() {
        return wspolrzednaX;
    }
    public int getWspolrzednaY() {
        return wspolrzednaY;
    }
    public int getPojemnosc() {
        return this.pojemnosc;
    }

    public Map<Kierunki, List<Pojazd>> getKolejkiKierunkowe() {
        return this.kolejkiKierunkowe;
    }

    public Skrzyzowanie(int x, int y, int pojemnosc) {
        this.wspolrzednaX = x;
        this.wspolrzednaY = y;
        this.pojemnosc = pojemnosc;
        this.kolejkiKierunkowe = new HashMap<>();

        for (Kierunki k : Kierunki.values()) {
            this.kolejkiKierunkowe.put(k, new ArrayList<>());
        }
    }

    public abstract boolean czyWolne(Pojazd p);

    // sprawdzamy, czy auto zmieści się na skrzyżowaniu na podstawie rozmiarów
    public boolean czyZmiesciSie(Pojazd p) {
        int aktualnieZajeteMiejsce = 0;
        boolean czyPojJuzJest = false;
        //przeszukujemy każdą szufladę i sumujemy rozmiary aut znajdujących się tam
        for (Kierunki k : Kierunki.values()) {
            List<Pojazd> kolejka = kolejkiKierunkowe.get(k);
            for (Pojazd pojazd : kolejka) {
                aktualnieZajeteMiejsce += pojazd.getRozmiar();
                if (pojazd == p) {
                    czyPojJuzJest = true;
                }
            }
        }
        // zwracamy czy zajęte miejsce i poprawnie obliczony rozmiar nie przekracza pojemności
        return (aktualnieZajeteMiejsce + (czyPojJuzJest ? 0 : p.getRozmiar())) <= getPojemnosc();
    }
    public void wjedzSkrzyz(Pojazd p) {
        Kierunki kierunek = p.getAktualnyKierunek();
        List<Pojazd> kolejka = getKolejkiKierunkowe().get(kierunek);

        //jeśli jest kolejka, pojazd jest w stanie W_korku
        kolejka.add(p);
    }

    public void opuscSkrzyz(Pojazd p) {
        Kierunki kierunek = p.getAktualnyKierunek();
        List<Pojazd> kolejka = getKolejkiKierunkowe().get(kierunek);

        // usuwany pojazd z kolejki, bo przejechał
        kolejka.remove(p);
    }
}
