package skrzyzowania;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojazdy.Pojazd;
import static org.junit.jupiter.api.Assertions.*;

class RondoTest {
    private Rondo rondo;
    @BeforeEach
    public void setUp() {
        // tworzy się rondo o pojemności np. 4
        rondo = new Rondo(0, 0, 4);
    }
    //test nr 1 - czy skrzyżowanie pozwala wjechać, gdy jest puste
    @Test
    public void pozwWjazdGdyPuste() {
        Pojazd pojazd = new testowyPojazd(1, Kierunki.Gora);
        // na pustym rondzie czyWolne() ma zwrócić true
        assertTrue(rondo.czyWolne(pojazd), "Puste rondo powinno pozwolić na wjazd pojazdu.");
    }
    //test nr 2 - czy zabrania wjechać, gdy jest zajęte
    @Test
    public void testCzyZabraniaWjechacGdyJestZajete() {
        Pojazd duzyPojazd = new testowyPojazd(4, Kierunki.Gora); // zajmuje całe rondo
        Pojazd kolejnyPojazd = new testowyPojazd(1, Kierunki.Prawo);

        // wjeżdżamy dużym pojazdem – rondo pełne
        rondo.wjedzSkrzyz(duzyPojazd);

        // rondo powinno zabronić wjazdu kolejnemu pojazdu
        assertFalse(rondo.czyWolne(kolejnyPojazd), "Pełne rondo powinno zabronić wjazdu.");
    }
    //test nr 3 - czy wpuszcza mniejszy pojazd podczas gdy większy nie wjedzie
    @Test
    public void testCzyWpuszczaMniejszyPojazdGdyWiekszyNieWjedzie() {
        // na rondzie o pojemności 4 stoi już pojazd o rozmiarze 2, są 2 wolne miejsca
        Pojazd pojazdNaRondzie = new testowyPojazd(2, Kierunki.Gora);
        rondo.wjedzSkrzyz(pojazdNaRondzie);

        Pojazd ciezarowka = new testowyPojazd(3, Kierunki.Lewo);  // potrzebuje 3 miejsc, nie zmieści się
        Pojazd rower = new testowyPojazd(1, Kierunki.Prawo);   // potrzebuje 1 miejsca, zmieści się

        // sprawdzamy ciężarówkę – powinna dostać zakaz wjazdu
        assertFalse(rondo.czyWolne(ciezarowka), "Ciężarówka o rozmiarze 3 nie powinna się zmieścić na rondzie.");

        // sprawdzamy rower – powinien zostać wpuszczony, mimo że ciężarówka przed nim stoi
        assertTrue(rondo.czyWolne(rower), "Rower o rozmiarze 1 powinien zostać wpuszczony na wolne miejsce.");
    }
}
