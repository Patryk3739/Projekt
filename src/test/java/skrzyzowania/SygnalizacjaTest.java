package skrzyzowania;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojazdy.Pojazd;
import static org.junit.jupiter.api.Assertions.*;

public class SygnalizacjaTest {
    private Sygnalizacja skrzyzowanie;

    @BeforeEach
    public void setUp() {
        // tworzą się skryżowanie z sygnalizacją o pojemności 4 na pozycji 0,0
        skrzyzowanie = new Sygnalizacja(0, 0, 4);
    }
    // test nr 1,2 - przepuszczanie na zielonym, zatrzymanie na czerwonym
    @Test
    public void ruchNaSygnal() {
        Pojazd pojazdPion = new TestowyPojazd(1, Kierunki.Gora);
        Pojazd pojazdPoziom = new TestowyPojazd(1, Kierunki.Lewo);

        skrzyzowanie.wjedzSkrzyz(pojazdPion);
        skrzyzowanie.wjedzSkrzyz(pojazdPoziom);

        // 2 - zatrzymanie na czerwonym(z reguły na starcie światła czerwone)
        assertFalse(skrzyzowanie.czyWolne(pojazdPion), "Pojazd pionowy powinien stać na czerwonym świetle.");
        assertFalse(skrzyzowanie.czyWolne(pojazdPoziom), "Pojazd poziomy powinien stać na czerwonym świetle.");

        // 1 - zmiana światel na zielonePionowe, ruch pionu
        skrzyzowanie.zmianaSwiatla();
        assertTrue(skrzyzowanie.czyWolne(pojazdPion), "Pojazd pionowy powinien dostać zielone światło.");
        assertFalse(skrzyzowanie.czyWolne(pojazdPoziom), "Pojazd poziomy wciąż powinien stać na czerwonym.");
    }
    // test nr 3 -
    @Test
    public void cyklSwiatel() {
        Pojazd pojazdPion = new TestowyPojazd(1, Kierunki.Dol);
        Pojazd pojazdPoziom = new TestowyPojazd(1, Kierunki.Prawo);

        skrzyzowanie.wjedzSkrzyz(pojazdPion);
        skrzyzowanie.wjedzSkrzyz(pojazdPoziom);

        // wszystkieCzerwone -> zielonePionowe
        skrzyzowanie.zmianaSwiatla();
        assertTrue(skrzyzowanie.czyWolne(pojazdPion));

        // zielonePionowe -> wszystkieCzerwone
        skrzyzowanie.zmianaSwiatla();
        assertFalse(skrzyzowanie.czyWolne(pojazdPion), "Po przełączeniu pion powinien się zatrzymać (WszystkieCzerwone).");
        assertFalse(skrzyzowanie.czyWolne(pojazdPoziom), "Poziom również powinien stać.");

        // wszystkieCzerwone -> zielonePoziome
        skrzyzowanie.zmianaSwiatla();
        assertFalse(skrzyzowanie.czyWolne(pojazdPion), "Pion powinien teraz bezwzględnie stać.");
        assertTrue(skrzyzowanie.czyWolne(pojazdPoziom), "Poziom powinien w końcu otrzymać zielone światło.");
    }
}
