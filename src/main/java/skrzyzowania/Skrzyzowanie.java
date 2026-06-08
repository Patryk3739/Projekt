package skrzyzowania;
import pojazdy.Pojazd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Skrzyzowanie {
    private int pojemnosc;
    private int wspolrzednaX;  //atributy klasy
    private int wspolrzednaY;
    private Map<Kierunki, List<Pojazd>> kolejkiKierunkowe = new HashMap<>();

    public java.util.Map<Kierunki, java.util.List<Pojazd>> getkolejkiKierunkowe() {
        return this.kolejkiKierunkowe;
    }

    public Skrzyzowanie(int x, int y) {
        this.wspolrzednaX = x;
        this.wspolrzednaY = y;
    }
    public int getWspolrzednaX() {
        return wspolrzednaX;
    }

    public int getWspolrzednaY() {
        return wspolrzednaY;
    }

    public Boolean czyWolne(Pojazd p) {
        return true;
    }
    public void wjedzSkrzyz(Pojazd p) {
        Kierunki kierunek = p.getAktualnyKierunek();
        java.util.List<Pojazd> kolejka = getkolejkiKierunkowe().get(kierunek);

        //jeśli jest kolejka, pojazd jest w stanie W_korku
        if (kolejka != null) {
            kolejka.add(p);
        }
    }

    public void opuscSkrzyz(Pojazd p) {
        Kierunki kierunek = p.getAktualnyKierunek();
        java.util.List<Pojazd> kolejka = getkolejkiKierunkowe().get(kierunek);

        // usuwany pojazd z kolejki, bo przejechał
        if (kolejka != null) {
            kolejka.remove(p);
        }

        // nowy kierunek na wyjeździe
        java.util.Random rand = new java.util.Random();
        Kierunki[] kierunki = Kierunki.values();
        Kierunki nowyKierunek = kierunki[rand.nextInt(kierunki.length)];

        // zmiana trasy
        p.setZmianaKierunku(nowyKierunek);
    }
}
