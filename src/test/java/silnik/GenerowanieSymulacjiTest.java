package silnik;

import org.junit.jupiter.api.Test;
import pojazdy.*;
import skrzyzowania.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GenerowanieSymulacjiTest {
    private final GenerowanieSymulacji generowanie = new GenerowanieSymulacji();

    @Test
    void czyMapaJestPelnaSkrzyzowan() {
        int wymiary = 5;
        Mapa mapa = generowanie.stworzMape(wymiary);
        for (int x = 0; x < wymiary; x++) {
            for (int y = 0; y < wymiary; y++) {
                assertNotNull(mapa.pobierzWspolrzedneSkrzyz(x, y), x + " " + y + "nie ma skrzyzowania");
            }
        }
    }

    @Test
    void czyAutaTrzymajaSieGranic() {
        Mapa mapa = generowanie.stworzMape(10);
        for (int i = 0; i < 50; i++) {
            Pojazd p = generowanie.losujPojazd(mapa);
            assertTrue(p.getWspolrzednaX() >= 0 && p.getWspolrzednaX() < 10, "poza granicami mapy");
            assertTrue(p.getWspolrzednaY() >= 0 && p.getWspolrzednaY() < 10, "poza granicami mapy");
        }
    }

    @Test
    void czySpawnNieNaTymSamymMiejscu() {
        Mapa mapa = generowanie.stworzMape(5);
        Skrzyzowanie s = mapa.pobierzWspolrzedneSkrzyz(0, 0);
        Pojazd p1 = new Samochod(0, 0, mapa);
        Pojazd p2 = new Samochod(0, 0, mapa);
        s.wjedzSkrzyz(p1);
        s.wjedzSkrzyz(p2);

        boolean czyJestP1 = false;
        for (List<Pojazd> kolejka : s.getKolejkiKierunkowe().values()) {
            if (kolejka.contains(p1)) {
                czyJestP1 = true;
                break;
            }
        }
        assertTrue(czyJestP1, "skrzyzowanie ma pojazd 1");

        boolean czyJestP2 = false;
        for (List<Pojazd> kolejka : s.getKolejkiKierunkowe().values()) {
            if (kolejka.contains(p2)) {
                czyJestP2 = true;
                break;
            }
        }
        assertTrue(czyJestP2, "skrzyzowanie ma pojazd 2");
    }

    @Test
    void czyDobrzeZwrociSkrzyzowanie() {
        Mapa mapa = generowanie.stworzMape(2);
        assertNotNull(mapa.pobierzWspolrzedneSkrzyz(0, 0), "nie zwraca skrzyzowania");
    }
}