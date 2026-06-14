package silnik;

import pojazdy.Pojazd;
import skrzyzowania.Kierunki;

public class TestowyPojazd extends Pojazd {
    public TestowyPojazd(Kierunki kierunek, Mapa mapa) {
        super(0, 0, 1, mapa);
        this.setZmianaKierunku(kierunek);
    }
}