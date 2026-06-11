package skrzyzowania;

import pojazdy.Pojazd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Skrzyzowanie {
    private int pojemnosc;
    private int wspolrzednaX;  //atributy klasy
    private int wspolrzednaY;
    private Map<Kierunki, List<Pojazd>> kolejkiKierunkowe ;

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
    public int getWspolrzednaX() {
        return wspolrzednaX;
    }

    public int getWspolrzednaY() {
        return wspolrzednaY;
    }

    public abstract boolean czyWolne(Pojazd p);

    // sprawdzamy, czy auto zmieści się na skrzyżowaniu na podstawie rozmiarów
    public boolean czyZmiesciSie(Pojazd p) {
        int aktualnieZajeteMiejsce = 0;
        //przeszukujemy każdą szufladę i sumujemy rozmiary aut znajdujących się tam
        for (Kierunki k : Kierunki.values()) {
            List<Pojazd> kolejka = kolejkiKierunkowe.get(k);
            for (Pojazd auto : kolejka) {
                aktualnieZajeteMiejsce += auto.getRozmiar();
            }
        }

        // zwracamy czy zajęte miejsce + rozmiar nowego pojazdu nie przekracza pojemności
        return (aktualnieZajeteMiejsce + p.getRozmiar()) <= this.pojemnosc;
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
