package pojazdy;
import silnik.Mapa;

public class Samochod extends Pojazd {
    public Samochod(int wspolrzednaX, int wspolrzednaY, Mapa mapa){
        super(wspolrzednaX, wspolrzednaY, 2, mapa);
    }
}
