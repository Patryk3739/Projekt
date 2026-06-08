package pojazdy;
import silnik.Mapa;

public class Samochod extends Pojazd {

    public Samochod(int wspolrzedna_x, int wspolrzedna_y, Mapa mapa){
        super(wspolrzedna_x, wspolrzedna_y, 2, mapa);
    }
}
