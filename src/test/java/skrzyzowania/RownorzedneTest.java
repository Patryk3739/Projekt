package skrzyzowania;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojazdy.Pojazd;
import static org.junit.jupiter.api.Assertions.*;

public class RownorzedneTest {
    private Rownorzedne skrzyzowanie;
    @BeforeEach
    public void setUp() {
        // tworzy się skrzyżowanie równorzędne o pojemności 4 na pozycji 0,0
        skrzyzowanie = new Rownorzedne(4);
    }
    //test nr 1 - test wolnej drogi
    @Test
    public void wolnaDroga() {
        // jeden pojazd nadjeżdżający z Góry
        Pojazd pojazd = new TestowyPojazd(1, Kierunki.Gora);
        skrzyzowanie.wjedzSkrzyz(pojazd);

        // skrzyżowanie jest puste z innych stron, więc droga wolna (true)
        assertTrue(skrzyzowanie.czyWolne(pojazd), "Pojazd powinien móc przejechać, gdy droga jest wolna");
    }
    // test nr 2 - sprawdzenie reguły prawej ręki
    @Test
    public void ustapPierwsz() {
        // dwa auta: jedno z Lewej, drugie z Dołu
        Pojazd pojazdLewo = new TestowyPojazd(1, Kierunki.Lewo);
        Pojazd pojazdDol = new TestowyPojazd(1, Kierunki.Dol);

        // wpuszczamy oba auta na wjazdy skrzyżowania
        skrzyzowanie.wjedzSkrzyz(pojazdLewo);
        skrzyzowanie.wjedzSkrzyz(pojazdDol);

        // dla kierunku Lewo prawą stroną jest Dol.
        // pojazd z Lewej musi ustąpić pojazdowi z Dołu
        assertFalse(skrzyzowanie.czyWolne(pojazdLewo), "Pojazd z Lewej powinno ustąpić pojazdowi po swojej prawej (z Dołu).");
        assertTrue(skrzyzowanie.czyWolne(pojazdDol), "Pojazd z Dołu powinien móc jechać, bo jego prawa strona jest wolna.");
    }
    // test nr 3 - 4 pojazdy, każdy na innym wjeździe
    @Test
    public void testZakleszczenia() {
        // tworzą się 4 pojazdy
        Pojazd pojazdGora = new TestowyPojazd(1, Kierunki.Gora);
        Pojazd pojazdLewo = new TestowyPojazd(1, Kierunki.Lewo);
        Pojazd pojazdDol = new TestowyPojazd(1, Kierunki.Dol);
        Pojazd pojazdPrawo = new TestowyPojazd(1, Kierunki.Prawo);

        // jednocześnie chcą wjechać na skryżowanie
        skrzyzowanie.wjedzSkrzyz(pojazdGora);
        skrzyzowanie.wjedzSkrzyz(pojazdLewo);
        skrzyzowanie.wjedzSkrzyz(pojazdDol);
        skrzyzowanie.wjedzSkrzyz(pojazdPrawo);

        // bezwzględne pierwszeństwo dostaje kierunek Góra, żeby rozładować korek.
        assertTrue(skrzyzowanie.czyWolne(pojazdGora), "W przypadku zakleszczenia pojazd z Góry powinien zostać przepuszczony.");

        // reszta pojazdów musi w tym momencie stać i czekać na ruch Góry
        assertFalse(skrzyzowanie.czyWolne(pojazdLewo), "Pojazd z Lewej powinien czekać podczas zakleszczenia.");
        assertFalse(skrzyzowanie.czyWolne(pojazdDol), "Pojazd z Dołu powinien czekać podczas zakleszczenia.");
        assertFalse(skrzyzowanie.czyWolne(pojazdPrawo), "Auto z Prawej powinno czekać podczas zakleszczenia.");
    }

}
