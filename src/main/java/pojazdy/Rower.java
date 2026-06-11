package pojazdy;
import silnik.Mapa;
public class Rower extends Pojazd {

    public Rower(int wspolrzednaX, int wspolrzednaY, Mapa mapa){
        super(wspolrzednaX, wspolrzednaY, 1, mapa);
    }
}
