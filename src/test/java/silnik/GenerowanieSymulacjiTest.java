package silnik;

import org.junit.jupiter.api.Test;
import pojazdy.*;
import skrzyzowania.*;
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
    void czyDobrzeZwrociSkrzyzowanie() {
        Mapa mapa = generowanie.stworzMape(10);
        boolean jestRondo = false;
        boolean jestSygnalizacja = false;
        boolean jestRownorzedne = false;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Skrzyzowanie s = mapa.pobierzWspolrzedneSkrzyz(x, y);
                if (s instanceof Rondo) {
                    assertEquals(12, s.getPojemnosc(), "to nie jest rondo");
                    jestRondo = true;
                } else if (s instanceof Sygnalizacja) {
                    assertEquals(20, s.getPojemnosc(), "to nie jest sygnalizacja");
                    jestSygnalizacja = true;
                } else if (s instanceof Rownorzedne) {
                    assertEquals(16, s.getPojemnosc(), "to nie jest rownorzedne");
                    jestRownorzedne = true;
                }
                }
            }
        assertTrue(jestRondo, "Na mapie nie ma ronda");
        assertTrue(jestSygnalizacja, "Na mapie nie ma sygnalizacji");
        assertTrue(jestRownorzedne, "Na mapie nie ma skrzyzowania równorzednego");
        }
    }