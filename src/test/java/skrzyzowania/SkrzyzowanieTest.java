package skrzyzowania;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojazdy.Pojazd;
import static org.junit.jupiter.api.Assertions.*;

class SkrzyzowanieTest {
    private Skrzyzowanie skrzyzowanie;

    @BeforeEach
    public void setUp() {
        //przed każdym testem tworzy się rondo o pojemności 5 na pozycji 0,0
        skrzyzowanie = new Rondo(0, 0, 5);
    }
    // test nr 1 - czy skryżowanie blokuje wjazd, jeśli brak miejsca
    @Test
    public void blokWjazdBrakMiejsca() {
        Pojazd pojazd1 = new TestowyPojazd(3, Kierunki.Gora);
        Pojazd pojazd2 = new TestowyPojazd(3, Kierunki.Prawo); // łączny rozmiar 6, a pojemność 5

        // wpuszcza pierwszy pojazd, powinien się zmieścić
        skrzyzowanie.wjedzSkrzyz(pojazd1);
        assertFalse(skrzyzowanie.czyZmiesciSie(pojazd2) , "Skrzyżowanie powinno zablokować pojazd2, bo brakuje miejsca.");
    }
    // test nr 2 - czy skrzyżowanie prawidłowo ustawia kolejki
    @Test
    public void czyPrawUstKol() {
        Pojazd autoGora = new TestowyPojazd(1, Kierunki.Gora);
        Pojazd autoPrawo = new TestowyPojazd(1, Kierunki.Prawo);

        // pojazdy z różnych kierunków
        skrzyzowanie.wjedzSkrzyz(autoGora);
        skrzyzowanie.wjedzSkrzyz(autoPrawo);

        //sprawdzamy, czy pojazdy trafiły do odpowiednich "szuflad" w mapie
        assertTrue(skrzyzowanie.getKolejkiKierunkowe().get(Kierunki.Gora).contains(autoGora), "Pojazd z Góry powinno być w kolejce Góra");
        assertTrue(skrzyzowanie.getKolejkiKierunkowe().get(Kierunki.Prawo).contains(autoPrawo), "Pojazd z Prawej powinno być w kolejce Prawo");

        // sprawdzamy, czy kolejka Dół jest pusta
        assertTrue(skrzyzowanie.getKolejkiKierunkowe().get(Kierunki.Dol).isEmpty(), "Kolejka Dół powinna być pusta");
    }
    //test nr 3 - czy prawidłowo usuwa pojazd z kolejki
    @Test
    public void czyPrawUsuwZKolejki() {
        Pojazd pojazd = new TestowyPojazd(2, Kierunki.Lewo);
        // pojazd wjeżdża
        skrzyzowanie.wjedzSkrzyz(pojazd);
        assertEquals(1, skrzyzowanie.getKolejkiKierunkowe().get(Kierunki.Lewo).size(), "Kolejka powinna mieć 1 pojazd");
        // pojazd opuszcza skrzyżowanie
        skrzyzowanie.opuscSkrzyz(pojazd);
        // sprawdzamy, czy zniknął z kolejki
        assertTrue(skrzyzowanie.getKolejkiKierunkowe().get(Kierunki.Lewo).isEmpty(), "Kolejka powinna być pusta po opuszczeniu skrzyżowania");
    }
}
