package skrzyzowania;
import pojazdy.Pojazd;

import java.util.List;
import java.util.Map;

public abstract class Skrzyzowanie {
    private int pojemnosc;
    private int wspolrzednaX;  //atributy klasy
    private int wspolrzednaY;

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
    }

    public void opuscSkrzyz(Pojazd p) {
    }
}
