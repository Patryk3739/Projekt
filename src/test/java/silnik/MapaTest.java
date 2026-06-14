package silnik;

import org.junit.jupiter.api.Test;
import pojazdy.Pojazd;
import skrzyzowania.Kierunki;
import skrzyzowania.Rondo;
import skrzyzowania.Sygnalizacja;

import static org.junit.jupiter.api.Assertions.*;

class MapaTest {

    @Test
    void czyPoprawnieAktualizujeSwiatlaTylkoNaSygnalizacji() {
        Mapa mapa = new Mapa(2);
        Sygnalizacja sygnalizacja = new Sygnalizacja( 4);
        Rondo rondo = new Rondo( 4);
        mapa.wstawSkrzyzowanie(0, 0, sygnalizacja);
        mapa.wstawSkrzyzowanie(0, 1, rondo);

        Pojazd auto = new TestowyPojazd(Kierunki.Gora, mapa);
        sygnalizacja.wjedzSkrzyz(auto);

        assertFalse(sygnalizacja.czyWolne(auto), "Przed aktualizacją pion powinien mieć czerwone światło");
        mapa.aktualizujSwiatla();
        assertTrue(sygnalizacja.czyWolne(auto), "Po aktualizacji mapy, sygnalizacja powinna zmienić cykl na zielone");
    }

    @Test
    void czyMapaMaPoprawnyWymiar(){
     Mapa mapa = new Mapa(20);
     assertEquals(20,mapa.getWymiar(),"Sprawdzenie przypisania przez konstruktor wymiaru 20 do mapy.");
    }

    @Test
    void czyNowaMapaJestPusta(){
        Mapa mapa = new Mapa(20);
        assertNull(mapa.pobierzWspolrzedneSkrzyz(5,5),"Sprawdzenie czy mapa zwraca nulla.");
    }
}
