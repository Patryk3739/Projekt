package pojazdy;
import silnik.Mapa;

public class Ciezarowka extends Pojazd {

    public Ciezarowka(int wspolrzedna_x, int wspolrzedna_y, Mapa mapa) {
        super(wspolrzedna_x, wspolrzedna_y, 4, mapa);
    }
}
