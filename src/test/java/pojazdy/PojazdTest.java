package pojazdy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import silnik.*;
import skrzyzowania.Kierunki;
import static org.junit.jupiter.api.Assertions.*;

class PojazdTest {
    private static class SamochodTestowy extends Pojazd {
        public SamochodTestowy(int x, int y, int rozmiar, Mapa mapa) {
            super(x, y, rozmiar, mapa);
        }
        @Override
        public Kierunki gdzieJechac() {
            return Kierunki.Prawo;
        }
    }
    private Mapa mapa;

    @BeforeEach
    void przygotujMape() {
        GenerowanieSymulacji generowanie = new GenerowanieSymulacji();
        mapa = generowanie.stworzMape(5);
    }

    @Test
    void czyWykrywaWyjazdPozaMape() {
        SamochodTestowy samochod = new SamochodTestowy(4, 4, 2, mapa);
        samochod.setZmianaKierunku(Kierunki.Prawo);
        samochod.setStanPojazdu(StanPojazdu.W_ruchu);
        samochod.jedzNastepnaTure();
        assertFalse(samochod.czyNaMapie(), "wyjechal z mapy, powinien wypasc");
    }

    @Test
    void czyWieZeMaStac() {
        SamochodTestowy samochod = new SamochodTestowy(2, 2, 2, mapa);
        samochod.setStanPojazdu(StanPojazdu.W_korku);
        samochod.setZmianaKierunku(Kierunki.Prawo);
        samochod.jedzNastepnaTure();
        assertEquals(2, samochod.getWspolrzednaX(), "nie powinien zmieniac wspolrzednej y");
        assertEquals(2, samochod.getWspolrzednaY(), "nie powinien zmieniac wspolrzednej y");
    }

    @Test
    void czyZmieniaWspolrzednePoTurze() {
        SamochodTestowy samochod = new SamochodTestowy(2, 2, 2, mapa);
        samochod.setZmianaKierunku(Kierunki.Prawo);
        samochod.setStanPojazdu(StanPojazdu.W_ruchu);

        samochod.jedzNastepnaTure();
        assertEquals(3, samochod.getWspolrzednaX(), "wspolrzedna x powinna wzrosnac o 1");
        assertEquals(2, samochod.getWspolrzednaY(), "wspolrzedna y powinna zostac taka sama");
    }

}
