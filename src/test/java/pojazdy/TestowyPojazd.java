package pojazdy;

import silnik.Mapa;
import skrzyzowania.Kierunki;

public class TestowyPojazd extends Pojazd {
    public TestowyPojazd(int x, int y, int rozmiar, Mapa mapa) {
        super(x, y, rozmiar, mapa);
    }
    @Override
    public Kierunki gdzieJechac() {
        return Kierunki.Prawo;
    }
}

